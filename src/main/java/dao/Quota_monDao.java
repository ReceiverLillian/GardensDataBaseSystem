package dao;

import bean.Quota_monBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Quota_monDao {
    public Quota_monBean selectQuota_monByMon_id(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from quota_mon where mon_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Quota_monBean quotaMonBean = new Quota_monBean();
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                quotaMonBean.setMon_id(rs.getInt("mon_id"));
                quotaMonBean.setQuo_id(rs.getInt("quo_id"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return quotaMonBean;
    }
}
