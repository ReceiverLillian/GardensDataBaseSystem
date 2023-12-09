package dao;

import bean.ConserveBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConserveDao {

    public ArrayList<ConserveBean> getAllConserve(){
        ArrayList<ConserveBean> tag_Array = new ArrayList<ConserveBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from conserve ";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                ConserveBean tag = new ConserveBean();
                tag.setCon_id(rs.getInt("con_id"));
                tag.setCon_name(rs.getString("con_name"));
                tag.setCon_desc(rs.getString("con_desc"));
                tag.setConby(rs.getString("conby"));
                tag.setCon_place(rs.getString("con_place"));
                tag.setCon_utime(rs.getDate("con_utime"));
                tag.setCon_ctime(rs.getDate("con_ctime"));
                tag.setCreatedby(rs.getString("createdby"));
                tag_Array.add(tag);
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
