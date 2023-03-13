package sr.unasat.libraryfrontweb.chainofresponsibilities;

public interface ReceiverInterface {
    boolean processMessage(Message msg);
    void setNextChain(ReceiverInterface nextChain);
}
