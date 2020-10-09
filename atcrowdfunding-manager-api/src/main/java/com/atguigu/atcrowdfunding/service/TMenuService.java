package com.atguigu.atcrowdfunding.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.TMenu;

public interface TMenuService {

	List<TMenu> listMenuAll();//组合父子关系
	
	List<TMenu> listMenuAllTree();//不用组合父子关系

	void saveTMenu(TMenu menu);

	TMenu getMenuById(Integer id);

	void updateMenu(TMenu menu);

	void deleteMenu(Integer id);

}
