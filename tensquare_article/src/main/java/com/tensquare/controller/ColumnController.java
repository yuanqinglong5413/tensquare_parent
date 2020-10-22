package com.tensquare.controller;


import com.tensquare.model.Column;
import com.tensquare.service.ColumnService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    ColumnService columnService;

    //查询全部
    @GetMapping
    public ResultObject getAll(){
        //调用业务层
        List<Column> list = columnService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询
    @GetMapping("/{columnId}")
    public ResultObject findById(@PathVariable String columnId){
        //调用业务层
        Column column = columnService.findById(columnId);
        return new ResultObject(StatusCode.OK,"查询id成功",true,column);
    }

    //添加
    @PostMapping
    public ResultObject save(@RequestBody Column column){
        columnService.save(column);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }

    //修改
    @PutMapping("/{columnId}")
    public ResultObject updateById(@RequestBody Column column,@PathVariable String columnId){
        column.setId(columnId);
        //调用业务层
        columnService.update(column);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }

    //删除
    @DeleteMapping("/{columnId}")
    public ResultObject deleteById(@PathVariable String columnId){
        //调用业务层
        columnService.deleteById(columnId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }
    //模糊查询
    @PostMapping("/search")
    public ResultObject search(@RequestBody Column column){
        //调用业务层
        List<Column> list = columnService.search(column);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,list);
    }

    //模糊查询分页
    @PostMapping("/search/{pageNo}/{size}")
    public ResultObject searchPage(@RequestBody Column column,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Column> page = columnService.searchPage(column,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Column>(page.getTotalElements(),page.getContent()));
    }
}
