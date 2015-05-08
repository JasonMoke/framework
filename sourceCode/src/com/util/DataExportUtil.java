package com.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.framework.entity.user.UserInfoAndDataManager;
import com.framework.service.user.IUserService;
import com.orm.Page;
import com.util.poi.ExcelView;

@Controller
public class DataExportUtil {
	@Resource(name = "userService")
	private IUserService userService;

	private UserInfoAndDataManager userInfoAndData;

	
	
	@RequestMapping(value = "/springUser/exportUser")
	public ModelAndView exportUser(){
		String filename="export_product_"+new Date().getTime()+".xls"; 
		String sheetName="用户信息"; 
		String[] titles={"用户名","状态","名称"};
		String[] properties={"userName","status","fullName"}; 
		Integer[] widths={5000,2000,5000};
//		Converter[] converters={};
//		List<UserInfoAndDataManager> products = new ArrayList<UserInfoAndDataManager>();
		int pageNo = 1;
		int pageSize = 10;
		Page<UserInfoAndDataManager> page = new Page<UserInfoAndDataManager>();
		page.setPageSize(pageSize);
		page.setCurrentPage(pageNo);
		page = userService.findByCondition("getAllUserList", userInfoAndData, page);
		List<UserInfoAndDataManager> list = new ArrayList<UserInfoAndDataManager>();
		list=page.getResultList();
		Collection<UserInfoAndDataManager> data=list;
 
		String[] contents={};
		ExcelView ev=new ExcelView(filename,sheetName,properties,titles,widths,null,data,contents);
		return new ModelAndView(ev);
	}


}
