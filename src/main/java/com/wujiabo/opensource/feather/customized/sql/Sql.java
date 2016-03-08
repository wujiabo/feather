package com.wujiabo.opensource.feather.customized.sql;

public class Sql {
	public static final String GET_USERS_BY_USERNAME = "select * from t_user where user_name like ? and screen_name like ? order by user_id";
}
