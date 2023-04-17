package edu.alma.teamleft;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;


import static edu.alma.teamleft.Tables.*;
import static org.jooq.impl.DSL.date;

public class Main {
    public static final String PASSWORD = System.getenv("PG_PASSWORD");
    public static final String USER_NAME = System.getenv("PG_USERNAME");
    public static void main(String[] args){

        final String URL = "jdbc:postgresql://localhost/EightCAP";

        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            System.out.println("Successfully Connected to the Database");

            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            addPhoneNumber("2", conn);


//            // Simple select statement to test connection
//            Result<Record> result = create
//                    .select()
//                    .from(PREFERRED_CONTACT)
//                    .fetch();
//
//            for (Record r : result){
//                String method = r.getValue(PREFERRED_CONTACT.CONTACT_METHOD);
//                System.out.println("Method: " + method);
//            }

//            Random rand = new Random();
//            StringBuilder numberBuilder = new StringBuilder();
//            for(int i = 0; i < 10; i++){
//                int num = rand.nextInt(9) + 1;
//                numberBuilder.append(num);
//            }
//            String number = numberBuilder.toString();
//            addPhoneNumber(number, conn);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPhoneNumber(String number, Connection conn){
        DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
        create.insertInto(PHONE_NUMBER,
                        PHONE_NUMBER.PHONE_NUMBER_)
                .values(number)
                .execute();
    }

    public static void addAddress(String line1, String line2, String city, String state, int type, Connection conn){
        DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
        create.insertInto(ADDRESS,
                ADDRESS.ADDRESS_LINE_1, ADDRESS.ADDRESS_LINE_2, ADDRESS.CITY, ADDRESS.STATE, ADDRESS.ADDRESS_TYPE_ID)
                .values(line1, line2, city, state, type)
                .execute();
    }

    public static void addEmail(String emailAddress, Connection conn){
        DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
//        create.insertInto()
    }

    public static void addClient(String first, String last, String dob, int dba, int hmis, Connection conn){
        DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

        Field<Date> date_birth = date(dob);
        LocalDate date = LocalDate.now();
        Field<Date> date_entry = date(String.valueOf(date));
//
//        create.insertInto(CLIENT,
//                CLIENT.FIRST_NAME, CLIENT.LAST_NAME, CLIENT.DATE_OF_BIRTH, CLIENT.DATETIME_OF_ENTRY, CLIENT.DBA_ID, CLIENT.HMIS)
//                .values(first, last, date(dob), date(String.valueOf(LocalDate.now())), dba, hmis)
//                .execute();
    }
}
