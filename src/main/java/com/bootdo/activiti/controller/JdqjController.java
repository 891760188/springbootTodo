package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.Jdqj;
import com.bootdo.activiti.domain.SalaryDO;
import com.bootdo.activiti.service.JdqjService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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


}
