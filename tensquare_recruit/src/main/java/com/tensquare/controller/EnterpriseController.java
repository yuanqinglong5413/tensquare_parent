package com.tensquare.controller;

import com.tensquare.model.Enterprise;
import com.tensquare.service.EnterpriseService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;
    /*查询全部企业*/
    @GetMapping
    public ResultObject findAll(){
        List<Enterprise> list = enterpriseService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部企业成功",true,list);
    }
    /*根据id查询企业*/
    @GetMapping("/{enterpriseId}")
    public ResultObject findById(@PathVariable String enterpriseId){
        Enterprise enterprise = enterpriseService.findById(enterpriseId);
        return new ResultObject(StatusCode.OK,"根据id查询企业成功",true,enterprise);
    }
    /*添加企业*/
    @PostMapping
    @ResponseBody
    public ResultObject add(@RequestBody Enterprise enterprise){
        enterpriseService.add(enterprise);
        return new ResultObject(StatusCode.OK,"添加企业成功",true);
    }
    /*修改企业*/
    @PutMapping(value = "/{enterpriseId}")
    public ResultObject update(@RequestBody Enterprise enterprise,@PathVariable String enterpriseId){
        enterprise.setId(enterpriseId);
        enterpriseService.update(enterprise);
        return new ResultObject(StatusCode.OK,"修改企业成功",true);
    }
    /*根据id删除企业*/
    @DeleteMapping("/{enterpriseId}")
    public ResultObject delete(@PathVariable String enterpriseId){
        enterpriseService.delete(enterpriseId);
        return new ResultObject(StatusCode.OK,"删除企业成功",true);
    }
    /*模糊,多条件查询*/
    @PostMapping("/search")
    public ResultObject search(@RequestBody Enterprise enterprise){
        List<Enterprise> enterprises = enterpriseService.search(enterprise);
        return new ResultObject(StatusCode.OK,"模糊查询企业成功",true,enterprises);
    }
    /*模糊,分页,多条件查询*/
    @PostMapping("/search/{page}/{size}")
    public ResultObject searchPage(@RequestBody Enterprise enterprise,@PathVariable Integer page,@PathVariable Integer size){
        Page page1 = enterpriseService.searchPage(enterprise,page,size);
        return new ResultObject(StatusCode.OK,"分页成功",true,new PageResult<Enterprise>(page1.getTotalElements(),page1.getContent()));
    }
    /*查询热门企业列表*/
    @GetMapping("/search/hotlist")
    public ResultObject searchIhost(){
        return new ResultObject(StatusCode.OK,"热门企业列表",true,enterpriseService.findByIshot("1"));
    }

}
