package org.camel.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
/**
 * 插入日志
 * 

     * <p>Title : RecordMapper.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月15日 下午4:41:50

     * @version : 0.0.1
 */
public interface LogMapper {

	/**
	 * //用户对账时插入日志表记录 操作人id，操作类型，，
	 * @param operId 操作人id
	 * @param operType 操作类型，，
	 * @param logRemark 描述
	 * @return
	 */
	Integer addLog(@Param("operId")int operId,@Param("operType")String operType,@Param("logRemark")String logRemark);

	
	Integer delLog(@Param("logIdList")ArrayList<String> logIdList);
	
}
