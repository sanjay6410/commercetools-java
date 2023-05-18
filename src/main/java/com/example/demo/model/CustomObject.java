package com.example.demo.model;

public class CustomObject {

	private String containerName;
	private String keyCustomObject;
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public String getKeyCustomObject() {
		return keyCustomObject;
	}
	public void setKeyCustomObject(String keyCustomObject) {
		this.keyCustomObject = keyCustomObject;
	}
	public CustomObject(String containerName, String keyCustomObject) {
		super();
		this.containerName = containerName;
		this.keyCustomObject = keyCustomObject;
	}
	public CustomObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
