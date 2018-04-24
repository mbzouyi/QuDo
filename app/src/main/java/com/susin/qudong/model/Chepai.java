package com.susin.qudong.model;

import java.io.Serializable;

public class Chepai implements Serializable{
	public String id;
	public String name;
	public String initial;
	public String parentid;
	public String logo;
	public String depth;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	
}
