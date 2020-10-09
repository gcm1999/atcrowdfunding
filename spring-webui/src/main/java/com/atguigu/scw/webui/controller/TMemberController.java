package com.atguigu.scw.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TMemberController {
	
	@RequestMapping("/member/minecrowdfunding")
	public String payOrder() {
		
		return "member/minecrowdfunding";
	}

}
