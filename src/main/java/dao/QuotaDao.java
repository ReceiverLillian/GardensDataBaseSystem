package dao;


import bean.QuotaBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<QuotaBean> selectAllQuota(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from quota";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<QuotaBean> quotaBeans = new ArrayList<>();

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                QuotaBean quotaBean = new QuotaBean();
                quotaBean.setQuo_id(rs.getInt("quo_id"));
                quotaBean.setQuo_name(rs.getString("quo_name"));
                quotaBean.setQuo_value(rs.getFloat("quo_value"));
                quotaBeans.add(quotaBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return quotaBeans;
    }

    public Map<String, Double> getAverageQuoValueByQuoName() {
        Map<String, Double> averages = new HashMap<>();
        String sql = "SELECT quo_name, AVG(quo_value) AS average_quo_value FROM quota GROUP BY quo_name";
        try (Connection conn = DBUtil.getConnectDb();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                averages.put(rs.getString("quo_name"), rs.getDouble("average_quo_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averages;
    }

    public Map<String, Double> getMaxQuoValueByQuoName() {
        Map<String, Double> maxValues = new HashMap<>();
        String sql = "SELECT quo_name, MAX(quo_value) AS max_quo_value FROM quota GROUP BY quo_name";
        try (Connection conn = DBUtil.getConnectDb();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                maxValues.put(rs.getString("quo_name"), rs.getDouble("max_quo_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxValues;
    }
}
