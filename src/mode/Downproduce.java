 package mode;

import java.io.Serializable;

public class   Downproduce implements Serializable{	
private int  downProduceId;	
private int  produceId;	
Downproduce() {

}
	public Downproduce(int produceId) {
	super();
	this.produceId = produceId;
}
	public int  getDownProduceId() {	
  return downProduceId;
	}
	public void   setDownProduceId(int downProduceId) {
	this.downProduceId=downProduceId;
	}
	public int  getProduceId() {	
  return produceId;
	}
	public void   setProduceId(int produceId) {
	this.produceId=produceId;
	}	
}