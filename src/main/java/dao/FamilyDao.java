package dao;

import bean.FamilyBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FamilyDao {

    /*
    获取全部科
     */
    public ArrayList<FamilyBean> getAllFamily(){
        ArrayList<FamilyBean> tag_Array = new ArrayList<FamilyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT * FROM family";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
             stm = conn.prepareStatement(sql);
             rs = stm.executeQuery();
            while (rs.next()) {

                FamilyBean familyBean = new FamilyBean();
                familyBean.setFamily_name(rs.getString("family_name"));
                familyBean.setFamily_id(rs.getInt("family_id"));
                tag_Array.add(familyBean);
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
    获取全部的类别信息
     */
    public ArrayList<FamilyBean> getAllTypeByFamily(){
        ArrayList<FamilyBean> tag_Array = new ArrayList<FamilyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT s.species_name,g.genus_name,f.family_name,f.family_id\n" +
                "FROM family f\n" +
                "LEFT JOIN family_genus fg ON f.family_id = fg.family_id\n" +
                "LEFT JOIN genus g ON fg.genus_id= g.genus_id\n" +
                "LEFT JOIN genus_species gs ON g.genus_id = gs.genus_id\n" +
                "LEFT JOIN species s ON gs.species_id=s.species_id";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                FamilyBean familyBean = new FamilyBean();
                familyBean.setFamily_name(rs.getString("family_name"));
                familyBean.setFamily_id(rs.getInt("family_id"));

                if(rs.getString("genus_name")==null){
                    familyBean.setGenus_name("暂未添加下属属");
                }else{
                    familyBean.setGenus_name(rs.getString("genus_name"));
                }

                if(rs.getString("species_name")==null){
                    familyBean.setSpecies_name("暂未添加下属种");
                }else{
                    familyBean.setSpecies_name(rs.getString("species_name"));
                }

                tag_Array.add(familyBean);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return tag_Array;
    }

    public ArrayList<FamilyBean> SelectTypeByGenus(String name){
        ArrayList<FamilyBean> tag_Array = new ArrayList<FamilyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT s.species_name,g.genus_name,f.family_name,f.family_id\n" +
                "FROM family f\n" +
                "LEFT JOIN family_genus fg ON f.family_id = fg.family_id\n" +
                "LEFT JOIN genus g ON fg.genus_id= g.genus_id\n" +
                "LEFT JOIN genus_species gs ON g.genus_id = gs.genus_id\n" +
                "LEFT JOIN species s ON gs.species_id=s.species_id\n" +
                "where g.genus_name='"+name+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                FamilyBean familyBean = new FamilyBean();
                familyBean.setFamily_name(rs.getString("family_name"));
                familyBean.setFamily_id(rs.getInt("family_id"));

                familyBean.setGenus_name(rs.getString("genus_name"));

                if(rs.getString("species_name")==null){
                    familyBean.setSpecies_name("暂未添加下属种");
                }else{
                    familyBean.setSpecies_name(rs.getString("species_name"));
                }

                tag_Array.add(familyBean);
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
    根据科名查询下属分类
     */
    public ArrayList<FamilyBean> SelectTypeByFamily(String name){
        ArrayList<FamilyBean> tag_Array = new ArrayList<FamilyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT s.species_name,g.genus_name,f.family_name,f.family_id\n" +
                "FROM family f\n" +
                "LEFT JOIN family_genus fg ON f.family_id = fg.family_id\n" +
                "LEFT JOIN genus g ON fg.genus_id= g.genus_id\n" +
                "LEFT JOIN genus_species gs ON g.genus_id = gs.genus_id\n" +
                "LEFT JOIN species s ON gs.species_id=s.species_id\n" +
                "where f.family_name='"+name+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                FamilyBean familyBean = new FamilyBean();
                familyBean.setFamily_name(rs.getString("family_name"));
                familyBean.setFamily_id(rs.getInt("family_id"));

                familyBean.setGenus_name(rs.getString("genus_name"));


                if(rs.getString("species_name")==null){
                    familyBean.setSpecies_name("暂未添加下属种");
                }else{
                    familyBean.setSpecies_name(rs.getString("species_name"));
                }

                tag_Array.add(familyBean);
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
    根据科名和属名查询下属
     */
    public ArrayList<FamilyBean> SelectTypeByFamilyAndGenus(String name1,String name2){
        ArrayList<FamilyBean> tag_Array = new ArrayList<FamilyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql="SELECT s.species_name,g.genus_name,f.family_name,f.family_id\n" +
                "FROM family f\n" +
                "LEFT JOIN family_genus fg ON f.family_id = fg.family_id\n" +
                "LEFT JOIN genus g ON fg.genus_id= g.genus_id\n" +
                "LEFT JOIN genus_species gs ON g.genus_id = gs.genus_id\n" +
                "LEFT JOIN species s ON gs.species_id=s.species_id\n" +
                "where f.family_name='"+name1+"' and g.genus_name='"+name2+"'";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                FamilyBean familyBean = new FamilyBean();
                familyBean.setFamily_name(rs.getString("family_name"));
                familyBean.setFamily_id(rs.getInt("family_id"));

                familyBean.setGenus_name(rs.getString("genus_name"));

                if(rs.getString("species_name")==null){
                    familyBean.setSpecies_name("暂未添加下属种");
                }else{
                    familyBean.setSpecies_name(rs.getString("species_name"));
                }

                tag_Array.add(familyBean);
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
