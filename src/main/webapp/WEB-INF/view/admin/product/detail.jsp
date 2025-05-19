<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
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
            <h1 class="mt-4">Manage Users</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item">
                <a href="/admin/product">Products</a>
              </li>
              <li class="breadcrumb-item active">Detail</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="table-responsive col-12 mx-auto">
                  <div class="justify-content-between d-flex">
                    <h3>Product Detail with Id: ${product.getId()}</h3>
                  </div>
                  <hr />
                  <img
                    src="/images/product/${product.getImage()}"
                    alt="Product Image"
                    class="img-fluid"
                    style="width: 300px; height: 300px"
                  />
                  <div class="card" style="width: 60%">
                    <div class="card-header">User information</div>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item">ID: ${product.getId()}</li>
                      <li class="list-group-item">
                        Name: ${product.getName()}
                      </li>
                      <li class="list-group-item">
                        Price: ${product.getPrice()}
                      </li>
                    </ul>
                  </div>
                  <a class="btn btn-success" href="/admin/product">Back</a>
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
