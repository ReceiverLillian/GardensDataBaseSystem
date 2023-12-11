package dao;

import bean.DiseaseBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiseaseDao {

    /*
    获取所有病虫害信息
     */
    public ArrayList<DiseaseBean> getAllDisease(){
        ArrayList<DiseaseBean> tag_Array = new ArrayList<DiseaseBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM disease";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {
                DiseaseBean diseaseBean=new DiseaseBean();
                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_medi(rs.getString("dis_medi"));
                diseaseBean.setDis_mednum(rs.getString("dis_mednum"));
                diseaseBean.setDis_name(rs.getString("dis_name"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));

                tag_Array.add(diseaseBean);
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
    根据id获取病虫害信息
     */
    public DiseaseBean getDiseaseById(int dis_id){
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM disease where dis_id='"+dis_id+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        DiseaseBean diseaseBean=new DiseaseBean();
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_medi(rs.getString("dis_medi"));
                diseaseBean.setDis_mednum(rs.getString("dis_mednum"));
                diseaseBean.setDis_name(rs.getString("dis_name"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return diseaseBean;
    }
}
