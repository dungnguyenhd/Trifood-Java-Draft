package com.tripath.trifood.api.trifood.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;

	String fieldValueString1;

	String fieldValueString2;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString1, String fieldValueString2) {
		super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldValueString1, fieldValueString2));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueString1 = fieldValueString1;
		this.fieldValueString2 = fieldValueString2;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue, String fieldValueString1, String fieldValueString2) {
		super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldValue, fieldValueString1, fieldValueString2));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldValueString1 = fieldValueString1;
		this.fieldValueString2 = fieldValueString2;
	}
	
}