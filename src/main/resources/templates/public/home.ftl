<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>

<b>Home page</b>

<br />

<!-- below doesn't work
My Name is from lbl file <h2 th:text="$ {myNameIs} "></h2>;
-->

<a href="/login" >Login</a>

<a href="/errorPage" >Error page</a>

</body>
</html>