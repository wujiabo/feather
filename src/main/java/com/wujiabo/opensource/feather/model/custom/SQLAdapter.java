package com.wujiabo.opensource.feather.model.custom;

/**
 * 通用sql
* @ClassName: SQLAdapter
* @author fuce
* @date 2019-09-02 01:10
*
 */
public class SQLAdapter {
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @param sql
	 */
	public SQLAdapter(String sql) {
		super();
		this.sql = sql;
	}
	
}
