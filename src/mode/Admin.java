package mode;

import java.io.Serializable;

public class Admin implements Serializable{
	private int adminId;
	private String adminName;
	private String adminPassword;
	private int adminTypeId;

	Admin() {

	}

	public Admin(int adminId, String adminName) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
	}

	public Admin(String adminName, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getAdminTypeId() {
		return adminTypeId;
	}

	public void setAdminTypeId(int adminTypeId) {
		this.adminTypeId = adminTypeId;
	}
}