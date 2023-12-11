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
        ArrayList<SpeciesBean> speciesBeans=new ArrayList<>();

        Connection conn = DBUtil.getConnectDb();
        String sql = "SELECT s.species_id, s.species_name, s.species_othername, s.createdtime, s.updatetime, s.createdby,\n" +
                "       s.species_morph, s.species_tech, s.species_appl,\n"  +
                "       g.genus_id, g.genus_name,\n" +
                "       f.family_id, f.family_name\n" +
                "FROM species s\n" +
                "LEFT JOIN genus_species gs ON s.species_id = gs.species_id\n" +
                "LEFT JOIN genus g ON gs.genus_id = g.genus_id\n" +
                "LEFT JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                "LEFT JOIN family f ON fg.family_id = f.family_id;";
        PreparedStatement stm = null;
        ResultSet rs = null;

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
                speciesBean.setFamily_name(rs.getString("family_name"));
                speciesBean.setGenus_name(rs.getString("genus_name"));
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
    /*
    根据植物ID获取植物信息，包括科、属
     */
    public SpeciesBean selectDetailedSpeciesById(int spe_id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "SELECT s.species_id, s.species_name, s.species_othername, s.createdtime, s.updatetime, s.createdby,\n" +
                "                s.species_morph, s.species_tech, s.species_appl,\n" +
                "                       g.genus_id, g.genus_name,\n" +
                "                       f.family_id, f.family_name\n" +
                "                FROM species s\n" +
                "                LEFT JOIN genus_species gs ON s.species_id = gs.species_id\n" +
                "                LEFT JOIN genus g ON gs.genus_id = g.genus_id\n" +
                "                LEFT JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                "                LEFT JOIN family f ON fg.family_id = f.family_id\n" +
                "                where s.species_id='"+spe_id+"' ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        SpeciesBean speciesBean = new SpeciesBean();
        try {

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                speciesBean.setSpecies_id(rs.getInt("species_id"));
                speciesBean.setSpecies_name(rs.getString("species_name"));
                speciesBean.setSpecies_othername(rs.getString("species_othername"));
                speciesBean.setCreatedby(rs.getString("createdby"));
                speciesBean.setCreatedtime(rs.getDate("createdtime"));
                speciesBean.setUpdatetime(rs.getDate("updatetime"));
                speciesBean.setSpecies_morph(rs.getString("species_morph"));
                speciesBean.setSpecies_tech(rs.getString("species_tech"));
                speciesBean.setSpecies_appl(rs.getString("species_appl"));
                speciesBean.setFamily_name(rs.getString("family_name"));
                speciesBean.setGenus_name(rs.getString("genus_name"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }

        return speciesBean;
    }
}
