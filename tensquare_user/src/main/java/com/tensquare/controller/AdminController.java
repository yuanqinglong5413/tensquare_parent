package com.tensquare.controller;

import com.tensquare.model.Admin;
import com.tensquare.service.AdminService;
import com.tensquare.util.ResultObject;
import com.tensquare.util.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService;

    /**
     * 增加Admin
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResultObject add(@RequestBody Admin admin ){
        adminService.add(admin);
        return new ResultObject( StatusCode.OK,"增加成功",true);
    }
}
