package com.bootdo.activiti.utils;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**

 */
@Component
public class ActivitiUtils {
    /**
     * 根据taskId查找businessKey
     */
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;

    /**
     *  由任务编号获取实例编号，由实例编号获取业务主键
     * @param taskId
     * @return
     */
    public String getBusinessKeyByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
    }

    public Task getTaskByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }
}
