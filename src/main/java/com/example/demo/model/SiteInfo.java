package com.example.demo.model;

import java.util.List;

public class SiteInfo {
    public List<ItemSite> itemSite;

    public List<ItemSite> getItemSite() {
        return itemSite;
    }

    public void setItemSite(List<ItemSite> itemSite) {
        this.itemSite = itemSite;
    }

	@Override
	public String toString() {
		return "SiteInfo [itemSite=" + itemSite + ", getItemSite()=" + getItemSite() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
}
