package dao;


import bean.QuotaBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotaDao {
    public QuotaBean selectQuotaById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from quota where quo_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        QuotaBean quotaBean = new QuotaBean();

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                quotaBean.setQuo_id(rs.getInt("quo_id"));
                quotaBean.setQuo_name(rs.getString("quo_name"));
                quotaBean.setQuo_value(rs.getFloat("quo_value"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return quotaBean;
    }
}
