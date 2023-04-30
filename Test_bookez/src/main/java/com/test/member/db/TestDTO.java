package com.test.member.db;

import java.sql.Date;

public class TestDTO {
	
	// bookez
	private int user_no;
	private String user_id;
	private String pw;
	private String pwre;
	private String name;
	private String nickname;
	private String tel;
	private String email;
	private Date regdate;
	private int status;
	private String photo;
	private String address;
	private int code_num;
	
	public String getPwre() {
		return pwre;
	}
	public void setPwre(String pwre) {
		this.pwre = pwre;
	}
	// Setter/Getter
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCode_num() {
		return code_num;
	}
	public void setCode_num(int code_num) {
		this.code_num = code_num;
	}
	
	@Override
	public String toString() {
		return "TestDTO [user_no=" + user_no + ", user_id=" + user_id + ", pw=" + pw + ", pwre=" + pwre + ", name="
				+ name + ", nickname=" + nickname + ", tel=" + tel + ", email=" + email + ", regdate=" + regdate
				+ ", status=" + status + ", photo=" + photo + ", address=" + address + ", code_num=" + code_num + "]";
	}
	
	
	
	
	
	

}
