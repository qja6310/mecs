package org.camel.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取综合信息业务

     * <p>Title : MecSynthesizeService</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 下午10:29:08

     * @version : 0.0.1
 */
public interface MecSynthesizeService {

	/**
	 * 获取体检综合信息
	 * @param nowPage
	 * @param sTime
	 * @param eTime
	 * @param userName
	 * @param userTel
	 * @param mecCode
	 * @return
	 */
	JSONObject getMecSynthesize(String nowPage,String sTime,String eTime,String userName,String userTel,String mecCode);
	
}
