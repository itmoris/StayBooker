<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:useBean id="apartments" scope="request" type="java.util.List"/>
<fmt:parseNumber var="page" integerOnly="true" type="number" value="${requestScope.currentPage}"/>
<fmt:parseNumber var="lastPage" integerOnly="true" type="number" value="${requestScope.lastPage}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StayBooker - Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>

<jsp:include page="include/header.jsp"/>

<div class="container mt-5">
    <h1 class="text-center">Find Your Perfect Stay</h1>
    <form method="GET" action="${pageContext.request.contextPath}/?page=${page}" class="form-inline justify-content-center mt-4">
        <input type="text" class="form-control mb-2 mr-sm-2" name="city" placeholder="City" value="${param.city}">
        <input type="date" class="form-control mb-2 mr-sm-2" name="checkIn" value="${param.checkIn}">
        <input type="date" class="form-control mb-2 mr-sm-2" name="checkOut" value="${param.checkOut}">
        <input type="number" class="form-control mb-2 mr-sm-2" name="guests" placeholder="Guests" value="${param.guests}">
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

            <c:forEach var="p" begin="${page}" end="${lastPage}">
                <c:if test="${p<=lastPage && p<=4}">
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
