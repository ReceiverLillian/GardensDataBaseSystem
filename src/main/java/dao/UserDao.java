package dao;

import bean.ConserveBean;
import bean.UserBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class UserDao {

    public UserBean getUserByNamePass(String username, String password){
        // TODO Auto-generated method stub
        UserBean userBean = null;
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from user where user_name= '"+username+"' and password= '"+password+"'";

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                userBean = new UserBean();
                userBean.setUser_id(rs.getInt("user_id"));
                userBean.setUser_name(rs.getString("user_name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return userBean;
    }
    /*
    获取养护人员信息
     */
    public ArrayList<UserBean> getConserveUser(){
        ArrayList<UserBean> tag_Array = new ArrayList<UserBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM user u\n" +
                "LEFT JOIN user_role ur ON u.user_id = ur.user_id\n" +
                "LEFT JOIN role r ON ur.role_id = r.role_id\n" +
                "WHERE r.role_name = 'conserve'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setUser_id(rs.getInt("user_id"));
                userBean.setUser_state(rs.getString("user_state"));
                userBean.setUser_name(rs.getString("user_name"));
                tag_Array.add(userBean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }
    /*
    获取监测人员信息
     */
    public ArrayList<UserBean> getMonitorUser(){
        ArrayList<UserBean> tag_Array = new ArrayList<UserBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM user u\n" +
                "LEFT JOIN user_role ur ON u.user_id = ur.user_id\n" +
                "LEFT JOIN role r ON ur.role_id = r.role_id\n" +
                "WHERE r.role_name = 'monitor'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setUser_id(rs.getInt("user_id"));
                userBean.setUser_state(rs.getString("user_state"));
                userBean.setUser_name(rs.getString("user_name"));
                tag_Array.add(userBean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }
}
