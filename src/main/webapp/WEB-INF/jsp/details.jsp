<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StayBooker - Stay Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Cozy Apartment in City Center</h2>
    <div class="row mt-4">
        <div class="col-md-8">
            <img src="image1.jpg" class="img-fluid" alt="Stay Image">
        </div>
        <div class="col-md-4">
            <h4>Details</h4>
            <p><strong>Location:</strong> City Center</p>
            <p><strong>Price:</strong> $100/night</p>
            <p><strong>Amenities:</strong> Free WiFi, Kitchen, Air Conditioning</p>
            <p><strong>Availability:</strong> Check calendar for dates</p>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#bookingModal">Book Now</button>
        </div>
    </div>

    <div class="mt-5">
        <h4>Reviews</h4>
        <div class="card mt-3">
            <div class="card-body">
                <h5 class="card-title">John Doe</h5>
                <p class="card-text">Great place! Very clean and close to everything.</p>
            </div>
        </div>
        <!-- Add more reviews -->
    </div>

    <!-- Форма для оставления нового комментария -->
    <div class="mt-4">
        <h5>Leave a Review</h5>
        <form>
            <div class="form-group">
                <label for="userName">Your Name</label>
                <input type="text" class="form-control" id="userName" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <label for="userReview">Your Review</label>
                <textarea class="form-control" id="userReview" rows="3" placeholder="Write your review here" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit Review</button>
        </form>
    </div>
</div>

<!-- Модальное окно с формой для бронирования -->
<div class="modal fade" id="bookingModal" tabindex="-1" role="dialog" aria-labelledby="bookingModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="bookingModalLabel">Book Your Stay</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="checkinDate">Check-in Date</label>
                        <input type="date" class="form-control" id="checkinDate" required>
                    </div>
                    <div class="form-group">
                        <label for="checkoutDate">Check-out Date</label>
                        <input type="date" class="form-control" id="checkoutDate" required>
                    </div>
                    <div class="form-group">
                        <label for="guests">Number of Guests</label>
                        <input type="number" class="form-control" id="guests" min="1" value="1" required>
                    </div>
                    <div class="form-group">
                        <label for="specialRequests">Special Requests</label>
                        <textarea class="form-control" id="specialRequests" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Confirm Booking</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
