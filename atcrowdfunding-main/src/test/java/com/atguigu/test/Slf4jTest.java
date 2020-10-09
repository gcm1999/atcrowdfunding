package com.atguigu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest {
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Slf4jTest.class);
		/*
		 * log.debug("debug..."); //用于调试程序 log.info("info...");//用于请求处理提示消息
		 * log.warn("warn...");//用于警告处理提示消息 log.error("error...");//用于异常处理提示消息
		 * log.error("==>>"+log.getClass());
		 */
		// class ch.qos.logback.classic.Logger

		log.debug("debug消息id={},name={}", 1, "zhangsan");
	}
}