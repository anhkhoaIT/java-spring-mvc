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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
      $(document).ready(() => {
        const avatarFile = $("#productFile");
        avatarFile.change(function (e) {
          const imgURL = URL.createObjectURL(e.target.files[0]);
          $("#oldProductPreview").css({ display: "none" });
          $("#productPreview").attr("src", imgURL);
          $("#productPreview").css({ display: "block" });
        });
      });
    </script>

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
              <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
              <li class="breadcrumb-item active">Update</li>
            </ol>

            <div class="mt-5">
              <div class="row">
                <div class="col-md-6 col-12 mx-auto">
                  <h3>Create a product</h3>
                  <hr />
                  <form:form
                    method="post"
                    action="/admin/product/update"
                    modelAttribute="currentProduct"
                    enctype="multipart/form-data"
                  >
                    <div class="mb-3" style="display: none">
                      <label class="form-label">ID:</label>
                      <form:input type="text" class="form-control" path="id" />
                    </div>
                    <div class="row g-3">
                      <div class="mb-3 col">
                        <label class="form-label">Name:</label>
                        <c:set var="nameError">
                          <form:errors path="name" />
                        </c:set>
                        <form:input
                          type="name"
                          class="form-control ${not empty nameError? 'is-invalid':''}"
                          path="name"
                        />
                        <form:errors path="name" cssClass="invalid-feedback" />
                      </div>
                      <div class="mb-3 col">
                        <label class="form-label">Price:</label>
                        <c:set var="priceError">
                          <form:errors path="price" />
                        </c:set>
                        <form:input
                          type="number"
                          class="form-control ${not empty priceError? 'is-invalid':''}"
                          path="price"
                        />
                        <form:errors path="price" cssClass="invalid-feedback" />
                      </div>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Detail description:</label>
                      <c:set var="detailDescError">
                        <form:errors path="detailDesc" />
                      </c:set>
                      <form:textarea
                        class="form-control ${not empty detailDescError? 'is-invalid':''}"
                        path="detailDesc"
                      />
                      <form:errors
                        path="detailDesc"
                        cssClass="invalid-feedback"
                      />
                    </div>

                    <div class="row g-3">
                      <div class="mb-3 col">
                        <label class="form-label">Short description:</label>
                        <c:set var="shortDescError">
                          <form:errors path="shortDesc" />
                        </c:set>
                        <form:input
                          type="text"
                          class="form-control ${not empty shortDescError? 'is-invalid':''}"
                          path="shortDesc"
                        />
                        <form:errors
                          path="shortDesc"
                          cssClass="invalid-feedback"
                        />
                      </div>
                      <div class="mb-3 col">
                        <label class="form-label">Quantity:</label>
                        <c:set var="quantityError">
                          <form:errors path="quantity" />
                        </c:set>
                        <form:input
                          type="number"
                          class="form-control ${not empty quantityError? 'is-invalid':''}"
                          path="quantity"
                        />
                        <form:errors
                          path="quantity"
                          cssClass="invalid-feedback"
                        />
                      </div>
                    </div>

                    <div class="row g-3">
                      <div class="mb-3 col">
                        <label class="form-label">Factory:</label>
                        <form:select
                          class="form-select form-select-sm"
                          aria-label=".form-select-sm example"
                          path="factory"
                        >
                          <form:option value="APPLE"
                            >Apple (Macbook)</form:option
                          >
                          <form:option value="ASUS">Asus</form:option>
                          <form:option value="LENOVO">Lenovo</form:option>
                          <form:option value="DELL">Dell</form:option>
                          <form:option value="LG">LG</form:option>
                          <form:option value="ACER">Acer</form:option>
                        </form:select>
                      </div>

                      <div class="mb-3 col">
                        <label class="form-label">Target:</label>
                        <form:select
                          class="form-select form-select-sm"
                          aria-label=".form-select-sm example"
                          path="target"
                        >
                          <form:option value="SINHVIEN-VANPHONG"
                            >Sinh viên - Văn phòng</form:option
                          >
                          <form:option value="THIET-KE-DO-HOA"
                            >Thiết kế đồ họa</form:option
                          >
                          <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                          <form:option value="DOANH-NHAN"
                            >Doanh nhân</form:option
                          >
                        </form:select>
                      </div>
                    </div>

                    <div class="mb-3">
                      <label for="avatarFile" class="form-label">Image:</label>
                      <input
                        class="form-control"
                        type="file"
                        id="productFile"
                        accept=".png, .jpg, .jpeg"
                        name="khoaFile"
                      />
                    </div>

                    <div class="col-12 mb-3">
                      <img
                        src="/images/product/${currentProduct.getImage()}"
                        style="max-height: 250px; display: block"
                        id="oldProductPreview"
                      />
                      <img
                        style="max-height: 250px; display: none"
                        alt="product preview"
                        id="productPreview"
                      />
                    </div>
                    <div class="col-12 mb-5">
                      <button type="submit" class="btn btn-primary">
                        Update
                      </button>
                    </div>
                  </form:form>
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
