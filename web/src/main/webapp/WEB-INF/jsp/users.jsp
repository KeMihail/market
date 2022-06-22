<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

  <!-- Compiled and minified JavaScript -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

<div class="container">
    <table class="highlight">
        <thead>
          <tr>
              <th>user id</th>
              <th>user name</th>
              <th>user birthday</th>
              <th>action</th>
              <th></th>
          </tr>
       </thead>

        <tbody>
        <c:forEach var = "user" items ="${users}">
          <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.birthday}</td>
            <td><a class="waves-effect waves-light btn" href="/market/user/${user.id}">details</a></td>
            <td><a class="waves-effect waves-light btn" href="/market/user/update/${user.id}">edit</a></td>
            <td><a class="waves-effect waves-light btn" href="/market/user/delete/${user.id}">delete</a></td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
<div class="container">
