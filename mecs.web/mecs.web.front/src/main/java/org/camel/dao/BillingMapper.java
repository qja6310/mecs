package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mecs.camel.model.Account;
import mecs.camel.model.Combo;
import mecs.camel.model.Items;
import mecs.camel.model.ItemsRecord;
import mecs.camel.model.Log;
import mecs.camel.model.MecRecord;
import mecs.camel.model.User;
import mecs.camel.model.dto.ItemsDto;

public interface BillingMapper {

	/**
	 * --------------------------------------------邱-----------------------------------------
	 * 根据卡号获取卡的余额
	 * @param cardNum
	 * @return
	 */
	String getCardBalance(@Param("cardNum") String cardNum);
	
	/**
	 * -------------------------------------邱-------------------------------------------
	 * 根据卡号获取体检人信息
	 * @param cardNum
	 * @return
	 */
	User getUserInfoByCardNum(@Param("cardNum") String cardNum);
	
	/**
	 * 条件获取套餐
	 * @param comboName
	 * @return
	 */
	List<Combo> getAllComboInfo(@Param("comboName") String comboName,@Param("comboState") String comboState);
	
	/**
	 * 根据套餐ID获取某个套餐的详情
	 * @param comboId
	 * @return
	 */
	List<ItemsDto> getDetailsByComboId(@Param("comboId") String comboId);
	
	/**
	 * 获取体检记录ID
	 * @return
	 */
	String getMecRecordId();
	
	/**
	 * 检查体检码是否重复
	 * @param mecNum
	 * @return
	 */
	String checkMecNumber(@Param("mrNum") String mrNum);
	
	/**
	 * 根据套餐ID获取套餐名
	 * @param comboIdList
	 * @return
	 */
	List<String> getConboName(@Param("comboIdList") List<String> comboIdList);
	
	/**
	 * 添加体检记录
	 * @param comboIdList
	 * @param cardNum
	 * @param adminId
	 * @return
	 */
	Integer addMecRecord(MecRecord mecRecord);
	
	/**
	 * 根据卡号获取卡ID
	 * @param cardNum
	 * @return
	 */
	String getCardIdByCardNum(@Param("cardNum") String cardNum);
	
	/**
	 * 批量查找项目,获取项目名和项目价格
	 * @param comboIdList
	 * @return
	 */
	List<Items> getItemsNameByComboId(@Param("comboIdList") List<String> comboIdList);
	
	/**
	 * 批量插入项目记录表
	 * @param itemsDtoList
	 * @param mrId
	 * @return
	 */
	Integer addItemsRecord(Items items);
	
	/**
	 * 插入日志表
	 * @param mgrLog
	 * @return
	 */
	Integer addFrontLog(Log frontLog);
	
	/**
	 * 根据卡号获取用户的体检记录
	 * @param cardNum
	 * @return
	 */
	List<MecRecord> getMecRecordList(@Param("cardIdList") List<String> cardIdList);
	
	/**
	 * 根据卡号获取用户的ID,再根据用户的ID获取卡ID
	 * @param cardNum
	 * @return
	 */
	List<String> getCardIdByUserId(@Param("cardNum") String cardNum);
	
	/**
	 * 根据体检报告获取用户信息
	 * @param mrId
	 * @return
	 */
	User getUserByMrId(@Param("mrNum") String mrNum);
	
	/**
	 * 根据体检ID获取开单时间
	 * @param mrId
	 * @return
	 */
	String getBilTimeByMrId(@Param("mrNum") String mrNum);
	
	/**
	 * 根据体检记录获取项目的一些信息
	 * @param mrId
	 * @return
	 */
	List<Items> getItemsInfoByMrId(@Param("mrNum") String mrNum);
	
	/**
	 * 修改卡上余额
	 * @param cardNum
	 * @param newBalance
	 * @return
	 */
	Integer changeCardBalance(@Param("cardNum") String cardNum,@Param("newBalance") Integer newBalance);
	
	/**
	 * 修改体检记录的状态
	 * @param mrId
	 * @return
	 */
	Integer changeMecRecordState(@Param("mrId") String mrId);
	
	/**
	 * 修改项目记录状态
	 * @param mrId
	 * @return
	 */
	Integer changeItemsRecordState(@Param("mrId") String mrId);
	
//	Integer addAccount(@Param("cardNum") String cardNum,@Param("remark") String remark, @Param("mrPrice") String mrPrice);
}
