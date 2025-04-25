<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Table User</title>
    <!-- Latest compiled and minified CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
      /* Điều chỉnh chiều rộng cột */
      table {
        table-layout: fixed;
        width: 100%;
      }
      th,
      td {
        overflow: hidden;
        text-overflow: ellipsis;
      }
      .action-column {
        width: 75%; /* Cột Action dài hơn */
      }
      .other-column {
        width: 25%; /* Các cột còn lại nhỏ lại */
      }
    </style>
  </head>
  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="table-responsive col-12 mx-auto">
          <div class="justify-content-between d-flex">
            <h3>Table users</h3>
            <a href="/admin/user/create" class="btn btn-primary"
              >Create a user</a
            >
          </div>
          <hr />
          <table class="table table-bordered table-hover">
            <thead>
              <tr>
                <th class="other-column" scope="col">ID</th>
                <th class="other-column" scope="col">Email</th>
                <th class="other-column" scope="col">Full Name</th>
                <th class="action-column" scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="user" items="${users}">
                <tr>
                  <td scope="row">${user.getId()}</td>
                  <td>${user.getEmail()}</td>
                  <td>${user.getFullName()}</td>
                  <td>
                    <a href="/admin/user/${user.getId()}" class="btn btn-success btn-sm">View</a>
                    <a href="/admin/user/update/${user.getId()}" class="btn btn-warning btn-sm mx-2">Update</a>
                    <a href="/admin/user/delete/${user.getId()}" class="btn btn-danger btn-sm">Delete</button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>
