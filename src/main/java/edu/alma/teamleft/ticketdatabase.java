package edu.alma.teamleft;

import edu.alma.teamleft.tables.Ticket;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static edu.alma.teamleft.Main.PASSWORD;
import static edu.alma.teamleft.Main.USER_NAME;

public class ticketdatabase {
    static final String URL = "jdbc:postgresql://localhost/EightCAP";
    public static int SelectedClientid;
    public static String SelectedSubject;
    public static int SelectedStatus;
    public static int SelectedService;
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

    public static Result<Record> SelectAllTickets(){
            return(create.select()
                    .from(Ticket.TICKET)
                    .fetch());
}

    public static Result<Record> SelectTicketWithID(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(SelectedClientid))
                .fetch());
    }

    public static Result<Record> SelectTicketWithSubject(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithStatus(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch());
    }

    public static Result<Record> SelectTicketWithService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch());
    }
    public ticketdatabase() throws SQLException {
    }}
