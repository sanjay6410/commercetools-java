package com.example.demo.model;

import java.util.Arrays;

public class PricingSection {

	private PricingSectionItem[] item;

	public PricingSection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PricingSection(PricingSectionItem[] item) {
		super();
		this.item = item;
	}

	@Override
	public String toString() {
		return "PricingSection [item=" + Arrays.toString(item) + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public PricingSectionItem[] getItem() {
		return item;
	}

	public void setItem(PricingSectionItem[] item) {
		this.item = item;
	}
	
	
}
