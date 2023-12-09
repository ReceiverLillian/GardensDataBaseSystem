package dao;

import bean.Conserve_speciesBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conserve_speciesDao {

    public  Conserve_speciesBean getSpeciesByCon_id(int con_id){
        Conserve_speciesBean converse_speciesBean = new Conserve_speciesBean();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from conserve_species where con_id = ?";
        //String sql = "select * from conserve_species where con_id ='"+con_id+"'";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, con_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                converse_speciesBean.setCon_id(rs.getInt("con_id"));
                converse_speciesBean.setSpecies_id(rs.getInt("species_id"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return converse_speciesBean;
    }
}
