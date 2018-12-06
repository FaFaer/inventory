package mode;

import java.io.Serializable;

public class ProduceandType implements Serializable{
	private int produceId;
	private String produceName;
	private double producePrice;
	private String produceCode;
	private int produceTypeId;
	private int produceNumber;
	private String produceTypeName;
	private String typeDescribe;

	ProduceandType() {

	}

	public ProduceandType(int produceId, String produceName, double producePrice, String produceCode, int produceTypeId,
			int produceNumber, String produceTypeName, String typeDescribe) {
		super();
		this.produceId = produceId;
		this.produceName = produceName;
		this.producePrice = producePrice;
		this.produceCode = produceCode;
		this.produceTypeId = produceTypeId;
		this.produceNumber = produceNumber;
		this.produceTypeName = produceTypeName;
		this.typeDescribe = typeDescribe;
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

	public int getProduceId() {
		return produceId;
	}

	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public double getProducePrice() {
		return producePrice;
	}

	public void setProducePrice(double producePrice) {
		this.producePrice = producePrice;
	}

	public String getProduceCode() {
		return produceCode;
	}

	public void setProduceCode(String produceCode) {
		this.produceCode = produceCode;
	}

	public int getProduceTypeId() {
		return produceTypeId;
	}

	public void setProduceTypeId(int produceTypeId) {
		this.produceTypeId = produceTypeId;
	}

	public int getProduceNumber() {
		return produceNumber;
	}

	public void setProduceNumber(int produceNumber) {
		this.produceNumber = produceNumber;
	}
}