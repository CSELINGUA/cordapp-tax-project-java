package com.template.states;

import com.template.contracts.CertificateContract;
import java.util.Arrays;
import java.util.List;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

// *********
// * State *
// *********
@BelongsToContract(CertificateContract.class)
public class CertificateState implements ContractState {
    private final String town;
    private final String state;
    private final String lga;
    private final String year;
    private final String name;
    private final String sex;
    private final String dob;
    private final String fatherName;
    private final String motherName;
    private final String registrarName;
    private final Party sender;
    private final Party receiver;

    public CertificateState(String town, String state, String lga, String year, String name, String sex, String dob, String fatherName, String motherName, String registrarName, Party sender, Party receiver) {
        this.town = town;
        this.state = state;
        this.lga = lga;
        this.year = year;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.registrarName = registrarName;
        this.sender = sender;
        this.receiver = receiver;
    }


    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(sender,receiver);
    }

    public String getTown() {
        return town;
    }

    public String getState() {
        return state;
    }

    public String getLga() {
        return lga;
    }

    public String getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDob() {
        return dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public Party getSender() {
        return sender;
    }

    public Party getReceiver() {
        return receiver;
    }
}
