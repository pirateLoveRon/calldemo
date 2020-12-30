package com.demo.calldemo.controller;

import com.demo.calldemo.entity.TabEmployeeInfo;
import com.demo.calldemo.service.TabEmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class TabEmployeeInfoController {

    @Autowired
    private TabEmployeeInfoService tabEmployeeInfoService;

    @GetMapping(value = "/list/employeeName/{employeeName}")
    public List<TabEmployeeInfo> findListByEmployeeName(@PathVariable String employeeName) {
        return tabEmployeeInfoService.findListByEmployeeName(employeeName);
    }

}
