package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"bookService","zhu",new BigDecimal(9999),10000,0,null));

    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21,"更新bookService", "zhu", new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void queryBookById() {
        bookService.queryBookById(21);
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }
}