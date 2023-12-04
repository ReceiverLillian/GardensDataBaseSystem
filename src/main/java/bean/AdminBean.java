package bean;

public class AdminBean {
	/**
	 * 用户的数据表的bean
	 */
	private int aid;// id
	private int status;// 用来判断是管理员还是读者，读者的值为1，管理员为2
	private String username;// 账号
	private String password;// 密码


	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
