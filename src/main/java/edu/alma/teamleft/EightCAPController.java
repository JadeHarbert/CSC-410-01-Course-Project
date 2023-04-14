package edu.alma.teamleft;

import edu.alma.teamleft.tables.Service;
import edu.alma.teamleft.tables.Status;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jooq.Result;
import org.jooq.Record;
import org.jooq.impl.QOM;

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
            dbTicketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));/*        dbTicketIDColumn.setCellValueFactory(new PropertyValueFactory<>("dbTicketIDColumn"));
        dbTicketSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("dbTicketSubjectColumn"));
        dbTicketClientColumn.setCellValueFactory(new PropertyValueFactory<>("dbTicketClientColumn"));
        dbTicketCOTColumn.setCellValueFactory(new PropertyValueFactory<>("dbTicketCOTColumn"));*/

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
    }

    public void SearchTicket(ActionEvent actionEvent) {
/*
        if (dbTicketClientID != null) {
            SelectedClientid = dbTicketClientID.getText();
        }
        if (dbTicketSubject != null) {
            SelectedSubject = dbTicketSubject.getText();
        }
        if (dbTicketStatus != null) {
            if (dbTicketStatus.getValue().toString() == "Open") {
                SelectedStatus = "1";
            }
            if (dbTicketStatus.getValue().toString() == "Closed") {
                SelectedStatus = "2";
            }
            if (dbTicketStatus.getValue().toString() == "Waiting On Client") {
                SelectedStatus = "3";
            }
            if (dbTicketStatus.getValue().toString() == "Pending Prescreening") {
                SelectedStatus = "4";
            }
        }
        else {
            SelectedStatus = null;
        }
        if (dbTicketService != null) {
            if (dbTicketService.getValue().toString() == "Community Service") {
                SelectedService = "1";
            }
            if (dbTicketService.getValue().toString() == "Eviction Diversion") {
                SelectedService = "2";
            }
        }*/
        SelectAllTickets();
        for(Object item : results){
            TicketDatabase.getItems().add(results);
        }
    }
    public void AddFollowUP(ActionEvent actionEvent) {
    }

    public void FollowUpSearch(ActionEvent actionEvent) {
    }
}
