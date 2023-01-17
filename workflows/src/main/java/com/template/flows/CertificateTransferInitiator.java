package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.template.contracts.CertificateContract;

import static com.template.contracts.CertificateContract.CERTIFICATE_CONTRACT_ID;
import com.template.states.CertificateState;
import net.corda.core.flows.FinalityFlow;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;

// ******************
// * Initiator flow *
// ******************
@InitiatingFlow
@StartableByRPC
public class CertificateTransferInitiator extends FlowLogic<SignedTransaction> {

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
    private final Party receiver;

    public CertificateTransferInitiator(String town, String state, String lga, String year, String name, String sex, String dob, String fatherName, String motherName, String registrarName, Party receiver) {
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
        this.receiver = receiver;
    }

    private final ProgressTracker progressTracker = new ProgressTracker();

    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        // CertificateTransferInitiator flow logic goes here.

//        Check if the txn is initiated by Fintech (MoneyTap)

        if (getOurIdentity().getName().getOrganisation() instanceof String) {
            System.out.println("Name is verified as human!");
        } else {
            throw new FlowException("Transaction is not human");
        }

//        Get Notary identity from network map

        Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

//        Create the elements for a transaction (Input/ Output states)

        CertificateState outputState = new CertificateState(
                town, state,lga, year, name, sex, dob, fatherName, motherName, registrarName, getOurIdentity(), receiver);

//        Transations in Corda are built using Transaction Builder and elements are added to it

        TransactionBuilder txBuilder = new TransactionBuilder(notary).addOutputState(outputState, CERTIFICATE_CONTRACT_ID).addCommand(new CertificateContract.CertificateRequest(), getOurIdentity().getOwningKey());

//        Sign the transation

        SignedTransaction loanReqTxn = getServiceHub().signInitialTransaction(txBuilder);

//        Create a session with Receiver

        FlowSession loanReqSession = initiateFlow(receiver);

//        Finalize the transaction

        return subFlow(new FinalityFlow(loanReqTxn, loanReqSession));
    }
}
