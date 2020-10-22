package com.tensquare.controller;


import com.tensquare.model.Reply;
import com.tensquare.service.ReplyService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    //查询全部回答
    @GetMapping
    public ResultObject findAll(){
        List<Reply> list = replyService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询回答
    @GetMapping("/{replyId}")
    public ResultObject findById(@PathVariable String replyId){
        Reply reply  = replyService.findById(replyId);
        return new ResultObject(StatusCode.OK,"根据id查询成功",true,reply);
    }
    //添加回答
    @PostMapping
    @ResponseBody
    public ResultObject add(@RequestBody Reply reply){
        replyService.add(reply);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }
    //修改回答
    @PutMapping(value = "/{replyId}")
    public ResultObject update(@RequestBody Reply reply,@PathVariable String replyId){
        reply.setId(replyId);
        replyService.update(reply);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }
    //删除回答
    @DeleteMapping("/{replyId}")
    public ResultObject delete(@PathVariable String replyId){
        replyService.delete(replyId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }
    //根据问题id查询回答列表
    @GetMapping("/problem/{problemId}")
    public ResultObject findByproblemId(@PathVariable String problemId){
        List<Reply> replies = replyService.findByproblemId(problemId);
        return new ResultObject(StatusCode.OK,"根据问题id查询回答列表成功",true,replies);
    }

    //模糊分页回答
    @PostMapping("/search/{page}/{size}")
    public ResultObject searchPage(@RequestBody Reply reply,@PathVariable Integer page,@PathVariable Integer size){
        Page page1 = replyService.searchPage(reply,page,size);
        return new ResultObject(StatusCode.OK,
                "分页成功",
                true,
                new PageResult<Reply>(page1.getTotalElements(),
                        page1.getContent()));
    }

    //回答问题
    @PostMapping("save")
    @ResponseBody
    public ResultObject save(@RequestBody Reply reply){
        replyService.save(reply);
        return new ResultObject(StatusCode.OK,"回答问题成功",true);
    }

    //我的回答列表
    @GetMapping("/list/{page}/{size}")
    public ResultObject findAllByPageandSize(@PathVariable Integer page,@PathVariable Integer size){
        Page<Reply> page1 = replyService.findAllByPageandSize(page,size);
        return new ResultObject(StatusCode.OK,
                "分页成功",
                true,
                new PageResult<Reply>(page1.getTotalElements(),
                        page1.getContent()));
    }
}
