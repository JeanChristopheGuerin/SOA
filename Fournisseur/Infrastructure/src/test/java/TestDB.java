import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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
        List<Product> res2 = db.findProducts(Arrays.asList("a","b","c"));
        System.out.println(res.getName());
        for(Product each : res2){
        	System.out.println(each.getDescription().getDescriptionText());
        }
    }
}
