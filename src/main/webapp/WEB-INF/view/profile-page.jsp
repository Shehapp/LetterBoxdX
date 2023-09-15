<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sheha
  Date: 9/1/2023
  Time: 8:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <style>
        .f {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        body{
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
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
            max-width: 550px;
            margin: 0 auto;
            padding: 20px;


        }

        .row {
            display:flex;
            margin-bottom: 20px;
        }

        .movie {
            flex: 0 0 calc(33.33% - 20px);
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            margin-right: 20px;
            padding: 10px;
            text-align: center;
        }

        .movie img {
            max-width: 100%;
            height: auto;
        }

        .movie-title {
            font-size: 13px;
            font-weight: bold;
            margin: 5px 0;
        }

        /* Style for unvisited links */
        a {
            color: #0078d4; /* Link text color */
            text-decoration: none; /* Remove underline */
            transition: color 0.2s; /* Smooth color transition */
        }

        /* Style for visited links */
        a:visited {
            color: #4a90e2; /* Visited link text color */
        }

        /* Style for hover (mouse-over) links */
        a:hover {
            color: #0056b3; /* Hovered link text color */
        }

        /* Style for active (clicked) links */
        a:active {
            color: #ff4500; /* Active link text color */
        }

        /* Style for links within specific elements (e.g., class="button") */
        .button a {
            background-color: #0078d4; /* Background color for links inside .button elements */
            color: #fff; /* Text color for links inside .button elements */
            padding: 10px 20px; /* Add padding for buttons */
            border-radius: 4px; /* Add rounded corners for buttons */
            text-decoration: none;
            transition: background-color 0.2s; /* Smooth background color transition */
        }

        /* Style for button links on hover */
        .button a:hover {
            background-color: #0056b3; /* Background color when hovered */
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
        h4 {
            font-size: 15px;
            font-weight: bold;
            color: #311f1f;
            margin-bottom: 10px;
        }
        .nav-links a:hover {
            color: #e74c3c; /* Red color on hover */
        }
        /* Add these styles to your existing CSS */

        .review-section {
            display: flex;
            /*align-items: center; !* Center vertically *!*/
            margin-bottom: 20px;
        }

        .review-pic {
            width: 120px; /* Adjust the size as needed */
            height: 180px;
            /*border-radius: 100%; !* Make it circular if desired *!*/
            margin-right: 10px; /* Add some spacing between the picture and name */
            margin-top: 15px;
        }
        .review-details {
             display: flex;
            margin-top: 12px;
             flex-direction: column;
             align-items: flex-start;
         }

        .profile-section {
            display: flex;
            align-items: center; /* Center vertically */
            margin-bottom: 20px;
        }

        .profile-pic {
            width: 120px; /* Adjust the size as needed */
            height: 120px;
            border-radius: 100%; /* Make it circular if desired */
            margin-right: 10px; /* Add some spacing between the picture and name */
            margin-top: 7px;
            margin-left: 40px;
        }
        .join-year {
            font-size: 19px;
            color: #777; /* Change color to your preference */
        }
        .rev {
            font-size: 19px;
            font-style: italic;

            color: #777; /* Change color to your preference */
        }
        .join-date {
            font-size: 14px;
            color: #777; /* Change color to your preference */
        }.user-details {
             display: flex;
             flex-direction: column;
             align-items: flex-start;
         }
        .user-name {
            font-size: 24px;
            font-weight: bold;
            color: #333; /* Change color to your preference */
        }
        .data {
            font-size: 14px;
            color: #555; /* Change color to your preference */
        }
        .navbar {
            background-color: #F5F5F5FF;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
        }

        .navbar a {
            color: #312828;
            font-weight: bold;
            text-decoration: none;
            padding: 10px 20px;
        }

        .navbar a:hover {
            background-color: #67c76c;
        }


        .navbar .menu {
            align-items: center;
            display: flex;
        }

        .navbar .menu a {
            align-items: center;
            flex: 1;
            text-align: center;
        }
        .here {
            background-color: #67c76c;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
        }
    </style>
</head>
<body>
<header>
    <a href="/">
        <img class="logo" src="https://a.ltrbxd.com/logos/letterboxd-logo-h-neg-rgb-1000px.png" alt="Letterboxd Logo">
    </a>
    <ul class="nav-links">
        <li><a href="/home">Home</a></li>
        <c:if test="${profileData.myProfile==true || userName!=null}">
            <li><a href="/profile/${userName}">Profile</a></li>
            <li><a href="/logout">Sign Out</a></li>
        </c:if>
        <c:if test="${profileData.myProfile==false and userName==null}">
            <li><a href="/login">Sign In</a></li>
            <li><a href="/register">Create Account</a></li>

        </c:if>
    </ul>
</header>
<br><br>

<div class="f">
    <div class="profile-section">
        <img class="profile-pic" src="/UrlToReach/assets/profilePics/${profileData.poster}" alt="User Profile Picture">
        <div class="user-details">
            <span class="user-name">${profileData.name}</span><br>
            <span class="join-date">Join Date: ${profileData.createdAt}</span>
        </div>
    </div>


<div class="navbar">
    <div class="menu">
        <a href="/profile/${profileData.name}" <c:if test="${profile == true}" >class="here"</c:if>>Profile</a>
        <a href="/profile/${profileData.name}/films" <c:if test="${films == true}" >class="here"</c:if>>Films</a>
        <a href="/profile/${profileData.name}/reviews" <c:if test="${review == true}" >class="here"</c:if>>Reviews</a>
        <a href="/profile/${profileData.name}/watchlist" <c:if test="${watchlist == true}" >class="here"</c:if>>Watchlist</a>
        <a href="/profile/${profileData.name}/favorite" <c:if test="${favorite == true}" >class="here"</c:if>>Favorite</a>
        <a href="/profile/${profileData.name}/logs" <c:if test="${log == true}" >class="here"</c:if>>Activity</a>
    </div>
</div>
</div>
<br><br>


<c:if test="${review == null and log == null}">
<div class="container" >
    <c:if test="${profile == true}" > <h4>RECENTLY WATCHED</h4></c:if>
    <c:if test="${films == true}" > <h4>FILMS</h4></c:if>
    <c:if test="${review == true}" > <h4>RECENTLY REVIEWS</h4></c:if>
    <c:if test="${watchlist == true}" > <h4>WATCHLIST</h4></c:if>
    <c:if test="${favorite == true}" > <h4>FAVORITE</h4></c:if>
    <c:if test="${log == true}" > <h4>ACTIVITY</h4></c:if>

    <%int c=0;%>



        <c:forEach var="movie" items="${movies}" varStatus="loop">
            <%c++;%>
            <c:if test="${loop.index % 3 == 0}">
                <div class="row">
            </c:if>
            <div class="movie">
                <c:if test="${films == true}" >
                    <p class="data">Watched ${movie.createdAt}</p>
                    <a href="/movie/${movie.imdbID}"><img src="${movie.poster}" alt="${movie.title} Poster"></a>
                    <div class="movie-title">
                        <a href="/movie/${movie.imdbID}"> ${movie.title} (${movie.year})</a>
                    </div>
                </c:if>

                <c:if test="${watchlist == true || favorite==true}" >
                <a href="/movie/${movie.imdbID}"><img src="${movie.poster}" alt="${movie.title} Poster"></a>
                <div class="movie-title">
                    <a href="/movie/${movie.imdbID}"> ${movie.title} (${movie.year})</a>
                </div>
                </c:if>

                <c:if test="${profile==true}" >
                    <a href="/movie/${movie.imdbID}"><img src="${movie.poster}" alt="${movie.title} Poster"></a>
                    <div class="movie-title">
                        <a href="/movie/${movie.imdbID}"> ${movie.title} (${movie.year})</a>
                    </div>
                </c:if>
            </div>
            <c:if test="${loop.index % 3 == 2 || loop.last}">
                </div>
            </c:if>
        </c:forEach>
        <%if(c==0){%>
        <h1>Not Found</h1>
        <%}%>
</div>
    </c:if>










<c:if test="${review == true}">
    <div class="container" >
        <c:if test="${profile == true}" > <h4>RECENTLY WATCHED</h4></c:if>
        <c:if test="${films == true}" > <h4>FILMS</h4></c:if>
        <c:if test="${review == true}" > <h4>RECENTLY REVIEWS</h4></c:if>
        <c:if test="${watchlist == true}" > <h4>WATCHLIST</h4></c:if>
        <c:if test="${favorite == true}" > <h4>FAVORITE</h4></c:if>
        <%int c=0;%>



        <c:forEach var="review1" items="${reviews}" varStatus="loop">
            <%c++;%>
            <div >
             <div >

    <div class="review-section">
        <a href="/movie/${review1.imdbID}"><img class="review-pic" src="${review1.moviePoster}.jpg" alt="User Profile Picture"></a>
        <div class="review-details">
            <span class="user-name">${review1.movieTitle}<span class="join-year"> ${review1.movieYear}</span></span><br>
            <span  >${review1.rating} out of 5.0 &nbsp;&nbsp;&nbsp;  Reviewed ${review1.createdAt}</span>
            <br>
            <span class="rev">" ${review1.review} "</span>
            <c:if test="${userName==null || review1.userName==userName }">
            <span class="likes">&#10084;
              <span class="join-year">
                ${review1.likes} likes
            </span>
            </span>
            </c:if>
            <c:if test="${userName!=null && review1.userName!=userName}">
                <form>
                    <a   href="/movie/${review1.imdbID}/like-review/${review1.reviewId}"><span class="likes">&#10084;<span class="join-year">
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

             </div>
            </div>
        </c:forEach>


        <%if(c==0){%>
        <h1>Not Found</h1>
        <%}%>
    </div>

</c:if>

<br>

<c:if test="${log == true}">
    <div class="container" >
        <c:if test="${profile == true}" > <h4>RECENTLY WATCHED</h4></c:if>
        <c:if test="${films == true}" > <h4>FILMS</h4></c:if>
        <c:if test="${review == true}" > <h4>RECENTLY REVIEWS</h4></c:if>
        <c:if test="${watchlist == true}" > <h4>WATCHLIST</h4></c:if>
        <c:if test="${favorite == true}" > <h4>FAVORITE</h4></c:if>
        <c:if test="${log == true}" > <h4>ACTIVITY</h4></c:if>
    <c:forEach var="log1" items="${logs}" varStatus="loop">
        <c:if test="${userName==null || profileData.name!=userName}">${log1.userName}</c:if>
        <c:if test="${userName!=null and profileData.name==userName}">You</c:if>
        &nbsp; ${log1.description} &nbsp;
        <c:if test="${log1.movieName==null}"><a href="/review/${log1.id}">review</a></c:if>
        <c:if test="${log1.movieName!=null}"><a href="/movie/${log1.id}">${log1.movieName}</a></c:if>

        at:${log1.createdAt}
        <br><br>


    </c:forEach>
    </div>
</c:if>
</body>
</html>