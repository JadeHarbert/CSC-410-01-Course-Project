package edu.alma.teamleft;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static edu.alma.teamleft.ticketdatabase.*;
import static java.lang.Integer.parseInt;

public class EightCAPController {

    public TextField dbTicketSubject;
    public TextField dbTicketClientID;
    public ChoiceBox dbTicketStatus;

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
        if(dbTicketClientID != null){
            SelectedClientid = dbTicketClientID.getText();
        }
        if(dbTicketSubject != null){
            SelectedSubject = dbTicketSubject.getText();
        }

        SelectAllTickets();
    }

    public void AddFollowUP(ActionEvent actionEvent) {
    }

    public void FollowUpSearch(ActionEvent actionEvent) {
    }
}