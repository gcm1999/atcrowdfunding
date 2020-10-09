package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.util.Datas;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class TRoleController {
	
	@Autowired
	TRoleService roleService;
	
	Logger log = LoggerFactory.getLogger(TAdminController.class);
	
	
	@ResponseBody
	@RequestMapping("/role/listPermissionIdByRoleId")
	public List<Integer> listPermissionIdByRoleId(Integer roleId) {
		
		log.debug("roleIddd={}",roleId);
		
		List<Integer> list = roleService.listPermissionIdByRoleId(roleId);
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/role/doAssignPermissionToRole")
	public String doAssignPermissionToRole(Integer roleId, Datas ds) {
		
		log.debug("roleId={}",roleId);
		log.debug("permissionId={}",ds.getIds());
		
		roleService.saveRoleAndPermissionRelationship(roleId, ds.getIds());
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doDelete")
	public String doDelete(Integer id) {
		roleService.deleteTRole(id);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/doUpdate")
	public String doUpdate(TRole role) {
		roleService.updateTRole(role);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/role/getRoleById")
	public TRole getRoleById(Integer id) {
		return roleService.getRoleById(id);
	}
	
	@PreAuthorize("hasRole('PM - 项目经理')")
	@ResponseBody
	@RequestMapping("/role/doAdd")
	public String doAdd(TRole role) {
		roleService.saveTRole(role);
		return "ok";
	}
	
	@RequestMapping("/role/index")
	public String index() {
		
		return "role/index";
	}
	
	
	/**
	 * 启用消息转换器：HttpMessageConverter
	 * 
	 * 如果返回结果为对象(Entity,Class,List,Map..)类型，启用这个转换器->MsppingJackson2HttpMessageConverter  将对象序列化为json串，使用Jackson2组件转换
	 * 如果返回结果为String类型，启用这个转换器->StringHttpMessageConverter  将字符串原样输出。
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/role/loadData")
	public PageInfo<TRole> loadData(@RequestParam(value="pageNum",required=false,defaultValue="1")Integer pageNum,
									@RequestParam(value="pageSize",required=false,defaultValue="2")Integer pageSize,
									@RequestParam(value="condition",required=false,defaultValue="")String condition) {
		
		PageHelper.startPage(pageNum,pageSize);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("condition", condition);
		
		PageInfo<TRole> page = roleService.listRolePage(paramMap);
		
		return page;//转换为json串返回
	}

}
