<style>
    /* Стили для шапки */
    header {
        background-color: #333;
        color: white;
        padding: 10px 20px;
        text-align: center;
    }

    /* Стили для навигационного меню */
    nav ul {
        list-style-type: none;
        padding: 0;
    }

    nav ul li {
        display: inline;
        margin-right: 20px;
    }

    nav ul li a {
        color: white;
        text-decoration: none;
    }

    /* Стили для основного содержимого */
    main {
        padding: 20px;
        text-align: center;
    }

    /* Стили для кнопки */
    button {
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }

    /* Стили для подвала */
    footer {
        background-color: #333;
        color: white;
        text-align: center;
        padding: 10px 0;
    }
    .container {
        max-width: 1200px;
        margin: 20px auto;
        padding: 20px;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        margin-bottom: 20px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    label{
        display: block;

    }
    input{
        width: 100%;
        padding: 10px;
    }
    .line{
        border: 1px solid black;
        margin-bottom: 15px;
    }
    form{
        max-width: 400px;
        margin: 0 auto;
    }
    button{
        background-color: #007bff;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
    }
</style>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Библиотека</title>
</head>
<body>
<header>
    <h1>Главная</h1>
    <nav>
        <ul>
            <li><a href="/admin">Главная</a></li>
            <li><a href="/catalog">Каталог</a></li>
            <li><a href="/reserve">Книги в брони</a></li>
            <li><a href="/tenders">Создать тендер</a></li>
            <li><a href="/requests">Тендеры на поставку</a></li>
            <li><a href="/supplier">Заявки на поставку</a></li>
            <li><a href="/register">Регистрация пользователей</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
    </nav>
</header>

<main>
    <div class="header">
        <h1>Список читателей</h1>
    </div>
    <div class="container">
        <#if readers?size == 0>
        <p>Нет доступных читателей</p>
        <#else>
        <table>
            <thead>
            <tr>
                <th>Читательский билет</th>
                <th>Имя</th>
                <th>Возраст</th>
                <th>Номер телефона</th>
                <th>Электронная почта</th>
            </tr>
            </thead>
            <tbody>
            <#list readers as reader>
                <tr>
                    <td>${reader.library_card}</td>
                    <td>${reader.fio}</td>
                    <td>${reader.age}</td>
                    <td>${reader.phone}</td>
                    <td>${reader.email}</td>
                </tr>
            </#list>
            </tbody>
        </table>
        </#if>
    </div>

    <div class="header">
        <h1>Список работников</h1>
    </div>
    <div class="container">
        <#if readers?size == 0>
            <p>Нет доступных работников</p>
        <#else>
        <table>
            <thead>
            <tr>
                <th>Имя</th>
                <th>Должность</th>
                <th>Номер телефона</th>
                <th>Смена</th>
            </tr>
            </thead>
            <tbody>
            <#list employees as employee>
                <tr>
                    <td>${employee.fio}</td>
                    <#if employee.function == "librarian">
                        <td>Библиотекарь</td>
                    <#elseif employee.function == "purchasing_manager">
                        <td>Менеджер по закупкам</td>
                    <#elseif employee.function == "programmer">
                        <td>Программист</td>
                    </#if>
                    <td>${employee.phone}</td>
                    <#if employee.shift == "first">
                    <td>Первая</td>
                    <#elseif employee.shift == "second">
                        <td>Вторая</td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
        </#if>
    </div>

    <h2>Добавление книги</h2>
    <p style="color: red;">${error!""}</p>
    <form action="/add_book" method="POST">
        <div class="line">
            <label class="main" for="title">Название книги:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="line">
            <label class="main" for="author" >Автор:</label>
            <input type="text" id="author" name="author" required>
        </div>
        <div class="line">
            <label class="main" for="rating">Рейтинг:</label>
            <label for="G">0+</label>
            <input type="radio" id="G" name="rating" value="G" required>
            <label for="PG">12+</label>
            <input type="radio" id="PG" name="rating" value="PG" required>
            <label for="R">18+</label>
            <input type="radio" id="R" name="rating" value="R" required>
        </div>
        <div class="line">
            <label class="main" for="type">Тип книги:</label>
            <label for="printed">Печатная книга</label>
            <input type="radio" id="printed" name="type" value="printed" required>
            <label for="eBook">Электронная книга</label>
            <input type="radio" id="eBook" name="type" value="eBook" required>

        </div>
        <div class="line">
            <label class="main" for="year">Год:</label>
            <input type="text" id="year" name="year" required>
        </div>
        <div class="line">
            <label class="main" for="cost">Цена:</label>
            <input type="text" id="cost" name="cost" required>
        </div>
        <div class="line">
            <label class="main" for="quantity">Количество:</label>
            <input type="text" id="quantity" name="quantity" required>
        </div>
        <button type="submit">Добавить книгу</button>
    </form>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>