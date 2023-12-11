package dao;

import bean.ConserveBean;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ConserveDao {

    public ArrayList<ConserveBean> getAllConserve(){
        ArrayList<ConserveBean> tag_Array = new ArrayList<ConserveBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from conserve ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ConserveBean tag = new ConserveBean();
                tag.setCon_id(rs.getInt("con_id"));
                tag.setCon_name(rs.getString("con_name"));
                tag.setCon_desc(rs.getString("con_desc"));
                tag.setConby(rs.getString("conby"));
                tag.setCon_place(rs.getString("con_place"));
                tag.setCon_utime(rs.getDate("con_utime"));
                tag.setCon_ctime(rs.getDate("con_ctime"));
                tag.setCreatedby(rs.getString("createdby"));
                tag_Array.add(tag);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }

    public ArrayList<ConserveBean> getViewConserveDetail(){
        ArrayList<ConserveBean> tag_Array = new ArrayList<ConserveBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from view_conserve_details ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ConserveBean tag = new ConserveBean();
                tag.setCon_id(rs.getInt("con_id"));
                tag.setCon_name(rs.getString("con_name"));
                tag.setCon_desc(rs.getString("con_desc"));
                tag.setConby(rs.getString("conby"));
                tag.setCon_place(rs.getString("con_place"));
                tag.setCon_utime(rs.getDate("con_utime"));
                tag.setCon_ctime(rs.getDate("con_ctime"));
                tag.setCreatedby(rs.getString("createdby"));
                tag_Array.add(tag);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }
    /*
    根据ID获取养护信息
     */
    public ConserveBean getConserveById(int con_id){
        ConserveBean tag = new ConserveBean();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from conserve where con_id=? ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, con_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                tag.setCon_id(rs.getInt("con_id"));
                tag.setCon_name(rs.getString("con_name"));
                tag.setCon_desc(rs.getString("con_desc"));
                tag.setConby(rs.getString("conby"));
                tag.setCon_place(rs.getString("con_place"));
                tag.setCon_utime(rs.getDate("con_utime"));
                tag.setCon_ctime(rs.getDate("con_ctime"));
                tag.setCreatedby(rs.getString("createdby"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag;
    }

    public boolean updateConserve(ConserveBean conserveBean){

        Connection conn = DBUtil.getConnectDb();
        String sql = "UPDATE conserve SET createdby=?, conby=?, con_place=?, con_name=?, con_desc=?, con_utime=? WHERE con_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean updateResult = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setString(1, conserveBean.getCreatedby());
            stm.setString(2,conserveBean.getConby());
            stm.setString(3,conserveBean.getCon_place());
            stm.setString(4, conserveBean.getCon_name());
            stm.setString(5, conserveBean.getCon_desc());
            java.util.Date utilDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // 转换为 java.sql.Date
            stm.setDate(6, sqlDate);
            stm.setInt(7, conserveBean.getCon_id());

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

    public ConserveBean insertConserve(ConserveBean conserveBean){


        Connection conn = DBUtil.getConnectDb();
        String sql = "INSERT INTO conserve (createdby, conby, con_place, con_name, con_desc, con_utime, con_ctime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        ResultSet generatedKeys = null;
        boolean insertResult = false;

        try {
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, conserveBean.getCreatedby());
            stm.setString(2, conserveBean.getConby());
            stm.setString(3, conserveBean.getCon_place());
            stm.setString(4, conserveBean.getCon_name());
            stm.setString(5, conserveBean.getCon_desc());
            java.util.Date utilDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // 转换为 java.sql.Date
            stm.setDate(6, sqlDate);
            stm.setDate(7, sqlDate);

            int rowsInserted = stm.executeUpdate();
            insertResult = rowsInserted > 0;

            if (insertResult) {
                generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    conserveBean.setCon_id(newId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (stm != null) try { stm.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
        }

        return conserveBean;

    }

    public boolean deleteConserveById(int id){

        Connection conn = DBUtil.getConnectDb();
        String sql = "DELETE FROM conserve WHERE con_id=? ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isDeleted = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);

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

}
