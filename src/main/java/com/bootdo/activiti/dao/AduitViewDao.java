package com.bootdo.activiti.dao;


import com.bootdo.activiti.domain.AduitView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AduitViewDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AduitView record);

    int insertSelective(AduitView record);

    AduitView selectByPrimaryKey(Integer id);

    List<AduitView> selectByBusinessKey(String businessKey);

    int updateByPrimaryKeySelective(AduitView record);

    int updateByPrimaryKey(AduitView record);
}