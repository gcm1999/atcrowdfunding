package com.atguigu.scw.user.exp;

import com.atguigu.scw.user.enums.UserExceptionEnum;

public class UserException extends RuntimeException { //RuntimeException异常会事务自动回滚

	public UserException() {}
	
	public UserException(UserExceptionEnum enums) {
		super(enums.getMessage());
	}
	
}
