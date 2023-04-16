package edu.alma.teamleft;

import edu.alma.teamleft.tables.Followups;
import edu.alma.teamleft.tables.Service;
import edu.alma.teamleft.tables.Status;
import edu.alma.teamleft.tables.Ticket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jooq.Result;
import org.jooq.Record;
import org.jooq.impl.QOM;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Objects;

import static edu.alma.teamleft.ticketdatabase.*;
import static java.lang.Integer.parseInt;

public class EightCAPController {

    public TextField dbTicketSubject;
    public TextField dbTicketClientID;
    public ChoiceBox dbTicketStatus;
    public ChoiceBox dbTicketService;
    public TableView<Result> TicketDatabase;
    public TableColumn<Result, Integer> dbTicketIDColumn;
    public TableColumn<Result, String> dbTicketSubjectColumn;
    public TableColumn<Result, Integer> dbTicketClientColumn;
    public TableColumn<Result, Integer> dbTicketCOTColumn;
    public TableColumn<Result, String> dbTicketCallDateColumn;
    public TableColumn<Result, String> dbTicketStatusColumn;
    public TableColumn<Result, String> dbTicketServiceColumn;
    public TextField ticketSubject;
    public TextArea ticketDetails;
    public TextField ticketClient;
    public ChoiceBox ticketService;
    public TextField followUpTicketID;
    public TextArea followUpOutcome;
    public String NewTicketSubject;
    public String NewTicketDetails;
    public Integer NewTicketClient;
    public Integer NewTicketService;
    public Integer NewFollowUpTicket;
    public String NewFollowupOutcome;


    Result<Record> statuses = create.select()
            .from(Status.STATUS)
            .fetch();

    Result<Record> services = create.select()
            .from(Service.SERVICE)
            .fetch();
    public void initialize() {
        for (Record r : statuses) {
            dbTicketStatus.getItems().add((r.getValue(Status.STATUS.STATUS_NAME)));
        }
        for (Record r : services) {
            dbTicketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));
            ticketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));

        }
    }
    public void AddEmail(ActionEvent actionEvent) {
    }

    public void AddPhoneNumber(ActionEvent actionEvent) {
    }

    public void AddAddress(ActionEvent actionEvent) {
    }

    public void AddClient(ActionEvent actionEvent) {
    }

    public void ClientSearch(ActionEvent actionEvent) {
    }

    public void AddTicket(ActionEvent actionEvent) {
        NewTicketSubject = ticketSubject.getText();
        NewTicketDetails = ticketDetails.getText();
        NewTicketClient = parseInt(ticketClient.getText());
        if(ticketService.getValue().equals("Community Service")) {
            NewTicketService = 1;
        }
        if(ticketService.getValue().equals("Eviction Diversion")) {
            NewTicketService = 2;
        }
        create.insertInto(Ticket.TICKET, Ticket.TICKET.SUBJECT, Ticket.TICKET.DETAILS, Ticket.TICKET.CLIENT_ID, Ticket.TICKET.CREATOR_OF_TICKET, Ticket.TICKET.ENTRY_DATE, Ticket.TICKET.CALL_DATE, Ticket.TICKET.STATUS_ID, Ticket.TICKET.SERVICE_TYPE_ID)
                .values(NewTicketSubject, NewTicketDetails, NewTicketClient, 1, LocalDateTime.now(), LocalDateTime.now(), 1, NewTicketService)
                .execute();
    }

    public void SearchTicket(ActionEvent actionEvent) {

        try {
            if (dbTicketClientID != null) {
                SelectedClientid = dbTicketClientID.getText();
            }
            if (dbTicketSubject != null) {
                SelectedSubject = dbTicketSubject.getText();
            }
            if (dbTicketStatus != null) {
                if (dbTicketStatus.getValue().equals("Open")) {
                    SelectedStatus = 1;
                }
                if (dbTicketStatus.getValue().equals("Closed")) {
                    SelectedStatus = 2;
                }
                if (dbTicketStatus.getValue().equals("Waiting On Client")) {
                    SelectedStatus = 3;
                }
                if (dbTicketStatus.getValue().equals("Pending Prescreening")) {
                    SelectedStatus = 4;
                }
                System.out.println(dbTicketStatus.getValue());
                System.out.println(SelectedStatus);
            }
            else{
                SelectedStatus = null;
            }
        }
        catch (Exception e){
            SelectedStatus = null;
        }
        try {
            if (dbTicketService != null) {
                if (dbTicketService.getValue().equals("Community Service")) {
                    SelectedService = 1;
                }
                if (dbTicketService.getValue().equals("Eviction Diversion")) {
                    SelectedService = 2;
                }
            }
            else{
                SelectedService = null;
            }
        }
        catch (Exception e){
            SelectedService = null;
        }
        if(Objects.equals(SelectedClientid, ""))
        {SelectedClientid = null;}
        if(Objects.equals(SelectedSubject, ""))
        {SelectedSubject = null;}
        if (SelectedClientid == null) {
            if(SelectedSubject == null){
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectAllTickets();
                    }
                    else{
                        SelectTicketWithService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithStatus();
                    }
                    else{
                        SelectTicketWithStatusAndService();
                    }
                }
            }
            else{
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithSubject();
                    }
                    else{
                        SelectTicketWithSubjectAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithSubjectAndStatus();
                    }
                    else{
                        SelectTicketWithSubjectAndStatusAndService();
                    }
                }
            }
        }
        else{
            if(SelectedSubject == null){
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithID();
                    }
                    else{
                        SelectTicketWithIDAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithIDAndStatus();
                    }
                    else{
                        SelectTicketWithIDAndStatusAndService();
                    }
                }
            }
            else{
                if(SelectedStatus == null){
                    if(SelectedService == null){
                        SelectTicketWithIDAndSubject();
                    }
                    else{
                        SelectTicketWithIDAndSubjectAndService();
                    }
                }
                else{
                    if(SelectedService == null){
                        SelectTicketWithIDAndSubjectAndStatus();
                    }
                    else{
                        SelectTicketWithAll();
                    }
                }
            }
        }
    }
    public void AddFollowUP(ActionEvent actionEvent) {
        NewFollowUpTicket = parseInt(followUpTicketID.getText());
        NewFollowupOutcome = followUpOutcome.getText();
        create.insertInto(Followups.FOLLOWUPS, Followups.FOLLOWUPS.TICKET_ID, Followups.FOLLOWUPS.FOLLOWUP_DATE, Followups.FOLLOWUPS.FOLLOWUP_OUTCOME)
                .values(NewFollowUpTicket, LocalDateTime.now(), NewFollowupOutcome)
                .execute();
    }

    public void FollowUpSearch(ActionEvent actionEvent) {
    create.select();
    }
}
