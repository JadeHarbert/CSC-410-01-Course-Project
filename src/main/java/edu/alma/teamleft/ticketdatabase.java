package edu.alma.teamleft;

import edu.alma.teamleft.tables.Ticket;
import javafx.scene.control.TableView;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.security.auth.Subject;
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
    public static Integer SelectedStatus;
    public static Integer SelectedService;
    static Connection conn;
    public static Result results;


    static {
        try {
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);

    public static Result<Record> SelectAllTickets(){
            results = create.select()
                    .from(Ticket.TICKET)
                    .fetch();
        System.out.println(results);
            return results;
}

    public static Result<Record> SelectTicketWithID(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithSubject(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithStatus(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(SelectedStatus);
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndSubject(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndStatus(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithSubjectAndStatus(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithSubjectAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithStatusAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndSubjectAndStatus(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndSubjectAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.SUBJECT.contains((SelectedSubject)))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithIDAndStatusAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }

    public static Result<Record> SelectTicketWithSubjectAndStatusAndService(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.SUBJECT.eq(SelectedSubject))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .fetch();
        System.out.println(results);
        return results;
    }
    public static Result<Record> SelectTicketWithAll(){
        results = create.select()
                .from(Ticket.TICKET)
                .where(Ticket.TICKET.CLIENT_ID.eq(parseInt(SelectedClientid)))
                .and(Ticket.TICKET.STATUS_ID.eq(SelectedStatus))
                .and(Ticket.TICKET.SERVICE_TYPE_ID.eq(SelectedService))
                .and(Ticket.TICKET.SUBJECT.eq(SelectedSubject))
                .fetch();
        System.out.println(results);
        return results;
    }
    public ticketdatabase() throws SQLException {
    }}
