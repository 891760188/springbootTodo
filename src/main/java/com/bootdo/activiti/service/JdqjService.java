package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.Jdqj;
import com.bootdo.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface JdqjService {

    Jdqj save(Jdqj jdqj);

    List<Jdqj> list(Map<String,Object> map);

    List<Map<String,Object>> myTask(Map<String,Object> map);

    int complete(Map<String,Object> map);

}
