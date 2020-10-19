package org.camel.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.dto.ItemsRecordDto;

public interface BackMoneyMapper {
	/**
	 * 通过卡号获取余额
	 * @param cardNum
	 * @return
	 */
	String getBalanceByCard(@Param("cardNum") String cardNum);
	/**
	 * 通过卡号获得用户名
	 * @param cardNum
	 * @return
	 */
	String getUserNameByCard(@Param("cardNum") String cardNum);
    /**
     * 通过卡号获取所有的体检项目，动态查询：通过项目的状态，分页显示
     * @param cardNum
     * @param itemsState
     * @param rb
     * @return
     */
	ArrayList<ItemsRecordDto> getItemsByCard(@Param("cardNum")String cardNum,@Param("itemsState")String itemsState,RowBounds rb);
	/**
	 * 通过卡号获取项目数量，动态查询：通过项目的状态
	 * @param cardNum
	 * @param itemsState
	 * @return
	 */
	Integer getItemsCountByCard(@Param("cardNum")String cardNum,@Param("itemsState")String itemsState);
	/**
	 * 退款，修改卡与项目关系表 的状态，通过id
	 * @param cardItemsId 卡与项目关系表id
	 * @return
	 */
	Integer modifyItemsStateById(@Param("cardItemsId") Integer cardItemsId);
	/**
	 * 退钱后 ，通过卡号 以及金额，修改卡余额
	 * @param cardNum
	 * @param money
	 * @return
	 */
	Integer modifyBalanceByCard(@Param("cardNum")String cardNum,@Param("money") String money);
	/**
	 * 退费成功后增加对账表数据
	 * @param cardNum
	 * @param money
	 * @return
	 */
	Integer addAccount(@Param("cardNum")String cardNum,@Param("money") String money);
	/**
	 *退费成功后修改退费时间
	 * @param cardItemsId
	 * @return
	 */
	Integer modifyReturnTime(@Param("cardItemsId") Integer cardItemsId);
	/**
	 * 退费成功后修改体检记录中的体检费用
	 * @param cardItemsId
	 * @return
	 */
	Integer modifyMecRecordMoney(@Param("cardItemsId") Integer cardItemsId,@Param("money") String money);
}
