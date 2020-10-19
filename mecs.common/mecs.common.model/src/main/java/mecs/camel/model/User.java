package mecs.camel.model;

import java.io.Serializable;

/**
 * 体检人model
 * 
 * @author Administrator
 *
 */
public class User implements Serializable{

	public String userId;// id
	public String userName;// 姓名
	public String userSex;// 性别
	public String userAge;// 年龄
	public String userBloodType;// 血型
	public String userBirthday;// 出生日期
	public String userPhone;// 电话
	public String userAddress;// 住址
	public String userNativePlace;// 籍贯
	public String userMemo;// 备注
	public String userCtime;// 创建时间
	public String userUtime;// 修改时间

	public User() {
		super();
	}

	public User(String userId, String userName, String userSex, String userAge, String userBloodType,
			String userBirthday, String userPhone, String userAddress, String userNativePlace, String userMemo,
			String userCtime, String userUtime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userSex = userSex;
		this.userAge = userAge;
		this.userBloodType = userBloodType;
		this.userBirthday = userBirthday;
		this.userPhone = userPhone;
		this.userAddress = userAddress;
		this.userNativePlace = userNativePlace;
		this.userMemo = userMemo;
		this.userCtime = userCtime;
		this.userUtime = userUtime;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserBloodType() {
		return userBloodType;
	}

	public void setUserBloodType(String userBloodType) {
		this.userBloodType = userBloodType;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserNativePlace() {
		return userNativePlace;
	}

	public void setUserNativePlace(String userNativePlace) {
		this.userNativePlace = userNativePlace;
	}

	public String getUserMemo() {
		return userMemo;
	}

	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	public String getUserCtime() {
		return userCtime;
	}

	public void setUserCtime(String userCtime) {
		this.userCtime = userCtime;
	}

	public String getUserUtime() {
		return userUtime;
	}

	public void setUserUtime(String userUtime) {
		this.userUtime = userUtime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userSex=");
		builder.append(userSex);
		builder.append(", userAge=");
		builder.append(userAge);
		builder.append(", userBloodType=");
		builder.append(userBloodType);
		builder.append(", userBirthday=");
		builder.append(userBirthday);
		builder.append(", userPhone=");
		builder.append(userPhone);
		builder.append(", userAddress=");
		builder.append(userAddress);
		builder.append(", userNativePlace=");
		builder.append(userNativePlace);
		builder.append(", userMemo=");
		builder.append(userMemo);
		builder.append(", userCtime=");
		builder.append(userCtime);
		builder.append(", userUtime=");
		builder.append(userUtime);
		builder.append("]");
		return builder.toString();
	}

}
