<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Oops!</title>

</head>
<html>

<!--

 Custom error page ->
 All we must do to customize the error page is create a file named “error.html” and place it in the templates
folder along with our other application templates.
Attributes to use are -
timestamp—The time that the error occurred
■ status—The HTTP status code
■ error—The error reason
■ exception—The class name of the exception
■ message—The exception message (if the error was caused by an exception)
■ errors—Any errors from a BindingResult exception (if the error was caused
by an exception)
■ trace—The exception stack trace (if the error was caused by an exception)
■ path—The URL path requested when the error occurred
-->
<div class="errorPage">
    <span class="oops">Oops!</span><br/>
    <p>Looks good...This is our error page.
    </p>
    <p>Stacktrace is -
    </p>
    <p th:text="${message}"></p>
</div>
</html>
</html>