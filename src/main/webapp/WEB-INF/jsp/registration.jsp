<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StayBooker - Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<jsp:include page="include/header.jsp"/>

<div class="container mt-5">
    <h2 class="text-center">Register for StayBooker</h2>
    <div class="row justify-content-center mt-4">
        <div class="col-md-4">
            <c:if test="${requestScope.hasError}">
                <div class="alert alert-danger mt-2" role="alert">${requestScope.error}</div>
            </c:if>
            <c:if test="${requestScope.successful}">
                <div class="alert alert-success mt-2" role="alert">You have successfully registered!</div>
            </c:if>
            <form method="POST" action="${pageContext.request.contextPath}/registration">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Enter your name" value="${requestScope.fullName}">
                </div>
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email" value="${requestScope.email}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                           placeholder="Confirm Password">
                </div>
                <button type="submit" class="btn btn-primary btn-block">Register</button>
                <p class="text-center mt-2"><a href="${pageContext.request.contextPath}/login">Already have an account?</a></p>
            </form>
        </div>
    </div>
</div>

<jsp:include page="include/footer.jsp"/>

</body>
</html>
