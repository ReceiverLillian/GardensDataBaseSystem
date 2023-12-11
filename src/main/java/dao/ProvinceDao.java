package dao;

import bean.ProvinceBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceDao {

    /*
    获取植物对应的省分布信息
     */
    public ArrayList<ProvinceBean> getProvinceBySpecies(int spe_id){
        ArrayList<ProvinceBean> tag_Array = new ArrayList<ProvinceBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM province p\n" +
                "LEFT JOIN province_species ps ON p.province_id = ps.province_id\n" +
                "LEFT JOIN species s ON ps.species_id=s.species_id\n" +
                "WHERE s.species_id = '"+spe_id+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {
                ProvinceBean  provinceBean = new ProvinceBean();
                provinceBean.setProvince_name(rs.getString("province_name"));
                provinceBean.setProvince_id(rs.getInt("province_id"));
                tag_Array.add(provinceBean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }
}
