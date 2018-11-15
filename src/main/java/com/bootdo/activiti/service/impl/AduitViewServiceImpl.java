package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.AduitViewDao;
import com.bootdo.activiti.domain.AduitView;
import com.bootdo.activiti.service.AduitViewService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AduitViewServiceImpl implements AduitViewService {

    @Resource
    private AduitViewDao aduitViewDao ;
    @Resource
    private HistoryService historyService ;

    private String processInstanceId = "190001";

    @Override
    public List<AduitView> selectByBusinessKey(String businessKey) {

//        List<HistoricActivityInstance> hisActivityInstanceList = ((HistoricActivityInstance) historyService
//                .createHistoricActivityInstanceQuery()
//                .processInstanceId(processInstanceId).activityType("userTask")
//                .finished().orderByHistoricActivityInstanceEndTime().desc())
//                .list();


        return aduitViewDao.selectByBusinessKey(businessKey);
    }
}
