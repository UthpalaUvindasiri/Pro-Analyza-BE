package restPackage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class SaveFiles {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public SaveFiles(String projectID) {
        File ff = new File("D:\\4thyearBackEnd\\temp_files\\");
        try {
            FileUtils.cleanDirectory(ff);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        con = connect();
        try {
            String sql = "SELECT * from files where projectID = " + projectID;
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                //Retrieve by column name
                String file = rs.getString("file");

                String name = rs.getString("fileName");

                System.out.println(file.substring(13));
//System.out.println(file);


                FileOutputStream fos = new FileOutputStream("D:\\4thyearBackEnd\\temp_files\\" + name);
//fos.write(Base64.getDecoder().decode(x));

                byte[] byteData = file.substring(13).getBytes("UTF-8");
                fos.write(Base64.getUrlDecoder().decode(byteData));

                fos.close();
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection connect() {
        Connection connectdb = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectdb = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jcomplex", "root", "");

        } catch (Exception error) {
            System.out.println(error);
        }
        return connectdb;
    }
}


