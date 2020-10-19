package org.camel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import mecs.camel.model.Item;
import mecs.camel.model.Items;

public interface ItemsMapper {

	/**
	 * 获取所有项目信息
	 * 
	 * @param itemsName
	 * @param rb
	 * @return
	 */
	List<Items> getAllItems(@Param("name") String itemsName, @Param("dep") String dep, RowBounds rb);

	/**
	 * 获取项目数量
	 * 
	 * @param itemsName
	 * @return
	 */
	Integer getAllItemsCount(@Param("name") String itemsName);

	/**
	 * 删除项目信息
	 * 
	 * @param itemsId
	 * @return
	 */
	Integer delItems(@Param("id") String itemsId);

	/**
	 * 添加项目
	 * 
	 * @param itemsName
	 * @param itemsPrice
	 * @return
	 */
	Integer addItems(Items items);

	/**
	 * 修改项目
	 * 
	 * @param itemsId
	 * @param itemsName
	 * @param itemsPrice
	 * @return
	 */
	Integer alterItems(@Param("id") String itemsId, @Param("name") String itemsName, @Param("price") String itemsPrice,
			@Param("depId") String depId);

	/**
	 * 获取所有细项
	 * 
	 * @return
	 */
	List<Item> getAllItem();

	/**
	 * 查询项目名字是否存在
	 * 
	 * @param itemsName
	 * @return
	 */
	String QueryItemsName(@Param("itemsName") String itemsName);

	/**
	 * 通过id获得项目信息回填
	 * 
	 * @param itemsId
	 * @return
	 */
	Items getItemsById(@Param("itemsId") String itemsId);
	/**
	 * 查看项目
	 * @param itemsId
	 * @return
	 */
	Items lookItemsById(@Param("itemsId") String itemsId);

}
