package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.AduitView;

import java.util.List;

public interface AduitViewService {
    List<AduitView> selectByBusinessKey(String businessKey);
}
