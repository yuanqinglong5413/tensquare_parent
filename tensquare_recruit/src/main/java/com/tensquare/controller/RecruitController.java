package com.tensquare.controller;


import com.tensquare.model.Recruit;
import com.tensquare.service.RecruitService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    RecruitService recruitService;

    //查询全部
    @GetMapping
    public ResultObject getAll(){
        //调用业务层
        List<Recruit> list = recruitService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询
    @GetMapping("/{recruitId}")
    public ResultObject findById(@PathVariable String recruitId){
        //调用业务层
        Recruit recruit = recruitService.findById(recruitId);
        return new ResultObject(StatusCode.OK,"查询id成功",true,recruit);
    }

    //添加招聘
    @PostMapping
    public ResultObject save(@RequestBody Recruit recruit){
        recruitService.save(recruit);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }

    //修改招聘
    @PutMapping("/{recruitId}")
    public ResultObject updateById(@RequestBody Recruit recruit,@PathVariable String recruitId){
        recruit.setId(recruitId);
        //调用业务层
        recruitService.update(recruit);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }

    //删除招聘
    @DeleteMapping("/{recruitId}")
    public ResultObject deleteById(@PathVariable String recruitId){
        //调用业务层
        recruitService.deleteById(recruitId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }

    //模糊查询招聘
    @PostMapping("/search")
    public ResultObject search(@RequestBody Recruit recruit){
        //调用业务层
        List<Recruit> list = recruitService.search(recruit);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,list);
    }


    //模糊查询分页招聘
    @PostMapping("/search/{pageNo}/{size}")
    public ResultObject searchPage(@RequestBody Recruit recruit,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Recruit> page = recruitService.searchPage(recruit,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Recruit>(page.getTotalElements(),page.getContent()));
    }

    //推荐查询
    @GetMapping("/search/recommend")
    public ResultObject findByRecom(){
        return new ResultObject(StatusCode.OK,"推荐查询成功",true,recruitService.findByRecom("2"));
    }

    //最新查询
    @GetMapping("/search/newlist")
    public ResultObject findByNewRecom(){
        return new ResultObject(StatusCode.OK,"最新查询成功",true,recruitService.findByNewRecom("0"));
    }

}
