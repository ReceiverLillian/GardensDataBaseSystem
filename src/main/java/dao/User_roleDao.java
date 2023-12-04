package dao;

import bean.RoleBean;
import bean.UserBean;
import bean.User_roleBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class User_roleDao {

    public User_roleBean getUser_roleByUser_id(int user_id){
        User_roleBean userRoleBean = new User_roleBean();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from user_role where user_id ='"+user_id+"'";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                userRoleBean.setUser_id(rs.getInt("user_id"));
                userRoleBean.setRole_id(rs.getInt("role_id"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return userRoleBean;
    }
}
