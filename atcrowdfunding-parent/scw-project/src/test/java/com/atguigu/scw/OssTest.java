package com.atguigu.scw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class OssTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI4GGUwA9vPP2nByyCz4WX";
		String accessKeySecret = "AfuzmpAxdcgbE7Xl6wFg8gqgklB1GA";

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		// 上传文件流。
		InputStream inputStream = new FileInputStream("D:/亚索.jpg");
		ossClient.putObject("scw202009", "亚索2.jpg", inputStream);

		// 关闭OSSClient。
		ossClient.shutdown();
		
	}

}
