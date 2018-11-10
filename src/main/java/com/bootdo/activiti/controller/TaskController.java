package com.bootdo.activiti.controller;

import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.vo.ProcessVO;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.common.utils.PageUtils;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.xerces.xs.datatypes.ObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**

 */
@RequestMapping("activiti/task")
@RestController
public class TaskController {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    TaskService taskService;
    @Autowired
    ActTaskService actTaskService;
    @Autowired
    HistoryService historyService;
    @GetMapping("goto")
    public ModelAndView gotoTask(){
        return new ModelAndView("act/task/gotoTask");
    }

    @GetMapping("/gotoList")
    PageUtils list(int offset, int limit) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .listPage(offset, limit);
        int count = (int) repositoryService.createProcessDefinitionQuery().count();
        List<Object> list = new ArrayList<>();
        for(ProcessDefinition processDefinition: processDefinitions){
            list.add(new ProcessVO(processDefinition));
        }

        PageUtils pageUtils = new PageUtils(list, count);
        return pageUtils;
    }

    @GetMapping("/form/{procDefId}")
    public void startForm(@PathVariable("procDefId") String procDefId  ,HttpServletResponse response) throws IOException {
        String formKey = actTaskService.getFormKey(procDefId, null);
        response.sendRedirect(formKey);
    }

    @GetMapping("/form/{procDefId}/{taskId}")
    public void form(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId ,HttpServletResponse response) throws IOException {
        // 获取流程XML上的表单KEY

        String formKey = actTaskService.getFormKey(procDefId, taskId);


        response.sendRedirect(formKey+"/"+taskId);
    }

    @GetMapping("/todo")
    ModelAndView todo(){
        return new ModelAndView("act/task/todoTask");
    }

    /**
     * 我的任务 待办事项
     * @return
     */
    @GetMapping("/todoList")
    List<TaskVO> todoList(){
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("admin").list();
        List<TaskVO> taskVOS =  new ArrayList<>();
        for(Task task : tasks){
            TaskVO taskVO = new TaskVO(task);
            taskVOS.add(taskVO);
        }
        return taskVOS;
    }

    /**
     * 我的历史任务
     * @return
     */
    @GetMapping("/hisTaskList")
    Object hisTaskList(){
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        List<HistoricTaskInstance> list = query.taskAssignee("admin").list();
        Set<Map<String,Object>> resultSet = new HashSet<Map<String,Object>>();
        for ( HistoricTaskInstance hpi : list){
            System.out.println(hpi.getId() + ":" + hpi.getAssignee() + ":" + hpi.getName());
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("name",hpi.getName());//节点名称
            resultMap.put("assignee",hpi.getAssignee());//操作人
            resultMap.put("id",hpi.getId());//任务编号
//            resultMap.put("startTime",(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(hpi.getStartTime()));//开始时间
//            resultMap.put("endTime",(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(hpi.getEndTime()));//开始时间
            resultMap.put("deleteReason",hpi.getDeleteReason());
            resultSet.add(resultMap);
        }
        Iterator<Map<String,Object>> iterator = resultSet.iterator();
        while (iterator.hasNext()){
            Map<String,Object> map = iterator.next();
            if(null == map.get("deleteReason")){
                iterator.remove();
            }
        }
        return resultSet ;
    }

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}/{execId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


}
