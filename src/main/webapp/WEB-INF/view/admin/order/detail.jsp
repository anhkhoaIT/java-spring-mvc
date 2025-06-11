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
              <li class="breadcrumb-item active">View Detail</li>
            </ol>
            <h3>Order detail with id = ${orderId}</h3>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Sản phẩm</th>
                  <th scope="col">Tên</th>
                  <th scope="col">Giá cả</th>
                  <th scope="col">Số lượng</th>
                  <th scope="col">Thành tiền</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="orderDetail" items="${orderDetails}">
                  <tr>
                    <th scope="row">
                      <div class="d-flex align-items-center">
                        <img
                          src="/images/product/${orderDetail.product.image}"
                          class="img-fluid me-5 rounded-circle"
                          style="width: 80px; height: 80px"
                          alt=""
                        />
                      </div>
                    </th>
                    <td>
                      <a
                        class="mb-0 mt-4"
                        href="/admin/product/${orderDetail.product.id}"
                      >
                        ${orderDetail.product.name}
                      </a>
                    </td>
                    <td>
                      <p class="mb-0 mt-4">
                        <fmt:formatNumber
                          type="number"
                          value="${orderDetail.price}"
                        />
                        đ
                      </p>
                    </td>
                    <td>
                      <div
                        class="input-group quantity mt-4"
                        style="width: 100px"
                      >
                        <input
                          type="text"
                          class="form-control form-control-sm text-center border-0"
                          value="${orderDetail.quantity}"
                          readonly
                        />
                      </div>
                    </td>
                    <td>
                      <p class="mb-0 mt-4">
                        <fmt:formatNumber type="number" value="${totalPrice}" />
                        đ
                      </p>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
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
