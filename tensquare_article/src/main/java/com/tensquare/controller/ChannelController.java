package com.tensquare.controller;


import com.tensquare.model.Channel;
import com.tensquare.service.ChannelService;
import com.tensquare.util.PageResult;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    //查询全部
    @GetMapping
    public ResultObject getAll(){
        //调用业务层
        List<Channel> list = channelService.findAll();
        return new ResultObject(StatusCode.OK,"查询全部成功",true,list);
    }

    //根据id查询
    @GetMapping("/{channelId}")
    public ResultObject findById(@PathVariable String channelId){
        //调用业务层
        Channel channel = channelService.findById(channelId);
        return new ResultObject(StatusCode.OK,"查询id成功",true,channel);
    }

    //添加
    @PostMapping
    public ResultObject save(@RequestBody Channel channel){
        channelService.save(channel);
        return new ResultObject(StatusCode.OK,"添加成功",true);
    }

    //修改
    @PutMapping("/{channelId}")
    public ResultObject updateById(@RequestBody Channel channel,@PathVariable String channelId){
        channel.setId(channelId);
        //调用业务层
        channelService.update(channel);
        return new ResultObject(StatusCode.OK,"修改成功",true);
    }

    //删除
    @DeleteMapping("/{channelId}")
    public ResultObject deleteById(@PathVariable String channelId){
        //调用业务层
        channelService.deleteById(channelId);
        return new ResultObject(StatusCode.OK,"删除成功",true);
    }

    //模糊查询
    @PostMapping("/search")
    public ResultObject search(@RequestBody Channel channel){
        //调用业务层
        List<Channel> list = channelService.search(channel);
        return new ResultObject(StatusCode.OK,"模糊查询成功",true,list);
    }

    //模糊查询分页
    @PostMapping("/search/{pageNo}/{size}")
    public ResultObject searchPage(@RequestBody Channel channel,@PathVariable Integer pageNo,@PathVariable Integer size){
        //调用业务层
        Page<Channel> page = channelService.searchPage(channel,pageNo,size);
        return new ResultObject(StatusCode.OK,
                "模糊查询分页成功",
                true,
                new PageResult<Channel>(page.getTotalElements(),page.getContent()));
    }

}
