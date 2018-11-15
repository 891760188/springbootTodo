package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.AduitView;
import com.bootdo.activiti.service.AduitViewService;
import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/act/av")
public class AduitViewController {

    @Autowired
    private AduitViewService aduitViewService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("/viewList")
    @ResponseBody
    public List<AduitView> selectByBusinessKey(String businessKey) {

        historyService.createHistoricVariableInstanceQuery()
                .processInstanceId("190001").list();

        return aduitViewService.selectByBusinessKey(businessKey);
    }
}
