package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Obtain;
import com.xunmaw.graduate.entity.Student;
import com.xunmaw.graduate.service.ObtainService;
import com.xunmaw.graduate.service.StudentService;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.ObtainVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obtain")
public class ObtainController {

    @Autowired
    private ObtainService obtainService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/listAll")
    public String listAll(@RequestParam("stuId")String stuId,
                          @RequestParam("offset") Integer offset,
                          @RequestParam("size")Integer size, Model model){
        if (size<=0){
            size=5;
        }
        if (offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
        Pager<Obtain> pager=new Pager<>();
        Integer total = obtainService.selectCount(stuId);
        int start=(offset-1)*size;
        int totalPage=total%size==0?total/size:(total/size)+1;
        if (totalPage<=3){
            begin=1;
            end=totalPage;
        }else{
            end=offset+1;
            begin=offset-1;
            if (begin<1){
                begin=1;
                end=begin+2;
            }
            if (end>totalPage){
                end=totalPage;
                begin=end-2;
            }
        }
        List<Obtain> pagers = obtainService.findByPage(stuId, start, size);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(pagers);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        return "obtain_list";
    }

    @RequestMapping("/forwardToAddObtain/{stuId}")
    public String forwardToAddObtain(@PathVariable("stuId") String stuId,Model model){
        Student student = studentService.selectById(stuId);
        model.addAttribute("student",student);
        return "obtain_add";
    }
    @RequestMapping("/addObtain")
    @ResponseBody
    public String addObtain(@RequestBody ObtainVo obtainVo){
        Obtain obtain=new Obtain();
        BeanUtils.copyProperties(obtainVo,obtain);
        obtainService.insert(obtain);
        return "true";
    }

    @RequestMapping("/forwardToUpdate/{obtainId}")
    public String forwardToUpdate(@PathVariable("obtainId")String obtainId,Model model){
        Obtain obtain = obtainService.selectById(obtainId);
        model.addAttribute("obtain",obtain);
        return "obtain_edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody ObtainVo obtainVo){
        Obtain obtain=new Obtain();
        BeanUtils.copyProperties(obtainVo,obtain);
        obtainService.updateById(obtain);
        return "true";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("obtainId")String obtainId){
        obtainService.deleteById(obtainId);
        return "true";
    }
}
