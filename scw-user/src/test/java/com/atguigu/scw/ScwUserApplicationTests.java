package com.atguigu.scw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() throws SQLException {
		
		Connection conn = dataSource.getConnection();
		
		System.out.println(conn);
		
		conn.close();//归还对象到连接池而不是销毁
		
	}
	
	@Test
	public void test01() {
		
		stringRedisTemplate.opsForValue().set("k1", "v1");
		
	}
	
	@Test
	public void test02() {
		
		String value = stringRedisTemplate.opsForValue().get("k1");
		System.out.println(value);
		
	}

}
