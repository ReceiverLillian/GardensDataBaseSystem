package dao;

import bean.Species;
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

    public Species selectPlantById(int id) {//含外表字段版
        Connection conn = DBUtil.getConnectDb();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Species species = new Species();

        String query = /*"SELECT s.species_name, s.species_othername, s.species_morph, s.species_tech, s.species_appl, s.species_environment,"
                + "f.family_name, g.genus_name , p.province_name"
                + "FROM species s "
                + "JOIN genus_species gs ON s.species_id = gs.species_id "
                + "JOIN genus g ON gs.genus_id = g.genus_id "
                + "JOIN family_genus fg ON g.genus_id = fg.genus_id "
                + "JOIN family f ON fg.family_id = f.family_id "
                + "JOIN province_species ps ON s.species_id = ps.species_id"
                + "JOIN province p ON ps.province_id = p.pronvince_id"
                + "WHERE s.species_id = ?";*/
        "SELECT s.species_name, s.species_othername, s.species_morph, s.species_tech, s.species_appl, s.species_environment,\n" +
                "       f.family_name, g.genus_name, p.province_name\n" +
                "FROM species s\n" +
                "JOIN genus_species gs ON s.species_id = gs.species_id\n" +
                "JOIN genus g ON gs.genus_id = g.genus_id\n" +
                "JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                "JOIN family f ON fg.family_id = f.family_id\n" +
                "JOIN province_species ps ON s.species_id = ps.species_id\n" +
                "JOIN province p ON ps.province_id = p.province_id\n" +
                "WHERE s.species_id = ?";

        try {

            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                /*String speciesName = rs.getString("species_name");
                String speciesOtherName = rs.getString("species_othername");
                String speciesMorph = rs.getString("species_morph");
                String speciesTech = rs.getString("species_tech");
                String speciesAppl = rs.getString("species_appl");
                String familyName = rs.getString("family_name");
                String genusName = rs.getString("genus_name");*/

                species.setSpeciesName(rs.getString("species_name"));
                species.setSpeciesOtherName(rs.getString("species_othername"));
                species.setSpeciesMorph(rs.getString("species_morph"));
                species.setSpeciesTech(rs.getString("species_tech"));
                species.setSpeciesAppl(rs.getString("species_appl"));
                species.setEnvironment(rs.getString("species_environment"));
                species.setFamilyName(rs.getString("family_name"));
                species.setGenusName(rs.getString("genus_name"));
                species.setProvince(rs.getString("province_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return species;
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

    public ArrayList<SpeciesBean> SuperiorselectAllSpecies(){
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

}
