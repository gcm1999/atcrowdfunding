package com.atguigu.scw.user.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@ApiModel
public class User {
	
	@ApiModelProperty("用户主键")
	private Integer id;
	
	@ApiModelProperty("用户姓名")
	private String name;


}
