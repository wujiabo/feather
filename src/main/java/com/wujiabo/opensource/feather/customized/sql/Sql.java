package com.wujiabo.opensource.feather.customized.sql;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wujiabo.opensource.feather.constants.SqlConstants;

public class Sql {
	private static Logger log = Logger.getLogger(Sql.class);
	private static final String XML_SQL = "sql";
	private static Sql instance = null;
	private Map<String, String> sqlConfig = new HashMap<String, String>();

	private Sql() {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("sql/customizedSql.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 返回documentBuilderFactory对象
			DocumentBuilder db = dbf.newDocumentBuilder();// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			Document dt = db.parse(is); // 得到一个DOM并返回给document对象
			Element element = dt.getDocumentElement();// 得到一个elment根元素
			NodeList childNodes = element.getChildNodes(); // 获得根元素下的子节点
			for (int i = 0; i < childNodes.getLength(); i++) // 遍历这些子节点
			{
				Node node = childNodes.item(i); // childNodes.item(i);
				if (XML_SQL.equals(node.getNodeName())) {
					String nodeDetail = node.getTextContent().trim();
					sqlConfig.put(node.getAttributes().getNamedItem("id").getNodeValue(), nodeDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("loading sql...");
	}

	// 静态方法访问时，直接访问不需要实例化
	public static synchronized Sql getInstance() {
		// synchronized表示同时只能一个线程进行实例化
		if (instance == null) {
			// 如果两个进程同时进入时，同时创建很多实例，不符合单例
			instance = new Sql();
		}
		return instance;
	}

	public String getSqlConfig(String sqlId) {
		return sqlConfig.get(sqlId);
	}

	public static void main(String[] args) {
		// getInstance()是一个静态方法，不需要实例化
		String sql = Sql.getInstance().getSqlConfig(SqlConstants.GET_USERS_BY_USERNAME);
		System.out.println(sql);
	}

}
