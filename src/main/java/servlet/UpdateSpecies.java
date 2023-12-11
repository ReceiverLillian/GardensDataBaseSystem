package servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

@WebServlet("/UpdateSpecies")
public class UpdateSpecies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取表单中的数据
        String speciesId = request.getParameter("speciesId");
        String speciesName = request.getParameter("speciesName");
        String alias = request.getParameter("alias");   //别名
        String family = request.getParameter("family");
        String genus = request.getParameter("genus");
        String morphology = request.getParameter("morphology");  //特征
        String cultivation = request.getParameter("cultivation");  //栽培技术
        String application = request.getParameter("application");  //应用

        System.out.println("ccccccc  "+speciesId+":"+speciesName);


        try (Connection connection = DBUtil.getConnectDb();) {
            // 获取属名对应的属ID
            String genusId = getGenusId(connection, genus);

            // 获取科名对应的科ID
            String familyId = getFamilyId(connection, genusId);

            // 更新植物种表
            updateSpeciesTable(connection, speciesId, speciesName, alias, morphology, cultivation, application);

            // 更新属名和科名
            updateGenusAndFamily(connection, genusId, familyId, speciesId);
        } catch (SQLException e) {
            // 处理数据库连接或查询错误
            e.printStackTrace();
        }

        // 重定向到 /gardens/basicMessageManagement
        response.sendRedirect(request.getContextPath() + "/basicMessageManagement");
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

    private void updateSpeciesTable(Connection connection, String speciesId, String speciesName, String alias, String morphology, String cultivation, String application) throws SQLException {
        // 更新植物种表
        String updateQuery = "UPDATE species SET species_name = ?, species_othername = ?, species_morph = ?, species_tech = ?, species_appl = ?, updatetime = ? WHERE species_id = ?";
        System.out.println(updateQuery);
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, speciesName);
            updateStatement.setString(2, alias);
            updateStatement.setString(3, morphology);
            updateStatement.setString(4, cultivation);
            updateStatement.setString(5, application);
            updateStatement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
            updateStatement.setString(7, speciesId);

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
}