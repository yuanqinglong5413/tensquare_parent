package com.tensquare.controller;

import com.tensquare.model.Spit;
import com.tensquare.service.SpitService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    SpitService spitService;

    @GetMapping
    public ResultObject findAll(){

        List<Spit> list = spitService.findAll();
        return new ResultObject(StatusCode.OK,"查询成功",true,list);
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public ResultObject findById(@PathVariable String spitId){
        return new ResultObject(StatusCode.OK, "查询成功",true, spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultObject save(@RequestBody Spit spit){
        spitService.save(spit);
        return new ResultObject(StatusCode.OK,"保存成功",true);
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public ResultObject update(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new ResultObject(StatusCode.OK, "修改成功",true);
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public ResultObject delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }

    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public ResultObject findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new ResultObject( StatusCode.OK, "查询成功",true, new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

}

