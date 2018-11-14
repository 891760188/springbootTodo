package com.bootdo.activiti.dao;


import com.bootdo.activiti.domain.Jdqj;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JdqjDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Jdqj record);

    int insertSelective(Jdqj record);

    Jdqj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Jdqj record);

    int updateByPrimaryKey(Jdqj record);
}