package mecs.camel.model.dto;

import java.util.List;

import mecs.camel.model.ItemsRecord;
import mecs.camel.model.MecRecord;

/**
 * 用于显示体检记录

     * <p>Title : MecRecordDto</p>

     * <p>Description : </p>

     * <p>DevelopTools : Eclipse_x64_8.5</p>

     * <p>DevelopSystem : macOS Sierra 10.12.1</p>

     * <p>Company : org.camel</p>

     * @author : camel

     * @date : 2019年6月19日 上午10:46:51

     * @version : 0.0.1
 */
public class MecRecordDto extends MecRecord {

	private List<ItemsRecord> irList;//项目记录对象

	public MecRecordDto() {
		super();
	}

	public MecRecordDto(List<ItemsRecord> irList) {
		super();
		this.irList = irList;
	}

	public List<ItemsRecord> getIrList() {
		return irList;
	}

	public void setIrList(List<ItemsRecord> irList) {
		this.irList = irList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MecRecordDto [irList=");
		builder.append(irList);
		builder.append("]");
		return builder.toString();
	}
	
	
}
