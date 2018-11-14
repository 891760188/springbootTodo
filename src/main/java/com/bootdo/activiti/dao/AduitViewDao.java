package com.bootdo.activiti.dao;


import com.bootdo.activiti.domain.AduitView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AduitViewDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AduitView record);

    int insertSelective(AduitView record);

    AduitView selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AduitView record);

    int updateByPrimaryKey(AduitView record);
}