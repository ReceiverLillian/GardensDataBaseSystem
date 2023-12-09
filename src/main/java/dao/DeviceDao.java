package dao;

import bean.DeviceBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceDao {
    public DeviceBean selectDeviceById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from device where dev_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        DeviceBean deviceBean = new DeviceBean();

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                deviceBean.setDev_id(rs.getInt("dev_id"));
                deviceBean.setDev_name(rs.getString("dev_name"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return deviceBean;
    }
}
