package org.camel.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Combo;
import mecs.camel.model.Items;
import mecs.camel.model.dto.ItemsDto;

public interface ComboMapper {

	/**
	 * 获取所有套餐信息
	 * 
	 * @param ComboName
	 * @param rb
	 * @return
	 */
	List<Combo> getAllCombo(@Param("name") String ComboName, RowBounds rb);

	/**
	 * 获取套餐数量
	 * 
	 * @param ComboName
	 * @return
	 */
	Integer getAllComboCount(@Param("name") String ComboName);

	/**
	 * 删除套餐
	 * 
	 * @param ComboId
	 * @return
	 */
	Integer delCombo(@Param("id") String ComboId);

	/**
	 * 获取套餐内所有项目
	 * 
	 * @return
	 */

	/**
	 * 获得所有套餐详情
	 * 
	 * @return
	 */
	List<ItemsDto> getDetailsByCombo();

	/**
	 * 修改套餐的状态
	 * 
	 * @param ComboId
	 * @param ComboState
	 * @return
	 */
	Integer alterComboState(@Param("id") String ComboId, @Param("state") String ComboState);

	/**
	 * 增加套餐
	 * 
	 * @param combName
	 * @param price
	 * @return
	 */
	Integer addCombo(Combo combo);

	/**
	 * 增加套餐项目
	 * 
	 * @param comboId
	 * @param itemsList
	 * @return
	 */
	Integer addComboItems(@Param("comboId") String comboId, @Param("itemsList") ArrayList<String> itemsList);

	/**
	 * 查询套餐名是否重复
	 * 
	 * @param comboName
	 * @return
	 */
	String queryComboNameByName(@Param("comboName") String comboName);

	/**
	 * 获得套餐详情
	 * 
	 * @param comboId
	 * @return
	 */
	Combo getComboById(@Param("comboId") String comboId);

	/**
	 * 获得套餐中的项目id
	 * 
	 * @param comboId
	 * @return
	 */
	List<String> getItemsByCombo(@Param("comboId") String comboId);

	/**
	 * 修改套餐
	 * 
	 * @param comboId
	 * @param comboName
	 * @param price
	 * @return
	 */
	Integer updateCombo(@Param("comboId") String comboId, @Param("comboName") String comboName,
			@Param("price") String price);


}
