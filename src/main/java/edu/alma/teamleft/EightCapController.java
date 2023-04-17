package edu.alma.teamleft;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static edu.alma.teamleft.Tables.*;

public class EightCapController {
    public TextField    clientPhoneNumber, clientEmail, clientFirstName, clientLastName, clientDBA,
                        clientHMIS, clientAddressLine1, clientAddressLine2, clientCity, clientState;
    public DatePicker clientDOB;
    public ChoiceBox<String> clientCounty, clientPCM, clientAddressType;
    public TextArea clientNotes;

    private DSLContext create;
    public EightCapController() throws SQLException {
        String PASSWORD = System.getenv("PG_PASSWORD");
        String USER_NAME = System.getenv("PG_USERNAME");
        String URL = "jdbc:postgresql://localhost/eightcap";
        Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        create = DSL.using(conn, SQLDialect.POSTGRES);
    }

    public void initialize() {
        Result<Record> result = create.select()
                .from(COUNTY)
                .fetch();
        for(Record r : result){
            clientCounty.getItems().add(r.getValue(COUNTY.COUNTY_NAME));
        }
        result = create.select()
                .from(PREFERRED_CONTACT)
                .fetch();
        for(Record r: result){
            clientPCM.getItems().add(r.getValue(PREFERRED_CONTACT.CONTACT_METHOD));
        }
        result = create.select()
                .from(ADDRESS_TYPE)
                .fetch();
        for(Record r: result){
            clientAddressType.getItems().add(r.getValue(ADDRESS_TYPE.ADDRESS_TYPE_));
        }
    }

    @FXML
    protected void AddClient() {
        String first = clientFirstName.getText();
        String last = clientLastName.getText();
        LocalDate dob = clientDOB.getValue();
        String county = clientCounty.getValue();
        String contactMethod = clientPCM.getValue();
        String email = clientEmail.getText();
        String notes = clientNotes.getText();
        int hmis = Integer.parseInt(clientHMIS.getText());
        int dba = Integer.parseInt(clientDBA.getText());
        LocalDate now = LocalDate.now();

        create.insertInto(CLIENT,
                CLIENT.FIRST_NAME, CLIENT.LAST_NAME, CLIENT.DATE_OF_BIRTH, CLIENT.EMAIL, CLIENT.NOTES, CLIENT.HMIS, CLIENT.DBA_ID, CLIENT.DATETIME_OF_ENTRY)
                .values(first, last, dob, email, notes, hmis, dba, now.atStartOfDay())
                .execute();
        System.out.println("Client added");
    }
    @FXML
    protected void AddPhoneNumber() {
        String number = clientPhoneNumber.getText();
        create.insertInto(PHONE_NUMBER, PHONE_NUMBER.PHONE_NUMBER_)
                .values(number)
                .execute();

        Result<Record> result = create.select()
                .from(PHONE_NUMBER)
                .fetch();
        for(Record r: result){
            System.out.print(r.getValue(PHONE_NUMBER.PHONE_NUMBER_ID));
            System.out.print(" " + r.getValue(PHONE_NUMBER.PHONE_NUMBER_));
            System.out.println(" " + r.getValue(PHONE_NUMBER.NUMBER_TYPE_ID));

        }
    }
    @FXML
    protected void AddAddress() {
        String line1 = clientAddressLine1.getText();
        String line2 = clientAddressLine2.getText();
        String city = clientCity.getText();
        String state = clientState.getText();


    }


    @FXML
    protected void ClientSearch() {

    }

    @FXML
    protected void AddTicket() {

    }
    @FXML
    protected void SearchTicket() {

    }
    @FXML
    protected void AddFollowUP() {

    }
    @FXML
    protected void FollowUpSearch() {

    }

    public void AddEmail(ActionEvent actionEvent) {
    }
}
