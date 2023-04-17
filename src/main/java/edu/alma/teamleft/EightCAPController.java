package edu.alma.teamleft;

import edu.alma.teamleft.tables.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jooq.DeleteOrderByStep;
import org.jooq.Result;
import org.jooq.Record;
import org.jooq.conf.ParamCastMode;
import org.jooq.impl.QOM;

import java.sql.SQLOutput;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import static edu.alma.teamleft.ticketdatabase.*;
import static java.lang.Integer.parseInt;

public class EightCAPController {

    public TextField dbTicketSubject;
    public TextField dbTicketClientID;
    public ChoiceBox dbTicketStatus;
    public ChoiceBox dbTicketService;
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
    public LocalDateTime NewTicketCallDate;
    public DatePicker ticketCallDate;

    public TextField dbClientID;

    public TextField dbClientFirstName;
    public TextField dbCLientLastName;
    public DatePicker dbClientDOB;
    public ChoiceBox dbClientCounty;
    public ChoiceBox dbClientPCM;
    public TextField dbClientDBA;
    public TextField dbClientHMIS;
    public TextField dbClientEmail;
    public TextField dbClientPhoneNumber;

    public Integer CurrentClientID;
    public String CurrentClientFirstName;
    public String CurrentClientLastName;
    public LocalDate CurrentClientDOB;
    public Integer CurrentClientCounty;
    public Integer CurrentClientPCM;
    public Integer CurrentClientDBA;
    public Integer CurrentClientHMIS;
    public String CurrentClientEmail;



    Result<Record> statuses = create.select()
            .from(Status.STATUS)
            .fetch();

    Result<Record> services = create.select()
            .from(Service.SERVICE)
            .fetch();

    Result<Record> counties = create.select()
            .from(County.COUNTY)
            .fetch();

    Result<Record> contactmethods = create.select()
            .from(PreferredContact.PREFERRED_CONTACT)
            .fetch();
    public void initialize() {
        for (Record r : statuses) {
            dbTicketStatus.getItems().add((r.getValue(Status.STATUS.STATUS_NAME)));
        }
        for (Record r : services) {
            dbTicketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));
            ticketService.getItems().add((r.getValue(Service.SERVICE.SERVICE_NAME)));

        }
        for (Record r: counties) {
            dbClientCounty.getItems().add((r.getValue(County.COUNTY.COUNTY_NAME)));
        }
        for (Record r: contactmethods) {
            dbClientPCM.getItems().add((r.getValue(PreferredContact.PREFERRED_CONTACT.CONTACT_METHOD)));
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
        Result ClientSearchResults = null;
        try {
            if (dbClientID != null) {
                CurrentClientID = parseInt(dbClientID.getText());
            }
            if (dbClientID.getText().equals("")) {
                CurrentClientID = null;
            }
        } catch (Exception e) {
            CurrentClientID = null;
        }
        if (dbClientFirstName != null) {
            CurrentClientFirstName = dbClientFirstName.getText();
        }
        if (dbClientFirstName.getText().equals("")) {
            CurrentClientFirstName = null;
        }
        if (dbCLientLastName != null) {
            CurrentClientLastName = dbCLientLastName.getText();
        }
        if (dbCLientLastName.getText().equals("")) {
            CurrentClientLastName = null;
        }
        if (dbClientDOB != null) {
            CurrentClientDOB = dbClientDOB.getValue();
        }
        try {
            if (dbClientCounty.getValue().equals("Montcalm")) {
                CurrentClientCounty = 1;
            } else if (dbClientCounty.getValue().equals("Gratiot")) {
                CurrentClientCounty = 2;
            } else if (dbClientCounty.getValue().equals("Ionia")) {
                CurrentClientCounty = 3;
            } else if (dbClientCounty.getValue().equals("Isabella")) {
                CurrentClientCounty = 4;
            } else {
                CurrentClientCounty = null;
            }
        } // Set County
        catch (Exception e) {
            CurrentClientCounty = null;
        }
        try {
            if (dbClientPCM.getValue() == "phone") {
                CurrentClientPCM = 1;
            } else if (dbClientPCM.getValue() == "WI-FI Phone") {
                CurrentClientPCM = 2;
            } else if (dbClientPCM.getValue() == "email") {
                CurrentClientPCM = 3;
            } else {
                CurrentClientPCM = null;
            }
        } // Set Preferred Contact Method
        catch (Exception e) {
            CurrentClientPCM = null;
        }
        try {
            if (dbClientDBA.getText() != null) {
                CurrentClientDBA = parseInt(dbClientDBA.getText());
            }
            if (dbClientDBA.getText().equals("")) {
                CurrentClientDBA = null;
            }
        } catch (Exception e) {
            CurrentClientDBA = null;
        }
        try {
            if (dbClientHMIS.getText() != null) {
                CurrentClientHMIS = parseInt(dbClientHMIS.getText());
            }
            if (dbClientHMIS.getText().equals("")) {
                CurrentClientHMIS = null;
            }
        } catch (Exception e) {
            CurrentClientHMIS = null;
        }
        if (dbClientEmail.getText() != null) {
            CurrentClientEmail = dbClientEmail.getText();
        }
        if (dbClientEmail.getText().equals("")) {
            CurrentClientEmail = null;
        }
       /* if(dbClientPhoneNumber.getText() != null) {
            CurrentClientPhoneNumber = parseInt(dbClientPhoneNumber.getText());
        }*/
        if (CurrentClientID == null) {
            if (CurrentClientFirstName == null) {
                if (CurrentClientLastName == null) {
                    if (CurrentClientDOB == null) {
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) { // All Null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else { // Email Not Null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // HMIS not null, Email Not Checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {// HMIS not null, Email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // DBA not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {//DBA not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else { //PCM not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else { // PCM not null, Email Not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {// PCM not null, hmis not null, email not checked
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // PCM not null, dba not null, hmis not checked, email not checked
                                    if (dbClientHMIS == null) {
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {//PCM not null, dba not null, email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // PCM not null, dba not null, hmis not null, email not checked
                                        if (dbClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        } else { // County not null, PCM not checked, DBA not checked, HMIS not checked, Email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                        } else {//county, email not null
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // county, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // County not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {// County not null, dba not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// County not null, PCM not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else { // county not null, pcm not null, hmis not null, email not checked
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // county not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    } else {//DOB not null, County not checked,pcm not checked, dba not checked, hmis not checked, email not checked
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// DOB not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {// DOB not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        } else {// dob not null, county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else { //DOB not null, county not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {// Last name not null, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                    if (CurrentClientDOB == null) {
                        if (CurrentClientCounty == null) {
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else { // last name not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// Last name not null, pcm not null, DBA not checked, HMIS not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else {// Last name not null, county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if (CurrentClientPCM == null) {
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {//Last name not null, county not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            } else {// Last name not null, county not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if (CurrentClientDBA == null) {
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                } else {// Last name not null, county not null, pcm not null, dba not null, hmis not checked, email not checked
                                    if (CurrentClientHMIS == null) {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    } else {
                                        if (CurrentClientEmail == null) {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        } else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{ // Last name not null, DOB not null, County not checked, PCM not checked, DBA not checked, HMIS not checked, Email not checked
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{// Last name not null, dob not null, pcm not null, dba not checked, hmis not checked, email not checked
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{// Last name not null, DOB not null, county not null, pcm not checked, dba not checked, hmis  not checked, email not checked
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                                ClientSearchResults = create.select()
                                                        .from(Client.CLIENT)
                                                        .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                        .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                        .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                        .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                        .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                        .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                        .fetch();
                                                System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{// First name not null, last name not checked, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                if(CurrentClientLastName == null){
                    if(CurrentClientDOB == null){
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{// First name not null,county not null, pcm not checked, dba not checked, hmis not checked, email not checked
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{// First name not null, dob not null, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else {
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else{// First name not null, last name not null, dob not checked, county not checked, pcm not checked, dba not checked, hmis not checked, email not checked
                    if(CurrentClientDOB == null){
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{ // First Name not null, last name not null, dob not null, county not checked, etc.
                        if(CurrentClientCounty == null){
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                            else{
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.PREFERRED_CONTACT_ID.eq(CurrentClientPCM))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(CurrentClientPCM == null){
                                if(CurrentClientDBA == null){
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                                else{
                                    if(CurrentClientHMIS == null){
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                    else{
                                        if(CurrentClientEmail == null){
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                        else{
                                            ClientSearchResults = create.select()
                                                    .from(Client.CLIENT)
                                                    .where(Client.CLIENT.FIRST_NAME.eq(CurrentClientFirstName))
                                                    .and(Client.CLIENT.LAST_NAME.eq(CurrentClientLastName))
                                                    .and(Client.CLIENT.DATE_OF_BIRTH.eq(CurrentClientDOB))
                                                    .and(Client.CLIENT.COUNTY_ID.eq(CurrentClientCounty))
                                                    .and(Client.CLIENT.DBA_ID.eq(CurrentClientDBA))
                                                    .and(Client.CLIENT.HMIS.eq(CurrentClientHMIS))
                                                    .and(Client.CLIENT.EMAIL.contains(CurrentClientEmail))
                                                    .fetch();
                                            System.out.println(ClientSearchResults);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else{
            ClientSearchResults = create.select()
                    .from(Client.CLIENT)
                    .where(Client.CLIENT.CLIENT_ID.eq(CurrentClientID))
                    .fetch();
        }
    }

    public void AddTicket(ActionEvent actionEvent) {
        NewTicketSubject = ticketSubject.getText();
        NewTicketDetails = ticketDetails.getText();
        NewTicketClient = parseInt(ticketClient.getText());
        NewTicketCallDate = ticketCallDate.getValue().atTime(LocalTime.now());
        if(ticketService.getValue().equals("Community Service")) {
            NewTicketService = 1;
        }
        if(ticketService.getValue().equals("Eviction Diversion")) {
            NewTicketService = 2;
        }
        create.insertInto(Ticket.TICKET, Ticket.TICKET.SUBJECT, Ticket.TICKET.DETAILS, Ticket.TICKET.CLIENT_ID, Ticket.TICKET.CREATOR_OF_TICKET, Ticket.TICKET.ENTRY_DATE, Ticket.TICKET.CALL_DATE, Ticket.TICKET.STATUS_ID, Ticket.TICKET.SERVICE_TYPE_ID)
                .values(NewTicketSubject, NewTicketDetails, NewTicketClient, 1, LocalDateTime.now(), NewTicketCallDate, 1, NewTicketService)
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
