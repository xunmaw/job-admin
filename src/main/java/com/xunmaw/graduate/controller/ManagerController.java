package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.*;
import com.xunmaw.graduate.service.*;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.StudentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private PoliticalService politicalService;

    @Autowired
    private DepartService departService;

    @Autowired
    private ObtainStateService obtainStateService;

    @Autowired
    private IntentionService intentionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private EnterpriseTypeService enterpriseTypeService;

    @Autowired
    private GraduatePlaceService graduatePlaceService;

    @RequestMapping("/showInfo/{managerId}")
    public String showInfo(@PathVariable("managerId") String managerId, Model model) {
        Manager manager = managerService.selectById(managerId);
        model.addAttribute("manager", manager);
        return "manager_personal";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody Manager manager, HttpServletRequest request) {
        if (manager != null) {
            managerService.updateById(manager);
            Manager manager1 = managerService.selectById(manager.getManagerId());
            request.getSession().setAttribute("manager", manager1);
            return "true";
        }
        return "false";
    }

    @RequestMapping("/selectStuById")
    public String selectStuById(@RequestParam("stuId") String id,
                                @RequestParam("flag") String flag,
                                Model model) {
        Student student = studentService.selectById(id);
        List<GraduatePlace> places = graduatePlaceService.listAll();
        List<Political> politicalList = politicalService.listAll();
        List<Depart> departList = departService.listAll();
        String sql="select * from major where depart_id="+student.getDepartId();
        List<Major> majorList = majorService.listBySqlReturnEntity(sql);
        List<ObtainState> states = obtainStateService.listAll();
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(student, studentVo);
        String stuBirthday = student.getStuBirthday();
        int year = Integer.parseInt(stuBirthday.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        studentVo.setStuAge((yearNow - year) + "");
        System.out.println(studentVo);
        model.addAttribute("politicalList", politicalList);
        model.addAttribute("student", studentVo);
        model.addAttribute("departList", departList);
        model.addAttribute("majorList", majorList);
        model.addAttribute("states", states);
        model.addAttribute("places", places);
        if (flag.equals("0")) {
            return "manager_student_personal";
        } else {
            return "manager_student_edit";
        }
    }

    @RequestMapping("/getItemByResumeId/{intentionId}")
    public String getItemByResumeId(@PathVariable("intentionId") String intentionId, Model model) {
        Intention intention = intentionService.selectById(intentionId);
        intentionService.checkStatus(intention);
        List<Item> items = itemService.listAll(intentionId);
        model.addAttribute("intention", intention);
        model.addAttribute("items", items);
        return "manager_resume_detail";
    }

    @RequestMapping("/forwardToChangResumeState/{intentionId}")
    public String changResumeState(@PathVariable("intentionId") String intentionId, Model model) {
        Intention intention = intentionService.selectById(intentionId);
        model.addAttribute("intention", intention);
        return "manager_resume_edit";
    }

    @RequestMapping("/changResumeState")
    @ResponseBody
    public String changResumeState(@RequestParam("intentionId") String intentionId,
                                   @RequestParam("intentionState") String intentionState) {
        Intention intention = intentionService.selectById(intentionId);
        intention.setIntentionState(intentionState);
        intentionService.updateById(intention);
        return "true";
    }

    @RequestMapping("/forwardToChangRequireState/{requireId}")
    public String changRequireState(@PathVariable("requireId") String requireId, Model model) {
        Requirement requirement = requirementService.selectById(requireId);
        model.addAttribute("requirement", requirement);
        return "manager_require_edit";
    }

    @RequestMapping("/changRequireState")
    @ResponseBody
    public String changRequireState(@RequestParam("requireId") String requireId,
                                   @RequestParam("requireState") String requireState) {
        Requirement requirement = requirementService.selectById(requireId);
        requirement.setRequireState(requireState);
        requirement.setApply("0");
        requirementService.updateById(requirement);
        return "true";
    }

    @RequestMapping("/showAllEnterprise")
    public String showAllEnterprise(@RequestParam(value = "offset", required = false, defaultValue = "1") Integer offset,
                                    @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                                    @RequestParam(value = "regionId", required = false) String regionId,
                                    @RequestParam(value = "enterpriseId", required = false) String enterpriseId,
                                    @RequestParam(value = "typeId", required = false) String typeId,
                                    Model model) {
        if (size == null || size <= 0) {
            size = 5;
        }
        if (offset == null || offset <= 0) {
            offset = 1;
        }
        Pager<Enterprise> pager = new Pager<>();
        String cSql = "select count(*) from enterprise where 1=1";
        String sql = "select * from enterprise where 1=1";
        if (regionId != null && !regionId.equals("")) {
            cSql += " and region_id='" + regionId + "' ";
            sql += " and region_id='" + regionId + "' ";
            model.addAttribute("regionId", regionId);
        }
        if (enterpriseId != null && !enterpriseId.equals("")) {
            cSql += " and enterprise_id='" + enterpriseId + "' ";
            sql += " and enterprise_id='" + enterpriseId + "' ";
            model.addAttribute("enterpriseId", enterpriseId);
        }
        if (typeId != null && !typeId.equals("")) {
            cSql += " and type_id='" + typeId + "' ";
            sql += " and type_id='" + typeId + "' ";
            model.addAttribute("typeId", typeId);
        }
        Integer total = enterpriseService.getCount(cSql);
        Integer begin;
        Integer end;
        int start = (offset - 1) * size;
        int totalPage = total % size == 0 ? total / size : (total / size) + 1;
        if (totalPage <= 3) {
            begin = 1;
            end = totalPage;
        } else {
            end = offset + 1;
            begin = offset - 1;
            if (begin < 1) {
                begin = 1;
                end = begin + 2;
            }
            if (end > totalPage) {
                end = totalPage;
                begin = end - 2;
            }
        }
        sql += " order by enterprise_no limit " + start + "," + size;
        List<Region> regions = regionService.listAll();
        List<EnterpriseType> enterpriseTypes = enterpriseTypeService.listAll();
        List<Enterprise> enterprises = enterpriseService.listBySqlReturnEntity(sql);
        pager.setDatas(enterprises);
        pager.setOffset(offset);
        pager.setSize(size);
        pager.setTotalPage(totalPage);
        pager.setTotalCount(total);
        model.addAttribute("pager", pager);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("regions", regions);
        model.addAttribute("enterpriseTypes", enterpriseTypes);
        return "enterprise_list";
    }

    @RequestMapping("/selectEntById")
    public String selectEntById(@RequestParam("enterpriseId") String enterpriseId,
                                @RequestParam("flag") String flag,
                                Model model) {
        Enterprise enterprise = enterpriseService.selectById(enterpriseId);
        System.out.println(enterprise);
        List<Region> regions = regionService.listAll();
        List<EnterpriseType> types = enterpriseTypeService.listAll();
        model.addAttribute("enterprise", enterprise);
        model.addAttribute("regions", regions);
        model.addAttribute("types", types);
        if (flag.equals("0"))
            return "manager_enterprise_personal";
        return "manager_enterprise_edit";
    }
}
