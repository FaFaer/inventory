 package mode;

import java.io.Serializable;
import java.util.Date;

public class   Income implements Serializable{	
private int  incomeId;	
private int  incomeNumber;	
private Date  incomeTime;	
private int  supplierId;	
private int  adminId;	
private int  produceId;	
private int  wearPosition;	
Income() {

}
	public Income(int incomeNumber, Date incomeTime, int supplierId, int adminId, int produceId, int wearPosition) {
	super();
	this.incomeNumber = incomeNumber;
	this.incomeTime = incomeTime;
	this.supplierId = supplierId;
	this.adminId = adminId;
	this.produceId = produceId;
	this.wearPosition = wearPosition;
}
	public int  getIncomeId() {	
  return incomeId;
	}
	public void   setIncomeId(int incomeId) {
	this.incomeId=incomeId;
	}
	public int  getIncomeNumber() {	
  return incomeNumber;
	}
	public void   setIncomeNumber(int incomeNumber) {
	this.incomeNumber=incomeNumber;
	}
	public Date  getIncomeTime() {	
  return incomeTime;
	}
	public void   setIncomeTime(Date incomeTime) {
	this.incomeTime=incomeTime;
	}
	public int  getSupplierId() {	
  return supplierId;
	}
	public void   setSupplierId(int supplierId) {
	this.supplierId=supplierId;
	}
	public int  getAdminId() {	
  return adminId;
	}
	public void   setAdminId(int adminId) {
	this.adminId=adminId;
	}
	public int  getProduceId() {	
  return produceId;
	}
	public void   setProduceId(int produceId) {
	this.produceId=produceId;
	}
	public int  getWearPosition() {	
  return wearPosition;
	}
	public void   setWearPosition(int wearPosition) {
	this.wearPosition=wearPosition;
	}	
}