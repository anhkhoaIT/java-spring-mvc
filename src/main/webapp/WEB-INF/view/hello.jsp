<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
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
    <h1>Hello, World!</h1>
  </body>
</html>
