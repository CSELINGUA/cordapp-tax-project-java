package com.template.states;

import com.template.contracts.TemplateContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;

import java.util.Arrays;
import java.util.List;

// *********
// * State *
// *********
@BelongsToContract(TemplateContract.class)
public class TemplateState implements ContractState {

    public TemplateState() {

    }

    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList();
    }
}

// start LoanRequestInitiator customerName: Rajapandian, customerPAN: ABCD, loanAmount: 50000, loanType: Personal, bank: "O=RBL,L=New York,C=US"

//    run vaultQuery contractStateType: com.template.states.LoanState
