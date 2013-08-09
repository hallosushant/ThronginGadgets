package com.sushant.verma.common.dto;

import java.util.Date;


public class FeedDto{

	/*
	 * Channel Nodes
	 */
	private Date lastBuildDate;
	/*
	 * Item nodes
	 */
	private String guid;
	private String link;
	private String pubDate;
	private String author;
	private String title;
	private String description;
	
	private String modelLink;
	private String modelName;
	private String modelImageUrl;
	private String launchDate;
	private String price;
	private String itemXmlNode;
	/*
	 * Getters & Setters
	 */
	public Date getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModelLink() {
		return modelLink;
	}
	public void setModelLink(String modelLink) {
		this.modelLink = modelLink;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelImageUrl() {
		return modelImageUrl;
	}
	public void setModelImageUrl(String modelImageUrl) {
		this.modelImageUrl = modelImageUrl;
	}
	public String getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItemXmlNode() {
		return itemXmlNode;
	}
	public void setItemXmlNode(String itemXmlNode) {
		this.itemXmlNode = itemXmlNode;
	}

	
}