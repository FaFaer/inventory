 package mode;

import java.io.Serializable;

public class   Dealer implements Serializable{	
private int  dealerId;	
private String  dealerName;	
private String  dealerAddress;	
private String  dealerTel;	
Dealer() {

}

	public Dealer(int dealerId, String dealerName, String dealerAddress, String dealerTel) {
	super();
	this.dealerId = dealerId;
	this.dealerName = dealerName;
	this.dealerAddress = dealerAddress;
	this.dealerTel = dealerTel;
}

	public int  getDealerId() {	
  return dealerId;
	}
	public void   setDealerId(int dealerId) {
	this.dealerId=dealerId;
	}
	public String  getDealerName() {	
  return dealerName;
	}
	public void   setDealerName(String dealerName) {
	this.dealerName=dealerName;
	}
	public String  getDealerAddress() {	
  return dealerAddress;
	}
	public void   setDealerAddress(String dealerAddress) {
	this.dealerAddress=dealerAddress;
	}
	public String  getDealerTel() {	
  return dealerTel;
	}
	public void   setDealerTel(String dealerTel) {
	this.dealerTel=dealerTel;
	}	
}