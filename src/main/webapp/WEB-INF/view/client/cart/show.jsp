<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Detail Cart</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
      rel="stylesheet"
    />

    <!-- Icon Font Stylesheet -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
      rel="stylesheet"
    />

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
    <link
      href="/client/lib/owlcarousel/assets/owl.carousel.min.css"
      rel="stylesheet"
    />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet" />
  </head>

  <body>
    <!-- Spinner Start -->
    <div
      id="spinner"
      class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50 d-flex align-items-center justify-content-center"
    >
      <div class="spinner-grow text-primary" role="status"></div>
    </div>
    <!-- Spinner End -->
    <jsp:include page="../layout/header.jsp" />

    <!-- Modal Search Start -->
    <div
      class="modal fade"
      id="searchModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Search by keyword
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body d-flex align-items-center">
            <div class="input-group w-75 mx-auto d-flex">
              <input
                type="search"
                class="form-control p-3"
                placeholder="keywords"
                aria-describedby="search-icon-1"
              />
              <span id="search-icon-1" class="input-group-text p-3"
                ><i class="fa fa-search"></i
              ></span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Search End -->

    <!-- Cart Page Start -->
    <div class="container-fluid py-5">
      <div class="container py-5">
        <div class="mb-3">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/">Home</a></li>
              <li class="breadcrumb-item active">Chi tiết giỏ hàng</li>
            </ol>
          </nav>
        </div>
        <div class="table-responsive">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">Sản phẩm</th>
                <th scope="col">Tên</th>
                <th scope="col">Giá cả</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Thành tiền</th>
                <th scope="col">Xử lý</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${not empty cartDetails}">
                <c:forEach var="cartDetail" items="${cartDetails}">
                  <tr>
                    <th scope="row">
                      <div class="d-flex align-items-center">
                        <img
                          src="images/product/${cartDetail.product.image}"
                          class="img-fluid me-5 rounded-circle"
                          style="width: 80px; height: 80px"
                          alt=""
                        />
                      </div>
                    </th>
                    <td>
                      <a
                        class="mb-0 mt-4"
                        href="/product/${cartDetail.product.id}"
                      >
                        ${cartDetail.product.name}
                      </a>
                    </td>
                    <td>
                      <p class="mb-0 mt-4">
                        <fmt:formatNumber
                          type="number"
                          value="${cartDetail.price}"
                        />
                        đ
                      </p>
                    </td>
                    <td>
                      <div
                        class="input-group quantity mt-4"
                        style="width: 100px"
                      >
                        <div class="input-group-btn">
                          <button
                            class="btn btn-sm btn-minus rounded-circle bg-light border"
                          >
                            <i class="fa fa-minus"></i>
                          </button>
                        </div>
                        <input
                          type="text"
                          class="form-control form-control-sm text-center border-0"
                          value="${cartDetail.quantity}"
                        />
                        <div class="input-group-btn">
                          <button
                            class="btn btn-sm btn-plus rounded-circle bg-light border"
                          >
                            <i class="fa fa-plus"></i>
                          </button>
                        </div>
                      </div>
                    </td>
                    <td>
                      <p class="mb-0 mt-4">
                        <fmt:formatNumber
                          type="number"
                          value="${cartDetail.getTotalPrice()}"
                        />
                        đ
                      </p>
                    </td>
                    <td>
                      <form
                        method="post"
                        action="/delete-cart-product/${cartDetail.id}"
                      >
                        <input
                          type="hidden"
                          name="${_csrf.parameterName}"
                          value="${_csrf.token}"
                        />
                        <button
                          class="btn btn-md rounded-circle bg-light border mt-4"
                          type="submit"
                        >
                          <i class="fa fa-times text-danger"></i>
                        </button>
                      </form>
                    </td>
                  </tr>
                </c:forEach>
              </c:if>
              <c:if test="${empty cartDetails}">
                <tr>
                  <td colspan="6" class="text-center">
                    <p class="mb-0 mt-4">
                      Không có sản phẩm nào trong giỏ hàng của bạn.
                    </p>
                  </td>
                </tr>
              </c:if>
            </tbody>
          </table>
        </div>

        <c:if test="${not empty cartDetails}">
          <div class="mt-5 row g-4 justify-content-start">
            <div class="col-12 col-md-8">
              <div class="bg-light rounded">
                <div class="p-4">
                  <h1 class="display-6 mb-4">Thông tin đơn hàng</h1>
                  <div class="d-flex justify-content-between mb-4">
                    <h5 class="mb-0 me-4">Tạm tính:</h5>
                    <p class="mb-0">
                      <fmt:formatNumber
                        type="number"
                        value="${cart.getTotalPriceInCart()}"
                      />
                      đ
                    </p>
                  </div>
                  <div class="d-flex justify-content-between">
                    <h5 class="mb-0 me-4">Phí vận chuyển:</h5>
                    <p class="mb-0">0 đ</p>
                  </div>
                </div>
                <div
                  class="py-4 mb-4 border-top border-bottom d-flex justify-content-between"
                >
                  <h5 class="mb-0 ps-4 me-4">Tổng số tiền</h5>
                  <p class="mb-0 pe-4">
                    <fmt:formatNumber
                      type="number"
                      value="${cart.getTotalPriceInCart()}"
                    />
                    đ
                  </p>
                </div>
                <form action="/checkout" method="post">
                  <input
                    type="hidden"
                    name="${_csrf.parameterName}"
                    value="${_csrf.token}"
                  />
                  <button
                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
                    type="submit"
                  >
                    XÁC NHẬN ĐẶT HÀNG
                  </button>
                </form>
              </div>
            </div>
          </div>
        </c:if>
      </div>
    </div>
    <!-- Cart Page End -->

    <jsp:include page="../layout/footer.jsp" />

    <!-- Back to Top -->
    <a
      href="#"
      class="btn btn-primary border-3 border-primary rounded-circle back-to-top"
      ><i class="fa fa-arrow-up"></i
    ></a>

    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/client/js/main.js"></script>
  </body>
</html>
