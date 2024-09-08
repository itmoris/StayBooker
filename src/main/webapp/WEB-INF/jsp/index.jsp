<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<fmt:parseNumber var="page" integerOnly="true" type="number" value="${requestScope.currentPage}"/>
<fmt:parseNumber var="lastPage" integerOnly="true" type="number" value="${requestScope.lastPage}"/>
<c:set var="apartments" scope="request" value="${requestScope.apartments}"/>
<c:set var="hasError" scope="request" value="${requestScope.hasError}"/>
<c:set var="error" scope="request" value="${requestScope.error}"/>
<c:set var="errorField" scope="request" value="${requestScope.errorField}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StayBooker - Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>

<jsp:include page="include/header.jsp"/>

<div class="container mt-5">
    <h1 class="text-center">Find Your Perfect Stay</h1>
    <form class="form-row justify-content-center mt-4">
        <div class="form-group mb-2 mr-sm-2">
            <input type="text" class="form-control" id="city" name="city" placeholder="City" value="${param.city}">
            <div class="error-message" id="city-error" style="${hasError && errorField.equals("city") ? "display:block": ""}">${error}</div>
        </div>

        <div class="form-group mb-2 mr-sm-2">
            <input type="date" class="form-control" id="checkin" name="checkIn" value="${param.checkIn}">
            <div class="error-message" id="checkin-error" style="${hasError && errorField.equals("checkIn") ? "display:block": ""}">${error}</div>
        </div>

        <div class="form-group mb-2 mr-sm-2">
            <input type="date" class="form-control" id="checkout" name="checkOut" value="${param.checkOut}">
            <div class="error-message" id="checkout-error" style="${hasError && errorField.equals("checkOut") ? "display:block": ""}">${error}</div>
        </div>

        <div class="form-group mb-2 mr-sm-2">
            <input type="number" class="form-control" id="guests" name="guests" placeholder="Guests" value="${param.guests}">
            <div class="error-message" id="guests-error" style="${hasError && errorField.equals("guests") ? "display:block": ""}">${error}</div>
        </div>

        <button type="submit" class="btn btn-primary mb-2">Search</button>
    </form>
</div>


<div class="container mt-5">
    <div class="row">
        <c:forEach var="apartment" items="${apartments}">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="${apartment.imageUrl}" class="card-img-top" alt="Stay Image">
                    <div class="card-body">
                        <h5 class="card-title">${apartment.title}</h5>
                        <p class="card-text">Location: ${apartment.location}</p>
                        <p class="card-text">Price: ${apartment.price}</p>
                        <a href="${pageContext.request.contextPath}/details/${apartment.id}" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <!-- Add more cards for different stays -->
    </div>

    <!-- Pagination -->
    <nav aria-label="Page navigation" class="mt-4">
        <ul class="pagination justify-content-center">
            <li class="page-item ${page<=1 ? "disabled" : ""}">
                <a class="page-link" href="${pageContext.request.contextPath}/?page=${page-1}" tabindex="-1">Previous</a>
            </li>

            <c:forEach var="p" begin="1" end="${lastPage}">
                <c:if test="${p<=lastPage}">
                    <li class="page-item ${p==page ? "active disabled" : ""}"><a class="page-link" href="${pageContext.request.contextPath}/?page=${p}">${p}</a></li>
                </c:if>
            </c:forEach>

            <li class="page-item ${page>=lastPage ? "disabled" : ""}">
                <a class="page-link" href="${pageContext.request.contextPath}/?page=${page+1}">Next</a>
            </li>
        </ul>
    </nav>

</div>

<jsp:include page="include/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
