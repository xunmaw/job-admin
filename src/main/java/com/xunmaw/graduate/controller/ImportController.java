package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Enterprise;
import com.xunmaw.graduate.entity.Student;
import com.xunmaw.graduate.service.*;
import com.xunmaw.graduate.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private PoliticalService politicalService;
    @Autowired
    private DepartService departService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ObtainStateService obtainStateService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private EnterpriseTypeService enterpriseTypeService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private GraduatePlaceService graduatePlaceService;
    @RequestMapping("/importStuExcel")
    @ResponseBody
    public boolean importStuExcel(@RequestParam("file") MultipartFile file){
        try {
            InputStream in = file.getInputStream();
            List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, file.getOriginalFilename());
            System.out.println("============="+listob);
            // List<UserInfo> inventoryList = new ArrayList<UserInfo>();
            //遍历listob数据，把数据放到List中
            for (int i = 0; i < listob.size(); i++) {
                List<Object> ob = listob.get(i);
                Student student = new Student();
                student.setStuId(String.valueOf(ob.get(0)).trim());
                student.setStuName(String.valueOf(ob.get(1)).trim());
                student.setStuSex(String.valueOf(ob.get(2)).trim());
                student.setStuBirthday(String.valueOf(ob.get(3)).trim());
                student.setStuCredit(String.valueOf(ob.get(4)).trim());
                student.setPoliticalId(politicalService.change(String.valueOf(ob.get(5)).trim()));
                student.setDepartId(departService.change(String.valueOf(ob.get(6)).trim()));
                student.setMajorId(majorService.change(String.valueOf(ob.get(7)).trim(),student.getDepartId()));
                student.setStuEntrance(String.valueOf(ob.get(8)).trim());
                student.setStuGraduTime(String.valueOf(ob.get(9)).trim());
                student.setStuContr(String.valueOf(ob.get(10)).trim());
                student.setStuPass(student.getStuCredit().substring(student.getStuCredit().length()-6));
                student.setStateId(obtainStateService.change(String.valueOf(ob.get(11)).trim()));
                try{
                    student.setPlaceId(String.valueOf(ob.get(12)).trim());
                }catch (Exception e){
                    System.out.println("没有毕业去向");
                }
                studentService.insert(student);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/importPastedStuExcel")
    @ResponseBody
    public boolean importPastedStuExcel(@RequestParam("file") MultipartFile file){
        try {
            InputStream in = file.getInputStream();
            List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, file.getOriginalFilename());
            System.out.println("============="+listob);
            //遍历listob数据，把数据放到List中
            for (int i = 0; i < listob.size(); i++) {
                List<Object> ob = listob.get(i);
                Student student = new Student();
                student.setStuId(String.valueOf(ob.get(0)).trim());
                student.setStuName(String.valueOf(ob.get(1)).trim());
                student.setStuBirthday(String.valueOf(ob.get(2)).trim());
                student.setStuSex(String.valueOf(ob.get(3)).trim());
                student.setDepartId(departService.change(String.valueOf(ob.get(4)).trim()));
                student.setMajorId(majorService.change(String.valueOf(ob.get(5)).trim(),student.getDepartId()));
                student.setStuGraduTime(String.valueOf(ob.get(6)).trim());
                student.setPlaceId(graduatePlaceService.change(String.valueOf(ob.get(7)).trim()));
                studentService.insert(student);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/importEntExcel")
    @ResponseBody
    public boolean importEntExcel(@RequestParam("file") MultipartFile file){
        try {
            InputStream in = file.getInputStream();
            List<List<Object>> listob = ExcelUtils.getBankListByExcel(in, file.getOriginalFilename());
            System.out.println("============="+listob);
            // List<UserInfo> inventoryList = new ArrayList<UserInfo>();
            //遍历listob数据，把数据放到List中
            for (int i = 0; i < listob.size(); i++) {
                List<Object> ob = listob.get(i);
                Enterprise enterprise = new Enterprise();
                enterprise.setEnterpriseId(String.valueOf(ob.get(0)).trim());
                enterprise.setEnterpriseName(String.valueOf(ob.get(1)).trim());
                enterprise.setRegionId(regionService.change(String.valueOf(ob.get(2)).trim()));//
                enterprise.setTypeId(enterpriseTypeService.change(String.valueOf(ob.get(3)).trim()));//
                enterprise.setEnterpriseIntro(String.valueOf(ob.get(4)).trim());
                enterprise.setContactName(String.valueOf(ob.get(5)).trim());
                enterprise.setContactTel(String.valueOf(ob.get(6)).trim());
                enterprise.setContactEmail(String.valueOf(ob.get(7)).trim());
                enterprise.setEnterprisePass("123456");
                enterpriseService.insert(enterprise);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
