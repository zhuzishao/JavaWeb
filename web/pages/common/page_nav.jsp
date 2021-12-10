<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/11/23
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $(function (){
        $("#searchPageBtn").click(function (){
            var pageNo = $("#pn_input").val();
            location.href="${pageScope.basePath}${requestScope.page.url }&pageNo=" + pageNo;
        });
    });


</script>
<%--		分页条开始--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url }&pageNo=1&pageSize=4">首页</a>
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--			分页显示的开始--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal <=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【 ${i} 】
                </c:if>
                <c:if test="${i !=requestScope.page.pageNo}">
                    <a href="${requestScope.page.url }&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
    </c:choose>
    <%--			分页显示的结束--%>



    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
</div>

</div>