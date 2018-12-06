 package mode;

import java.io.Serializable;

public class   Wear implements Serializable{	
private int  wearId;	
private String  wearDesc;	
private String  wearAddress;	
private String  wearName;	
private int  adminId;	
Wear() {

}
	public Wear(int wearId, String wearName) {
	super();
	this.wearId = wearId;
	this.wearName = wearName;
}
	public Wear(int wearId, String wearDesc, String wearAddress, String wearName, int adminId) {
	super();
	this.wearId = wearId;
	this.wearDesc = wearDesc;
	this.wearAddress = wearAddress;
	this.wearName = wearName;
	this.adminId = adminId;
}
	public Wear(String wearDesc, String wearAddress, String wearName, int adminId) {
	super();
	this.wearAddress = wearAddress;
	this.wearName = wearName;
	this.adminId = adminId;
}
	public int  getWearId() {	
  return wearId;
	}
	public void   setWearId(int wearId) {
	this.wearId=wearId;
	}
	public String  getWearDesc() {	
  return wearDesc;
	}
	public void   setWearDesc(String wearDesc) {
	this.wearDesc=wearDesc;
	}
	public String  getWearAddress() {	
  return wearAddress;
	}
	public void   setWearAddress(String wearAddress) {
	this.wearAddress=wearAddress;
	}
	public String  getWearName() {	
  return wearName;
	}
	public void   setWearName(String wearName) {
	this.wearName=wearName;
	}
	public int  getAdminId() {	
  return adminId;
	}
	public void   setAdminId(int adminId) {
	this.adminId=adminId;
	}	
}