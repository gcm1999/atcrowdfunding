package com.atguigu.scw.order.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.atguigu.scw.enums.OrderStatusEnumes;
import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.mapper.TOrderMapper;
import com.atguigu.scw.order.service.TOrderService;
import com.atguigu.scw.order.service.TProjectServiceFeign;
import com.atguigu.scw.order.vo.req.OrderInfoSubmitVo;
import com.atguigu.scw.order.vo.resp.TReturn;
import com.atguigu.scw.util.AppDateUtils;
import com.atguigu.scw.vo.resp.AppResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TOrderServiceImpl implements TOrderService {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	TOrderMapper orderMapper;
	
	@Autowired
	TProjectServiceFeign projectServiceFeign;

	@Override
	public TOrder saveOrder(OrderInfoSubmitVo vo) {
		TOrder order = new TOrder();
		
		String accessToken = vo.getAccessToken();
		String memberId = stringRedisTemplate.opsForValue().get(accessToken);
		
		order.setMemberid(Integer.parseInt(memberId));
		order.setProjectid(vo.getProjectid());
		order.setReturnid(vo.getReturnid());
		order.setOrdernum(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setCreatedate(AppDateUtils.getFormatTime());
		
		AppResponse<TReturn> resp = projectServiceFeign.returnInfo(vo.getReturnid()); //调用远程服务
		TReturn reObj = resp.getData();
		Integer totalMoney = vo.getRtncount() * reObj.getSupportmoney() + reObj.getFreight();
		
		order.setMoney(totalMoney);
		order.setRtncount(vo.getRtncount());
		order.setStatus(OrderStatusEnumes.UNPAY.getCode()+"");
		order.setAddress(vo.getAddress());
		order.setInvoice(vo.getInvoice().toString());
		order.setInvoictitle(vo.getInvoictitle());
		order.setRemark(vo.getRemark());
		
		orderMapper.insertSelective(order);
		
		log.debug("业务层保存订单= {}",order);
		
		return order;
	}

}
