 package mode;

import java.io.Serializable;
import java.util.Date;

public class   Outlibrary implements Serializable{	
private int  outLibraryId;	
private int  outLibraryNumber;	
private Date outLibraryTime;	
private int  dealerId;	
private int  produceId;	
private int  adminId;	
private int  wearPosition;	
Outlibrary() {

}
	public Outlibrary(int outLibraryNumber, Date outLibraryTime, int dealerId, int produceId, int adminId,
		int wearPosition) {
	super();
	this.outLibraryNumber = outLibraryNumber;
	this.outLibraryTime = outLibraryTime;
	this.dealerId = dealerId;
	this.produceId = produceId;
	this.adminId = adminId;
	this.wearPosition = wearPosition;
}
	public int  getOutLibraryId() {	
  return outLibraryId;
	}
	public void   setOutLibraryId(int outLibraryId) {
	this.outLibraryId=outLibraryId;
	}

	public int getOutLibraryNumber() {
		return outLibraryNumber;
	}
	public void setOutLibraryNumber(int outLibraryNumber) {
		this.outLibraryNumber = outLibraryNumber;
	}
	public Date getOutLibraryTime() {
		return outLibraryTime;
	}
	public void setOutLibraryTime(Date outLibraryTime) {
		this.outLibraryTime = outLibraryTime;
	}
	public int  getDealerId() {	
  return dealerId;
	}
	public void   setDealerId(int dealerId) {
	this.dealerId=dealerId;
	}

	public int getProduceId() {
		return produceId;
	}
	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}
	public int  getAdminId() {	
  return adminId;
	}
	public void   setAdminId(int adminId) {
	this.adminId=adminId;
	}
	public int  getWearPosition() {	
  return wearPosition;
	}
	public void   setWearPosition(int wearPosition) {
	this.wearPosition=wearPosition;
	}	
}