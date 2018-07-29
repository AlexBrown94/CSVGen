import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class CSVdb {

    private Path filePath;
    private String url;

    private Connection con;

    public static void main(String[] args)
    {
        CSVdb csv = new CSVdb();
        csv.init(args);

    }

    public void init(String[] args)
    {
        filePath = Paths.get(args[0]);
        url = args[1];
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "root");
            createDB(con);
            parseCSVFile(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void parseCSVFile(Path filePath)
    {
        try
        {
            String firstName;
            String lastName;
            int age;

            BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
            br.readLine();
            String data = br.readLine();

            while(data != null)
            {
                firstName = data.split(",")[0];
                lastName = data.split(",")[1];
                age = Integer.parseInt(data.split(",")[2]);
                insertIntoDB(con, firstName, lastName, age);
                data = br.readLine();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createDB(Connection con)
    {
        try
        {
            Statement s = con.createStatement();
            s.executeUpdate("CREATE TABLE people ( id INT NOT NULL AUTO_INCREMENT, FIRSTNAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT NOT NULL, PRIMARY KEY (id ))");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void insertIntoDB(Connection con, String firstName, String lastName, int age)
    {
        try
        {
            PreparedStatement ps = con.prepareStatement("insert into people.people values (default, ?, ?, ?)");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            System.out.println("Inserting: " + firstName + " " + lastName + " " + age);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
