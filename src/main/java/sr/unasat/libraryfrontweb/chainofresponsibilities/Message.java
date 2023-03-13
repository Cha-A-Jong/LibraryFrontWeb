package sr.unasat.libraryfrontweb.chainofresponsibilities;

public class Message {
    public String text;
    public MessagePriority priority;

    public Message(String msg, MessagePriority p){
        text = msg;
        priority = p;
    }
}
