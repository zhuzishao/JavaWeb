package filter;

import utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        try {

            filterChain.doFilter(servletRequest,servletResponse);
            // 提交事务
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            // 回滚事务
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public void destroy() {

    }
}
