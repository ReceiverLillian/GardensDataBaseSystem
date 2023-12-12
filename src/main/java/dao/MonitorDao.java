package dao;


import bean.MonitorBean;
import util.DBUtil;

import java.sql.*;
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

    public ArrayList<MonitorBean> getViewMonitorDetails(){
        ArrayList<MonitorBean> monitorBeans = new ArrayList<>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from view_monitor_details";
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
                monitorBean.setSpecies_name(rs.getString("spcecies_name"));
                monitorBean.setSpecies_othername(rs.getString("species_othername"));
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

    public boolean updateMonitor(MonitorBean monitorBean){
        Connection conn = DBUtil.getConnectDb();
        String sql = "UPDATE monitor SET createdby=?, monby=?, mon_place=?, mon_target=?, mon_utime = ? WHERE mon_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean updateResult = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, monitorBean.getCreatedby());
            stm.setString(2, monitorBean.getMonby());
            stm.setString(3, monitorBean.getMon_place());
            stm.setInt(4, monitorBean.getMon_target());
            java.util.Date utilDate = monitorBean.getMon_utime(); // 获取 java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // 转换为 java.sql.Date
            stm.setDate(5, sqlDate);
            stm.setInt(6, monitorBean.getMon_id());

            int rowsUpdated = stm.executeUpdate();
            updateResult = rowsUpdated > 0;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return updateResult;
    }

    public boolean deleteMonitorById(int mon_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "DELETE FROM monitor WHERE mon_id=? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isDeleted = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, mon_id);

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

    public MonitorBean insertMonitor(MonitorBean monitorBean){
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet generatedKeys = null;
        boolean insertResult = false;

        try {
            conn = DBUtil.getConnectDb();
            String sql = "INSERT INTO monitor (createdby, monby, mon_place, mon_target, mon_time, mon_ctime, mon_utime) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, monitorBean.getCreatedby());
            stm.setString(2, monitorBean.getMonby());
            stm.setString(3, monitorBean.getMon_place());
            stm.setInt(4, monitorBean.getMon_target());

            // 转换 java.util.Date 为 java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(monitorBean.getMon_time().getTime());
            stm.setDate(5, sqlDate);

            // 对于 mon_ctime 和 mon_utime，我们使用当前时间
            java.sql.Date sqlCurrentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(6, sqlCurrentDate);
            stm.setDate(7, sqlCurrentDate);

            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating monitor failed, no rows affected.");
            }

            generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                monitorBean.setMon_id(generatedKeys.getInt(1)); // 获取并设置生成的 mon_id
            } else {
                throw new SQLException("Creating monitor failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            insertResult = false;
        } finally {
            // 关闭资源
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return monitorBean;

    }
}
