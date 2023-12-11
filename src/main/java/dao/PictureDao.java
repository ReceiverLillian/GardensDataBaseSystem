package dao;

import bean.PictureBean;
import bean.UserBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PictureDao {

    /*
    获取植物对应的配图信息
     */
    public ArrayList<PictureBean> getPictureBySpecies(int spe_id){
        ArrayList<PictureBean> tag_Array = new ArrayList<PictureBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM picture p\n" +
                "LEFT JOIN picture_species ps ON p.picture_id = ps.picture_id\n" +
                "LEFT JOIN species s ON ps.species_id=s.species_id\n" +
                "WHERE s.species_id = '"+spe_id+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {

                PictureBean pictureBean = new PictureBean();
                pictureBean.setPicture_content(rs.getBlob("picture_content"));
                pictureBean.setPicture_discri(rs.getString("picture_discri"));
                pictureBean.setPhototedby(rs.getString("photoedby"));
                pictureBean.setPicture_id(rs.getInt("picture_id"));
                tag_Array.add(pictureBean);
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
