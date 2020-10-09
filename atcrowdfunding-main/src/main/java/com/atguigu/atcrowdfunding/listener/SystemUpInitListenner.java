package com.atguigu.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
/**
 * 监听application对象的创建和销毁
 * 
 */
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atguigu.atcrowdfunding.util.Const;

public class SystemUpInitListenner implements ServletContextListener {
	
	Logger log = LoggerFactory.getLogger(SystemUpInitListenner.class);
	
	//当application创建时执行方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		log.debug("当前应用上下文路径：{}",contextPath);
		application.setAttribute(Const.PATH, contextPath);
	}

	//当application创销毁时执行方法
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.debug("当前applicat对象被销毁");
	}

}
