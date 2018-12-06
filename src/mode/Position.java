package mode;

import java.io.Serializable;

public class Position implements Serializable{
	private int positioninNumber;
	private int positionId;
	private int produceId;
	private int areaId;
	private int wearId;
	private int positionAId;

	public int getPositionAId() {
		return positionAId;
	}


	public void setPositionAId(int positionAId) {
		this.positionAId = positionAId;
	}


	public Position(int positioninNumber, int positionId, int produceId, int areaId, int wearId) {
		super();
		this.positioninNumber = positioninNumber;
		this.positionId = positionId;
		this.produceId = produceId;
		this.areaId = areaId;
		this.wearId = wearId;
	}

	public Position(int positioninNumber, int positionId, int produceId, int areaId, int wearId, int positionAId) {
		super();
		this.positioninNumber = positioninNumber;
		this.positionId = positionId;
		this.produceId = produceId;
		this.areaId = areaId;
		this.wearId = wearId;
		this.positionAId = positionAId;
	}

	public Position() {
	}

	public int getPositioninNumber() {
		return positioninNumber;
	}

	public void setPositioninNumber(int positioninNumber) {
		this.positioninNumber = positioninNumber;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getProduceId() {
		return produceId;
	}

	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getWearId() {
		return wearId;
	}

	public void setWearId(int wearId) {
		this.wearId = wearId;
	}

}