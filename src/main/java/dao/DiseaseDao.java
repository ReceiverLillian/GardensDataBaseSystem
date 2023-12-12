package dao;

import bean.DeviceBean;
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
    public ArrayList<DiseaseBean> SuperiorgetAllDisease(){
        ArrayList<DiseaseBean> tag_Array = new ArrayList<DiseaseBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT d.dis_id,d.dis_name,d.createdby,d.createdtime,d.dis_tech,d.updatetime,s.species_name,m.med_name,dm.dis_ddl,dm.dis_mednum\n" +
                "                    FROM disease d\n" +
                "                    LEFT JOIN disease_med dm ON d.dis_id = dm.dis_id\n" +
                "                    LEFT JOIN medicine m ON dm.med_id = m.med_id\n" +
                "                    LEFT JOIN species s ON d.dis_target = s.species_id";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {
                DiseaseBean diseaseBean=new DiseaseBean();
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_name(rs.getString("dis_name"));

                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));

                diseaseBean.setDis_medi(rs.getString("med_name"));
                diseaseBean.setDis_mednum(rs.getString("dis_mednum"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));

                diseaseBean.setDis_target(rs.getString("species_name"));

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
        String sql="SELECT d.dis_id,d.dis_name,d.createdby,d.createdtime,d.dis_tech,d.updatetime,\n" +
                "s.species_name,m.med_name,dm.dis_ddl,dm.dis_mednum\n" +
                "                                    FROM disease d\n" +
                "                                    LEFT JOIN disease_med dm ON d.dis_id = dm.dis_id\n" +
                "                                    LEFT JOIN medicine m ON dm.med_id = m.med_id\n" +
                "                                   LEFT JOIN species s ON d.dis_target = s.species_id\n" +
                "where d.dis_id='"+dis_id+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        DiseaseBean diseaseBean=new DiseaseBean();
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_name(rs.getString("dis_name"));

                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));

                diseaseBean.setDis_medi(rs.getString("med_name"));
                diseaseBean.setDis_mednum(rs.getString("dis_mednum"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));

                diseaseBean.setDis_target(rs.getString("species_name"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return diseaseBean;
    }

    public DiseaseBean selectDiseaseById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from disease where dis_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        DiseaseBean diseaseBean = new DiseaseBean();

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_name(rs.getString("dis_name"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));
                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return diseaseBean;
    }

    public ArrayList<DiseaseBean> selectAllDisease(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from disease ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<DiseaseBean> diseaseBeans = new ArrayList<>();

        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                DiseaseBean diseaseBean = new DiseaseBean();
                diseaseBean.setDis_id(rs.getInt("dis_id"));
                diseaseBean.setDis_name(rs.getString("dis_name"));
                diseaseBean.setDis_tech(rs.getString("dis_tech"));
                diseaseBean.setDis_ddl(rs.getString("dis_ddl"));
                diseaseBean.setCreatedby(rs.getString("createdby"));
                diseaseBean.setCreatedtime(rs.getDate("createdtime"));
                diseaseBean.setUpdatetime(rs.getDate("updatetime"));
                diseaseBeans.add(diseaseBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return diseaseBeans;
    }
}
