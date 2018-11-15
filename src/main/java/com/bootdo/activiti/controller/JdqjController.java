package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.Jdqj;
import com.bootdo.activiti.domain.SalaryDO;
import com.bootdo.activiti.service.JdqjService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/act/jdqj")
public class JdqjController {

    @Autowired
    private JdqjService jdqjService ;

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R saveOrUpdate(Jdqj jdqj) {

        if(null == jdqj || null == jdqj.getReason()){
            return R.error(1, "出差理由不能为空");
        }
        jdqjService.save(jdqj);
        System.out.println(jdqj.getId());
        return R.ok(jdqj.getId().toString());
    }

    /**
     * 我的请假his
     * @param params
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
//    PageUtils list(@RequestParam Map<String, Object> params) {
    List<Jdqj> list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Jdqj> sysUserList = jdqjService.list(query);
//        int total = userService.count(query);
//        PageUtils pageUtil = new PageUtils(sysUserList, total);
//        return pageUtil;
        return sysUserList ;
    }

    @PostMapping("/complete")
    @ResponseBody
    R complete (@RequestParam Map<String,Object> params){
        jdqjService.complete(params);
        return R.ok();
    }

    @GetMapping("/myTask")
    @ResponseBody
//    PageUtils list(@RequestParam Map<String, Object> params) {
    List<Map<String,Object>> myTask(@RequestParam Map<String, Object> params) {

        List<Map<String, Object>> dataList = jdqjService.myTask(params);

        return dataList ;
    }


}
