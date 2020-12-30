package com.demo.calldemo.service;

import com.demo.calldemo.entity.TabEmployeeInfo;
import com.demo.calldemo.mapper.TabEmployeeInfoMapper;
import com.demo.calldemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabEmployeeInfoService {

    @Autowired
    private TabEmployeeInfoMapper tabEmployeeInfoMapper;
    @Autowired
    private RedisUtil redisUtil;

    public List<TabEmployeeInfo> findListByEmployeeName(String employeeName) {
        List<TabEmployeeInfo> list = tabEmployeeInfoMapper.findListByEmployeeName(employeeName);
        redisUtil.set("findListByEmployeeName", list, 30);
        return list;
    }

}
