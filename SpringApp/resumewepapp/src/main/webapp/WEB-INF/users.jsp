
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/4/2020
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="assets/js/users.js"></script>
    <title>JSP Page</title>
</head>
<body >

<div>
    <div>
        <f:form class="form-inline" style="margin: 35px" method="GET" action="users" modelAttribute="user">
            <div class="form-group">
                <%--@declare id="name"--%><label for="name">Name:</label>
                <f:input  placeholder="Enter name" style="width: 135px;margin-left:10px"
                          class="form-control" type="text" path="name"/>
            </div>

            <div class="form-group" style="margin-left: 15px">
                <%--@declare id="surname"--%><label for="surname">Surname: </label>
                <f:input placeholder="Enter surname" style="width: 135px;margin-left: 10px" class="form-control"
                       type="text" path="surname" />
            </div>

            <f:button class="btn btn-outline-primary " style="margin-left: 10px;" type="submit" name="search"
            >Search</f:button>
            <f:button class="btn btn-outline-primary " style="margin-left: 50%;"  data-toggle="modal" data-target="#logOutModel" type="button" name="logout"
                   >Log Out</f:button>
        </f:form>

        <div class="modal fade" id="logOutModel" tabindex="-1" role="dialog" aria-labelledby="LogOutModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="LogOutModalLabel">LogOut</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <form action="logout" method="POST">
                            <input  type = "hidden" name = "${_csrf.parameterName}"  value = "${_csrf.token}"/>
                            <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-danger" >LogOut</button>
                        </form>
                    </div>
                </div>
            </div>

    </div>

    <hr noshade/>
    <div>
        <table style="width: 50%;margin:0 auto;" class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Nationality</th>
                <th colspan="2"></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.name}
                </td>
                <td>${u.surname}
                </td>
                <td>${u.nationality.nationality}
                </td>
                <td>

                    <button class="btn btn-danger" type="submit" value="delete" data-toggle="modal" data-target="#exampleModal" onclick="setIdForDelete(${u.id})">
                        <i style="font-size:15px" class="far fa-trash-alt"></i></button>

                </td>
                <td>
                    <form method="GET" action="userdetail">
                    <input type="hidden" name="id" value="${u.id}">
                    <input type="hidden" name="action" value="update">
                    <button class="btn btn-info" type="submit" value="update" ><i style="font-size:15px"class="fas fa-pen-square"></i></button>
                    </form>
                </td>
            </tr>

            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="POST">
                <input type="hidden" name="id" value="" id="idForDelete">
                <input type="hidden" name="action" value="delete">
                <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-danger" >Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
