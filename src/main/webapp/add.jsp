<%@ page contentType="text/html; charset=UTF-8" %>
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

    <title>Работа мечты</title>
</head>
<body>
<script>
    function validate() {
        let result = true;
        if ($('#model').val() == '') {
            alert('Model field is empty');
            result = false;
        } else if ($('#miles').val() == '') {
            alert('Miles field is empty');
            result = false;
        } else if ($('#price').val() == '') {
            alert('Price field is empty');
            result = false;
        }
        return result;
    }
</script>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">Новое объявление</div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/add.do" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="brand">Марка</label>
                        <select class="custom-select" name="brand" id="brand">
                            <c:forEach items="${brands}" var="brand">
                                <option value="<c:out value="${brand.id}"/>"><c:out value="${brand.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="model">Модель</label>
                        <input type="text" class="form-control" name="model" id="model">
                    </div>
                    <div class="form-group">
                        <label for="color">Цвет</label>
                        <select class="custom-select" name="color" id="color">
                            <c:forEach items="${colors}" var="color">
                                <option value="<c:out value="${color.id}"/>"><c:out value="${color.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="category">Категория</label>
                        <select class="custom-select" name="category" id="category">
                            <c:forEach items="${categories}" var="category">
                                <option value="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="year">Год выпуска</label>
                        <select type="text" class="custom-select" name="year" id="year">
                            <c:forEach items="${years}" var="year">
                                <option value="<c:out value="${year.value}"/>"><c:out value="${year.value}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="miles">Пробег</label>
                        <input type="number" class="form-control" name="miles" id="miles">
                    </div>
                    <div class="form-group">
                        <label for="price">Цена</label>
                        <input type="number" class="form-control" name="price" id="price">
                    </div>
                    <div class="form-group">
                        <label for="photo">Фото</label>
                        <input type="file" name="photo" id="photo">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate();">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>