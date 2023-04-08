package edu.alma.teamleft;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static edu.alma.teamleft.Tables.PREFERRED_CONTACT;
import static edu.alma.teamleft.ticketdatabase.SelectAllTickets;


public class Main {
    public static final String PASSWORD = System.getenv("PG_PASSWORD");
    public static final String USER_NAME = System.getenv("PG_USERNAME");

    public static void main(String[] args){

        final String URL = "jdbc:postgresql://localhost/EightCAP";

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            System.out.println("Successfully Connected to the Database");

            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

            // Simple select statement to test connection
            Result<Record> result = create
                    .select()
                    .from(PREFERRED_CONTACT)
                    .fetch();

            for (Record r : result){
                String method = r.getValue(PREFERRED_CONTACT.CONTACT_METHOD);
                System.out.println("Method: " + method);
            }
            System.out.println(SelectAllTickets());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
