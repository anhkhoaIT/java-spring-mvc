<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <style>
      body {
        font-family: Arial, sans-serif;
        padding: 30px;
      }
      .container {
        background-color: #ffffff;
        padding: 40px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        max-width: 700px;
        margin: 50px auto; /* Căn giữa */
      }
      h1 {
        font-size: 1.8rem;
        color: #333;
        margin-bottom: 25px;
        padding-bottom: 10px;
        border-bottom: 1px solid #eee;
      }
      .order-info {
        font-size: 1.1rem;
        color: #555;
        margin-bottom: 30px;
      }
      .order-info strong {
        color: #000;
      }
      .form-label {
        font-weight: 600;
        color: #444;
      }
      .form-control[readonly] {
        background-color: #e9ecef;
        cursor: not-allowed;
      }
      .btn-update {
        background-color: #ffc107; /* Màu vàng cam của Bootstrap (warning) */
        color: #343a40; /* Màu chữ tối */
        font-weight: bold;
        padding: 10px 25px;
        border-radius: 5px;
        border: none;
        transition: background-color 0.3s ease;
      }
      .btn-update:hover {
        background-color: #e0a800; /* Màu tối hơn khi hover */
        color: #343a40;
      }
    </style>
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
              <li class="breadcrumb-item"><a href="/admin/order">Orders</a></li>
              <li class="breadcrumb-item active">Update</li>
            </ol>

            <div class="container">
              <h1>Update a order</h1>

              <div class="order-info">
                Order id = <strong>${orderId}</strong> &nbsp;&nbsp;&nbsp;&nbsp;
                Price:
                <strong>
                  <fmt:formatNumber type="number" value="${totalPrice}" />
                  đ
                </strong>
              </div>

              <form action="/admin/update-order/${order.getId()}" method="post">
                <input
                  type="hidden"
                  name="${_csrf.parameterName}"
                  value="${_csrf.token}"
                />
                <div class="row mb-4">
                  <div class="col-md-6">
                    <label for="userName" class="form-label">User:</label>
                    <input
                      type="text"
                      class="form-control"
                      id="userName"
                      name="userName"
                      value="${order.user.fullName}"
                      readonly
                    />
                    <input type="hidden" name="orderId" value="3" />
                  </div>
                  <div class="col-md-6">
                    <label for="orderStatus" class="form-label">Status:</label>
                    <select
                      class="form-select"
                      id="orderStatus"
                      name="orderStatus"
                    >
                      <option value="PENDING" selected>PENDING</option>
                      <option value="SHIPPING">SHIPPING</option>
                      <option value="COMPLETE">COMPLETE</option>
                      <option value="CANCEL">CANCEL</option>
                    </select>
                  </div>
                </div>

                <button type="submit" class="btn btn-update">Update</button>
              </form>
            </div>
            <a class="btn btn-success" href="/admin/order">Back</a>
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
