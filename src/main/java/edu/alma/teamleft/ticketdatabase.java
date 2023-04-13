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
import static java.lang.Integer.parseInt;

public class ticketdatabase {
    static final String URL = "jdbc:postgresql://localhost/EightCAP";
    public static String SelectedClientid;
    public static String SelectedSubject;
    public static String SelectedStatus;
    public static String SelectedService;
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
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
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
                .where(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndSubject(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndStatus(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithSubjectAndStatus(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithSubjectAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithStatusAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndSubjectAndStatus(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndSubjectAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithIDAndStatusAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }

    public static Result<Record> SelectTicketWithSubjectAndStatusAndService(){
        return(create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(parseInt(SelectedStatus)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(parseInt(SelectedService)))
                .fetch());
    }
    public ticketdatabase() throws SQLException {
    }}
