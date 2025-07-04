<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
            <h1 class="mt-4">Manage Products</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item active">Products</li>
            </ol>
            <!-- <div>Table product</div> -->

            <div class="mt-5">
              <div class="row">
                <div class="table-responsive col-12 mx-auto">
                  <div class="justify-content-between d-flex">
                    <h3>Table products</h3>
                    <a href="/admin/product/create" class="btn btn-primary"
                      >Create a product</a
                    >
                  </div>
                  <hr />
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Factory</th>
                        <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
              <c:forEach var="product" items="${products}">
                <tr>
                  <td scope="row">${product.getId()}</td>
                  <td>${product.getName()}</td>
                  <td>
                    <fmt:formatNumber
                      type="number"
                      value="${product.getPrice()}"
                    />
                    đ
                  </td>
                  <td>${product.getFactory()}</td>

                  <td>
                    <a href="/admin/product/${product.getId()}" class="btn btn-success btn-sm">View</a>
                    <a href="/admin/product/update/${product.getId()}" class="btn btn-warning btn-sm mx-2">Update</a>
                    <a href="/admin/product/delete/${product.getId()}" class="btn btn-danger btn-sm">Delete</button>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
                  </table>
                  <nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="${1 eq currentPage? 'page-link disabled':'page-link'}" 
      href="/admin/product?page=${currentPage - 1}" aria-label="Previous"
      >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    
    <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
    <li class="page-item">
      <a class="${(loop.index + 1) eq currentPage? 'page-link active':'page-link'}" 
      href="/admin/product?page=${loop.index + 1}"
      >
        ${loop.index + 1}
      </a>
    </li>
    </c:forEach>
    <li class="page-item">
      <a class="${totalPages eq currentPage? 'page-link disabled':'page-link'}"
      href="/admin/product?page=${currentPage + 1}" aria-label="Next"
      >
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
                </div>
              </div>
            </div>
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
