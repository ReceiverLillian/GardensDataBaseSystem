package dao;


import bean.MonitorBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonitorDao {
    public ArrayList<MonitorBean> getAllMonitor(){
        ArrayList<MonitorBean> monitorBeans = new ArrayList<>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from monitor";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                MonitorBean monitorBean = new MonitorBean();
                monitorBean.setMon_id(rs.getInt("mon_id")); // 对于int类型的字段
                monitorBean.setMon_time(rs.getDate("mon_time")); // 对于Date类型的字段
                monitorBean.setMon_place(rs.getString("mon_place")); // 对于String类型的字段
                monitorBean.setCreatedby(rs.getString("createdby")); // 对于String类型的字段
                monitorBean.setMonby(rs.getString("monby")); // 对于String类型的字段
                monitorBean.setMon_utime(rs.getDate("mon_utime")); // 对于Date类型的字段
                monitorBean.setMon_ctime(rs.getDate("mon_ctime")); // 对于Date类型的字段
                monitorBean.setMon_target(rs.getInt("mon_target"));
                monitorBeans.add(monitorBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return monitorBeans;
    }

    public MonitorBean selectMonitorById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from monitor where mon_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        MonitorBean monitorBean = new MonitorBean();
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                monitorBean.setMon_id(rs.getInt("mon_id")); // 对于int类型的字段
                monitorBean.setMon_time(rs.getDate("mon_time")); // 对于Date类型的字段
                monitorBean.setMon_place(rs.getString("mon_place")); // 对于String类型的字段
                monitorBean.setCreatedby(rs.getString("createdby")); // 对于String类型的字段
                monitorBean.setMonby(rs.getString("monby")); // 对于String类型的字段
                monitorBean.setMon_utime(rs.getDate("mon_utime")); // 对于Date类型的字段
                monitorBean.setMon_ctime(rs.getDate("mon_ctime")); // 对于Date类型的字段
                monitorBean.setMon_target(rs.getInt("mon_target"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return monitorBean;
    }
}
