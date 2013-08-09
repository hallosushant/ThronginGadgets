package com.sushant.verma.common.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UrlRewriterAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public String addUrlRules() throws ParserConfigurationException, TransformerException, SAXException, IOException {
		System.out.println("addUrlRules");
		ServletContext context = ServletActionContext.getServletContext();
		String filePath=context.getInitParameter("urlrewriteConfPath");
		System.out.println("filePath="+filePath);
		InputStream stream=context.getResourceAsStream(filePath);
		System.out.println("stream="+stream);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Get the DocumentBuilder
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		//Create blank DOM Document
		Document doc = documentBuilder.parse(stream);
		
		NodeList urlrewriteNodeList = doc.getElementsByTagName("urlrewrite");
		Node urlrewrite = urlrewriteNodeList.item(0);
		
		//create the Rule element
		Element ruleElement = doc.createElement("rule");
		urlrewrite.appendChild(ruleElement);
		Element fromElement = doc.createElement("from");
		fromElement.setTextContent("^/a/b.html$");
		ruleElement.appendChild(fromElement);

		Element toElement = doc.createElement("to");
		toElement.setTextContent("/modelGallery!catgModels.html?dId=1&cId=65");
		ruleElement.appendChild(toElement);

		Element forElement = doc.createElement("for");
		forElement.setAttribute("type","modelId");
		forElement.setTextContent("1");
		ruleElement.appendChild(forElement);



		//create the outbound-rule element
		Element outboundRuleElement = doc.createElement("outbound-rule");
		urlrewrite.appendChild(outboundRuleElement);

		toElement = doc.createElement("to");
		toElement.setTextContent("model.action?dId=1");
		outboundRuleElement.appendChild(toElement);

		fromElement = doc.createElement("from");
		fromElement.setTextContent("^/a/b.html$");
		outboundRuleElement.appendChild(fromElement);

		forElement = doc.createElement("for");
		forElement.setAttribute("type","modelId");
		forElement.setTextContent("1");
		outboundRuleElement.appendChild(forElement);






		TransformerFactory tranFactory = TransformerFactory.newInstance(); 
		Transformer aTransformer = tranFactory.newTransformer(); 

		Source src = new DOMSource(doc); 
//		Result dest = new StreamResult(System.out); 
//		aTransformer.transform(src, dest); 

		String fullPath = context.getRealPath(filePath);
		System.out.println("\nfullPath="+fullPath);
		StreamResult result = new StreamResult(fullPath);
		aTransformer.transform(src, result);

		System.out.println("\nDone");

		return NONE;
	}
	public String removeUrlRules() throws ParserConfigurationException, TransformerException, SAXException, IOException
	{
		System.out.println("removeUrlRules");
		ServletContext context = ServletActionContext.getServletContext();
		String filePath=context.getInitParameter("urlrewriteConfPath");
		System.out.println("filePath="+filePath);
		InputStream stream=context.getResourceAsStream(filePath);
		System.out.println("stream="+stream);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//Get the DocumentBuilder
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		//Create blank DOM Document
		Document document = documentBuilder.parse(stream);
		
		NodeList urlrewriteNodeList = document.getElementsByTagName("urlrewrite");
		Node urlrewrite = urlrewriteNodeList.item(0);

		NodeList forNodeList = document.getElementsByTagName("for");
		System.out.println("forNodeList-Length="+forNodeList.getLength());
		for(int n=0;n<forNodeList.getLength();n++) {
			Node forNode=forNodeList.item(n);
			NamedNodeMap attr = forNode.getAttributes();
			Node typeAttr = attr.getNamedItem("type");
			String forType=typeAttr.getNodeValue();
			String forTypeText=forNode.getTextContent();

			System.out.println("forType="+forType+" | forTypeText="+forTypeText);
			if(forType.equals("modelId") && forTypeText.equals("1"))
				urlrewrite.removeChild(forNode.getParentNode());
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		String fullPath = context.getRealPath(filePath);
		System.out.println("\nfullPath="+fullPath);
		StreamResult result = new StreamResult(fullPath);
		transformer.transform(source, result);

		System.out.println("Done");
		return NONE;
	}

	public static void createXmlString(String filePath) throws ParserConfigurationException, TransformerException, SAXException, IOException
	{
		System.out.println("|__createXmlString"+filePath);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		// documentBuilderFactory.setNamespaceAware(true);
		// documentBuilderFactory.setValidating(true);

		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(filePath);

		Node urlrewrite = document.getFirstChild();
		System.out.println("urlrewrite="+urlrewrite.getNodeName());
		NodeList list = urlrewrite.getChildNodes();
		System.out.println("list-length="+list.getLength());
		for(int i=0;i<list.getLength();i++) {
			Node node = list.item(i);
			System.out.println("i="+i+" Node="+node.getNodeName());
			if ("rule".equals(node.getNodeName())) {

				NodeList ruleChildNodeList=node.getChildNodes();

				for(int j=0;j<ruleChildNodeList.getLength();j++) {
					if("for".equals(ruleChildNodeList.item(j).getNodeName())) {
						Node forNode=ruleChildNodeList.item(j);
						NamedNodeMap attr = forNode.getAttributes();
						Node typeAttr = attr.getNamedItem("type");
						String forType=typeAttr.getNodeValue();

						String forTypeText=forNode.getTextContent();
						System.out.println("forType="+forType+" | forTypeText="+forTypeText);
						if(forType.equals("catg") && forTypeText.equals("11"))
							urlrewrite.removeChild(node);
					}
				}
			}
		}


		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.transform(source, result);

		System.out.println("Done");






	}
}
