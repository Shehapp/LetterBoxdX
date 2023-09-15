<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sheha
  Date: 9/1/2023
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Movie Review Login</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f5f5f5;
      margin: 0;
      padding: 0;
    }



    .login-block {
      width: 320px;
      padding: 20px;
      background: #fff;
      border-radius: 5px;
      border-top: 5px solid #202830;
      margin: 0 auto;
    }

    .login-block h1 {
      text-align: center;
      color: #000;
      font-size: 18px;
      text-transform: uppercase;
      margin-top: 0;
      margin-bottom: 20px;
    }

    .login-block input {
      width: 100%;
      height: 42px;
      box-sizing: border-box;
      border-radius: 5px;
      border: 1px solid #ccc;
      margin-bottom: 20px;
      font-size: 14px;
      font-family: Montserrat;
      padding: 0 20px 0 50px;
      outline: none;
    }

    .login-block input#username {
      background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px top no-repeat;
      background-size: 16px 80px;
    }

    .login-block input#username:focus {
      background: #fff url('http://i.imgur.com/u0XmBmv.png') 20px bottom no-repeat;
      background-size: 16px 80px;
    }

    .login-block input#password {
      background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px top no-repeat;
      background-size: 16px 80px;
    }

    .login-block input#password:focus {
      background: #fff url('http://i.imgur.com/Qf83FTt.png') 20px bottom no-repeat;
      background-size: 16px 80px;
    }

    .login-block input:active, .login-block input:focus {
      border: 1px solid #202830;
    }

    .enter {
      width: 100%;
      height: 40px;
      background: #202830;
      box-sizing: border-box;
      border-radius: 5px;
      border: 1px solid #202830;
      color: #fff;
      font-weight: bold;
      text-transform: uppercase;
      font-size: 14px;
      font-family: Montserrat;
      outline: none;
      cursor: pointer;
    }

    .login-block button:hover {
      background: #202830;
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

  </style>
</head>
<body>
<header>
  <a href="/">
    <img class="logo" src="https://a.ltrbxd.com/logos/letterboxd-logo-h-neg-rgb-1000px.png" alt="Letterboxd Logo">
  </a>
  <ul class="nav-links">
    <li><a href="/register">Create Account</a></li>
  </ul>
</header>
<br> <br> <br> <br> <br> <br> <br> <br>

<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>

<div class="logo"></div>
<div class="login-block">

  <h1>Login</h1>
  <c:if test="${param.error!=null}">
    <p style="color: red">username or password is not correct</p>
  </c:if>
  <c:if test="${param.logout!=null}">
    <p style="color: green">You sign out successfully</p>
  </c:if>
  <form:form >
    <label for="username"></label><input type="text" name="username" placeholder="Username" id="username" />
    <label for="password"></label><input type="password" name="password"  placeholder="Password" id="password" />
    <input type="submit" value="Login" class="enter">
</form:form>
</div>
</body>
</html>
