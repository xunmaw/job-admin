package com.xunmaw.graduate.controller;

import com.xunmaw.graduate.entity.NoteState;
import com.xunmaw.graduate.entity.RequireNote;
import com.xunmaw.graduate.entity.Requirement;
import com.xunmaw.graduate.service.NoteStateService;
import com.xunmaw.graduate.service.RequireNoteService;
import com.xunmaw.graduate.service.RequirementService;
import com.xunmaw.graduate.utils.Pager;
import com.xunmaw.graduate.vo.RequireNoteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/note")
public class RequireNoteController {
    @Autowired
    private RequireNoteService requireNoteService;

    @Autowired
    private NoteStateService noteStateService;

    @Autowired
    private RequirementService requirementService;

    @RequestMapping("/selectNoteAllById")
    public String selectNoteAllById(@RequestParam("enterpriseId") String enterpriseId,
                                    @RequestParam(value = "stateId",required = false) String stateId,
                                    @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                    @RequestParam(value = "size",defaultValue = "5")Integer size,
                                     Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
        Pager<RequireNote> pager=new Pager<>();
        String sql1="select count(*) from requirenote where enterprise_id='"+enterpriseId+"'";
        if (stateId!=null&& !stateId.equals("")){
            sql1+=" and state_id='"+stateId+"'";
            model.addAttribute("stateId",stateId);
        }
        Integer total = requireNoteService.getCount(sql1);
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
        String sql="select * from requirenote where enterprise_id='"+enterpriseId+"'";
        if (stateId!=null&& !stateId.equals("")){
            sql+=" and state_id='"+stateId+"'";
        }
        sql+=" limit "+start+","+size;
        List<RequireNote> requireNotes=requireNoteService.listBySqlReturnEntity(sql);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(requireNotes);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        return "enterprise_note_list";
    }

    @RequestMapping("/selectNoteAll")
    public String selectNoteAll(@RequestParam("stuId")String stuId,
                                @RequestParam(value = "stateId",required = false)String stateId,
                                @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                @RequestParam(value = "size",defaultValue = "5")Integer size,
                                Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
        Pager<RequireNote> pager=new Pager<>();
        String sql1="select count(*) from requirenote where stu_id='"+stuId+"' ";
        if (stateId!=null&& !stateId.equals("")){
            sql1+=" and state_id='"+stateId+"'";
            model.addAttribute("stateId",stateId);
        }
        Integer total = requireNoteService.getCount(sql1);
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
        String sql="select * from requirenote where stu_id='"+stuId+"' ";
        if (stateId != null && stateId != "") {
            sql+=" and state_id='"+stateId+"' ";
        }
        sql+=" limit "+start+","+size;
        List<RequireNote> requireNotes = requireNoteService.listBySqlReturnEntity(sql);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(requireNotes);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        return "student_note_list";
    }

    @RequestMapping("/forwardToUpdate/{noteId}")
    public String forwardToUpdate(@PathVariable("noteId")String noteId,Model model){
        RequireNote requireNote = requireNoteService.selectById(noteId);
        List<NoteState> noteStates = noteStateService.listAll();
        model.addAttribute("requireNote",requireNote);
        model.addAttribute("noteStates",noteStates);
        return "enterprise_note_edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestBody RequireNoteVo requireNoteVo){
        RequireNote requireNote=new RequireNote();
        BeanUtils.copyProperties(requireNoteVo,requireNote);
        requireNoteService.updateById(requireNote);
        return "true";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("noteId")String noteId){
        requireNoteService.deleteById(noteId);
        return "true";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(@RequestParam("stuId")String stuId,@RequestParam("requireId")String requireId){
        Requirement requirement = requirementService.selectById(requireId);
        RequireNote requireNote=new RequireNote();
        requireNote.setEnterpriseId(requirement.getEnterpriseId());
        requireNote.setStuId(stuId);
        requireNote.setRequireId(Integer.parseInt(requireId));
        requireNote.setStateId("0");
        requireNote.setNoteDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        requireNoteService.insert(requireNote);
        return "true";
    }

    @RequestMapping("/showAllNote")
    public String showAllNote(@RequestParam(value = "enterpriseId",required = false)String enterpriseId,
                                @RequestParam(value = "stateId",required = false)String stateId,
                                @RequestParam(value = "offset",defaultValue = "1")Integer offset,
                                @RequestParam(value = "size",defaultValue = "5")Integer size,
                                Model model){
        if (size==null||size<=0){
            size=5;
        }
        if (offset==null||offset<=0){
            offset=1;
        }
        Integer begin;
        Integer end;
        Pager<RequireNote> pager=new Pager<>();
        String cSql="select count(*) from requirenote where 1=1 ";
        String sql="select * from requirenote where 1=1 ";
        if (enterpriseId!=null&& !enterpriseId.equals("")){
            cSql+=" and enterprise_id like '%"+enterpriseId+"%' ";
            sql+=" and enterprise_id like '%"+enterpriseId+"%' ";
            model.addAttribute("enterpriseId",enterpriseId);
        }
        if (stateId!=null&& !stateId.equals("")){
            cSql+=" and state_id='"+stateId+"'";
            sql+=" and state_id='"+stateId+"' ";
            model.addAttribute("stateId",stateId);
        }
        Integer total = requireNoteService.getCount(cSql);
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
        sql+=" order by note_id limit "+start+","+size;
        List<RequireNote> requireNotes = requireNoteService.listBySqlReturnEntity(sql);
        pager.setOffset(offset);//当前页码
        pager.setSize(size);//大小
        pager.setTotalCount(total);//总记录数
        pager.setDatas(requireNotes);//数据
        pager.setTotalPage(totalPage);//总页数
        model.addAttribute("pager",pager);
        model.addAttribute("end",end);
        model.addAttribute("begin",begin);
        return "manager_note_list";
    }
}
