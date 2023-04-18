/*
 * This file is generated by jOOQ.
 */
package edu.alma.teamleft.tables.records;


import edu.alma.teamleft.tables.AddressType;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AddressTypeRecord extends TableRecordImpl<AddressTypeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.address_type.address_type_id</code>.
     */
    public void setAddressTypeId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.address_type.address_type_id</code>.
     */
    public Integer getAddressTypeId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.address_type.address_type</code>.
     */
    public void setAddressType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.address_type.address_type</code>.
     */
    public String getAddressType() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return AddressType.ADDRESS_TYPE.ADDRESS_TYPE_ID;
    }

    @Override
    public Field<String> field2() {
        return AddressType.ADDRESS_TYPE.ADDRESS_TYPE_;
    }

    @Override
    public Integer component1() {
        return getAddressTypeId();
    }

    @Override
    public String component2() {
        return getAddressType();
    }

    @Override
    public Integer value1() {
        return getAddressTypeId();
    }

    @Override
    public String value2() {
        return getAddressType();
    }

    @Override
    public AddressTypeRecord value1(Integer value) {
        setAddressTypeId(value);
        return this;
    }

    @Override
    public AddressTypeRecord value2(String value) {
        setAddressType(value);
        return this;
    }

    @Override
    public AddressTypeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AddressTypeRecord
     */
    public AddressTypeRecord() {
        super(AddressType.ADDRESS_TYPE);
    }

    /**
     * Create a detached, initialised AddressTypeRecord
     */
    public AddressTypeRecord(Integer addressTypeId, String addressType) {
        super(AddressType.ADDRESS_TYPE);

        setAddressTypeId(addressTypeId);
        setAddressType(addressType);
    }
}
