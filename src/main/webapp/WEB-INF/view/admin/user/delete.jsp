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
              <li class="breadcrumb-item active">Users</li>
            </ol>
            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Delete user with id = ${user.getId()}</h3>
                  <hr />
                  <div class="alert alert-danger" role="alert">
                    Are you sure you want to delete this user?
                  </div>
                  <form
                    action="/admin/user/delete/${user.getId()}"
                    method="post"
                  >
                    <button type="submit" class="btn btn-danger">
                      Confirm
                    </button>
                  </form>
                  <a class="btn btn-success" href="/admin/user">Back</a>
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
