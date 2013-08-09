package com.sushant.verma.common.constants;

import com.sushant.verma.common.dto.FeedDto;

public class FeedConstants {

	public static String getNewModelItemNode(FeedDto feedDto){
		StringBuilder newModelItemNode=new StringBuilder();
		newModelItemNode.append("<item>");
		newModelItemNode.append("	<guid isPermaLink='false'>"+feedDto.getGuid()+"</guid>");
		newModelItemNode.append("	<link>"+feedDto.getLink()+"</link>");
		newModelItemNode.append("	<pubDate>"+feedDto.getPubDate()+"</pubDate>");
		newModelItemNode.append("	<author>"+feedDto.getAuthor()+"</author>");
		newModelItemNode.append("	<title>"+feedDto.getTitle()+"</title>");
		newModelItemNode.append("	<description>");
		newModelItemNode.append("	&lt;div &gt;");
		newModelItemNode.append("		&lt;div style='float:left'&gt;");
		newModelItemNode.append("			&lt;img alt='"+feedDto.getModelName()+"' src=\""+feedDto.getModelImageUrl()+"\" &gt;");
		newModelItemNode.append("	&lt;div &gt;&lt;a href=\""+feedDto.getLink()+"\"&gt;"+feedDto.getModelName()+"&lt;/a&gt;&lt;/div&gt;");
		newModelItemNode.append("		&lt;/div&gt;");
		newModelItemNode.append("		&lt;div &gt;");
		newModelItemNode.append("			&lt;div &gt;&lt;strong&gt;Brief Detail &lt;/strong&gt;"+feedDto.getDescription()+"&lt;/div&gt;");
		newModelItemNode.append("			&lt;div &gt;&lt;strong&gt;Launch Date  &lt;/strong&gt;"+feedDto.getLaunchDate()+"&lt;/div&gt;");
		newModelItemNode.append("			&lt;div &gt;&lt;strong&gt;Price (MRP) &lt;/strong&gt; Rs."+feedDto.getPrice()+"&lt;/div&gt;");
		newModelItemNode.append("		&lt;/div&gt;");
		newModelItemNode.append("	&lt;/div&gt;");
		newModelItemNode.append("	</description>");
		newModelItemNode.append("</item>");
		return newModelItemNode.toString();
	}
}
