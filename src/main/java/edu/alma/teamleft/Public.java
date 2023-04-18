/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft;


import edu.alma.teamleft.tables.Address;
import edu.alma.teamleft.tables.AddressType;
import edu.alma.teamleft.tables.Client;
import edu.alma.teamleft.tables.County;
import edu.alma.teamleft.tables.Followups;
import edu.alma.teamleft.tables.PhoneNumber;
import edu.alma.teamleft.tables.PhoneNumberType;
import edu.alma.teamleft.tables.PreferredContact;
import edu.alma.teamleft.tables.Service;
import edu.alma.teamleft.tables.Staff;
import edu.alma.teamleft.tables.Status;
import edu.alma.teamleft.tables.Ticket;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.address</code>.
     */
    public final Address ADDRESS = Address.ADDRESS;

    /**
     * The table <code>public.address_type</code>.
     */
    public final AddressType ADDRESS_TYPE = AddressType.ADDRESS_TYPE;

    /**
     * The table <code>public.client</code>.
     */
    public final Client CLIENT = Client.CLIENT;

    /**
     * The table <code>public.county</code>.
     */
    public final County COUNTY = County.COUNTY;

    /**
     * The table <code>public.followups</code>.
     */
    public final Followups FOLLOWUPS = Followups.FOLLOWUPS;

    /**
     * The table <code>public.phone_number</code>.
     */
    public final PhoneNumber PHONE_NUMBER = PhoneNumber.PHONE_NUMBER;

    /**
     * The table <code>public.phone_number_type</code>.
     */
    public final PhoneNumberType PHONE_NUMBER_TYPE = PhoneNumberType.PHONE_NUMBER_TYPE;

    /**
     * The table <code>public.preferred_contact</code>.
     */
    public final PreferredContact PREFERRED_CONTACT = PreferredContact.PREFERRED_CONTACT;

    /**
     * The table <code>public.service</code>.
     */
    public final Service SERVICE = Service.SERVICE;

    /**
     * The table <code>public.staff</code>.
     */
    public final Staff STAFF = Staff.STAFF;

    /**
     * The table <code>public.status</code>.
     */
    public final Status STATUS = Status.STATUS;

    /**
     * The table <code>public.ticket</code>.
     */
    public final Ticket TICKET = Ticket.TICKET;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.asList(
            Sequences.ADDRESS_TYPES_ID_SEQ,
            Sequences.ADDRESSES_ADDRESS_ID_SEQ,
            Sequences.CLIENT_ID_SEQ,
            Sequences.COUNTY_ID_SEQ,
            Sequences.PHONE_NUMBER_TYPES_ID_SEQ,
            Sequences.PHONE_NUMBERS_PHONE_NUMBER_ID_SEQ,
            Sequences.PREFERRED_CONTACT_ID_SEQ,
            Sequences.SERVICES_SERVICE_ID_SEQ,
            Sequences.STATUSES_STATUS_ID_SEQ
        );
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Address.ADDRESS,
            AddressType.ADDRESS_TYPE,
            Client.CLIENT,
            County.COUNTY,
            Followups.FOLLOWUPS,
            PhoneNumber.PHONE_NUMBER,
            PhoneNumberType.PHONE_NUMBER_TYPE,
            PreferredContact.PREFERRED_CONTACT,
            Service.SERVICE,
            Staff.STAFF,
            Status.STATUS,
            Ticket.TICKET
        );
    }
}
