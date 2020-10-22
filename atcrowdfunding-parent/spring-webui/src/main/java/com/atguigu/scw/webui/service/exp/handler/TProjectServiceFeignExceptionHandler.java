package com.atguigu.scw.webui.service.exp.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atguigu.scw.vo.resp.AppResponse;
import com.atguigu.scw.webui.service.TProjectServiceFeign;
import com.atguigu.scw.webui.vo.resp.ProjectDetailVo;
import com.atguigu.scw.webui.vo.resp.ProjectVo;
import com.atguigu.scw.webui.vo.resp.ReturnPayConfirmVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

	@Override
	public AppResponse<List<ProjectVo>> all() {

		AppResponse<List<ProjectVo>> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询首页热点项目】服务失败");
		
		log.error("调用远程【查询首页热点项目】服务失败");
		
		return resp;
	}

	@Override
	public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
		AppResponse<ProjectDetailVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询项目详情】服务失败");
		
		log.error("调用远程【查询项目详情】服务失败");
		
		return resp;
	}

	@Override
	public AppResponse<ReturnPayConfirmVo> returnInfo(Integer projectId, Integer returnId) {
		AppResponse<ReturnPayConfirmVo> resp = AppResponse.fail(null);
		resp.setMsg("调用远程服务【查询确认回报信息】服务失败");
		
		log.error("调用远程【查询确认回报信息】服务失败");
		
		return resp;
	}

}
