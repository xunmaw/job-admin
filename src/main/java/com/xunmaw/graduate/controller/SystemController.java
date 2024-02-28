package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.*;
import com.xunmaw.graduate.service.*;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.DepartVo;
import com.xunmaw.graduate.vo.MajorVo;
import com.xunmaw.graduate.vo.ProvinceVo;
import com.xunmaw.graduate.vo.RegionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("system")
public class SystemController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private DepartService departService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private RequirementService requirementService;

    @Autowired
    private IntentionService intentionService;

    @RequestMapping("/showAllProvince")
    public String showAllProvince(@RequestParam(value = "provinceName", required = false) String provinceName,
                                  @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                  @RequestParam(value = "size", defaultValue = "5") Integer size,
                                  Model model) {
        String cSql = "select count(*) from province where 1=1 ";
        String sql = "select * from province where 1=1 ";
        if (provinceName != null && !provinceName.equals("")) {
            cSql += " and province_name like '%" + provinceName + "%'";
            sql += " and province_name like '%" + provinceName + "%'";
            model.addAttribute("provinceName", provinceName);
        }
        Integer total = provinceService.getCount(cSql);
        if (size == null || size <= 0) {
            size = 5;
        }
        if (offset == null || offset <= 0) {
            offset = 1;
        }
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
        Pager<Province> pager = new Pager<>();
        sql += " order by province_id limit " + start + "," + size;
        List<Province> provinces = provinceService.listBySqlReturnEntity(sql);
        pager.setDatas(provinces);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager", pager);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "province_list";
    }

    @RequestMapping("/showAllRegion")
    public String showAllRegion(@RequestParam(value = "regionName", required = false) String regionName,
                                @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                @RequestParam(value = "size", defaultValue = "5") Integer size,
                                Model model) {
        String cSql = "select count(*) from region where 1=1 ";
        String sql = "select * from region where 1=1 ";
        if (regionName != null && !regionName.equals("")) {
            cSql += " and province_name like '%" + regionName + "%'";
            sql += " and province_name like '%" + regionName + "%'";
            model.addAttribute("regionName", regionName);
        }
        Integer total = regionService.getCount(cSql);
        if (size == null || size <= 0) {
            size = 5;
        }
        if (offset == null || offset <= 0) {
            offset = 1;
        }
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
        Pager<Region> pager = new Pager<>();
        sql += " order by province_id limit " + start + "," + size;
        List<Region> regions = regionService.listBySqlReturnEntity(sql);
        pager.setDatas(regions);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager", pager);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "region_list";
    }

    @RequestMapping("/addOrUpdateProvince")
    @ResponseBody
    public String addOrUpdateProvince(@RequestBody ProvinceVo provinceVo) {
        Province province = new Province();
        BeanUtils.copyProperties(provinceVo, province);
        if (province.getProvinceId() != null) {
            String sql = "select * from province where province_id<>'" + province.getProvinceId() + "'";
            List<Province> provinces = provinceService.listBySqlReturnEntity(sql);
            for (Province pro :
                    provinces) {
                if (pro.getProvinceName().equals(province.getProvinceName())){
                    return "false";
                }
            }
            provinceService.updateById(province);
            return "true";
        } else {
            String sql = "select count(*) from province where province_name='" + province.getProvinceName() + "'";
            Integer count = provinceService.getCount(sql);
            if (count > 0)
                return "false";
            provinceService.insert(province);
            return "true";
        }
    }

    @RequestMapping("/forwardToUpdatePro/{provinceId}")
    public String forwardToUpdate(@PathVariable("provinceId") String provinceId, Model model) {
        Province province = provinceService.selectById(provinceId);
        model.addAttribute("province", province);
        return "province_edit";
    }

    @RequestMapping("/forwardToUpdateReg/{regionId}")
    public String forwardToUpdateReg(@PathVariable("regionId") String regionId, Model model) {
        Region region = regionService.selectById(regionId);
        List<Province> provinces = provinceService.listAll();
        model.addAttribute("region", region);
        model.addAttribute("provinces", provinces);
        return "region_edit";
    }

    @RequestMapping("/deleteProvince")
    @ResponseBody
    public String deleteProvince(@RequestParam("provinceId") String provinceId) {
        provinceService.deleteById(provinceId);
        regionService.deleteBySql("delete from region where province_id='"+provinceId+"'");
        return "true";
    }

    @RequestMapping("/forwardToAddRegion")
    public String forwardToAddRegion(Model model) {
        List<Province> provinces = provinceService.listAll();
        model.addAttribute("provinces", provinces);
        return "region_add";
    }

    @RequestMapping("/addOrUpdateRegion")
    @ResponseBody
    public String addOrUpdateRegion(@RequestBody RegionVo regionVo) {
        Region region = new Region();
        BeanUtils.copyProperties(regionVo, region);
        if (region.getRegionId() != null) {
            Region region1 = regionService.selectById(region.getRegionId());
            String sql = "select * from region where province_id='" + region.getProvinceId() + "' and region_name <>'" + region1.getRegionName() + "'";
            List<Region> regions = regionService.listBySqlReturnEntity(sql);
            for (Region re :
                    regions) {
                if (re.getRegionName().equals(region.getRegionName())) {
                    return "false";
                }
            }
            regionService.updateById(region);
            return "true";
        } else {
            String sql = "select region_name from region where province_id='" + region.getProvinceId() + "'";
            List<String> names = regionService.findAllRegionName(sql);
            if (names.contains(region.getRegionName())) {
                return "false";
            } else {
                Integer maxIndex = regionService.findMaxIndex("select max(region_id) from region where province_id='" + region.getProvinceId() + "'");
                if (maxIndex==null){
                    region.setRegionId(1);
                }else{
                    region.setRegionId(maxIndex + 1);
                }
                int update = regionService.insert(region);
                if (update > 0)
                    return "true";
                return "false";
            }
        }
    }

    @RequestMapping("/deleteRegion")
    @ResponseBody
    public String deleteRegion(@RequestParam("regionId")String regionId){
        regionService.deleteById(regionId);
        return "true";
    }

    @RequestMapping("/showAllDepart")
    public String showAllDepart(@RequestParam(value = "departName", required = false) String departName,
                                @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                @RequestParam(value = "size", defaultValue = "5") Integer size,
                                Model model) {
        String cSql = "select count(*) from depart where 1=1 ";
        String sql = "select * from depart where 1=1 ";
        if (departName != null && !departName.equals("")) {
            cSql += " and depart_name like '%" + departName + "%'";
            sql += " and depart_name like '%" + departName + "%'";
            model.addAttribute("departName", departName);
        }
        Integer total = departService.getCount(cSql);
        if (size == null || size <= 0) {
            size = 5;
        }
        if (offset == null || offset <= 0) {
            offset = 1;
        }
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
        Pager<Depart> pager = new Pager<>();
        sql += " order by depart_id limit " + start + "," + size;
        List<Depart> regions = departService.listBySqlReturnEntity(sql);
        pager.setDatas(regions);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager", pager);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "depart_list";
    }

    @RequestMapping("/addOrUpdateDepart")
    @ResponseBody
    public String addDepart(@RequestBody DepartVo departVo){
        if (departVo!=null){
            Depart depart=new Depart();
            BeanUtils.copyProperties(departVo,depart);
            if (depart.getDepartId() != null)
            {
                Depart depart1 = departService.selectById(depart.getDepartId());
                if (!isRepeat(depart,depart1.getDepartName())){
                    departService.updateById(depart);
                    return "true";
                }else{
                    return "false";
                }
            }else{
                if (!isExit(depart)){
                    departService.insert(depart);
                    return "true";
                }else {
                    return "false";
                }
            }
        }
        return "false";
    }

    private boolean isRepeat(Depart depart,String name){
        String sql="select * from depart where depart_name<>'"+name+"'";
        List<Depart> departs = departService.listBySqlReturnEntity(sql);
        for (Depart de :
                departs) {
            if (de.getDepartName().equals(depart.getDepartName())){
                return true;
            }
        }
        return false;
    }
    private boolean isExit(Depart depart){
        String sql="select count(*) from depart where depart_name='"+depart.getDepartName()+"'";
        Integer count = departService.getCount(sql);
        return count > 0;
    }

    @RequestMapping("/deleteDepart")
    @ResponseBody
    public String deleteDepart(@RequestParam("departId")Integer departId){
        departService.deleteById(departId);
        String sql="delete from major where depart_id="+departId;
        majorService.deleteBySql(sql);
        return "true";
    }

    @RequestMapping("/forwardToUpdateDep/{departId}")
    public String forwardToUpdateDep(@PathVariable("departId")Integer departId,Model model){
        Depart depart = departService.selectById(departId);
        model.addAttribute("depart",depart);
        return "depart_edit";
    }

    @RequestMapping("/showAllMajor")
    public String showAllMajor(@RequestParam(value = "majorName", required = false) String majorName,
                                @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                @RequestParam(value = "size", defaultValue = "5") Integer size,
                                Model model) {
        String cSql = "select count(*) from major where 1=1 ";
        String sql = "select * from major where 1=1 ";
        if (majorName != null && !majorName.equals("")) {
            cSql += " and major_name like '%" + majorName + "%'";
            sql += " and major_name like '%" + majorName + "%'";
            model.addAttribute("majorName", majorName);
        }
        Integer total = majorService.getCount(cSql);
        if (size == null || size <= 0) {
            size = 5;
        }
        if (offset == null || offset <= 0) {
            offset = 1;
        }
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
        Pager<Major> pager = new Pager<>();
        sql += " order by depart_id limit " + start + "," + size;
        List<Major> majors = majorService.listBySqlReturnEntity(sql);
        pager.setDatas(majors);
        pager.setTotalCount(total);
        pager.setTotalPage(totalPage);
        pager.setOffset(offset);
        pager.setSize(size);
        model.addAttribute("pager", pager);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "major_list";
    }

    @RequestMapping("/forwardToAddMajor")
    public String forwardToAddMajor(Model model){
        List<Depart> departs = departService.listAll();
        model.addAttribute("departs",departs);
        return "major_add";
    }

    @RequestMapping("/addOrUpdateMajor")
    @ResponseBody
    public String addMajor(@RequestBody MajorVo majorVo){
        if (majorVo!=null){
            Major major=new Major();
            BeanUtils.copyProperties(majorVo,major);
            if (major.getMajorId()!=null){//更新
                Major major1 = majorService.selectById(major.getMajorId());
                if (!isRepeat(major.getMajorName(),major.getDepartId(),major1)){
                    majorService.updateById(major);
                    return "true";
                }
                return "false";
            }else {//添加
                if (!isExit(major)){
                    String sql="select * from major where depart_id="+major.getDepartId()+" order by major_id desc limit 1";
                    String index = majorService.findIndex(sql);
                    if (index!=null){
                        major.setMajorId(Integer.valueOf(index)+1+"");
                    }else{
                        major.setMajorId(major.getDepartId()+"01");
                    }
                    majorService.insert(major);
                    return "true";
                }
                return "false";
            }
        }
        return "false";
    }

    private boolean isRepeat(String majorName,Integer departId,Major major){
        String sql="select * from major where depart_id="+departId+" and major_name<>'"+major.getMajorName()+"'";
        List<Major> majors = majorService.listBySqlReturnEntity(sql);
        for (Major maj :
                majors) {
            if (maj.getMajorName().equals(majorName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExit(Major major){
        String sql="select count(*) from major where depart_id="+major.getDepartId()+" and major_name='"+major.getMajorName()+"'";
        Integer count = majorService.getCount(sql);
        return count>0;
    }

    @RequestMapping("/forwardToUpdateMajor/{majorId}")
    public String forwardToUpdateMajor(@PathVariable("majorId")String majorId,Model model){
        Major major = majorService.selectById(majorId);
        model.addAttribute("major",major);
        return "major_edit";
    }

    @RequestMapping("/deleteMajor")
    @ResponseBody
    public String deleteMajor(@RequestParam("majorId")String majorId){
        majorService.deleteById(majorId);
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
        return "system_hiring_list";
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
        String cSql="select count(*) from intention where 1=1 ";
        if (intentionMajor!=null&& !intentionMajor.equals("")){
            sql+=" and intention_major like '%"+intentionMajor+"%' ";
            cSql+=" and intention_major like '%"+intentionMajor+"%' ";
            model.addAttribute("intentionMajor",intentionMajor);
        }
        if (regionId != null && !regionId.equals("")) {

            sql+=" and region_id='"+regionId+"' ";
            cSql+=" and region_id='"+regionId+"' ";
            model.addAttribute("regionId",regionId);
        }
        if (intentionState != null && !intentionState.equals("")) {

            sql+=" and intention_state='"+intentionState+"' ";
            cSql+=" and intention_state='"+intentionState+"' ";
            model.addAttribute("intentionState",intentionState);
        }
        Integer begin;
        Integer end;
        Pager<Intention> pager=new Pager<>();
        Integer total = intentionService.getCount(cSql);
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
        return "system_resume_list";
    }
}
