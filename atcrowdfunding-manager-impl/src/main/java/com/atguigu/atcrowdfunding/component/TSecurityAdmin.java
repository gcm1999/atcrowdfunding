package com.atguigu.atcrowdfunding.component;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.atguigu.atcrowdfunding.bean.TAdmin;

public class TSecurityAdmin extends User {
	
	TAdmin admin;
	
	public TSecurityAdmin(TAdmin admin, Set<GrantedAuthority> authorities) {
//		super(admin.getLoginacct(), admin.getUserpswd(), authorities);
		super(admin.getLoginacct(), admin.getUserpswd(), true, true, true, true, authorities);
		this.admin = admin;
	}

}
