package com.example.demo.model;

import java.util.List;

public class ArticleStockinfo {
    public List<Item> item;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

	@Override
	public String toString() {
		return "ArticleStockinfo [item=" + item + ", getItem()=" + getItem() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
}
