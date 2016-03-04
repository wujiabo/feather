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

	@Override
	public List<Map<String, Object>> queryForList(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public PageBean queryForListPage(String sql, Object[] args, int currentPage) {
		Object[] objs = new Object[args.length + 2];
		for (int i = 0; i < args.length; i++) {
			objs[i] = args[i];
		}
		objs[args.length] = (currentPage - 1) * 10;
		objs[args.length + 1] = 10;

		String countSql = "select count(*) cnt from (" + sql + ") page_table_temp";

		String pageSql = "select page_table_temp.* from (" + sql + ") page_table_temp limit ?,?";

		PageBean pageBean = new PageBean();
		pageBean.setPageList(queryForList(pageSql, objs));
		pageBean.setTotalCount(Integer.valueOf(queryForMap(countSql, args).get("cnt").toString()));
		if (currentPage == 1) {
			pageBean.setIsFirst(true);
			pageBean.setIsLast(false);
		} else if (pageBean.getTotalCount() <= currentPage * 10 && pageBean.getTotalCount() > (currentPage - 1) * 10) {
			pageBean.setIsLast(true);
		}
		return pageBean;
	}

	@Override
	public Map<String, Object> queryForMap(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForMap(sql, args);
	}

	public class PageBean {
		private List<Map<String, Object>> pageList;
		private Integer totalCount;
		private Boolean isFirst;
		private Boolean isLast;

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

	}
}
