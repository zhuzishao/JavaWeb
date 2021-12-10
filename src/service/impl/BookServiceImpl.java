package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {

        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {

        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {

        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //new Page对象
        Page<Book> page = new Page<Book>();


        //设置每页数据的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);


        //设置当前页码
        page.setPageNo(pageNo);

        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页面数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return  page;
    }

//    查询价格区间
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //new Page对象
        Page<Book> page = new Page<Book>();


        //设置每页数据的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if (pageTotalCount%pageSize > 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);


        //设置当前页码
        page.setPageNo(pageNo);

        int begin = (page.getPageNo()-1)*pageSize;
        //求当前页面数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return  page;
    }
}
