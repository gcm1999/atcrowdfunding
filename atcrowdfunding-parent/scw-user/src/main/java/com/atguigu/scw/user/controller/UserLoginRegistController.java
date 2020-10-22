package com.atguigu.scw.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.user.component.SmsTemplate;
import com.atguigu.scw.user.service.TMemberService;
import com.atguigu.scw.user.vo.req.UserRegistVo;
import com.atguigu.scw.user.vo.resp.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用户登陆注册模块")
@RequestMapping("/user")
@RestController
public class UserLoginRegistController {

	@Autowired
	SmsTemplate smsTemplatel;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	TMemberService memberService;

	 @ApiOperation(value="用户登陆")
	 @ApiImplicitParams(value={
	 @ApiImplicitParam(value="登陆账号",name="loginacct"),
	 @ApiImplicitParam(value="用户密码",name="password")
	 })
	 @PostMapping("/login")
	 public AppResponse<UserRespVo> login(@RequestParam("loginacct") String loginacct,@RequestParam("password") String password){
		 
		  try {
			UserRespVo vo = memberService.getUserByLogin(loginacct, password);
			log.debug("登陆成功-{}",loginacct);
			 
			 return AppResponse.ok(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("登陆失败-{}-{}",loginacct,e.getMessage());
			AppResponse<UserRespVo> fail = AppResponse.fail(null);
			fail.setMsg(e.getMessage());
			return fail;
		}
	 }

	@ApiOperation(value = "用户注册")
	@PostMapping("/register")
	public AppResponse<Object> register(UserRegistVo vo) {

		String loginacct = vo.getLoginacct();

		if (!StringUtils.isEmpty(loginacct)) {

			String code = stringRedisTemplate.opsForValue().get(loginacct);

			if (!StringUtils.isEmpty(code)) {

				if (code.equals(vo.getCode())) {

					// 验证账号是否唯一

					// 验证email地址是否被占用

					// 保存数据
					int i = memberService.saveTMember(vo);
					if(i == 1) {
						stringRedisTemplate.delete(loginacct);//手动清理缓存
						return AppResponse.ok("ok");
					}else {
						return AppResponse.fail(null);
					}

				} else {
					AppResponse<Object> fail = AppResponse.fail(null);
					fail.setMsg("验证码错误！");

					return fail;
				}

			} else {
				AppResponse<Object> fail = AppResponse.fail(null);
				fail.setMsg("验证码已失效！");

				return fail;
			}
		} else {
			AppResponse<Object> fail = AppResponse.fail(null);
			fail.setMsg("用户名称不能为空！");

			return fail;
		}

	}

	@ApiOperation(value = "发送短信验证码")
	@PostMapping("/sendsms")
	public AppResponse<Object> sendsms(String loginacct) {

		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			code.append(new Random().nextInt(10));
		}

		Map<String, String> querys = new HashMap<String, String>();
		querys.put("mobile", loginacct);
		querys.put("param", "code:" + code.toString());
		querys.put("tpl_id", "TP1711063");

		smsTemplatel.sendSms(querys);

		// stringRedisTemplate.opsForValue().set(loginacct, code.toString());
		stringRedisTemplate.opsForValue().set(loginacct, code.toString(), 5, TimeUnit.MINUTES); // 超时时间

		log.debug("验证码：{}", code.toString());

		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "验证短信验证码")
	@PostMapping("/valide")
	public AppResponse<Object> valide() {
		return AppResponse.ok("ok");
	}

	@ApiOperation(value = "重置密码")
	@PostMapping("/reset")
	public AppResponse<Object> reset() {
		return AppResponse.ok("ok");
	}

}
