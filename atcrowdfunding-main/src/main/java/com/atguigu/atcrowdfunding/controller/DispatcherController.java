package com.atguigu.atcrowdfunding.controller;


import java.awt.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
public class DispatcherController {

	Logger log = LoggerFactory.getLogger(DispatcherController.class);
	
	@Autowired
	TMenuService menuService;
	
	
	@Autowired
	TAdminService adminService;

	@RequestMapping("/index")
	public String index() {
		log.debug("跳转到系统主页面...");

		return "index";
	}

	@RequestMapping("/toLogin")
	public String login() {
		log.debug("跳转到登录主页面...");
		return "login";
	}
	
	@RequestMapping("/main")
	public String main(HttpSession session) {
		log.debug("跳转到后台系统main主页面...");
		
		if(session == null) {
			return "redirect:/toLogin";
		}
		
		//存放父菜单
		List<TMenu> menuList = (List<TMenu>)session.getAttribute("menuList");
		
		if(menuList == null) {
			menuList = menuService.listMenuAll();
			session.setAttribute("menuList", menuList);
		}
		
		
		return "main";
	}
	
/*	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		log.debug("注销系统...");
		
		if(session != null) {
			session.removeAttribute(Const.LOGIN_ADMIN);
			session.invalidate();
		}
		
		return "redirect:/index";
	}*/

	/*@RequestMapping("/doLogin")
	public String doLogin(String loginacct,String userpwd,HttpSession session,Model model) {
		log.debug("开始登陆...");
		
		log.debug("loginacct={}",loginacct);
		log.debug("userpwd={}",userpwd);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpwd", userpwd);
		

		try {
			TAdmin admin = adminService.getTAdminByLogin(paramMap);
			session.setAttribute(Const.LOGIN_ADMIN, admin);
			log.debug("登陆成功...");
//			return "main";
			return "redirect:/main";
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登陆失败...",e.getMessage());
			model.addAttribute("message",e.getMessage());
			return "login";
		}
		
	}*/

}
