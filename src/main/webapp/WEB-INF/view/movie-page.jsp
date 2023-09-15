<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sheha
  Date: 9/1/2023
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Movie Details</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #202830;
      color: #fff;
      padding: 5px 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .logo {
      max-width: 20%;

      height: auto;
    }

    .nav-links {
      list-style: none;
      margin: 0;
      padding: 0;
      display: flex;
    }

    .nav-links li {
      margin-right: 20px;
    }

    .nav-links a {
      text-decoration: none;
      color: #fff;
      font-weight: bold;
      font-size: 16px;
      transition: color 0.3s ease;
    }

    .nav-links a:hover {
      color: #e74c3c; /* Red color on hover */
    }


    .container {
        justify-content: space-between;

        max-width: 800px;
      margin: 20px auto;
      background-color: #fff;
      padding: 20px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
      display: flex;
    }


    #movie-poster {
      max-width: 40%;
      height: auto;
      margin-right: 20px;
    }

    #movie-info {
      flex: 1;
    }

    #movie-title {
      font-size: 24px;
      font-weight: bold;
      margin: 10px 0;
    }

    #movie-details {
      font-size: 16px;
      margin-bottom: 20px;
    }

    #movie-plot {
      font-size: 18px;
      margin-top: 20px;
    }



    #comments-section h2 {
      font-size: 20px;
      margin-bottom: 10px;
    }

    .btn-container {
      display: flex;
      align-items: center;
      margin-top: 20px;
      justify-content: space-between;
    }

    .btn {

      width: 100px;
      height: 70px;
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
      font-size: 17px;
      transition: background-color 0.3s ease;
    }

    .btn i {
      font-size: 24px;
    }


    /* Add hover effect */
    .btn:hover {
      background-color: #222; /* Darken the background on hover */
    }






    /* Button used to open the review form - fixed at the bottom of the page */
    .open-button {
        background-color: #555;
        color: white;
        padding: 16px 20px;
        border: none;
        cursor: pointer;
        opacity: 0.8;
        position: fixed;
        bottom: 23px;
        right: 28px;
        width: 280px;
    }

    /* The popup form - hidden by default */
    .form-popup {
        width:300px;
        display: none;
        position: fixed;
        bottom: 0;
        right: 15px;
        border: 3px solid #f1f1f1;
        z-index: 9;
    }

    /* Add styles to the form container */
    .form-container {
        max-width: 300px;
        padding: 10px;
        background-color: white;
    }

    /* Full-width input fields */
    .form-container textarea {
        width: 100%;
        padding: 15px;
        margin: 5px 0 10px 0;
        border: none;
        background: #f1f1f1;
    }

    /* When the textarea gets focus, do something */
    .form-container textarea:focus {
        background-color: #ddd;
        outline: none;
    }

    .review-section {
      display: flex;
      /*align-items: center; !* Center vertically *!*/
      margin-bottom: 20px;
    }
    .review-details {
      display: flex;
      margin-top: 12px;
      flex-direction: column;
      align-items: flex-start;
    }
    /* Set a style for the submit/review button */
    .form-container .btn {
        background-color: #04AA6D;
        color: white;
        padding: 16px 20px;
        border: none;
        cursor: pointer;
        width: 100%;
        margin-bottom: 10px;
        opacity: 0.8;
    }
    .join-year {
      font-size: 19px;
      color: #777; /* Change color to your preference */
    }
    .review-section {
      display: flex;
      /*align-items: center; !* Center vertically *!*/
      margin-bottom: 20px;
    }

    .review-pic {
      width: 120px; /* Adjust the size as needed */
      height: 120px;
      border-radius: 100%; /* Make it circular if desired */
      margin-right: 10px; /* Add some spacing between the picture and name */
      margin-top: 15px;
    }
    .review-details {
      display: flex;
      margin-top: 12px;
      flex-direction: column;
      align-items: flex-start;
    }

    /* Add a red background color to the cancel button */

    /* Add a red background color to the cancel button */
    .form-container .delete {
      background-color: red;
    }
    /* Add some hover effects to buttons */
    .form-container .btn:hover, .open-button:hover {
        opacity: 1;
    }
    .rev {
      font-size: 19px;
      font-style: italic;

      color: #777; /* Change color to your preference */
    }
    .review-container{
      justify-content: center;
        max-width: 800px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
        display: flex;
        flex-direction: column;

    }
    .likes{
        font-size: 24px;
        /*font-weight: bold;*/
      /*font-style: italic;*/
        color: #333; /* Change color to your preference */
    }
    a{
      text-decoration: none;
      color: #333; /* Change color to your preference */
    }

    .icon-heart-empty {
      color: #333; /* Change color to your preference */
    }
    .test-button {
      border: none;
      background: none;
      padding: 0;
      font: inherit;
      cursor: pointer;
      color: #ffffff;
      text-decoration: none;
      font-weight: bold;
      font-size: 16px;
      transition: color 0.3s ease;
    }
    .test-button:hover {
      color: #e74c3c;
    }
  </style>

</head>
<body>

<header>
  <a href="/">
    <img class="logo" src="https://a.ltrbxd.com/logos/letterboxd-logo-h-neg-rgb-1000px.png" alt="Letterboxd Logo">
  </a>
  <ul class="nav-links">
    <c:if test="${userName!=null}" >
      <li><a href="/home">Home</a></li>
      <li><a href="/profile/${userName}">Profile</a></li>
      <li><form:form action="/logout" method="post">
        <input class="test-button" type="submit" value="logout">
      </form:form></li>

    </c:if>
    <c:if test="${userName==null}" >
      <li><a href="/home">Home</a></li>
      <li><a href="/login">Sign In</a></li>
      <li><a href="/register">Create Account</a></li>
    </c:if>
  </ul>
</header>

<div class="container">
  <img id="movie-poster" src="${movie.poster}" alt="Movie Poster">
  <div id="movie-info">
    <h2 id="movie-title">${movie.title}</h2>
    <div id="movie-details">
      <p><strong>Released:</strong> ${movie.released}</p>
      <p> ${movie.runtime}</p>
      <p><strong>IMDb Rating:</strong> ${movie.imdbRating}</p>
      <p><strong>Actors:</strong> ${movie.actors}</p>

    </div>
    <p id="movie-plot"> ${movie.plot}</p>
    <p class="director"><strong>Directed By:</strong> ${movie.directors}</p>
    <br>



    <div class="btn-container">
      <form:form action="/movie/${movie.imdbID}/watch/${!movieState.watched}" method="post">
        <button class="btn" type="submit">

          <c:if test="${movieState.watched==true}">
            <i class="fas fa-check"></i>
            <span>Remove From Watched</span>
            </c:if>

          <c:if test="${movieState==null || !movieState.watched}">
            <i class="fas fa-check"></i>
            <span>Add To Watched</span>
          </c:if>

        </button>
        </form:form>

      <form:form action="/movie/${movie.imdbID}/fav/${!movieState.favorite}" method="post">
        <i class="fas fa-heart"></i>
        <button class="btn" type="submit">

          <c:if test="${movieState.favorite==true}">
            <i class="fas fa-check"></i>
            <span>Remove From Favorite</span>
          </c:if>

          <c:if test="${movieState==null || !movieState.favorite}">
            <i class="fas fa-check"></i>
            <span>Add To Favorite</span>
          </c:if>

        </button>
        </form:form>

        <form:form action="/movie/${movie.imdbID}/watchlist/${!movieState.watchList}" method="post">
          <button class="btn" type="submit">
            <c:if test="${movieState.watchList == true}">
              <i class="fas fa-check"></i>
              <span>Remove From Watchlist</span>
            </c:if>

            <c:if test="${movieState==null || !movieState.watchList}">
              <i class="fas fa-check"></i>
              <span>Add To Watchlist</span>
            </c:if>

          </button>
        </form:form>

    </div>
  </div>
</div>
<c:if test="${userName!=null}" >
<button class="open-button" onclick="openForm()">Review</button>

<div class="form-popup" id="myForm">

    <form:form action="/movie/${movie.imdbID}/add-review" class="form-container" method="post">
      <button type="button" onclick="closeForm()">Close</button>

      <c:if test="${myReview!=null}">
      <h1>Update Review</h1>
      </c:if>
      <c:if test="${myReview==null}">
        <h1>Add Review</h1>
      </c:if>

        <textarea placeholder="Write your review here" name="review" required>${myReview.review}</textarea>
        <br>
        <label ><b>Rating</b></label>
      <label>
        <select   name="rating" id="rating" required>
            <option value="5">5 stars</option>
            <option value="4">4 stars</option>
            <option value="3">3 stars</option>
            <option value="2">2 stars</option>
            <option value="1">1 star</option>
        </select>
      </label>
      <br><br>
        <button type="submit" class="btn">Submit Review</button>
      <c:if test="${myReview!=null}">
        <button type="submit" class="btn delete" formaction="/movie/${movie.imdbID}/delete-review">Delete Review</button>
        </c:if>
    </form:form>
</div>
</c:if>
<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }

    var select = document.getElementById("rating");
    select.value = "${myReview.rating}"===""?5:parseInt(${myReview.rating});
</script>



<c:if test="${myReview!=null}">
<div class="review-container">


  <h3>YOUR REVIEW</h3>

    <div >
      <div >
        <div class="review-section">
          <a href="/profile/${myReview.userName}"><img class="review-pic" src="/UrlToReach/assets/profilePics/${myReview.userImage}" alt="User Profile Picture"></a>
          <div class="review-details">
            <span > Review by<span class="join-year"><a href="/profile/${myReview.userName}"> <strong>${myReview.userName}</strong></a></span></span><br>
            <span  >${myReview.rating} out of 5.0 &nbsp;&nbsp;&nbsp;  Reviewed ${myReview.createdAt}</span>
            <br>
            <span class="rev">" ${myReview.review} "</span>
            <br>

            <c:if test="${userName==null || myReview.userName==userName }">
            <span class="likes">&#10084;
              <span class="join-year">
                ${myReview.likes} likes
            </span>
            </span>
            </c:if>
            <c:if test="${userName!=null && myReview.userName!=userName}">
              <form>
                <a   href="/movie/${movie.imdbID}/like-review/${myReview.reviewId}"><span class="likes">&#10084;<span class="join-year">
                    <c:if test="${myReview.likedByUser ==true}">
                      unlike
                    </c:if>
                  <c:if test="${myReview.likedByUser ==false}">
                    like
                  </c:if>
                    ${myReview.likes} likes
                </span></span></a>
              </form>
            </c:if>
          </div>
        </div>
        <br><br>

      </div>
    </div>

</div>
</c:if>








<div class="review-container">
   <%int c=0;%>
  <h3>RECENT REVIEWS</h3>

  <c:forEach var="review1" items="${reviews}" varStatus="loop">
    <%c++;%>
    <div >
      <div >
        <div class="review-section">
          <a href="/profile/${review1.userName}"><img class="review-pic" src="/UrlToReach/assets/profilePics/${review1.userImage}" alt="User Profile Picture"></a>
          <div class="review-details">
            <span > Review by<span class="join-year"><a href="/profile/${review1.userName}"> <strong>${review1.userName}</strong></a></span></span><br>
            <span  >${review1.rating} out of 5.0 &nbsp;&nbsp;&nbsp;  Reviewed ${review1.createdAt}</span>
            <br>
            <span class="rev">" ${review1.review} "</span>
            <br>

            <c:if test="${userName==null || review1.userName==userName }">
            <span class="likes">&#10084;
              <span class="join-year">
                ${review1.likes} likes
            </span>
            </span>
            </c:if>
            <c:if test="${userName!=null && review1.userName!=userName}">
              <form>
                <a   href="/movie/${movie.imdbID}/like-review/${review1.reviewId}"><span class="likes">&#10084;<span class="join-year">
                    <c:if test="${review1.likedByUser ==true}">
                      unlike
                    </c:if>
                  <c:if test="${review1.likedByUser ==false}">
                    like
                  </c:if>
                    ${review1.likes} likes
                </span></span></a>
              </form>
                </c:if>
          </div>
        </div>
        <br><br>

      </div>
    </div>
  </c:forEach>


  <%if(c==0){%>
  <h1>Not Found</h1>
  <%}%>
</div>



</body>
</html>
