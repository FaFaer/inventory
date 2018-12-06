 package mode;

import java.io.Serializable;

public class   Supplier implements Serializable{	
private int  supplierId;	
private String  supplierName;	
private String  supplierCredit;	
private String  supplierTel;	
private String  supplierAddress;	
Supplier() {

}
	public Supplier(String supplierName, String supplierCredit, String supplierTel, String supplierAddress) {
	super();
	this.supplierName = supplierName;
	this.supplierCredit = supplierCredit;
	this.supplierTel = supplierTel;
	this.supplierAddress = supplierAddress;
}
	public int  getSupplierId() {	
  return supplierId;
	}
	public void   setSupplierId(int supplierId) {
	this.supplierId=supplierId;
	}
	public String  getSupplierName() {	
  return supplierName;
	}
	public void   setSupplierName(String supplierName) {
	this.supplierName=supplierName;
	}
	public String  getSupplierCredit() {	
  return supplierCredit;
	}
	public void   setSupplierCredit(String supplierCredit) {
	this.supplierCredit=supplierCredit;
	}
	public String  getSupplierTel() {	
  return supplierTel;
	}
	public void   setSupplierTel(String supplierTel) {
	this.supplierTel=supplierTel;
	}
	public String  getSupplierAddress() {	
  return supplierAddress;
	}
	public void   setSupplierAddress(String supplierAddress) {
	this.supplierAddress=supplierAddress;
	}	
}