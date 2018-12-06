package mode;

import java.io.Serializable;

public class Producetype implements Serializable{
	private int produceTypeId;
	private String produceTypeName;
	private String typeDescribe;

	Producetype() {
		super();
	}

	public Producetype(int produceTypeId, String produceTypeName, String typeDescribe) {
		super();
		this.produceTypeId = produceTypeId;
		this.produceTypeName = produceTypeName;
		this.typeDescribe = typeDescribe;
	}

	public int getProduceTypeId() {
		return produceTypeId;
	}

	public void setProduceTypeId(int produceTypeId) {
		this.produceTypeId = produceTypeId;
	}

	public String getProduceTypeName() {
		return produceTypeName;
	}

	public void setProduceTypeName(String produceTypeName) {
		this.produceTypeName = produceTypeName;
	}

	public String getTypeDescribe() {
		return typeDescribe;
	}

	public void setTypeDescribe(String typeDescribe) {
		this.typeDescribe = typeDescribe;
	}
}