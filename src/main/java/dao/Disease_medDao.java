package dao;

import bean.Disease_MedBean;
import bean.Err_monBean;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class Disease_medDao {
    public ArrayList<Disease_MedBean> selectAllDisease_med(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from view_disease_medicine_details";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Disease_MedBean> diseaseMedBeans = new ArrayList<>();
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Disease_MedBean diseaseMedBean = new Disease_MedBean();
                diseaseMedBean.setDis_id(rs.getInt("dis_id"));
                diseaseMedBean.setMed_id(rs.getInt("med_id"));
                diseaseMedBean.setDis_name(rs.getString("dis_name"));
                diseaseMedBean.setMed_name(rs.getString("med_name"));
                diseaseMedBean.setDis_mednum(rs.getFloat("dis_mednum"));
                diseaseMedBean.setDis_ddl(rs.getDate("dis_ddl"));
                diseaseMedBean.setDis_target(rs.getInt("dis_target"));
                diseaseMedBean.setDis_ctime(rs.getDate("dis_ctime"));
                diseaseMedBean.setDis_utime(rs.getDate("dis_utime"));
                diseaseMedBeans.add(diseaseMedBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return diseaseMedBeans;
    }

    public Disease_MedBean selectDisease_MedById(int dis_id, int med_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from disease_med where dis_id = ? AND med_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        Disease_MedBean diseaseMedBean = new Disease_MedBean();
        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dis_id);
            stm.setInt(2, med_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                diseaseMedBean.setDis_id(rs.getInt("dis_id"));
                diseaseMedBean.setMed_id(rs.getInt("med_id"));
                diseaseMedBean.setDis_mednum(rs.getFloat("dis_mednum"));
                diseaseMedBean.setDis_ddl(rs.getDate("dis_ddl"));
                diseaseMedBean.setDis_utime(rs.getDate("dis_utime"));
                diseaseMedBean.setDis_ctime(rs.getDate("dis_ctime"));
                diseaseMedBean.setDis_target(rs.getInt("dis_target"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return diseaseMedBean;
    }

    public boolean updateDisease_MedById(Disease_MedBean diseaseMedBean){
        Connection conn = DBUtil.getConnectDb();
        String sql = "UPDATE disease_med SET dis_target=?, dis_mednum=?, dis_ddl=? WHERE dis_id=? AND med_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean updateResult = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, diseaseMedBean.getDis_target());
            stm.setFloat(2, diseaseMedBean.getDis_mednum());

            java.util.Date utilDate = diseaseMedBean.getDis_ddl(); // 获取 java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // 转换为 java.sql.Date
            stm.setDate(3, sqlDate);
            stm.setInt(4, diseaseMedBean.getDis_id());
            stm.setInt(5, diseaseMedBean.getMed_id());

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

    public boolean deleteDisease_MedById(int dis_id, int med_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "DELETE FROM disease_med WHERE dis_id=? AND med_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean isDeleted = false;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, dis_id);
            stm.setInt(2, med_id);

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

    public boolean insertDisease_Med(Disease_MedBean diseaseMedBean){

        Connection conn = DBUtil.getConnectDb();
        String sql = "INSERT INTO disease_med (med_id, dis_id, dis_mednum, dis_ddl, dis_ctime, dis_utime, dis_target) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = null;
        ResultSet generatedKeys = null;
        boolean insertResult = false;

        try {
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, diseaseMedBean.getMed_id());
            stm.setInt(2, diseaseMedBean.getDis_id());
            stm.setFloat(3, diseaseMedBean.getDis_mednum());
            java.util.Date utilDate = diseaseMedBean.getDis_ddl();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // 转换为 java.sql.Date
            stm.setDate(4, sqlDate);
            utilDate = new java.util.Date();
            sqlDate = new java.sql.Date(utilDate.getTime());
            stm.setDate(5, sqlDate);
            utilDate = new java.util.Date();
            sqlDate = new java.sql.Date(utilDate.getTime());
            stm.setDate(6, sqlDate);
            stm.setInt(7, diseaseMedBean.getDis_target());

            int rowsInserted = stm.executeUpdate();
            insertResult = rowsInserted > 0;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException logOrIgnore) {}
            if (stm != null) try { stm.close(); } catch (SQLException logOrIgnore) {}
            if (conn != null) try { conn.close(); } catch (SQLException logOrIgnore) {}
        }

        return insertResult;
    }
}
