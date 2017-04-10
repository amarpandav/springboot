<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Spring Security Example </title>
</head>
<body>

<h1>Home page</h1>
<br />

<!-- below doesn't work
My Name is from lbl file <h2 th:text="$ {myNameIs} "></h2>;
-->

<h2>Public pages</h2>
<a href="login" >Login page</a>

<br/>

<a href="error" >Error page</a>

<h2>Private pages</h2>
<a href="books" >Read Books</a>


</body>
</html>