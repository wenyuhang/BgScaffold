package com.scaffold.demo.common.mapper;

import com.scaffold.demo.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/12/8 10:53
 * desc   :
 */
@Repository
@Mapper
public interface DeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dept record);

    int insertSelective(Dept record);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    /**
     * 多个数据 要用 @Param
     * @param oldStr
     * @param newStr
     * @param relationCode
     * @return
     */
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);

    Dept selectByPrimaryKey(String id);

    List<Dept> selectAll();

    List<String> selectChildIds(String relationCode);

    List<Dept> selectAllByNotContainChild(List<String> list);
}
