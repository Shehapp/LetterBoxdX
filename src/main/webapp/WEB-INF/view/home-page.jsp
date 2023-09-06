<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sheha
  Date: 9/1/2023
  Time: 7:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Search</title>
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


        .quote-container {
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            width: 80%;
            max-width: 500px;
            margin: 0 auto;
        }

        .quote-text {
            font-size: 24px;
            font-style: italic;
            color: #333;
            margin-bottom: 20px;
        }

        .author-text {
            font-size: 18px;
            color: #777;
        }

        .search-container {
            text-align: center;
            margin-top: 20px;
        }

        .search-input {
            padding: 10px;
            width: 60%;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
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


        .search-button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<header>
    <a href="/">
        <img class="logo" src="https://a.ltrbxd.com/logos/letterboxd-logo-h-neg-rgb-1000px.png" alt="Letterboxd Logo">
    </a>
    <ul class="nav-links">
        <c:if test="${sessionScope.userName!=null}" >
            <li><a href="/profile/${sessionScope.userName}">Profile</a></li>
            <li><a href="/logout">Sign Out</a></li>

        </c:if>
        <c:if test="${sessionScope.userName==null}" >
            <li><a href="/login">Sign In</a></li>
            <li><a href="/register">Create Account</a></li>
        </c:if>

    </ul>
</header>
<div class="quote-container">
    <p class="quote-text">"${quote.quote}"</p>
    <p class="author-text">- ${quote.author}</p>
</div>
<br>
<div class="search-container">
    <form action="/movie" method="get">
        <input class="search-input" type="text" name="movieName" placeholder="Search for a movie...">
        <input class="search-button" type="submit" value="Search">
    </form>
</div>
</body>
</html>
