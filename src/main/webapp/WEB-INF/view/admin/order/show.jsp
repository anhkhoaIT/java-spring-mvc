<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
    <meta name="author" content="Hỏi Dân IT" />
    <title>Dashboard - Khoa IT</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>

  <body class="sb-nav-fixed">
    <!-- header -->
    <jsp:include page="../layout/header.jsp" />

    <div id="layoutSidenav">
      <!-- sidebar -->
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Manage Orders</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Orders</li>
            </ol>
            <h3>Table orders</h3>
            <table class="table table-bordered table-hover">
            <thead>
              <tr>
                <th class="other-column" scope="col">ID</th>
                <th class="other-column" scope="col">Total Price</th>
                <th class="other-column" scope="col">User</th>
                <th class="other-column" scope="col">Status</th>
                <th class="action-column" scope="col">Action</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="order" items="${orders}">
                <tr>
                  <td scope="row">${order.getId()}</td>
                  <td>
                    <fmt:formatNumber
                      type="number"
                      value="${order.totalPrice}"
                    />
                    đ
                    </td>
                  <td>${order.getUser().getFullName()}</td>
                  <td>${order.getStatus()}</td>

                  <td>
                    <a href="/admin/order/${order.getId()}" class="btn btn-success btn-sm">View</a>
                    <a href="/admin/order/update/${order.getId()}" class="btn btn-warning btn-sm mx-2">Update</a>
                    <a href="/admin/order/delete/${order.getId()}" class="btn btn-danger btn-sm">Delete</button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          </div>
        </main>
        <!-- footer -->
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
    <script src="/js/chart-area-demo.js"></script>
    <script src="/js/chart-bar-demo.js"></script>
    <script src="/js/datatables-simple-demo.js"></script>
  </body>
</html>
