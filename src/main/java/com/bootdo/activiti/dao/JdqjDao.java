package com.bootdo.activiti.dao;


import com.bootdo.activiti.domain.Jdqj;
import com.bootdo.common.utils.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JdqjDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Jdqj record);

    int insertSelective(Jdqj record);

    Jdqj selectByPrimaryKey(Integer id);

    List<Jdqj> list(Map<String,Object> map);

    int updateByPrimaryKeySelective(Jdqj record);

    int updateByPrimaryKey(Jdqj record);
}