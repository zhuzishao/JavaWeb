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

public class ClientBookServlet extends BaseServlet{


    /**
     *处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        BookService bookService = new BookServiceImpl();
        //1获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page():获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=page");
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


    /**
     *处理价格区间查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        BookService bookService = new BookServiceImpl();
        //1获取请求的参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //调用BookService.page():获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");

        if (req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(stringBuilder.toString());
        //保存Page对象到request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
