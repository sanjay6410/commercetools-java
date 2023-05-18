package com.example.demo.model;

import java.util.List;

import com.commercetools.api.models.common.LocalizedString;
import com.commercetools.api.models.type.FieldDefinition;
import com.commercetools.api.models.type.ResourceTypeId;

public class CreateCustomType {

	private String key;
	private LocalizedString name;
	private ResourceTypeId[] resourceTypeIds;
	private LocalizedString description;
	private List<FieldDefinition> fieldDefinitions;
	public CreateCustomType(String key, LocalizedString name, ResourceTypeId[] resourceTypeIds,
			LocalizedString description, List<FieldDefinition> fieldDefinitions) {
		super();
		this.key = key;
		this.name = name;
		this.resourceTypeIds = resourceTypeIds;
		this.description = description;
		this.fieldDefinitions = fieldDefinitions;
	}
	public List<FieldDefinition> getFieldDefinitions() {
		return fieldDefinitions;
	}
	public void setFieldDefinitions(List<FieldDefinition> fieldDefinitions) {
		this.fieldDefinitions = fieldDefinitions;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LocalizedString getName() {
		return name;
	}
	public void setName(LocalizedString name) {
		this.name = name;
	}
	public ResourceTypeId[] getResourceTypeIds() {
		return resourceTypeIds;
	}
	public void setResourceTypeIds(ResourceTypeId[] resourceTypeIds) {
		this.resourceTypeIds = resourceTypeIds;
	}
	public LocalizedString getDescription() {
		return description;
	}
	public void setDescription(LocalizedString description) {
		this.description = description;
	}
	public CreateCustomType(String key, LocalizedString name, ResourceTypeId[] resourceTypeIds, LocalizedString description) {
		super();
		this.key = key;
		this.name = name;
		this.resourceTypeIds = resourceTypeIds;
		this.description = description;
	}
	public CreateCustomType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
