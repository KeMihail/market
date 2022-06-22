 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

<div class = "container">
  <form:form method = "post" action="/market/user/save" modelAttribute ="user">
             <table>
                 <tr><form:input path="id" type = "hidden" value = "${user.id}"/></tr>
                 <tr><form:label path="name">User Name</form:label></tr>
                 <tr><form:input path="name" value = "${user.name}" disabled ="${readonly}"/></tr>
                 <tr><form:label path="birthday">Birthday</form:label></tr>
                 <tr><form:input type = "date" path="birthday" value = "${user.birthday}" disabled ="${readonly}"/></tr>
                 <c:choose>
                    <c:when test="${readonly}">
                        <a href = "/market/user/users" class="waves-effect waves-light btn">close</a>
                     </c:when>
                     <c:otherwise>
                        <tr><input class="btn waves-effect waves-light" type="submit" value="submit"/></tr>
                     </c:otherwise>
                 </c:choose>
             </table>
  </form:form>
</div>