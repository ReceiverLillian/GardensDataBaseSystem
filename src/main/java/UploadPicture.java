import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

//更改一下
public class UploadPicture {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gardens";
        String username = "root";
        String password = "123456";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = null;
            //int rs = 0;

            String sql_insert = "insert into picture(picture_id, picture_content) values (?,?)";//sql语句
            File image = new File("C:\\Mycode\\Javacode\\IDEAProject\\GardensDataBaseSystem\\imgs\\picture04.jpg");
            try {
                pst = conn.prepareStatement(sql_insert);
                pst.setInt(1, 4);


                FileInputStream fis = new FileInputStream(image);
                pst.setBinaryStream(2, (InputStream) fis, (int) (image.length()));

                pst.executeUpdate();


                conn.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
