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
    <title>Movie List</title>
    <style>
        body {
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
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .row {
            display: flex;
            justify-content: space-between;
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
            font-size: 16px;
            font-weight: bold;
            margin: 5px 0;
        }

        .movie-year {
            font-size: 14px;
            color: #777;
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

        .search-container {
            text-align: center;
            margin-top: 20px;
        }

        .search-input {
            padding: 10px;
            width: 50%;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
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
        .search-button {
            padding: 10px 20px;
            background-color: #202830;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
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
            <li><a href="/logout">Sign Out</a></li>

        </c:if>
        <c:if test="${userName==null}" >
            <li><a href="/home">Home</a></li>
            <li><a href="/login">Sign In</a></li>
            <li><a href="/register">Create Account</a></li>
        </c:if>
    </ul>
</header>
<br><br>
<div class="search-container">
    <form action="/movie" method="get">
        <input class="search-input" type="text" name="movieName" value="${movieName}" placeholder="Search for a movie...">
        <input class="search-button" type="submit" value="Search">
    </form>
</div>
<br><br>
<div class="container">
    <%int c=0;%>
    <c:forEach var="movie" items="${movies}" varStatus="loop">
        <%c++;%>
        <c:if test="${loop.index % 3 == 0}">
            <div class="row">
        </c:if>
        <div class="movie">
            <a href="/movie/${movie.imdbID}"><img src="${movie.poster}" alt="${movie.title} Poster"></a>
            <div class="movie-title">
                <a href="/movie/${movie.imdbID}"> ${movie.title} (${movie.year})</a>
            </div>
        </div>
        <c:if test="${loop.index % 3 == 2 || loop.last}">
            </div>
        </c:if>
    </c:forEach>
    <%if(c==0){%>
    <h1>No Movies Found</h1>
    <%}%>
</div>
</body>
</html>

