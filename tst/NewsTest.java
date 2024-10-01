import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;


public class NewsTest {

    private static Connection connection;
    private static final String DB_URL = "jdbc:sqlite:news.sqlite";

    @BeforeClass
    public static void setUp() throws Exception {
        connection = DriverManager.getConnection(DB_URL);
    }

    //create a helper method that returns the size of the table

    @Test
    public void testInsert() throws Exception {
        int before = getTableSize();
        String sql = "insert into news values(null,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "test");
        ps.setString(2, "test");
        ps.execute();
        ps.close();
        int after = getTableSize();
        Assert.assertEquals(before + 1, after);
    }

    //create a helper method that returns of the size of the table
    private int getTableSize() throws SQLException {
        String sql = "select count(*) from news;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int size = resultSet.getInt(1);
        statement.close();
        return size;
    }

    @Test
    public void testTableSize() throws Exception {
        String sql = "select count(*) from news;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int size = resultSet.getInt(1);
        Assert.assertEquals(0, size);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        connection.close();
    }
}
