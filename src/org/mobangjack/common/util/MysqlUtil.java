﻿package org.mobangjack.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.apache.log4j.Logger;

import com.sun.rowset.CachedRowSetImpl;

public class MysqlUtil {
	
	private static final Logger logger = Logger.getLogger(MysqlUtil.class);
	
	public static Prop prop = PropUtil.use("config_mysql.txt", "UTF-8");
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName).newInstance();
			con = DriverManager.getConnection(prop.get("jdbcUrl"), prop.get("user"), prop.get("password"));
		} catch (Exception e) {
			close(null,null,con);
			logger.error(e);
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * close connection and release resources.
	 * @param rs
	 * @param sm
	 * @param con
	 */
	public static void close(ResultSet rs,Statement sm,Connection con) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a raw PreparedStatement using the given parameters.
	 * A raw PreparedStatement means that you need to fill it using related parameters to make it executable.
	 * @param con
	 * @param sql
	 * @param autoGeneratedKeys
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql,int autoGeneratedKeys){
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql,autoGeneratedKeys);
		} catch (SQLException e) {
			close(null,ps,null);
			logger.error(e);
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * Generate a raw PreparedStatement using the given parameters.
	 * A raw PreparedStatement means that you need to fill it using related parameters to make it executable.
	 * @param con
	 * @param sql
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql){
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			close(null,ps,null);
			logger.error(e);
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * Fill a raw PreparedStatement to make it executable.
	 * @param ps
	 * @param params
	 * @return
	 */
	public static PreparedStatement fillStatement(PreparedStatement ps,Object...params){
		try {
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * Fill a raw PreparedStatement to make it executable.
	 * @param ps
	 * @param params
	 * @return
	 */
	public static PreparedStatement fillStatement(PreparedStatement ps,List<Object> params){
		return fillStatement(ps,params.toArray());
	}
	
	/**
	 * Generate an executable PreparedStatement.
	 * @param con
	 * @param sql
	 * @param autoGeneratedKeys
	 * @param params
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql,int autoGeneratedKeys,Object...params){
		return fillStatement(prepareStatement(con,sql,autoGeneratedKeys),params);
	}
	
	/**
	 * Generate an executable PreparedStatement.
	 * @param con
	 * @param sql
	 * @param autoGeneratedKeys
	 * @param params
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql,int autoGeneratedKeys,List<Object> params){
		return prepareStatement(con,sql,autoGeneratedKeys,params.toArray());
	}
	
	/**
	 * Generate an executable PreparedStatement.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql,Object...params){
		return fillStatement(prepareStatement(con,sql),params);
	}
	
	/**
	 * Generate an executable PreparedStatement.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static PreparedStatement prepareStatement(Connection con,String sql,List<Object> params){
		return prepareStatement(con,sql,params.toArray());
	}
	
	/**
	 * Query using an executable PreparedStatement.
	 * @param ps
	 * @return
	 */
	public static CachedRowSet query(PreparedStatement ps) {
		ResultSet rs = null;
		CachedRowSet crs = null;
		try {
			rs = ps.executeQuery();
			crs = new CachedRowSetImpl();
			crs.populate(rs);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally{
			close(rs, ps, null);
		}
		return crs;
	}
	
	/**
	 * Query using the given parameters.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static CachedRowSet query(Connection con,String sql,Object...params) {
		return query(prepareStatement(con,sql,params));
	}
	
	/**
	 * Query using the given parameters.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static CachedRowSet query(Connection con,String sql,List<Object> params) {
		return query(prepareStatement(con,sql,params.toArray()));
	}
	
	/**
	 * Query using the given parameters.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static CachedRowSet query(String sql,Object...params) {
		Connection con = getConnection();
		CachedRowSet crs = query(con,sql,params);
		close(null, null, con);
		return crs;
	}
	
	/**
	 * Query using the given parameters.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static CachedRowSet query(String sql,List<Object> params) {
		return query(sql,params.toArray());
	}
	
	/**
	 * Query using the given parameters.
	 * @param con
	 * @param sql
	 * @return
	 */
	public static CachedRowSet query(Connection con,String sql) {
		Statement sm = null;
		ResultSet rs = null;
		CachedRowSet crs = null;
		try {
			sm = con.createStatement();
			rs = sm.executeQuery(sql);
			crs = new CachedRowSetImpl();
			crs.populate(rs);
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally{
			close(rs, sm, null);
		}
		return crs;
	}
	
	/**
	 * Query using the given parameters.
	 * @param sql
	 * @return
	 */
	public static CachedRowSet query(String sql) {
		Connection con = getConnection();
		CachedRowSet crs = query(con,sql);
		close(null,null,con);
		return crs;
	}
	
	/**
	 * Update using an executable PreparedStatement.
	 * @param ps
	 * @return
	 */
	public static boolean update(PreparedStatement ps) {
		boolean flag = true;
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * Update using the given parameters.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean update(Connection con,String sql,Object...params) {
		return update(prepareStatement(con,sql,params));
	}
	
	/**
	 * Update using the given parameters.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean update(Connection con,String sql,List<Object> params) {
		return update(prepareStatement(con,sql,params.toArray()));
	}
	
	/**
	 * Update using the given parameters.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean update(String sql,Object...params) {
		Connection con = getConnection();
		boolean flag = update(con,sql,params);
		close(null, null, con);
		return flag;
	}
	
	/**
	 * Update using the given parameters.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean update(String sql,List<Object> params) {
		return update(sql,params.toArray());
	}
	
	/**
	 * Update using the given parameters.
	 * @param con
	 * @param sql
	 * @return
	 */
	public static boolean update(Connection con,String sql) {
		boolean flag = false;
		Statement sm = null;
		try {
			sm = con.createStatement();
			sm.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(e);
			flag = false;
			e.printStackTrace();
		} finally{
			close(null, sm, null);
		}
		return flag;
	}
	
	/**
	 * Update using the given parameters.
	 * @param sql
	 * @return
	 */
	public static boolean update(String sql) {
		Connection con = getConnection();
		boolean flag = update(con,sql);
		close(null, null, con);
		return flag;
	}
	
	/**
	 * Update and return generated key.
	 * @param ps
	 * @return
	 */
	public static int updateAndRetrieveKey(PreparedStatement ps) {
		int id = -1;
		ResultSet rs = null;
		try {
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, null);
		}
		return id;
	}
	
	/**
	 * Update and return generated key.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int updateAndRetrieveKey(Connection con,String sql,Object...params) {
		return updateAndRetrieveKey(prepareStatement(con,sql,PreparedStatement.RETURN_GENERATED_KEYS,params));
	}
	
	/**
	 * Update and return generated key.
	 * @param con
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int updateAndRetrieveKey(Connection con,String sql,List<Object> params) {
		return updateAndRetrieveKey(prepareStatement(con,sql,PreparedStatement.RETURN_GENERATED_KEYS,params.toArray()));
	}
	
	/**
	 * Update and return generated key.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int updateAndRetrieveKey(String sql,Object...params) {
		Connection con = getConnection();
		int key = updateAndRetrieveKey(con,sql,params);
		close(null, null, con);
		return key;
	}
	
	/**
	 * Update and return generated key.
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int updateAndRetrieveKey(String sql,List<Object> params) {
		return updateAndRetrieveKey(sql,params.toArray());
	}
	
	/**
	 * Update and return generated key.
	 * @param con
	 * @param sql
	 * @return
	 */
	public static int updateAndRetrieveKey(Connection con,String sql) {
		int id = -1;
		Statement sm = null;
		ResultSet rs = null;
		try {
			sm = con.createStatement();
			sm.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			rs = sm.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally{
			close(rs, sm, null);
		}
		return id;
	}
	
	/**
	 * Update and return generated key.
	 * @param sql
	 * @return
	 */
	public static int updateAndRetrieveKey(String sql) {
		Connection con = getConnection();
		int id = updateAndRetrieveKey(con,sql);
		close(null, null, con);
		return id;
	}
	
	/**
	 * Get count.
	 * @param con
	 * @param table
	 * @param col
	 * @param colValue
	 * @return
	 */
	public static int getCount(Connection con,String table, String col, Object colValue) {
		String sql = "select count(*) from `"+table+"` where `"+col+"`=?";
		CachedRowSet crs = query(prepareStatement(con,sql,colValue));
		int count = -1;
		try {
			if(crs.next()){
				count = crs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(crs, null, null);
		}
		return count;
	}
	
	/**
	 * Get count.
	 * @param table
	 * @param col
	 * @param colValue
	 * @return
	 */
	public static int getCount(String table, String col, Object colValue) {
		Connection con = getConnection();
		int count = getCount(con, table, col, colValue);
		close(null,null,con);
		return count;
	}
	
	/**
	 * Has table.
	 * @param con
	 * @param tableName
	 * @return
	 */
	public static boolean hasTable(Connection con,String tableName){
		boolean flag = false;
		ResultSet rs = null;
		try {
			rs = con.getMetaData().getTables(null, null, tableName, null);
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error(e);
			e.printStackTrace();
		} finally{
			close(rs, null, null);
		}
		return flag;
	}
	
	/**
	 * Has table.
	 * @param tableName
	 * @return
	 */
	public static boolean hasTable(String tableName){
		boolean flag = false;
		Connection con = getConnection();
		flag = hasTable(con,tableName);
		close(null,null,con);
		return flag;
	}

	public static void main(String[] args){
		System.out.println(getCount("admin","openid","oWXKeuCTghFIXJtLOgZUziT6o6HI"));
		List<Object> list = new ArrayList<Object>();
		list.toArray();
	}
}
