package dao;


import bean.AdminBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 有关读者账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */
public class AdminDao {

	/**
	 * 登录验证功能，传入用户名和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
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


	/**
	 * 根据传入的账号，密码，来查找对应的读者信息，返回一个AdminBean类型，
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
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

	/**
	 * 获取全部用户的信息，其中sql语句中的status=1，表示只查找读者，不显示管理员的
	 *
	 * @return
	 */
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

	/**
	 * 获取全部用户的信息，其中sql语句中的status=2，表示只查找管理员，不显示读者的
	 *
	 * @return
	 */
	public ArrayList<AdminBean> get_ListInfo2() {
		ArrayList<AdminBean> tag_Array = new ArrayList<AdminBean>();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where status=2";
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

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据
	 *
	 * @param aid
	 * @return
	 */
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

	/**
	 * 根据传入的aid，查找到对应的读者的全部信息，返回一个AdminBean类型的数据，与上一个相似，只是aid的类型为String
	 *
	 * @param aid
	 * @return
	 */
	public AdminBean get_AidInfo2(String aid) {
		AdminBean adminbean = new AdminBean();
		Connection conn = DBUtil.getConnectDb();
		String sql = "select * from admin where aid=" + aid;
		PreparedStatement stm = null;
		ResultSet rs = null;
		//根据用户id查询到数据表中的一行信息
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

	/**
	 * 修改读者的信息
	 */
	public void updateUser(int aid, String username, String name, String email, String phone,
			String address,String sex) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnectDb();
		String sql = "update admin set username=?,name=?,email=?,phone=?,address=?,sex=? where aid=?";
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, name);
			stm.setString(3, email);
			stm.setString(4, phone);
			stm.setString(5, address);
			stm.setString(6, sex);
			stm.setInt(7, aid);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
