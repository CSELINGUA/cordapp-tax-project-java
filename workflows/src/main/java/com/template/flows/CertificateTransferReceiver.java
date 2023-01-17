package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatedBy;
import net.corda.core.flows.ReceiveFinalityFlow;
import net.corda.core.transactions.SignedTransaction;

// ******************
// * Responder flow *
// ******************
@InitiatedBy(CertificateTransferInitiator.class)
public class CertificateTransferReceiver extends FlowLogic<SignedTransaction> {
    private FlowSession loanReqSession;

    public CertificateTransferReceiver(FlowSession loanReqSession) {
        this.loanReqSession = loanReqSession;
    }
    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        // CertificateTransferReceiver flow logic goes here.

        System.out.println("Certificate received from User: " + loanReqSession.getCounterparty().getName().getOrganisation());
        return subFlow(new ReceiveFinalityFlow(loanReqSession));

    }
}
