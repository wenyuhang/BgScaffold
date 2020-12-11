package com.scaffold.demo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/7 18:44
 * desc   :
 */
public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 判断是否是 ajax/app请求
     * @param request
     * @return
     */
    public  static boolean isAjaxRequest(HttpServletRequest request){

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.indexOf("application/json") != -1
                || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        ));
    }
}
