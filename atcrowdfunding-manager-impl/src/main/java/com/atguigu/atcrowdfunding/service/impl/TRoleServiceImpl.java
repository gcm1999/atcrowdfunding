package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TAdminRoleMapper;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;

@Service
public class TRoleServiceImpl implements TRoleService {
	
	@Autowired
	TRoleMapper roleMapper;
	@Autowired
	TAdminRoleMapper adminRoleMapper;
	@Autowired
	TRolePermissionMapper rolePermissionMapper;

	@Override
	public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
		
		String condition = (String) paramMap.get("condition");
		
		TRoleExample example = new TRoleExample();
		example.setOrderByClause("id desc");
		List<TRole> list = null;
		
		if(!StringUtils.isEmpty(condition)) {
			example.createCriteria().andNameLike("%"+condition+"%");
		}
		list = roleMapper.selectByExample(example);
		
		PageInfo<TRole> page = new PageInfo<TRole>(list,5);
		
		return page;
	}
//	@PreAuthorize("hasRole('PM - 项目经理')")
	@Override
	public void saveTRole(TRole role) {
		roleMapper.insertSelective(role);
		
	}

	@Override
	public TRole getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateTRole(TRole role) {
		roleMapper.updateByPrimaryKey(role);
	}

	@Override
	public void deleteTRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TRole> listAllRole() {
		return roleMapper.selectByExample(null);
	}

	@Override
	public List<Integer> getRoleByAdminId(Integer id) {
		return adminRoleMapper.getRoleIdByAdminId(id);
	}

	@Override
	public void saveAdminAndRoleRelationship(Integer[] roleId, Integer adminId) {
		adminRoleMapper.saveAdminAndRoleRelationship(roleId, adminId);
	}

	@Override
	public void deleteAdminAndRoleRelationship(Integer[] roleId, Integer adminId) {
		adminRoleMapper.deleteAdminAndRoleRelationship(roleId, adminId);
	}

	@Override
	public void saveRoleAndPermissionRelationship(Integer roleId, List<Integer> ids) {
		//先删除之前分配过的
		TRolePermissionExample example = new TRolePermissionExample();
		example.createCriteria().andRoleidEqualTo(roleId);
		rolePermissionMapper.deleteByExample(example);
		
		rolePermissionMapper.saveRoleAndPermissionRelationship(roleId, ids);
	}

	@Override
	public List<Integer> listPermissionIdByRoleId(Integer roleId) {
		
		return rolePermissionMapper.listPermissionIdByRoleId(roleId);
	}

}
