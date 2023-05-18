package com.example.demo.model;

public class ItemSite {
    public String site;
    public int qty;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

	@Override
	public String toString() {
		return "ItemSite [site=" + site + ", qty=" + qty + ", getSite()=" + getSite() + ", getQty()=" + getQty()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
}
