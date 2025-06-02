<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib
uri="http://www.springframework.org/tags/form" prefix="form"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Access Denied - 403 Forbidden</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <style>
      body {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: #f8f9fa; /* Màu nền nhẹ của Bootstrap */
        font-family: Arial, sans-serif;
      }
      .error-container {
        text-align: center;
        padding: 40px;
        border-radius: 10px;
        background-color: #ffffff;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        max-width: 500px;
        width: 90%;
      }
      .error-container h1 {
        font-size: 8rem; /* Kích thước lớn cho mã lỗi */
        color: #dc3545; /* Màu đỏ của Bootstrap (danger) */
        margin-bottom: 20px;
        font-weight: bold;
      }
      .error-container h2 {
        font-size: 2rem;
        color: #343a40; /* Màu tối của Bootstrap */
        margin-bottom: 15px;
      }
      .error-container p {
        font-size: 1.1rem;
        color: #6c757d; /* Màu xám của Bootstrap */
        margin-bottom: 30px;
      }
      .btn-home {
        background-color: #007bff; /* Màu xanh chính của Bootstrap (primary) */
        color: white;
        padding: 12px 25px;
        font-size: 1.1rem;
        border-radius: 5px;
        text-decoration: none;
        transition: background-color 0.3s ease;
      }
      .btn-home:hover {
        background-color: #0056b3;
        color: white; /* Đảm bảo màu chữ vẫn trắng khi hover */
      }
    </style>
  </head>
  <body>
    <div class="error-container">
      <h1>403</h1>
      <h2>Truy cập bị cấm!</h2>
      <p>
        Xin lỗi, bạn không có quyền để truy cập tài nguyên này. Vui lòng liên hệ
        quản trị viên nếu bạn tin đây là lỗi.
      </p>
      <a href="/" class="btn btn-home">Quay về trang chủ</a>
    </div>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
