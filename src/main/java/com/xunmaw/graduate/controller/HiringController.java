package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Major;
import com.xunmaw.graduate.entity.Region;
import com.xunmaw.graduate.entity.Requirement;
import com.xunmaw.graduate.enumer.StateEnum;
import com.xunmaw.graduate.service.MajorService;
import com.xunmaw.graduate.service.RegionService;
import com.xunmaw.graduate.service.RequireNoteService;
import com.xunmaw.graduate.service.RequirementService;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.RequirementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hiring")
public class HiringController {

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private RequireNoteService requireNoteService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private RegionService regionService;

    @RequestMapping("/findHireById")
    public String findHireById(@RequestParam("enterpriseId")String enterpriseId,
                               @RequestParam(value = "requireJob",required = false)String requireJob,
                               @RequestParam(value = "requireState",required = false)String requireState,
                               @RequestParam(value = "offset",required = false)Integer offset,
                               @RequestParam(value = "size",required = false)Integer size,
                               Model model){
        Integer total = requirementService.selectCountBy(enterpriseId, requireJob, requireState);
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
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
        Pager<Requirement> pager=new Pager<>();
        String sql="select * from requirement where 1=1 ";
        if (enterpriseId!=null&& !enterpriseId.equals("")){
            sql+=" and enterprise_id ='"+enterpriseId+"' ";
        }
        if (requireJob!=null&& !requireJob.equals("")){
            sql+=" and require_job like '%"+requireJob+"%' ";
            model.addAttribute("requireJob",requireJob);
        }
        if (requireState!=null&& !requireState.equals("")){
            sql+=" and require_state = '"+requireState+"' ";
            model.addAttribute("requireState",requireState);
        }
        sql+=" limit "+start+","+size;
        List<Requirement> requirements = requirementService.listBySqlReturnEntity(sql);
        pager.setDatas(requirements);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager",pager);
        model.addAttribute("begin",begin);
        model.addAttribute("end",end);
        return "enterprise_hiring_list";
    }

    @RequestMapping("/findHire")
    public String findHire(@RequestParam(value = "stuId",required = false)String stuId,
                               @RequestParam(value = "requireJob",required = false)String requireJob,
                               @RequestParam(value = "regionId",required = false)String regionId,
                               @RequestParam(value = "offset",required = false)Integer offset,
                               @RequestParam(value = "size",required = false)Integer size,
                               Model model){
        List<Region> regions = regionService.listAll();
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
        String sql="select * from requirement where require_state = '1' ";
        String cSql="select count(*) from requirement where require_state = '1' ";
        if (requireJob!=null&& !requireJob.equals("")){
            sql+=" and require_job like '%"+requireJob+"%' ";
            cSql+=" and require_job like '%"+requireJob+"%' ";
            model.addAttribute("requireJob",requireJob);
        }
        if (regionId!=null&& !regionId.equals("")){
            sql+=" and region_id ="+regionId;
            cSql+=" and region_id ="+regionId;
            model.addAttribute("regionId",regionId);
        }
        Integer total = requirementService.getCount(cSql);
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
        Pager<RequirementVo> pager=new Pager<>();
        sql+=" limit "+start+","+size;
        List<Requirement> requirements = requirementService.listBySqlReturnEntity(sql);
        List<RequirementVo> requirementVos = change(requirements, stuId);
        pager.setDatas(requirementVos);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager",pager);
        model.addAttribute("regions",regions);
        model.addAttribute("begin",begin);
        model.addAttribute("end",end);
        model.addAttribute("stuId",stuId);
        return "student_hiring_list";
    }

    private List<RequirementVo> change(List<Requirement> list, String stuId){
        String sql=null;
        List<RequirementVo> vo=new ArrayList<>();
        for (Requirement requirement:
             list) {
            RequirementVo re=new RequirementVo();
            BeanUtils.copyProperties(requirement,re);
            sql="select count(*) from requirenote where stu_id='"+stuId+"' and require_id="+requirement.getRequireId();
            Integer count = requireNoteService.getCount(sql);
            if (count>0){
                re.setApply("1");
            }else{
                re.setApply("0");
            }
            vo.add(re);
        }
        return vo;
    }

    @RequestMapping("/forwardToAddHire")
    public String forwardToAddHire(Model model){
        List<Major> majors = majorService.listAll();
        List<Region> regions = regionService.listAll();
        model.addAttribute("majors",majors);
        model.addAttribute("regions",regions);
        return "enterprise_hiring_add";
    }

    @RequestMapping("/addHire")
    @ResponseBody
    public String addHire(@RequestBody RequirementVo requirementVo){
        Requirement requirement=new Requirement();
        BeanUtils.copyProperties(requirementVo,requirement);
        requirement.setRequireState(StateEnum.UNCHECKED.getCode());
        requirement.setApply("0");
        int num = requirementService.insert(requirement);
        if (num>0)
            return "true";
        return "false";
    }

    @RequestMapping("/forwardToUpdate/{requireId}")
    public String forwardToUpdate(@PathVariable("requireId")String requireId,Model model){
        Requirement requirement = requirementService.selectById(requireId);
        List<Major> majors = majorService.listAll();
        List<Region> regions = regionService.listAll();
        model.addAttribute("requirement",requirement);
        model.addAttribute("majors",majors);
        model.addAttribute("regions",regions);
        return "enterprise_hiring_edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody RequirementVo requirementVo){
        Requirement requirement=new Requirement();
        BeanUtils.copyProperties(requirementVo,requirement);
        requirementService.updateById(requirement);
        return "true";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("requireId")String requireId){
        requirementService.deleteById(requireId);
        return "true";
    }

    @RequestMapping("/showAllHire")
    public String showAllHire(@RequestParam(value = "offset",defaultValue = "1")Integer offset,
                              @RequestParam(value = "size",defaultValue = "5")Integer size,
                              @RequestParam(value = "enterpriseId",required = false)String enterpriseId,
                              @RequestParam(value = "requireState",required = false)String requireState,
                              Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        String cSql="select count(*) from requirement where 1=1 ";
        String sql="select * from requirement where 1=1 ";
        if (enterpriseId!=null&& !enterpriseId.equals("")){
            cSql+=" and enterprise_id like '%"+enterpriseId+"%' ";
            sql+=" and enterprise_id like '%"+enterpriseId+"%' ";
            model.addAttribute("enterpriseId",enterpriseId);
        }
        if (requireState!=null&& !requireState.equals("")){
            cSql+=" and require_state='"+requireState+"' ";
            sql+=" and require_state='"+requireState+"' ";
            model.addAttribute("requireState",requireState);
        }
        Integer total = requirementService.getCount(cSql);
        Integer begin;
        Integer end;
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
        Pager<Requirement> pager=new Pager<>();
        sql+=" order by enterprise_id limit "+start+","+size;
        List<Requirement> requirements = requirementService.listBySqlReturnEntity(sql);
        pager.setDatas(requirements);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager",pager);
        model.addAttribute("begin",begin);
        model.addAttribute("end",end);
        return "manager_hiring_list";
    }
}
