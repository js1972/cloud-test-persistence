<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
  <head>
    <title>cloud-test-persistence</title>
  </head>
  <body>
  	<a href="logout">Logout</a><br><br>
  	
  	<table>
    	<tbody>
      		<c:forEach var="hello" items="${requestScope.helloDao.all}" varStatus="status">
        		<tr>
          			<td><fmt:formatDate type="both" value="${hello.when}" /></td>
          			<td>${hello.counter}</td>
          			<td>hello<c:if test = "${hello.counter > 1}">(s)</c:if> from</td>
          			<td>${hello.username}</td>
        		</tr>
      		</c:forEach>
    	</tbody>
  	</table>
  </body>
</html>