package dao;

import bean.Err_monBean;
import bean.MonitorBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Err_monDao {
    public ArrayList<Err_monBean> selectAllErr_mon(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from err_mon";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Err_monBean> errMonBeans = new ArrayList<>();

        try {
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Err_monBean errMonBean = new Err_monBean();
                errMonBean.setErr_id(rs.getInt("err_id"));
                errMonBean.setQuo_id(rs.getInt("quo_id"));
                errMonBeans.add(errMonBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return errMonBeans;
    }
}
