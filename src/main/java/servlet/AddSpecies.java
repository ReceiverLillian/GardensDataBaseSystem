package servlet;

import util.DBUtil;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddSpecies")
public class AddSpecies extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取表单字段的值
        String speciesName = request.getParameter("speciesName");
        String alias = request.getParameter("alias");
        String family = request.getParameter("family");
        String genus = request.getParameter("genus");
        String morphology = request.getParameter("morphology");
        String cultivation = request.getParameter("cultivation");
        String application = request.getParameter("application");
        String province = request.getParameter("province");
        String environment = request.getParameter("environment");


        // 执行数据库操作
        Connection connection = DBUtil.getConnectDb();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {

            // 插入到 species 表
            String insertSpeciesSql = "INSERT INTO species (species_name, species_othername, species_morph, species_tech, species_appl, species_environment) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            System.out.println(insertSpeciesSql);
            PreparedStatement speciesStatement = connection.prepareStatement(insertSpeciesSql, PreparedStatement.RETURN_GENERATED_KEYS);

            // 设置插入语句的参数值
            speciesStatement.setString(1, speciesName);
            speciesStatement.setString(2, alias);
            speciesStatement.setString(3, morphology);
            speciesStatement.setString(4, cultivation);
            speciesStatement.setString(5, application);
            speciesStatement.setString(6,environment);
            // 执行插入
            speciesStatement.executeUpdate();

            // 获取插入的 species_id
            ResultSet generatedKeys = speciesStatement.getGeneratedKeys();
            int speciesId = 0;
            if (generatedKeys.next()) {
                speciesId = generatedKeys.getInt(1);
                System.out.println("species_id=" + speciesId);
            }

            // 关闭 species 表的声明
            speciesStatement.close();

            // 插入到 genus 表
            String insertGenusSql = "INSERT INTO genus (genus_name) VALUES (?)";
            System.out.println(insertGenusSql);
            PreparedStatement genusStatement = connection.prepareStatement(insertGenusSql, PreparedStatement.RETURN_GENERATED_KEYS);
            genusStatement.setString(1, genus);
            // 执行插入
            genusStatement.executeUpdate();

            // 获取插入的 genus_id
            ResultSet generatedGenusKeys = genusStatement.getGeneratedKeys();
            int genusId = 0;
            if (generatedGenusKeys.next()) {
                genusId = generatedGenusKeys.getInt(1);
                System.out.println("genus_id="+genusId);
            }
            // 关闭 genus 表的声明
            genusStatement.close();

            // 插入到 genus_species 表
            String insertGenusSpeciesSql = "INSERT INTO genus_species (genus_id, species_id) VALUES (?, ?)";
            System.out.println(insertGenusSpeciesSql);
            PreparedStatement genusSpeciesStatement = connection.prepareStatement(insertGenusSpeciesSql);
            genusSpeciesStatement.setInt(1, genusId);
            genusSpeciesStatement.setInt(2, speciesId);

            // 执行插入
            genusSpeciesStatement.executeUpdate();

            // 关闭 genus_species 表的声明
            genusSpeciesStatement.close();

            //获取插入的province_id
            // 执行查询语句
            String query = "SELECT province_id FROM province WHERE province_name = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,province);
            ResultSet resultSet = ps.executeQuery();

            int provinceId = 0;
            if (resultSet.next()) {
                // 获取查询结果中的province_id
                provinceId = resultSet.getInt("province_id");
            }


            // 插入到 province_species表
            String insertProvinceSpeciesSql = "INSERT INTO province_species (province_id, species_id) VALUES (?, ?)";
            System.out.println(insertProvinceSpeciesSql);
            PreparedStatement provinceSpeciesStatement = connection.prepareStatement(insertProvinceSpeciesSql);
            provinceSpeciesStatement.setInt(1, provinceId);
            provinceSpeciesStatement.setInt(2, speciesId);
            // 执行插入
            provinceSpeciesStatement.executeUpdate();
            // 关闭 genus_species 表的声明
            provinceSpeciesStatement.close();


            // 插入到 family 表
            String insertFamilySql = "INSERT INTO family (family_name) VALUES (?)";
            System.out.println(insertFamilySql);
            PreparedStatement familyStatement = connection.prepareStatement(insertFamilySql, PreparedStatement.RETURN_GENERATED_KEYS);
            familyStatement.setString(1, family);

            // 执行插入
            familyStatement.executeUpdate();

            // 获取插入的 family_id
            ResultSet generatedFamilyKeys = familyStatement.getGeneratedKeys();
            int familyId = 0;
            if (generatedFamilyKeys.next()) {
                familyId = generatedFamilyKeys.getInt(1);
            }



            // 关闭 family 表的声明
            familyStatement.close();

            // 插入到 family_genus 表
            String insertFamilyGenusSql = "INSERT INTO family_genus (family_id, genus_id) VALUES (?, ?)";
            System.out.println(insertFamilyGenusSql);
            PreparedStatement familyGenusStatement = connection.prepareStatement(insertFamilyGenusSql);
            familyGenusStatement.setInt(1, familyId);
            familyGenusStatement.setInt(2, genusId);

            // 执行插入
            familyGenusStatement.executeUpdate();

            // 关闭 family_genus 表的声明
            familyGenusStatement.close();

            // 提交事务
            connection.commit();

            // 关闭连接
            connection.close();

            response.sendRedirect(request.getContextPath() + "/basicMessageManagement");

        } catch (SQLException e) {
            // 处理异常
            e.printStackTrace();

            // 回滚事务
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            // 关闭连接
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            response.sendRedirect("error.jsp");
        }
    }
}