package dao;

import bean.DiseaseBean;
import bean.MedicineBean;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDao {
    public MedicineBean selectMedicineById(int id){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from medicine where med_id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        MedicineBean medicineBean = new MedicineBean();

        try {
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                medicineBean.setMed_id(rs.getInt("med_id"));
                medicineBean.setMed_name(rs.getString("med_name"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return medicineBean;
    }

    public ArrayList<MedicineBean> selectAllMedicine(){
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from medicine";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<MedicineBean> medicineBeans =new ArrayList<>();
        try {
            stm = conn.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                MedicineBean medicineBean = new MedicineBean();
                medicineBean.setMed_id(rs.getInt("med_id"));
                medicineBean.setMed_name(rs.getString("med_name"));
                medicineBeans.add(medicineBean);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.CloseDB(rs, stm, conn);
        }
        return medicineBeans;
    }
}
