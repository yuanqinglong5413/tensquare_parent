package com.tensquare.controller;


import com.tensquare.model.Problem;
import com.tensquare.service.ProblemService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    //查询全部问题
    @GetMapping
    public ResultObject findAll(){
        List<Problem> list = problemService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询问题
    public ResultObject findById(@PathVariable String problemId){
        Problem problem  = problemService.findById(problemId);
        return new ResultObject(StatusCode.OK,"根据id查询成功",true,problem);
    }
    //添加问题
    @PostMapping
    @ResponseBody
    public ResultObject add(@RequestBody Problem problem){
        problemService.add(problem);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }
    //修改问题
    @PutMapping(value = "/{problemId}")
    public ResultObject update(@RequestBody Problem problem,@PathVariable String problemId){
        problem.setId(problemId);
        problemService.update(problem);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }
    //删除问题
    @DeleteMapping("/{problemId}")
    public ResultObject delete(@PathVariable String problemId){
        problemService.delete(problemId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }
    //模糊查询问题
    @PostMapping("/search")
    public ResultObject search(@RequestBody Problem problem){
        List<Problem> problems = problemService.search(problem);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,problems);
    }

    //模糊分页问题
    @PostMapping("/search/{page}/{size}")
    public ResultObject searchPage(@RequestBody Problem problem,@PathVariable Integer page,@PathVariable Integer size){
        Page page1 = problemService.searchPage(problem,page,size);
        return new ResultObject(StatusCode.OK,
                "分页成功",
                true,
                new PageResult<Problem>(page1.getTotalElements(),
                        page1.getContent()));
    }
    //最新回答列表
    @GetMapping("/newlist/{label}/{page}/{size}")
    public ResultObject newList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Problem> pages = problemService.newList(label,page,size);
        return new ResultObject(StatusCode.OK,
                "最新回答列表",
                true,
                new PageResult<Problem>(pages.getTotalElements(),
                        pages.getContent()));
    }

    //最热回答列表
    @GetMapping("/hotlist/{label}/{page}/{size}")
    public ResultObject hostList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Problem> pages = problemService.hostList(label,page,size);
        return new ResultObject(StatusCode.OK,
                "最热回答列表",
                true,
                new PageResult<Problem>(pages.getTotalElements(),pages.getContent()));
    }
    //等待回答列表
    @GetMapping("/waitlist/{label}/{page}/{size}")
    public ResultObject waitList(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Problem> pages = problemService.waitList(label,page,size);
        return new ResultObject(StatusCode.OK,
                "等待回答列表",
                true,
                new PageResult<Problem>(pages.getTotalElements(),pages.getContent()));
    }
    //问题分页
    @PostMapping("/all/{label}/{page}/{size}")
    public ResultObject getAll(@PathVariable String label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Problem> pages = problemService.getAll(label,page,size);
        return new ResultObject(StatusCode.OK,
                "等待回答列表",
                true,
                new PageResult<Problem>(pages.getTotalElements(),pages.getContent()));
    }
}
