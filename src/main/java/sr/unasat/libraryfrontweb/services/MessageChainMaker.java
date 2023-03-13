package sr.unasat.libraryfrontweb.services;

import sr.unasat.libraryfrontweb.chainofresponsibilities.*;

public class MessageChainMaker {

    public void messageMaker(){
        System.out.println("Chain Of Responsibility Design Pattern Handling.\n");

        FirstMemberHandler firstMemberHandler = new FirstMemberHandler();

        SecondMemberHandler secondMemberHandler = new SecondMemberHandler();
        secondMemberHandler.setNextChain(firstMemberHandler);

        ThirdMemberHandler thirdMemberHandler = new ThirdMemberHandler();
        thirdMemberHandler.setNextChain(secondMemberHandler);

        IssueRaiser raiser = new IssueRaiser(thirdMemberHandler);

        Message messagePrint = new Message("Bronto request book to be delivered", MessagePriority.High );

        raiser.raiseMessage(messagePrint);

    }
}
