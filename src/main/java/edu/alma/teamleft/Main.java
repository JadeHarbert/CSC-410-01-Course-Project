package edu.alma.teamleft;

import edu.alma.teamleft.tables.PreferredContact;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static edu.alma.teamleft.Tables.*;

public class Main {
    public static final String PASSWORD = System.getenv("PASSWORD");
    public static void main(String[] args){

        final String USER_NAME = "postgres";
        final String URL = "jdbc:postgresql://localhost/eightcap";


        System.out.println("Hello World");


        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            Result<Record> result = create
                    .select()
                    .from(PREFERRED_CONTACT)
                    .fetch();
            for (Record r : result){
                String method = r.getValue(PREFERRED_CONTACT.CONTACT_METHOD);
                System.out.println("Method: " + method);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
