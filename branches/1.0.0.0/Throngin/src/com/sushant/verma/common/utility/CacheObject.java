package com.sushant.verma.common.utility;

import java.util.Date;

public class CacheObject {
	public Date createdDate;
	public Date lastAccessedDate;
	public Object object;

	public CacheObject(Object obj){
		object=obj;
		createdDate=new Date();
		lastAccessedDate=new Date();
	}

	public String toString(){
		return "Created:"+createdDate+" LastAccessed:"+lastAccessedDate+" Object:"+object;
	}
}
