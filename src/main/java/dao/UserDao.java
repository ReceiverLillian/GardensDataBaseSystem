package dao;

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
                userBean.setPassword("password");
                userBean.setUser_state("user_state");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return userBean;
    }
}
