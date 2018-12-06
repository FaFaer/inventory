 package mode;

import java.io.Serializable;

public class   Weararea implements Serializable{	
private int  wearAreaId;	
private String  wearAreaDesc;	
private int  wearId;	
Weararea() {

}
	public Weararea(String wearAreaDesc, int wearId) {
	super();
	this.wearAreaDesc = wearAreaDesc;
	this.wearId = wearId;
}
	public Weararea(int wearAreaId, int wearId) {
	super();
	this.wearAreaId = wearAreaId;
	this.wearId = wearId;
}
	public Weararea(int wearAreaId, String wearAreaDesc, int wearId) {
	super();
	this.wearAreaId = wearAreaId;
	this.wearAreaDesc = wearAreaDesc;
	this.wearId = wearId;
}
	public int  getWearAreaId() {	
  return wearAreaId;
	}
	public void   setWearAreaId(int wearAreaId) {
	this.wearAreaId=wearAreaId;
	}
	public String  getWearAreaDesc() {	
  return wearAreaDesc;
	}
	public void   setWearAreaDesc(String wearAreaDesc) {
	this.wearAreaDesc=wearAreaDesc;
	}
	public int  getWearId() {	
  return wearId;
	}
	public void   setWearId(int wearId) {
	this.wearId=wearId;
	}	
}