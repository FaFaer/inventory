 package mode;

import java.io.Serializable;

public class   Wearposition implements Serializable{	
private int  wearPositionId;	
private String  wearPositionDesc;	
private int  position_full;	
private int  wearId;	
private int  wearAreaId;	
Wearposition() {

}
	public Wearposition(int wearPositionId, int position_full, int wearId, int wearAreaId) {
	super();
	this.wearPositionId = wearPositionId;
	this.position_full = position_full;
	this.wearId = wearId;
	this.wearAreaId = wearAreaId;
}
	public Wearposition(String wearPositionDesc, int position_full, int wearId, int wearAreaId) {
	super();
	this.wearPositionDesc = wearPositionDesc;
	this.position_full = position_full;
	this.wearId = wearId;
	this.wearAreaId = wearAreaId;
}
	public Wearposition(int wearPositionId, String wearPositionDesc, int position_full, int wearId, int wearAreaId) {
	super();
	this.wearPositionId = wearPositionId;
	this.wearPositionDesc = wearPositionDesc;
	this.position_full = position_full;
	this.wearId = wearId;
	this.wearAreaId = wearAreaId;
}
	public int  getWearPositionId() {	
  return wearPositionId;
	}
	public void   setWearPositionId(int wearPositionId) {
	this.wearPositionId=wearPositionId;
	}
	public String  getWearPositionDesc() {	
  return wearPositionDesc;
	}
	public void   setWearPositionDesc(String wearPositionDesc) {
	this.wearPositionDesc=wearPositionDesc;
	}
	public int  getPosition_full() {	
  return position_full;
	}
	public void   setPosition_full(int position_full) {
	this.position_full=position_full;
	}
	public int  getWearId() {	
  return wearId;
	}
	public void   setWearId(int wearId) {
	this.wearId=wearId;
	}
	public int  getWearAreaId() {	
  return wearAreaId;
	}
	public void   setWearAreaId(int wearAreaId) {
	this.wearAreaId=wearAreaId;
	}	
}