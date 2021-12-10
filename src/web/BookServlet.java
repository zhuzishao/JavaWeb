package web;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BookServlet extends BaseServlet{

    private BookService bookService =new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        //将获取到的请求参数封装成为一个book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //调用BookService中的addBook方法保存图书
        bookService.addBook(book);
        //转跳到管理页面
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
       resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" + pageNo);
    }


    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" +req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById查询需要修改的图书信息
        Book book = bookService.queryBookById(id);
        //保存图书信息到Requset域中
        req.setAttribute("book",book);
        //请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //将获取到的请求参数封装成为一个book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //调用BookService中的addBook方法保存图书
        bookService.updateBook(book);
        //转跳到管理页面
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo=" +req.getParameter("pageNo"));
    }


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    /**
     *处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page():获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }
}
