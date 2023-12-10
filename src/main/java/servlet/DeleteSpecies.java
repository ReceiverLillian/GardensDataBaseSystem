package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteSpecies")
public class DeleteSpecies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int speciesId = Integer.parseInt(request.getParameter("speciesId"));
        System.out.println("*****" + speciesId + "*******");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gardens", "root", "123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 开始事务
        try {
            conn.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            // 删除与species相关的picture_species表中的记录
            deleteRecord(conn, "picture_species", "species_id", speciesId);

            // 删除与species相关的picture记录
            deletePictureRecords(conn, speciesId);

            // 删除genus_species表中的相关记录
            deleteRecord(conn, "genus_species", "species_id", speciesId);

            // 删除family_genus表中的相关记录
            deleteRecord(conn, "family_genus", "genus_id", "SELECT genus_id FROM genus_species WHERE species_id = ?", speciesId);

            // 删除species表中的记录
            deleteRecord(conn, "species", "species_id", speciesId);

            // 删除genus表中的相关记录
            deleteRecord(conn, "genus", "genus_id", "SELECT genus_id FROM genus_species WHERE species_id = ?", speciesId);

            // 删除family表中的相关记录
            deleteRecord(conn, "family", "family_id", "SELECT family_id FROM family_genus WHERE genus_id IN (SELECT genus_id FROM genus_species WHERE species_id = ?)", speciesId);

            // 提交事务
            conn.commit();

            // 返回成功响应
            response.getWriter().write("success");
        } catch (SQLException e) {
            e.printStackTrace();
            // 回滚事务
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            // 返回失败响应
            response.getWriter().write("error");
        }
    }

    private void deleteRecord(Connection conn, String tableName, String column, int id) throws SQLException {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + column + " = ?";
        System.out.println(deleteQuery);
        PreparedStatement ps = conn.prepareStatement(deleteQuery);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    private void deleteRecord(Connection conn, String tableName, String column, String subQuery, int id) throws SQLException {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + column + " IN (" + subQuery + ")";
        System.out.println(deleteQuery);
        try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private void deletePictureRecords(Connection conn, int speciesId) throws SQLException {
        String deleteQuery = "DELETE FROM picture WHERE picture_id IN (SELECT picture_id FROM picture_species WHERE species_id = ?)";
        System.out.println(deleteQuery);
        try (PreparedStatement ps = conn.prepareStatement(deleteQuery)) {
            ps.setInt(1, speciesId);
            ps.executeUpdate();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}