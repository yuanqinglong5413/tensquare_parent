package com.tensquare.service.impl;

import com.tensquare.model.Admin;
import com.tensquare.repository.AdminRepository;
import com.tensquare.service.AdminService;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    IdWorker idWorker;

    /**
     * 增加Admin
     * 使用SpringSecurity的BCryptPasswordEncoder进行加密
     */
    public void add(Admin admin) {
        admin.setId( idWorker.nextId()+"" );
        //密码加密
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }
}
