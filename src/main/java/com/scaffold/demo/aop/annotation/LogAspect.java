package com.scaffold.demo.aop.annotation;

import com.alibaba.fastjson.JSON;
import com.scaffold.demo.common.mapper.OperLogMapper;
import com.scaffold.demo.constant.Constant;
import com.scaffold.demo.entity.Log;
import com.scaffold.demo.utils.AddressUtil;
import com.scaffold.demo.utils.HttpContextUtils;
import com.scaffold.demo.utils.IPUtil;
import com.scaffold.demo.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 11:13
 * desc   :
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private OperLogMapper operLogMapper;

    @Pointcut("@annotation(com.scaffold.demo.aop.annotation.LogAnnotation)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        try {
            saveSysLog(point, time);
        } catch (Exception e) {
            log.error("e={}",e);
        }

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log sysLog = new Log();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        if(logAnnotation != null){
            //注解上的描述
            sysLog.setOperation(logAnnotation.title()+"-"+logAnnotation.action());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        log.info("请求{}.{}耗时{}毫秒",className,methodName,time);
        try {
            //请求的参数
            Object[] args = joinPoint.getArgs();
            String params=null;
            if(args.length!=0){
                params= JSON.toJSONString(args);
            }

            sysLog.setParams(params);
        } catch (Exception e) {

        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtil.getIpAddr(request));
        log.info("Ip{}，接口地址{}，请求方式{}，入参：{}",sysLog.getIp(),request.getRequestURL(),request.getMethod(),sysLog.getParams());
        //用户名
        String  token = request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(token);
        String username=JwtTokenUtil.getUserName(token);
        sysLog.setUsername(username);
        sysLog.setUserId(userId);
        sysLog.setTime((int) time);
        sysLog.setId(UUID.randomUUID().toString());
        sysLog.setCreateTime(new Date());
        sysLog.setLocation(AddressUtil.getCityInfo(IPUtil.getIpAddr(request)));
        log.info(sysLog.toString());
        operLogMapper.insertSelective(sysLog);

    }
}
