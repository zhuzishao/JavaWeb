package test;

import dao.BookDao;
import dao.impl.BaseDao;
import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;
import pojo.Page;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"今天好舒适","zhu",new BigDecimal(9999),10000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(3);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"大家都可以这么帅！", "zhu", new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void queryBookById() {
        bookDao.queryBookById(21);
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
    @Test
    public void queryForPageTotalCount() {

        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }

    }
}