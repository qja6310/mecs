package mecs.camel.model.dto;

import mecs.camel.model.Department;
import mecs.camel.model.Dict;

public class DepartmentDto extends Department{
	private Dict dict;

	public DepartmentDto() {
		super();
	}

	public DepartmentDto(Dict dict) {
		super();
		this.dict = dict;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DepartmentDto [dict=");
		builder.append(dict);
		builder.append("]");
		return builder.toString();
	}
	
	
}
