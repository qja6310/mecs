package org.camel.dao;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.User;
/**
 * 
 * 
     用户通过卡号登录
     * <p>Title : LoginMapper.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月17日 上午11:58:31

     * @version : 0.0.1
 */
public interface LoginMapper {
	/**
	 * 用户通过卡号登录
	 * @param cardNum
	 * @return
	 */
	User loginByCardNum(@Param("cardNum") String cardNum);

}
