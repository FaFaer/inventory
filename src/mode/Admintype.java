 package mode;

import java.io.Serializable;

public class   Admintype implements Serializable{	
private int  adminTypeId;	
private String  adminPermission;	
private String  adminDepartment;	
Admintype() {

}
	public int  getAdminTypeId() {	
  return adminTypeId;
	}
	public void   setAdminTypeId(int adminTypeId) {
	this.adminTypeId=adminTypeId;
	}
	public String  getAdminPermission() {	
  return adminPermission;
	}
	public void   setAdminPermission(String adminPermission) {
	this.adminPermission=adminPermission;
	}
	public String  getAdminDepartment() {	
  return adminDepartment;
	}
	public void   setAdminDepartment(String adminDepartment) {
	this.adminDepartment=adminDepartment;
	}	
}