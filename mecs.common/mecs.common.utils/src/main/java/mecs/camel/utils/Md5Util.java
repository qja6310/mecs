package mecs.camel.utils;

import org.apache.commons.codec.digest.DigestUtils;

//导包记得是导入code上面的这个架包，不要导入错误了

public class Md5Util {
	public static String encryption(String st) {
		String md5=DigestUtils.md5Hex(st);
		return md5;
	}
	
	//查看mad5加密之后的样子
	public static void main(String[] args) {
		String st=encryption("123456");
		System.out.println(st);
	}

}
	