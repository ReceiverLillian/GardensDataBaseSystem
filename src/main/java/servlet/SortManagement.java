package servlet;

import bean.Species;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sortManagement")
public class SortManagement extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("已经进入SortManagementServlet的处理流程中。");
        List<Species> speciesList = retrieveSortData(); // 从数据库查询植物基本信息列表
        request.setAttribute("speciesList", speciesList); // 将查询结果传递到JSP页面
        request.getRequestDispatcher("/sortManagement.jsp").forward(request, response);
    }

    private List<Species> retrieveSortData() {
        List<Species> plantList = new ArrayList<>();

        try {
            // 连接到数据库
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gardens", "root", "123456");
            System.out.println("连接garden数据库成功。");

            // 执行查询语句
            Statement stmt = conn.createStatement();
            /*ResultSet rs = stmt.executeQuery("SELECT species.species_id, species.species_name, species.species_othername, family.family_name, genus.genus_name, species.species_morph, species.species_tech, species.species_appl, picture.picture_descri, picture.picture_place, picture.photoedby FROM species " +
                    "JOIN picture_species ON species.species_id = picture_species.species_id " +
                    "JOIN picture ON picture_species.picture_id = picture.picture_id " +
                    "JOIN genus_species ON species.species_id = genus_species.species_id " +
                    "JOIN genus ON genus_species.genus_id = genus.genus_id " +
                    "JOIN family_genus ON genus.genus_id = family_genus.genus_id " +
                    "JOIN family ON family_genus.family_id = family.family_id");*/

            ResultSet rs = stmt.executeQuery("SELECT s.species_id, s.species_name, s.species_othername, s.createdtime, s.updatetime, s.createdby,s.species_environment,\n" +
                    "       p.province_name,\n" +
                    "       g.genus_name,\n" +
                    "       f.family_name\n" +
                    "FROM species s\n" +
                    "JOIN province_species ps ON s.species_id = ps.species_id\n" +
                    "JOIN province p ON ps.province_id = p.province_id\n" +
                    "JOIN genus_species gs ON s.species_id = gs.species_id\n" +
                    "JOIN genus g ON gs.genus_id = g.genus_id\n" +
                    "JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                    "JOIN family f ON fg.family_id = f.family_id");

            // 处理查询结果
            while (rs.next()) {
                System.out.println("aaaaaaaaa");
                int speciesId = rs.getInt("species_id");
                String speciesName = rs.getString("species_name");
                String speciesOtherName = rs.getString("species_othername");
                String familyName = rs.getString("family_name");
                String genusName = rs.getString("genus_name");
                String environment = rs.getString("species_environment");
                String createdby = rs.getString("createdby");
                Date createdTime = rs.getDate("createdtime");
                String provinceName = rs.getString("province_name");

                System.out.println("*******"+speciesName);


                //Species plant= new Species(speciesId, speciesName, speciesOtherName, familyName, genusName, speciesMorph, speciesTech, speciesAppl, pictureDescri, picturePlace, photoedBy);
                Species plant = new Species();
                plant.setSpeciesId(speciesId);
                plant.setSpeciesName(speciesName);
                plant.setSpeciesOtherName(speciesOtherName);
                plant.setFamilyName(familyName);
                plant.setGenusName(genusName);
                plant.setEnvironment(environment);
                plant.setCreatedby(createdby);
                plant.setCreateTime(createdTime);
                plant.setProvince(provinceName);
                plantList.add(plant);
            }

            // 关闭数据库连接
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantList;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}