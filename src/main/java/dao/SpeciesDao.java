package dao;

import bean.SpeciesBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpeciesDao {

    public SpeciesBean selectSpeciesById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from species where species_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SpeciesBean speciesBean = new SpeciesBean();

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                speciesBean.setSpecies_id(rs.getInt("species_id"));
                speciesBean.setSpecies_name(rs.getString("species_name"));
                speciesBean.setSpecies_othername(rs.getString("species_othername"));
                speciesBean.setCreatedby(rs.getString("createdby"));
                speciesBean.setCreatedtime(rs.getDate("createdtime"));
                speciesBean.setUpdatetime(rs.getDate("updatetime"));
                speciesBean.setSpecies_morph(rs.getString("species_morph"));
                speciesBean.setSpecies_tech(rs.getString("species_tech"));
                speciesBean.setSpecies_appl(rs.getString("species_appl"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return speciesBean;
    }

    public ArrayList<SpeciesBean> selectAllSpecies(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from species";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<SpeciesBean> speciesBeans = new ArrayList<>();

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                SpeciesBean speciesBean = new SpeciesBean();
                speciesBean.setSpecies_id(rs.getInt("species_id"));
                speciesBean.setSpecies_name(rs.getString("species_name"));
                speciesBean.setSpecies_othername(rs.getString("species_othername"));
                speciesBean.setCreatedby(rs.getString("createdby"));
                speciesBean.setCreatedtime(rs.getDate("createdtime"));
                speciesBean.setUpdatetime(rs.getDate("updatetime"));
                speciesBean.setSpecies_morph(rs.getString("species_morph"));
                speciesBean.setSpecies_tech(rs.getString("species_tech"));
                speciesBean.setSpecies_appl(rs.getString("species_appl"));
                speciesBeans.add(speciesBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return speciesBeans;
    }
}
