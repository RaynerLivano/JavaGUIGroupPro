public class User extends Main {
	private String userID;
	private String userName;
	private String password;
	private String role;
	private String address;
	private Integer phoneNo;
	private String gender;
	
	public User(String userID, String userName, String password, String role, String address, Integer phoneNo,
			String gender) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.address = address;
		this.phoneNo = phoneNo;
		this.gender = gender;
	}
	
	public String UserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	

}
