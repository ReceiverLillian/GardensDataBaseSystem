package dao;

import bean.GenusBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenusDao {

    /*
    获取科名下的全部属
     */
    public ArrayList<GenusBean> getAllGenusByFamily(String family_name){
        ArrayList<GenusBean> tag_Array = new ArrayList<GenusBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM genus g\n" +
                "LEFT JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                "LEFT JOIN family f ON fg.family_id = f.family_id\n" +
                "WHERE f.family_name = '"+family_name+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {

                GenusBean genusBean = new GenusBean();
                genusBean.setGenus_name(rs.getString("genus_name"));
                genusBean.setGenus_id(rs.getInt("genus_id"));
                tag_Array.add(genusBean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }
    public ArrayList<GenusBean> getAllGenus(){
        ArrayList<GenusBean> tag_Array = new ArrayList<GenusBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM genus ";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                GenusBean genusBean = new GenusBean();
                genusBean.setGenus_name(rs.getString("genus_name"));
                genusBean.setGenus_id(rs.getInt("genus_id"));
                tag_Array.add(genusBean);
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
