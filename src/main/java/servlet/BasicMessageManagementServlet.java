package servlet;

import bean.Species;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/basicMessageManagement")
public class BasicMessageManagementServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("已经进入BasicMessageManagementServlet的处理流程中。");
        List<Species> speciesList = retrievePlantData(); // 从数据库查询植物基本信息列表
        request.setAttribute("speciesList", speciesList); // 将查询结果传递到JSP页面
        request.getRequestDispatcher("/basicMessageManagement.jsp").forward(request, response);
    }

    private List<Species> retrievePlantData() {
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

            ResultSet rs = stmt.executeQuery("SELECT s.species_id, s.species_name, s.species_othername, s.createdtime, s.updatetime, s.createdby,\n" +
                    "       s.species_morph, s.species_tech, s.species_appl,\n" +
                    "       p.picture_id, p.picture_descri, p.picture_place, p.photoedby,\n" +
                    "       g.genus_id, g.genus_name,\n" +
                    "       f.family_id, f.family_name\n" +
                    "FROM species s\n" +
                    "LEFT JOIN picture_species ps ON s.species_id = ps.species_id\n" +
                    "LEFT JOIN picture p ON ps.picture_id = p.picture_id\n" +
                    "LEFT JOIN genus_species gs ON s.species_id = gs.species_id\n" +
                    "LEFT JOIN genus g ON gs.genus_id = g.genus_id\n" +
                    "LEFT JOIN family_genus fg ON g.genus_id = fg.genus_id\n" +
                    "LEFT JOIN family f ON fg.family_id = f.family_id;");

            // 处理查询结果
            while (rs.next()) {
                System.out.println("aaaaaaaaa");
                int speciesId = rs.getInt("species_id");
                String speciesName = rs.getString("species_name");
                String speciesOtherName = rs.getString("species_othername");
                String familyName = rs.getString("family_name");
                String genusName = rs.getString("genus_name");
                String speciesMorph = rs.getString("species_morph");
                String speciesTech = rs.getString("species_tech");
                String speciesAppl = rs.getString("species_appl");
                String pictureDescri = rs.getString("picture_descri");
                String picturePlace = rs.getString("picture_place");
                String photoedBy = rs.getString("photoedby");

                System.out.println("*******"+speciesName);

                Species plant= new Species(speciesId, speciesName, speciesOtherName, familyName, genusName, speciesMorph, speciesTech, speciesAppl, pictureDescri, picturePlace, photoedBy);
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
}