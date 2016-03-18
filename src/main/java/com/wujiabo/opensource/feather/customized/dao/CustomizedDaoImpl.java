package com.wujiabo.opensource.feather.customized.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizedDaoImpl implements CustomizedDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private CustomizedSqlLoader getSqlInstance() {
		return CustomizedSqlLoader.getInstance();
	}

	@Override
	public List<Map<String, Object>> queryForList(String sqlId, Object[] args) {
		return queryForListBySql(getSqlInstance().getSqlConfig(sqlId), args);
	}

	private List<Map<String, Object>> queryForListBySql(String sql, Object[] args) {
		if (args != null) {
			return jdbcTemplate.queryForList(sql, args);
		}
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public Map<String, Object> queryForMap(String sqlId, Object[] args) {
		return queryForMapBySql(getSqlInstance().getSqlConfig(sqlId), args);
	}

	private Map<String, Object> queryForMapBySql(String sql, Object[] args) {
		if (args != null) {
			return jdbcTemplate.queryForMap(sql, args);
		}
		return jdbcTemplate.queryForMap(sql);
	}

	@Override
	public List<Map<String, Object>> queryForListByReplaceCond(String sqlId, List<Condition> conds, Object[] args) {
		String sql = getSqlInstance().getSqlConfig(sqlId);
		for (Condition cond : conds) {
			sql = sql.replace(cond.getKey(), cond.getValue());
		}
		return queryForListBySql(sql, args);
	}

	@Override
	public PageBean queryForListPage(String sqlId, Object[] args, int currentPage) {
		Object[] objs = new Object[args.length + 2];
		for (int i = 0; i < args.length; i++) {
			objs[i] = args[i];
		}
		objs[args.length] = (currentPage - 1) * 10;
		objs[args.length + 1] = 10;

		String countSql = "select count(*) cnt from (" + getSqlInstance().getSqlConfig(sqlId) + ") page_table_temp";

		String pageSql = "select page_table_temp.* from (" + getSqlInstance().getSqlConfig(sqlId)
				+ ") page_table_temp limit ?,?";

		PageBean pageBean = new PageBean();
		pageBean.setPageList(queryForListBySql(pageSql, objs));
		pageBean.setTotalCount(Integer.valueOf(queryForMapBySql(countSql, args).get("cnt").toString()));
		if (currentPage == 1) {
			pageBean.setIsFirst(true);
			pageBean.setIsLast(false);
		}
		if (pageBean.getTotalCount() <= currentPage * 10 && pageBean.getTotalCount() > (currentPage - 1) * 10) {
			pageBean.setIsLast(true);
		}
		pageBean.setTotalPage(pageBean.getTotalCount() / 10 + (pageBean.getTotalCount() % 10 > 0 ? 1 : 0));
		return pageBean;
	}

	public class PageBean {
		private List<Map<String, Object>> pageList;
		private Integer totalCount;
		private Boolean isFirst;
		private Boolean isLast;
		private Integer totalPage;

		public List<Map<String, Object>> getPageList() {
			return pageList;
		}

		public void setPageList(List<Map<String, Object>> pageList) {
			this.pageList = pageList;
		}

		public Integer getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(Integer totalCount) {
			this.totalCount = totalCount;
		}

		public Boolean getIsFirst() {
			return isFirst;
		}

		public void setIsFirst(Boolean isFirst) {
			this.isFirst = isFirst;
		}

		public Boolean getIsLast() {
			return isLast;
		}

		public void setIsLast(Boolean isLast) {
			this.isLast = isLast;
		}

		public Integer getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}

	}
}
