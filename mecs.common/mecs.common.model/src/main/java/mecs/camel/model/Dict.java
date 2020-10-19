	package mecs.camel.model;
/**
 * 字典model
 * @author Administrator
 *
 */
public class Dict {
	private String dictId;//字典id
	private String dictCode;//字典码
	private String dictName;//字典名
	private String dictType;//类型
	private String dictMemo;//备注
	private String dictCtime;//创建时间
	private String dictUtime;//改变时间
	public Dict() {
	}
	public Dict(String dictId, String dictCode, String dictName, String dictType, String dictMemo, String dictCtime,
			String dictUtime) {
		super();
		this.dictId = dictId;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.dictType = dictType;
		this.dictMemo = dictMemo;
		this.dictCtime = dictCtime;
		this.dictUtime = dictUtime;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictMemo() {
		return dictMemo;
	}
	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	public String getDictCtime() {
		return dictCtime;
	}
	public void setDictCtime(String dictCtime) {
		this.dictCtime = dictCtime;
	}
	public String getDictUtime() {
		return dictUtime;
	}
	public void setDictUtime(String dictUtime) {
		this.dictUtime = dictUtime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dict [dictId=");
		builder.append(dictId);
		builder.append(", dictCode=");
		builder.append(dictCode);
		builder.append(", dictName=");
		builder.append(dictName);
		builder.append(", dictType=");
		builder.append(dictType);
		builder.append(", dictMemo=");
		builder.append(dictMemo);
		builder.append(", dictCtime=");
		builder.append(dictCtime);
		builder.append(", dictUtime=");
		builder.append(dictUtime);
		builder.append("]");
		return builder.toString();
	}
	
    
}
