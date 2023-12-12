package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/UpdateSort")
public class UpdateSort extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取表单中的数据
        String speciesId = request.getParameter("speciesId");
        String speciesName = request.getParameter("speciesName");
        String alias = request.getParameter("alias");   //别名
        String family = request.getParameter("family");
        String genus = request.getParameter("genus");
        String province = request.getParameter("province");
        String environment = request.getParameter("environment");

        System.out.println("ccccccc  "+speciesId+":"+speciesName);


        try (Connection connection = DBUtil.getConnectDb();) {
            // 获取属名对应的属ID
            String genusId = getGenusId(connection, genus);

            // 获取科名对应的科ID
            String familyId = getFamilyId(connection, genusId);

            //获取省份对应的ID
            String provinceId = getProvinceId(connection, province);
            // 更新植物种表
            updateSpeciesTable(connection, speciesId, speciesName, alias, environment);
            //更新省份表
            updateProvince(connection,provinceId,speciesId);
            // 更新属名和科名
            updateGenusAndFamily(connection, genusId, familyId, speciesId);
        } catch (SQLException e) {
            // 处理数据库连接或查询错误
            e.printStackTrace();
        }

        // 重定向到 /gardens/basicMessageManagement
        response.sendRedirect(request.getContextPath() + "/sortManagement");
    }

    private String getGenusId(Connection connection, String genusName) throws SQLException {
        String genusId = null;

        // 查询属ID
        String query = "SELECT genus_id FROM genus WHERE genus_name = ?";
        System.out.println(query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genusName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    genusId = resultSet.getString("genus_id");
                }
            }
        }

        return genusId;
    }

    private String getProvinceId(Connection connection, String provinceName) throws SQLException {
        String provinceId = null;

        // 查询属ID
        String query = "SELECT province_id FROM province WHERE province_name = ?";
        System.out.println(query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, provinceName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    provinceId = resultSet.getString("province_id");
                }
            }
        }

        return provinceId;
    }


    private String getFamilyId(Connection connection, String genusId) throws SQLException {
        String familyId = null;

        // 查询科ID
        String query = "SELECT family_id FROM family_genus WHERE genus_id = ?";
        System.out.println(query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, genusId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    familyId = resultSet.getString("family_id");
                }
            }
        }

        return familyId;
    }

    private void updateSpeciesTable(Connection connection, String speciesId, String speciesName, String alias, String environment) throws SQLException {
        // 更新植物种表
        String updateQuery = "UPDATE species SET species_name = ?, species_othername = ?, species_environment = ?, updatetime = ? WHERE species_id = ?";
        System.out.println(updateQuery);
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, speciesName);
            updateStatement.setString(2, alias);
            updateStatement.setString(3, environment);
            updateStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            updateStatement.setString(5, speciesId);

            updateStatement.executeUpdate();
        }
    }

    private void updateGenusAndFamily(Connection connection, String genusId, String familyId, String speciesId) throws SQLException {
        // 更新属名和科名
        String updateQuery = "UPDATE genus_species SET genus_id = ? WHERE species_id = ?";
        System.out.println(updateQuery);
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, genusId);
            updateStatement.setString(2, speciesId);

            updateStatement.executeUpdate();
        }

        updateQuery = "UPDATE family_genus SET family_id = ? WHERE genus_id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, familyId);
            updateStatement.setString(2, genusId);

            updateStatement.executeUpdate();
        }
    }

    private void updateProvince(Connection connection, String provinceId, String speciesId) throws SQLException {
        // 更新属名和科名
        String updateQuery = "UPDATE province_species SET province_id = ? WHERE species_id = ?";
        System.out.println(updateQuery);
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, provinceId);
            updateStatement.setString(2, speciesId);

            updateStatement.executeUpdate();
        }

    }

}