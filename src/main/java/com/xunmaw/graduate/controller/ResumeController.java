package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.Intention;
import com.xunmaw.graduate.entity.Item;
import com.xunmaw.graduate.entity.Region;
import com.xunmaw.graduate.entity.Student;
import com.xunmaw.graduate.enumer.ReleaseStateEnum;
import com.xunmaw.graduate.enumer.StateEnum;
import com.xunmaw.graduate.service.IntentionService;
import com.xunmaw.graduate.service.ItemService;
import com.xunmaw.graduate.service.RegionService;
import com.xunmaw.graduate.service.StudentService;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.utils.SituationJob;
import com.xunmaw.graduate.utils.SituationRegion;
import com.xunmaw.graduate.vo.IntentionVo;
import com.xunmaw.graduate.vo.ItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private IntentionService intentionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/listAll")//带分页功能
    public String listAll(@RequestParam("stuId") String stuId,
                          @RequestParam("offset") Integer offset,
                          @RequestParam("size") Integer size, Model model) {
        if (size <= 0) {
            size = 5;
        }
        if (offset <= 0) {
            offset = 1;
        }
        Integer begin;
        Integer end;
        Pager<Intention> pager = new Pager<>();
        Integer total = intentionService.selectCount(stuId);
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
        List<Intention> pagers = intentionService.findByPage(stuId, start, size);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(pagers);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager", pager);
        model.addAttribute("end", end);
        model.addAttribute("begin", begin);
        return "resume_list";
    }

    @RequestMapping("/forwardToAdd/{stuId}")
    public String forwardToAdd(@PathVariable("stuId") String stuId, Model model) {

        Student student = studentService.selectById(stuId);
        List<Region> regions = regionService.listAll();
        model.addAttribute("student", student);
        model.addAttribute("regions", regions);
        return "resume_add";
    }

    @RequestMapping("/addResume")
    @ResponseBody
    public String addResume(@RequestBody IntentionVo intentionVo) {
        Intention intention = new Intention();
        BeanUtils.copyProperties(intentionVo, intention);
        intention.setIntentionState(StateEnum.UNCHECKED.getCode());
        intention.setReleaseState(ReleaseStateEnum.UNRELEASED.getCode());
        int num = intentionService.insert(intention);
        if (num > 0)
            return "true";
        return "false";
    }

    @RequestMapping("/deleteResume")
    @ResponseBody
    public String deleteResume(@RequestParam("intentionId") String intentionId) {
        Intention intention = intentionService.selectById(intentionId);
        if (intention != null)
            intentionService.deleteById(Integer.valueOf(intentionId));
        List<Item> items = itemService.selectByIntentionId(intentionId);
        if (items.size() > 0) {
            for (Item item :
                    items) {
                itemService.deleteById(item.getItemId());
            }
        }
        return "true";
    }

    @RequestMapping("/forwardToEdit/{intentionId}")
    public String forwardToEdit(@PathVariable("intentionId") String intentionId, Model model) {
        Intention intention = intentionService.selectById(intentionId);
        List<Region> regions = regionService.listAll();
        model.addAttribute("intention", intention);
        model.addAttribute("regions", regions);
        return "resume_edit";
    }

    @RequestMapping("/updateResume")
    @ResponseBody
    public String updateResume(@RequestBody IntentionVo intentionVo) {
        Intention intention = new Intention();
        BeanUtils.copyProperties(intentionVo, intention);
        intentionService.updateById(intention);
        return "true";
    }

    @RequestMapping("/getItemByResumeId/{intentionId}")
    public String getItemByResumeId(@PathVariable("intentionId") String intentionId, Model model) {
        Intention intention = intentionService.selectById(intentionId);
        intentionService.checkStatus(intention);
        List<Item> items = itemService.listAll(intentionId);
        model.addAttribute("intention", intention);
        model.addAttribute("items", items);
        return "resume_detail";
    }

    @RequestMapping("/forwardToAddItem/{intentionId}")
    public String forwardToAddItem(@PathVariable("intentionId") String intentionId, Model model) {
        model.addAttribute("intentionId", intentionId);
        return "item_add";
    }

    @RequestMapping("/addItem")
    @ResponseBody
    public String addItem(@RequestBody ItemVo itemVo) {
        Item item = new Item();
        BeanUtils.copyProperties(itemVo, item);
        int num = itemService.insert(item);
        if (num > 0)
            return "true";
        return "false";
    }

    @RequestMapping("/forwardToEditItem/{itemId}")
    public String forwardToEditItem(@PathVariable("itemId") String itemId, Model model) {
        Item item = itemService.selectById(itemId);
        model.addAttribute("item", item);
        return "item_edit";
    }

    @RequestMapping("/updateItem")
    @ResponseBody
    public String updateItem(@RequestBody ItemVo itemVo) {
        Item item = new Item();
        BeanUtils.copyProperties(itemVo, item);
        itemService.updateById(item);
        return "true";
    }

    @RequestMapping("/deleteItem")
    @ResponseBody
    public String deleteItem(@RequestParam("itemId") String itemId) {
        itemService.deleteById(itemId);
        return "true";
    }

    @RequestMapping("/analysis")
    public String analysis(Model model) {
        String sql = "SELECT count(*) FROM intention i " +
                "LEFT JOIN student s ON i.stu_id=s.stu_id " +
                "LEFT JOIN region r ON r.region_id=i.region_id " +
                "WHERE stu_gradu_time =DATE_FORMAT(NOW(), '%Y')";
        SituationRegion region = new SituationRegion();

        Integer total = intentionService.count(sql, null, null);

        Integer cs = intentionService.count(sql, "长沙", null);
        region.setCs(cs * 1.0 / total * 100);

        Integer bj = intentionService.count(sql, "北京", null);
        region.setBj(bj * 1.0 / total * 100);

        Integer sh = intentionService.count(sql, "上海", null);
        region.setSh(sh * 1.0 / total * 100);

        Integer cd = intentionService.count(sql, "成都", null);
        region.setCd(cd * 1.0 / total * 100);

        Integer hz = intentionService.count(sql, "杭州", null);
        region.setHz(hz * 1.0 / total * 100);

        Integer gz = intentionService.count(sql, "广州", null);
        region.setGz(gz * 1.0 / total * 100);

        Integer sz = intentionService.count(sql, "深圳", null);
        region.setSz(sz * 1.0 / total * 100);

        String s = "SELECT COUNT(*) FROM intention i " +
                "LEFT JOIN student s ON i.stu_id=s.stu_id " +
                "LEFT JOIN region r ON r.region_id=i.region_id " +
                "WHERE stu_gradu_time =DATE_FORMAT(NOW(), '%Y') " +
                "AND region_name NOT LIKE CONCAT('%','长沙','%') " +
                "AND region_name NOT LIKE CONCAT('%','北京','%') " +
                "AND region_name NOT LIKE CONCAT('%','上海','%') " +
                "AND region_name NOT LIKE CONCAT('%','深圳','%') " +
                "AND region_name NOT LIKE CONCAT('%','广州','%') " +
                "AND region_name NOT LIKE CONCAT('%','杭州','%') " +
                "AND region_name NOT LIKE CONCAT('%','成都','%')";
        Integer qt = intentionService.getCount(s);
        region.setQt(qt * 1.0 / total * 100);


        String sql2 = "SELECT count(*) FROM intention i " +
                "LEFT JOIN student s ON i.stu_id=s.stu_id " +
                "WHERE stu_gradu_time =DATE_FORMAT(NOW(), '%Y')";
        SituationJob job = new SituationJob();

        Integer java = intentionService.count(sql, null, "java");
        job.setJava(java * 1.0 / total * 100);

        Integer qd = intentionService.count(sql, null, "前端");
        job.setQd(qd * 1.0 / total * 100);

        String s2 = "SELECT COUNT(*) FROM intention i " +
                "LEFT JOIN student s ON i.stu_id=s.stu_id " +
                "WHERE stu_gradu_time =DATE_FORMAT(NOW(), '%Y') " +
                "AND intention_major NOT LIKE CONCAT('%','java','%') " +
                "AND intention_major NOT LIKE CONCAT('%','前端','%') ";
        Integer qt2 = intentionService.getCount(s2);
        region.setQt(qt2 * 1.0 / total * 100);

        model.addAttribute("region", region);
        model.addAttribute("job", job);



        return "resume_situation_analysis";
    }
}
