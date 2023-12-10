package dao;

import bean.Species;
import bean.SpeciesBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        String query = "SELECT s.species_name, s.species_othername, s.species_morph, s.species_tech, s.species_appl, "
                + "f.family_name, g.genus_name "
                + "FROM species s "
                + "JOIN genus_species gs ON s.species_id = gs.species_id "
                + "JOIN genus g ON gs.genus_id = g.genus_id "
                + "JOIN family_genus fg ON g.genus_id = fg.genus_id "
                + "JOIN family f ON fg.family_id = f.family_id "
                + "WHERE s.species_id = ?";

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
                species.setFamilyName(rs.getString("family_name"));
                species.setGenusName(rs.getString("genus_name"));
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

}
