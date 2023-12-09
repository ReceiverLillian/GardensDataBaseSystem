package dao;

import bean.Device_quotaBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Device_quotaDao {
    public Device_quotaBean selectDevice_quotaByQuo_id(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from device_quota where quo_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Device_quotaBean deviceQuotaBean = new Device_quotaBean();
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                deviceQuotaBean.setDev_id(rs.getInt("dev_id"));
                deviceQuotaBean.setQuo_id(rs.getInt("quo_id"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return deviceQuotaBean;
    }
}
