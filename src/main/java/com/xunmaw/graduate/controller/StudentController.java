package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.*;
import com.xunmaw.graduate.service.*;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.utils.Situation;
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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private PoliticalService politicalService;

    @Autowired
    private DepartService departService;

    @Autowired
    private ObtainStateService obtainStateService;

    @Autowired
    private GraduatePlaceService graduatePlaceService;

    @RequestMapping("/selectById/{stuId}")
    public String selectById(@PathVariable("stuId") String id, Model model){
        Student student = studentService.selectById(id);
        List<Political> politicalList = politicalService.listAll();
        List<Depart> departList = departService.listAll();
        List<GraduatePlace> places = graduatePlaceService.listAll();
        String sql="select * from major where depart_id="+student.getDepartId();
        List<Major> majorList = majorService.listBySqlReturnEntity(sql);
        List<ObtainState> states = obtainStateService.listAll();
        StudentVo studentVo=new StudentVo();
        BeanUtils.copyProperties(student,studentVo);
        String stuBirthday = student.getStuBirthday();
        int year = Integer.parseInt(stuBirthday.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        studentVo.setStuAge((yearNow-year)+"");
        System.out.println(studentVo);
        model.addAttribute("politicalList",politicalList);
        model.addAttribute("student",studentVo);
        model.addAttribute("departList",departList);
        model.addAttribute("majorList",majorList);
        model.addAttribute("states",states);
        model.addAttribute("places",places);
        return "student_personal";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody StudentVo studentVO,HttpServletRequest request){
        Student student1=new Student();
        student1.setStuNo(studentVO.getStuNo());
        student1.setStuId(studentVO.getStuId());
        student1.setStuName(studentVO.getStuName());
        student1.setStuSex(studentVO.getStuSex());
        student1.setStuBirthday(studentVO.getStuBirthday());
        student1.setStuCredit(studentVO.getStuCredit());
        student1.setPoliticalId(studentVO.getPoliticalId());
        student1.setDepartId(studentVO.getDepartId());
        student1.setMajorId(studentVO.getMajorId());
        student1.setStuEntrance(studentVO.getStuEntrance());
        student1.setStuGraduTime(studentVO.getStuGraduTime());
        student1.setPlaceId(studentVO.getPlaceId());
        student1.setStuContr(studentVO.getStuContr());
        student1.setStuPass(studentVO.getStuPass());
        student1.setStateId(studentVO.getStateId());
        studentService.update(student1);
        Student student = studentService.selectById(student1.getStuId());
        request.getSession().setAttribute("student",student);
        return "true";
    }

    @RequestMapping("/deleteStu")
    @ResponseBody
    public String deleteStu(@RequestParam("stuId")String stuId){
        studentService.deleteById(stuId);
        return "true";
    }

    @RequestMapping("/showAllPastedStudent")
    public String showAllStudent(@RequestParam(value = "stuId",required = false)String stuId,
                                 @RequestParam(value = "stuName",required = false) String stuName,
                                 @RequestParam(value = "majorId",required = false)String majorId,
                                 @RequestParam(value = "stuGraduTime",required = false)String stuGraduTime,
                                 @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                 @RequestParam(value = "size",defaultValue = "5")Integer size,
                                 Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        List<Major> majors = majorService.listAll();
        List<GraduatePlace> places = graduatePlaceService.listAll();
        List<ObtainState> obtainStates = obtainStateService.listAll();
        Pager<StudentVo> pager=new Pager<>();
        String sql="select * from student where stu_gradu_time<DATE_FORMAT(NOW(),'%Y') ";//查询毕业时间不为当前年份的学生
        String cSql="select count(*) from student where stu_gradu_time<DATE_FORMAT(NOW(),'%Y') ";
        if (stuId!=null&& !stuId.equals("")){
            sql+=" and stu_id='"+stuId+"' ";
            model.addAttribute("stuId",stuId);
        }
        if (stuName!=null&& !stuName.equals("")){
            sql+=" and stu_name='"+stuName+"' ";
            model.addAttribute("stuName",stuName);
        }
        if (majorId!=null&& !majorId.equals("")){
            sql+=" and major_id='"+majorId+"' ";
            model.addAttribute("majorId",majorId);
        }
        if (stuGraduTime!=null&& !stuGraduTime.equals("")){
            sql+=" and stu_gradu_time='"+stuGraduTime+"' ";
            model.addAttribute("stuGraduTime",stuGraduTime);
        }
        Integer begin;
        Integer end;
        Integer total = studentService.getCount(cSql);
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
        return "pasted_student_list";
    }

    @RequestMapping("/forwardToAddStu")
    public String forwardToAddStu(Model model){
        List<Depart> departs = departService.listAll();
        List<Major> majors = majorService.listAll();
        List<Political> politicals = politicalService.listAll();
        List<ObtainState> obtainStates = obtainStateService.listAll();
        model.addAttribute("departs",departs);
        model.addAttribute("majors",majors);
        model.addAttribute("politicals",politicals);
        model.addAttribute("obtainStates",obtainStates);
        return "student_add";
    }

    @RequestMapping("/addStu")
    @ResponseBody
    public String addStu(@RequestBody Student student){
        if (!isExist(student.getStuId(),student.getStuCredit())){
            student.setStuPass(student.getStuCredit().substring(student.getStuCredit().length()-6));
            studentService.insert(student);
            return "true";
        }
        return "false";
    }

    private boolean isExist(String stuId,String credit){
        String sql="select count(*) from student where stu_id='"+stuId+"' or stu_credit='"+credit+"'";
        Integer count = studentService.getCount(sql);
        return count>0;
    }

    @RequestMapping("/forwardToAddPastedStu")
    public String forwardToAddPastedStu(Model model){
        List<Depart> departs = departService.listAll();
        List<GraduatePlace> places = graduatePlaceService.listAll();
        model.addAttribute("departs",departs);
        model.addAttribute("places",places);
        return "pasted_student_add";
    }

    @RequestMapping("/addPastedStu")
    @ResponseBody
    public String addPastedStu(@RequestBody Student student){
        String sql="select count(*) from student where stu_gradu_time<DATE_FORMAT(NOW(),'%Y') and stu_id='"+student.getStuId()+"'";
        Integer count = studentService.getCount(sql);
        if (count>0){
            return "false";
        }else{
            studentService.insert(student);
            return "true";
        }
    }

    @RequestMapping("/selectPastedStuById")
    public String selectPastedStuById(@RequestParam("stuId")String stuId,
                                      @RequestParam("flag")String flag,
                                      Model model){
        Student student = studentService.selectById(stuId);
        List<Depart> departs = departService.listAll();
        List<Major> majors = majorService.listAll();
        List<GraduatePlace> places = graduatePlaceService.listAll();
        model.addAttribute("student",student);
        model.addAttribute("departs",departs);
        model.addAttribute("majors",majors);
        model.addAttribute("places",places);
        if (flag.equals("0"))
            return "pasted_student_personal";
        else
            return "pasted_student_edit";
    }

    @RequestMapping("/updatePastedStu")
    @ResponseBody
    public String updatePastedStu(@RequestBody Student student){
        String sql="select * from student where stu_no="+student.getStuNo();
        Student stu = studentService.getBySqlReturnEntity(sql);
        String sql1="select * from student where stu_id<>'"+stu.getStuId()+"' and stu_gradu_time<DATE_FORMAT(NOW(),'%Y')";
        List<Student> students = studentService.listBySqlReturnEntity(sql1);
        for (Student s:
            students){
            if (s.getStuId().equals(student.getStuId()))
                return "false";
        }
        studentService.updateById(student);
        return "true";
    }

    @RequestMapping("/situationAnalysis")
    public String situationAnalysis(@RequestParam(value = "stuGraduTime",required = false)String stuGraduTime,
                                    @RequestParam(value = "departId",required = false)Integer departId,
                                    @RequestParam(value = "majorId",required = false)String majorId,
                                    Model model){
        List<Depart> departs = departService.listAll();
        Situation situation=new Situation();
        String sql="select count(*) from student where stu_gradu_time<DATE_FORMAT(NOW(),'%Y')";
        Integer total=studentService.count(sql,null,stuGraduTime,departId,majorId);

        Integer gy = studentService.count(sql,"1",stuGraduTime,departId,majorId);
        situation.setGy(gy*1.0/total*100);

        Integer my = studentService.count(sql,"2",stuGraduTime,departId,majorId);
        situation.setMy(my*1.0/total*100);

        Integer wz = studentService.count(sql,"3",stuGraduTime,departId,majorId);
        situation.setWz(wz*1.0/total*100);

        Integer zz = studentService.count(sql,"4",stuGraduTime,departId,majorId);
        situation.setZz(zz*1.0/total*100);

        Integer ky = studentService.count(sql,"5",stuGraduTime,departId,majorId);
        situation.setKy(ky*1.0/total*100);

        Integer gx = studentService.count(sql,"6",stuGraduTime,departId,majorId);
        situation.setGx(gx*1.0/total*100);

        Integer qt = studentService.count(sql,"7",stuGraduTime,departId,majorId);
        situation.setQt(qt*1.0/total*100);
        model.addAttribute("situation",situation);
        model.addAttribute("departs",departs);
        return "pasted_situation_analysis";
    }
}
