/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alyx
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() throws Exception {

        Class.forName("org.h2.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test",
                "sa", "");
        Statement st = null;
        st = conn.createStatement();
//        st.execute("INSERT INTO TEST VALUES(3,'HELLO')");
        //st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
//        String name1 = "Jack";
//        String q = "insert into TEST(name) values(?)";
//        PreparedStatement st1 = null;
//
//        st1 = conn.prepareStatement(q);
//        st1.setString(1, name1);
//        st1.execute();

        ResultSet result;
        result = st.executeQuery("SELECT * FROM TEST");
        while (result.next()) {
            String name = result.getString("NAME");
            System.out.println(result.getString("ID") + " " + name);
        }

    }
}
