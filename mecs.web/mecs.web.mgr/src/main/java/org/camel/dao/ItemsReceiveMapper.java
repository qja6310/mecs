package org.camel.dao;
/**
 * 项目接收dao
 * 

     * <p>Title : ItemsReceiveMapper.java</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月18日 下午5:16:29

     * @version : 0.0.1
 */

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.dto.ItemsRecordDto;

public interface ItemsReceiveMapper {
	/**
	 * 通过卡号查询出项目记录
	 * @param cardNum 卡号
	 * @param admindepId 医生的科室id
	 * @param itemsName 项目名字
	 * @param itemsState 项目状态
	 * @param rb 
	 * @return
	 */
	ArrayList<ItemsRecordDto> getItemsRecord(@Param("cardNum")String cardNum,@Param("admindepId")String admindepId,
			@Param("itemsName")String itemsName,@Param("itemsState")String itemsState,RowBounds rb);
	
	/**
	 * 通过卡号查出出项目总记录
	 * @param cardNum 卡号
	 * @param admindepId 医生的科室id
	 * @param itemsName 项目名字
	 * @param itemsState 项目状态
	 * @return
	 */
	int getItemSRecordCount(@Param("cardNum")String cardNum,@Param("admindepId")String admindepId,
			@Param("itemsName")String itemsName,@Param("itemsState")String itemsState);
	/**
	 * 项目接收后修改项目记录表的数据
	 * @param docId
	 * @param irId
	 * @return
	 */
	Integer itemsReceive(@Param("docId")String docId,@Param("irId")String irId);
	/**
	 * 录入项目小结
	 * @param irId  项目记录id
	 * @param result 项目小结
	 * @return
	 */
	Integer addResult(@Param("irId")String irId,@Param("result")String result);
	

}
