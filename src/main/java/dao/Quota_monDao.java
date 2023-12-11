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

    public Quota_monBean selectQuota_monByQuo_id(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from quota_mon where quo_id = ?";
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

    public boolean deleteQuota_mon(int mon_id, int quo_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "DELETE FROM quota_mon WHERE mon_id=? AND quo_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isDeleted = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, mon_id);
            stm.setInt(2, quo_id);

            int rowsUpdated = stm.executeUpdate();
            isDeleted = rowsUpdated > 0;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return isDeleted;
    }

    public boolean insertQuota_mon(int mon_id, int quo_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "INSERT INTO quota_mon (mon_id, quo_id) VALUES (?, ?)";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isInsert = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, mon_id);
            stm.setInt(2, quo_id);

            int rowsUpdated = stm.executeUpdate();
            isInsert = rowsUpdated > 0;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return isInsert;
    }

}
