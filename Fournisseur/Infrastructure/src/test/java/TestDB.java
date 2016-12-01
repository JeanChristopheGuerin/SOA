import java.sql.SQLException;

import org.junit.Test;

import DB.DataBase;
import entities.Product;

public class TestDB {
	@Test
    public void test0() throws SQLException {
        DataBase db = new DataBase();
        db.connectDB();
        db.createTables();
        
        Product res = db.findProduct("a");
        System.out.println(res.getName());
        
    }
}
