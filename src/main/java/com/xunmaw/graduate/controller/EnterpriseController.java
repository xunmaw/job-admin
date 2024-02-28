package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.*;
import com.xunmaw.graduate.service.*;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.EnterpriseVo;
import com.xunmaw.graduate.vo.StudentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private EnterpriseTypeService enterpriseTypeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private ObtainStateService obtainStateService;

    @Autowired
    private ObtainService obtainService;

    @Autowired
    private IntentionService intentionService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private GraduatePlaceService graduatePlaceService;

    @RequestMapping("/selectById/{enterpriseId}")
    public String selectById(@PathVariable("enterpriseId")String enterpriseId, Model model){
        Enterprise enterprise = enterpriseService.selectById(enterpriseId);
        System.out.println(enterprise);
        List<Region> regions = regionService.listAll();
        List<EnterpriseType> types = enterpriseTypeService.listAll();
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("regions",regions);
        model.addAttribute("types",types);
        return "enterprise_personal";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody EnterpriseVo enterpriseVo, HttpServletRequest request){
        String sql="select * from enterprise where enterprise_no="+enterpriseVo.getEnterpriseNo();
        Enterprise enterprise1 = enterpriseService.getBySqlReturnEntity(sql);
        Enterprise enterprise=new Enterprise();
        String enterpriseId = enterprise1.getEnterpriseId();
        BeanUtils.copyProperties(enterpriseVo,enterprise);
        enterpriseService.update(enterprise);
        request.getSession().setAttribute("enterprise",enterprise);
        requirementService.updateBySql("update requirement set enterprise_id='"+enterpriseVo.getEnterpriseId()+"' where enterprise_id='"+enterpriseId+"'");
        return "true";
    }

    @RequestMapping("/showAllStudent")
    public String showAllStudent(@RequestParam(value = "stuId",required = false)String stuId,
                                 @RequestParam(value = "stuEntrance",required = false) String stuEntrance,
                                 @RequestParam(value = "stateId",required = false)String stateId,
                                 @RequestParam(value = "majorId",required = false)String majorId,
                                 @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                 @RequestParam(value = "size",defaultValue = "5")Integer size,
                                 Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer total = studentService.selectCountBy(stuId,stuEntrance,stateId,majorId);
        List<Major> majors = majorService.listAll();
        List<GraduatePlace> places = graduatePlaceService.listAll();
        List<ObtainState> obtainStates = obtainStateService.listAll();
        Pager<StudentVo> pager=new Pager<>();
        String sql="select * from student where 1=1 ";
        if (stuId!=null&& !stuId.equals("")){
            sql+=" and stu_id='"+stuId+"' ";
            model.addAttribute("stuId",stuId);
        }
        if (stuEntrance!=null&& !stuEntrance.equals("")){
            sql+=" and stu_entrance='"+stuEntrance+"' ";
            model.addAttribute("stuEntrance",stuEntrance);
        }
        if (stateId!=null&& !stateId.equals("")){
            sql+=" and state_id='"+stateId+"' ";
            model.addAttribute("stateId",stateId);
        }
        if (majorId!=null&& !majorId.equals("")){
            sql+=" and major_id='"+majorId+"' ";
            model.addAttribute("majorId",majorId);
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
            if (offset<1){
                begin=1;
                end=begin+2;
            }
            if (end>totalPage){
                end=totalPage;
                begin=end-2;
            }
        }
        sql+=" order by stu_entrance desc limit "+start+","+size+" ";
        List<Student> students = studentService.listBySqlReturnEntity(sql);
        List<StudentVo> studentVos=new ArrayList<>();
        for (Student student :
                students) {
            Integer old = Integer.valueOf(student.getStuBirthday().substring(0, 4));
            int now = Calendar.getInstance().get(Calendar.YEAR);
            StudentVo studentVo=new StudentVo();
            BeanUtils.copyProperties(student,studentVo);
            studentVo.setStuAge((now-old)+"");
            studentVos.add(studentVo);
        }
        pager.setSize(size);
        pager.setOffset(offset);
        pager.setTotalPage(totalPage);
        pager.setTotalCount(total);
        pager.setDatas(studentVos);
        model.addAttribute("pager",pager);
        model.addAttribute("majors",majors);
        model.addAttribute("obtainStates",obtainStates);
        model.addAttribute("places",places);
        model.addAttribute("begin",begin);
        model.addAttribute("end",end);
        return "student_list";
    }

    @RequestMapping("/showAllResume")
    public String showAllResume(@RequestParam(value = "size",defaultValue = "5")Integer size,
                                @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                @RequestParam(value = "intentionMajor",required = false)String intentionMajor,
                                @RequestParam(value = "regionId",required = false)String regionId,
                                @RequestParam(value = "intentionState",required = false)String intentionState,
                                Model model){
        List<Region> regions = regionService.listAll();
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        String sql="select * from intention where 1=1 ";
        if (intentionMajor!=null&& !intentionMajor.equals("")){
            sql+=" and intention_major like '%"+intentionMajor+"%' ";
            model.addAttribute("intentionMajor",intentionMajor);
        }
        if (regionId != null && !regionId.equals("")) {

            sql+=" and region_id='"+regionId+"' ";
            model.addAttribute("regionId",regionId);
        }
        if (intentionState != null && !intentionState.equals("")) {

            sql+=" and intention_state='"+intentionState+"' ";
            model.addAttribute("intentionState",intentionState);
        }
        Integer begin;
        Integer end;
        Pager<Intention> pager=new Pager<>();
        Integer total = intentionService.selectCountBy(intentionMajor,regionId);
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
        sql+=" limit "+start+","+size;
        List<Intention> pagers = intentionService.listBySqlReturnEntity(sql);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(pagers);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        model.addAttribute("regions",regions);
        return "enterprise_resume_list";
    }

    @RequestMapping("/showAllObtain")
    public String showAllObtain(@RequestParam(value = "size",defaultValue = "5")Integer size,
                                @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                @RequestParam(value = "stuId",required = false)String stuId,
                                Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        String sql="select * from obtain where 1=1 ";
        if (stuId!=null&& !stuId.equals("")){
            sql+=" and stu_id = '"+stuId+"'";
            model.addAttribute("stuId",stuId);
        }
        Integer begin;
        Integer end;
        Pager<Obtain> pager=new Pager<>();
        Integer total = obtainService.selectCountBy(stuId);
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
        sql+=" limit "+start+","+size;
        List<Obtain> pagers = obtainService.listBySqlReturnEntity(sql);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(pagers);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        return "enterprise_obtain_list";
    }

    @RequestMapping("/forwardToAddEnt")
    public String forwardToAddEnt(Model model){
        List<Region> regions = regionService.listAll();
        List<EnterpriseType> types = enterpriseTypeService.listAll();
        model.addAttribute("regions",regions);
        model.addAttribute("types",types);
        return "enterprise_add";
    }

    @RequestMapping("/addEnt")
    @ResponseBody
    public String addEnt(@RequestBody EnterpriseVo enterpriseVo){
        Enterprise enterprise=new Enterprise();
        BeanUtils.copyProperties(enterpriseVo,enterprise);
        if (!isExist(enterprise.getEnterpriseId())){
            enterprise.setEnterprisePass("123456");
            enterpriseService.insert(enterprise);
            return "true";
        }
        return "false";
    }

    private boolean isExist(String enterpriseId){
        String sql="select count(*) from enterprise where enterprise_id='"+enterpriseId+"'";
        Integer count = enterpriseService.getCount(sql);
        return count>0;
    }

    @RequestMapping("/deleteEnt")
    @ResponseBody
    private String deleteEnt(@RequestParam("enterpriseId")String enterpriseId){
        Enterprise enterprise = enterpriseService.selectById(enterpriseId);
        if (enterprise!=null){
            enterpriseService.deleteById(enterprise);
            return "true";
        }
        return "false";
    }
}
