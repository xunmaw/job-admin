package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Major;
import com.xunmaw.graduate.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping("/findByDepartId")
    @ResponseBody
    public List<Major> findByProvinceId(@RequestParam("departId")String departId){
        String sql="select * from major where depart_id="+departId;
        List<Major> majors = majorService.listBySqlReturnEntity(sql);
        return majors;
    }
}
