package mecs.camel.utils;

import java.util.Random;

/**
 * 生成标识码,重要用于生成体检号码

     * <p>Title : CreateCodeUtil</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 上午9:52:17

     * @version : 0.0.1
 */
public class CreateCodeUtil {

	/*
	 * 生成码,根据传参进来,参数为生成几位的码
	 */
	public static String createCode(int length) {
		String code = "";
		Random ran = new Random();//创建随机数对象
		String[] str = {"0","1","2","3","4","5","6","7","8","9"};
		for (int i = 0; i < length; i++) {
			int index = ran.nextInt(10);//获取一个0-9的随机数,作为下标
			code += str[index];
		}
		return code;
	}
}
