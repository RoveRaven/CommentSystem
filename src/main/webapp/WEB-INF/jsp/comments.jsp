<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/fmtzdt.tld" prefix="fmtzdt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>View Comments</title>
    </head>

    <body>
    <div>
        <form action="/">
        <button type = "submit" style="margin-left: 8%; font-size:25">Index</button>
        </form>
    </div>

    <div>
          <c:if test="${addCommentSuccess}" >
              <div style="margin-left: 8%">Successfully added Comment with id: ${savedComment.id}</div>
          </c:if>

                <c:url var="add_comment_url" value="/comments"/>
                <form:form action="${add_comment_url}" method="post" modelAttribute="newcomment" style="margin-left: 8%">
                    <form:label path="text" ></form:label> <form:textarea type="text" path="text" placeholder="Your Message" rows="10" cols="100" style="resize:none" autofocus="true"/>
                    <br>
                    <br>
                    <form:label path="user.username"></form:label> <form:input type="text" path="user.username" placeholder="Your Name" style= "font-size: 20px; width: 30%"/>
                    <input type="submit" value="Send Message" style="width: 15%; height: 5%; font-size: 20px; margin-left: 5%"/>
                </form:form>

                <c:forEach items="${comments}" var="comment">
                        <hr>
                        <p style="margin-bottom: -5px; margin-left: 8%"><span style="font-size: 20px"><b>${comment.user.username}</b></span>   <span style="font-size: 12px; color: blue"><fmtzdt:formatDate value="${comment.commentTime}" pattern="dd/MM/yyyy HH:mm" /></span></p>
                        <p style = "word-wrap: break-word; width: 65%; margin-left: 8%; text-align: justify">${comment.text}</p>
                <!--    <button style="margin-left: 8%; width: 60px">Reply</button>        -->
                        <br>
                </c:forEach>
    </div>
    </body>
</html>
