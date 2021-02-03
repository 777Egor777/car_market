<%@ page import="ru.job4j.carmarket.model.User" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Car Market</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-carBrand" href="#">CarMarket</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <% Object objUser = request.getSession().getAttribute("user"); %>
                    <% if (objUser == null) { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/auth.do">Войти в систему</a>
                    <% } else { %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/auth.do">
                        <%=((User) objUser).getName()%> | Выйти
                    </a>
                    <% } %>
                </li class="nav-item">
                <li>
                    <a class="nav-link" href="<%=request.getContextPath()%>/reg.do">Регистрация</a>
                </li>
                <li>
                    <a class="nav-link" href="<%=request.getContextPath()%>/add.do">Добавить объявление</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container pt-3 bg-primary">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Объявления
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Характеристики</th>
                        <th scope="col">Фото</th>
                        <th scope="col">Статус</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ads}" var="ad">
                        <tr>
                            <td>
                                <b style="font-size: 30px; color: Red"><c:out value="${ad.brand.name}"/> <c:out value="${ad.model}"/></b>
                                <br>
                                <ul>
                                    <li>Категория: <c:out value="${ad.category.name}"/></li>
                                </ul>
                                <ul>
                                    <li>Цвет: <c:out value="${ad.color.name}"/></li>
                                </ul>
                                <ul>
                                    <li>Год выпуска: <c:out value="${ad.year.value}"/></li>
                                </ul>
                                <ul>
                                    <li>Пробег: <c:out value="${ad.miles}"/></li>
                                </ul>
                                <ins style="font-size: 20px; color: Blue">Цена: <c:out value="${ad.price}"/> р.</ins>
                            </td>
                            <td>
                                <c:if test="${ad.photoName != null}">
                                    <img src='<c:url value="/download?name=${ad.photoName}"/>' width="200px" height="200px" />
                                </c:if>
                                <c:if test="${ad.photoName == null}">
                                    <img src='<c:url value="/download?name=empty.jpg"/>' width="200px" height="200px" />
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${ad.status == true}">
                                    <sup style="font-size: 20px; color: Blue">Активно</sup>
                                </c:if>
                                <c:if test="${ad.status == false}">
                                    <sup style="font-size: 20px; color: Red">Продано</sup>
                                </c:if>
                                <c:if test="${ad.user.email == user.email && ad.status == true}">
                                    <br>
                                    <form action='<%=request.getContextPath()%>/index.do?ad_id=<c:out value="${ad.id}"/>' method="post">
                                        <button type="submit" class="btn btn-primary">Снять</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
