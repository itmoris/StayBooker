<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StayBooker - Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>

<jsp:include page="include/header.jsp"/>

<div class="container mt-5">
    <h2 class="text-center">Login to StayBooker</h2>
    <div class="row justify-content-center mt-4">
        <div class="col-md-4">
            <c:if test="${requestScope.hasError}">
                <div class="alert alert-danger mt-2" role="alert">${requestScope.error}</div>
            </c:if>
            <form method="POST" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
                <p class="text-center mt-3"><a href="#">Forgot your password?</a></p>
                <p class="text-center mt-2">Don't have an account? <a href="${pageContext.request.contextPath}">Register</a></p>

            </form>
        </div>
    </div>
</div>

<jsp:include page="include/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
