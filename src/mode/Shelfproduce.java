 package mode;

import java.io.Serializable;

public class   Shelfproduce implements Serializable{	
private int  shelfProduceId;	
private int  produceId;		
Shelfproduce() {

}
	public Shelfproduce(int produceId) {
	super();
	this.produceId = produceId;
}
	public int  getShelfProduceId() {	
  return shelfProduceId;
	}
	public void   setShelfProduceId(int shelfProduceId) {
	this.shelfProduceId=shelfProduceId;
	}
	public int  getProduceId() {	
  return produceId;
	}
	public void   setProduceId(int produceId) {
	this.produceId=produceId;
	}

}