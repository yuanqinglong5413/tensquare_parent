package com.tensquare.controller;

import com.tensquare.model.Label;
import com.tensquare.service.BaseService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
public class BaseController {

    @Autowired
    BaseService baseService;

    //查询全部
    @GetMapping
    public ResultObject getAll(){
        //调用业务层
        List<Label> list = baseService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询
    @GetMapping("/{labelId}")
    public ResultObject findById(@PathVariable String labelId){
        //调用业务层
        Label label = baseService.findById(labelId);
        return new ResultObject(StatusCode.OK,"查询id成功",true,label);
    }
    //增加标签
    @PostMapping
    public ResultObject save(@RequestBody Label label){
        //调用业务层
        baseService.save(label);
        return new ResultObject(StatusCode.OK,"增加成功",true);
    }
    //修改标签
    @PutMapping("/{labelId}")
    public ResultObject updateById(@RequestBody Label label,@PathVariable String labelId){
        label.setId(labelId);
        //调用业务层  -- >save
        baseService.update(label);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }
    //删除标签
    @DeleteMapping("/{labelId}")
    public ResultObject deleteById(@PathVariable String labelId){
        //调用业务层  -- >save
        baseService.deleteById(labelId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }
    //模糊查询标签
    @PostMapping("/search")
    public ResultObject search(@RequestBody Label label){
        //调用业务层  -- >save
        List<Label> list = baseService.search(label);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,list);
    }
    //模糊查询分页标签
    @PostMapping("/search/{pageNo}/{size}")
    public ResultObject searchPage(@RequestBody Label label,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层  -- >save
        Page<Label> page = baseService.searchPage(label,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Label>(page.getTotalElements(),page.getContent()));
    }
}
