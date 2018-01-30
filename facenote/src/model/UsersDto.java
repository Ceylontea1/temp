package model;

public class UsersDto {
	private String email;
	private String password;
	private String name;
	private int age;
	private String gender;
	private String birth;
	private String phone;
	private String imagepath;
	private String regdate;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String eamil) {
		this.email = eamil;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getimagepath() {
		return imagepath;
	}
	public void setimagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
