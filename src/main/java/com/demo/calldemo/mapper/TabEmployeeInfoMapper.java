package com.demo.calldemo.mapper;

import com.demo.calldemo.entity.TabEmployeeInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TabEmployeeInfoMapper {

    List<TabEmployeeInfo> findListByEmployeeName(String employeeName);

}
