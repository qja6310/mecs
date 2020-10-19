package mecs.camel.utils;

import java.util.List;

/**
 * 非空判断工具类
 * 
 * <p>
 * Title : IsNullUtil
 * </p>
 * 
 * <p>
 * Description :
 * </p>
 * 
 * <p>
 * DevelopTools : Eclipse_x64_8.5
 * </p>
 * 
 * <p>
 * DevelopSystem : macOS Sierra 10.12.1
 * </p>
 * 
 * <p>
 * Company : org.camel
 * </p>
 * 
 * @author : camel
 * 
 * @date : 2019年6月13日 下午2:36:15
 * 
 * @version : 0.0.1
 */
public class IsNullUtil {

	public static boolean isNull(List<String> list) {
		/**
		 * 判断集合中的每一个元素是否为空 若出现一个空,返回false 全不为空,返回true
		 */
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || "".equals(list.get(i))) {
				return false;
			}
		}
		return true;
	}
}
