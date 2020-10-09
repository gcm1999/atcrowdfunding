package com.atguigu.scw.project.vo.req;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProjectBaseInfoVo extends BaseVo {

	private String projectToken;// 项目的临时token

	private List<Integer> typeids; // 项目的分类id
	private List<Integer> tagids; // 项目的标签id

	private String name;
	private String remark;
	private Long money;
	private Integer day;

	private String headerImage;// 项目头部图片
	private List<String> detailsImage;// 项目详情图片
	
	//发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
}
