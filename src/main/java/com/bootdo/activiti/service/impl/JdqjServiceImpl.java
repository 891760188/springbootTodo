package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.AduitViewDao;
import com.bootdo.activiti.dao.JdqjDao;
import com.bootdo.activiti.domain.AduitView;
import com.bootdo.activiti.domain.Jdqj;
import com.bootdo.activiti.service.JdqjService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdqjServiceImpl implements JdqjService {

    @Resource
    private JdqjDao jdqjDao ;

    @Resource
    private AduitViewDao aduitViewDao;

    @Resource
    private UserDao userDao;

    @Resource
    private RuntimeService runtimeService ;

    @Override
    @Transactional
    public Jdqj save(Jdqj jdqj) {
        if(null == jdqj.getId()) {
            jdqj.setCrdt(new Date());
            jdqj.setCrpsn(ShiroUtils.getUser().getUserId().toString());
        }
        //申请信息
        jdqjDao.insert(jdqj);
        System.out.println(jdqj.getId());
        AduitView auditview = new AduitView();
        auditview.setBusinessKey(jdqj.getId().toString());
        auditview.setAduitPsn(ShiroUtils.getUser().getUserId().toString());
        auditview.setAduitState("1");
        auditview.setCreateTime(new Date());
        auditview.setType("0");
        auditview.setRemark("提交申请");
        aduitViewDao.insert(auditview);

        /**组织工作流需要的数据*/
        Map<String,Object> aduitMap = new HashMap<String,Object>();
        //申请人
        aduitMap.put("appro",ShiroUtils.getUser().getUserId());
        //小组长审批
        String deptId = ShiroUtils.getUser().getDeptId().toString();
        Map<String,String> map1 = new HashMap<String,String>();
        map1.put("deptId",deptId);
        map1.put("roleId","63");
        List<Map<String,Object>> list1 = userDao.getAuditPsn(map1);
        aduitMap.put("xzz",list1.get(0).get("user_id"));
        //项目经理审批
        Map<String,String> map2 = new HashMap<String,String>();
        map2.put("deptId",list1.get(0).get("dept_id").toString());
        map2.put("roleId","61");
        List<Map<String,Object>> list2 = userDao.getAuditPsnQuery(map2);
        aduitMap.put("xmjl",list2.get(0).get("user_id"));

        //部门经理审批
        Map<String,String> map3 = new HashMap<String,String>();
        map3.put("deptId",list2.get(0).get("dept_id").toString());
        map3.put("roleId","62");
        List<Map<String,Object>> list3 = userDao.getAuditPsnQuery(map3);
        aduitMap.put("bmjl",list3.get(0).get("user_id"));

        // 启动流程
//
//       for(String key : aduitMap.keySet()){
//           aduitMap.put(key,"admin");
//       }

       Map<String,Object> vars = new HashMap<String,Object>();
//        vars.put("xzz","010");
//        vars.put("appro","admin");
//        vars.put("xmjl","admin");
//        vars.put("bmjl","admin");
//        vars.put("title","title001");

//        ProcessInstance procIns = runtimeService.startProcessInstanceByKey("jdqjKey",aduitMap);
        ProcessInstance procIns = runtimeService.startProcessInstanceByKey("jdqjId004",jdqj.getId().toString(),aduitMap);

        return null;
    }
}
