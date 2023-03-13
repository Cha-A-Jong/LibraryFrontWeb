package sr.unasat.libraryfrontweb.chainofresponsibilities;

import sr.unasat.libraryfrontweb.dao.BookDAO;
import sr.unasat.libraryfrontweb.entities.Book;

public class FirstMemberHandler implements ReceiverInterface{

    private ReceiverInterface nextReceiver;
    BookDAO bookDAO = new BookDAO();

    @Override
    public void setNextChain(ReceiverInterface nextReceiver) {
        this.nextReceiver = nextReceiver;

    }
    @Override
    public boolean processMessage(Message msg) {
        if (msg.text.contains("Bronto"))  {
            Book book = bookDAO.findByTitle("The Dark Tower 1");
            System.out.println("The following book for member Bronto has been requested: " + book + "\n" +msg.priority + "" + "priority issue: "+ msg.text);
            return true;
        }
        else { if (nextReceiver != null) { nextReceiver.processMessage(msg); } }
        return false;
    }


}
