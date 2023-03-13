package sr.unasat.libraryfrontweb.app;

import sr.unasat.libraryfrontweb.configuration.JPAConfig;
import sr.unasat.libraryfrontweb.services.*;

public class Application {
    public static void main(String[] args) {

        InsertDataService insertData= new InsertDataService();
        insertData.insertDataAllTables();


        GenreService genreService = new GenreService();
//        genreService.genreRetrieve();
//        genreService.genreUpdate();
//        genreService.genreDelete();

        BookService bookService = new BookService();
//        bookService.bookRetrieve();
//        bookService.bookUpdate();
//        bookService.bookDelete();

        MemberService memberService = new MemberService();
//        memberService.memberRetrieve();
//        memberService.memberUpdate();
//        memberService.memberDelete();

        BorrowReceiptService receiptService = new BorrowReceiptService();
//        receiptService.receiptRetrieve();
//        receiptService.receiptUpdate();
//        receiptService.receiptDelete();

        AuthorService authorService = new AuthorService();
//        authorService.authorRetrieve();
//        authorService.authorUpdate();
//        authorService.authorDelete();


        //Decorator
        DecoratorService decoratorService = new DecoratorService();
//        decoratorService.decoratorApp();

        //ChainOfResponsibilties
        MessageChainMaker messageChain = new MessageChainMaker();
//        messageChain.messageMaker();

        //Builder
        BuilderService builderService = new BuilderService();
//        builderService.builderApp();


        JPAConfig.shutdown();
    }
}
