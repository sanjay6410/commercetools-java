package com.example.demo.model;

public class ArticleStockDetails {
    public ArticleStockinfo article_stockinfo;

    public ArticleStockinfo getArticle_stockinfo() {
        return article_stockinfo;
    }

    public void setArticle_stockinfo(ArticleStockinfo article_stockinfo) {
        this.article_stockinfo = article_stockinfo;
    }

	@Override
	public String toString() {
		return "ArticleStockDetails [article_stockinfo=" + article_stockinfo + ", getArticle_stockinfo()="
				+ getArticle_stockinfo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
    
}
