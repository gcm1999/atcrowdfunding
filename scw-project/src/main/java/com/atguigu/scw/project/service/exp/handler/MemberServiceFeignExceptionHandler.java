package com.atguigu.scw.project.service.exp.handler;

import org.springframework.stereotype.Component;

import com.atguigu.scw.project.service.MemberServiceFeign;
import com.atguigu.scw.project.vo.resp.TMember;
import com.atguigu.scw.vo.resp.AppResponse;

@Component
public class MemberServiceFeignExceptionHandler implements MemberServiceFeign {

	@Override
	public AppResponse<TMember> getMemberById(Integer id) {
		AppResponse<TMember> resp = AppResponse.fail(null);
		resp.setMsg("远程调用服务【获取用户信息】失败");
		return resp;
	}

}
