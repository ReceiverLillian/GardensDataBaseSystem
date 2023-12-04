package dao;

import bean.RoleBean;
import bean.UserBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class RoleDao {

    public RoleBean getRoleById(int role_id){
        RoleBean roleBean = new RoleBean();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from role where role_id= '"+role_id+"'";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                roleBean.setRole_id(rs.getInt("role_id"));
                roleBean.setRole_name(rs.getString("role_name"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return roleBean;
    }
}
