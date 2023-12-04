package dao;


import bean.AdminBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao {


	public boolean Login_verify(String username, String password) {
		Connection conn = DBUtil.getConnectDb();
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "select * from admin where username='" + username + " 'and password='" + password + "'";
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return false;
	}


	public AdminBean getAdminInfo(String username, String password) {
		// TODO Auto-generated method stub
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where username= '"+username+"' and password= '"+password+"'";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}


	public ArrayList<AdminBean> get_ListInfo() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=1";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				AdminBean adminbean = new AdminBean();
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setStatus(rs.getInt("status"));
				tag_Array.add(adminbean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return tag_Array;
	}


	public AdminBean get_AidInfo(int aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}


	public AdminBean get_AidInfo2(String aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		//�����û�id��ѯ�����ݱ��е�һ����Ϣ
		try {
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if (rs.next()) {
				adminbean.setAid(rs.getInt("aid"));
				adminbean.setUsername(rs.getString("username"));
				adminbean.setPassword(rs.getString("password"));
				adminbean.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.CloseDB(rs, stm, conn);
		}
		return adminbean;
	}


}
