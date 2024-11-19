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
        margin: 20px;
    }
    .container {
        font-size: 20px;
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color: #f9f9f9;
    }
    h1 {
        text-align: center;
    }
    .info {
        margin-bottom: 10px;
    }
    .label {
        font-weight: bold;
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
        /* position: fixed;
        bottom: 0;
        width: 100%; */
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
    <h1>Личный кабинет</h1>
    <nav>
        <ul>
            <li><a href="/reader">Личный кабинет</a></li>
            <li><a href="/catalog">Каталог</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
    </nav>
</header>

<main>
    <div class="container">
        <h1>Личный кабинет читателя</h1>
        <div class="info">
            <span class="label">Имя:</span> <span id="name">${readerName}</span>
        </div>
        <div class="info">
            <span class="label">Возраст:</span> <span id="age">${readerAge!0}</span>
        </div>
        <div class="info">
            <span class="label">Номер телефона:</span> <span id="phone">${readerPhone!" "}</span>
        </div>
        <div class="info">
            <span class="label">Электронная почта:</span> <span id="email">${readerEmail!" "}</span>
        </div>
    </div>

    <div class="header">
        <h1>Взятые книги</h1>
    </div>

    <div class="container">
        <#if books?size == 0>
            <p>Нет доступных книг</p>
        <#else>
            <p style="color: red;">${error!""}</p>
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Автор</th>
                    <th>Тип книги</th>
                    <th>Дата взятия</th>
                    <th>Дата возврата</th>
                </tr>
                </thead>
                <tbody>
                <#list books as book>
                    <tr>
                        <td>${book.nameBook}</td>
                        <td>${book.author}</td>
                        <#if book.typeBook == "printed">
                            <td>Печатная книга</td>
                        <#elseif book.typeBook == "eBook">
                            <td>Электронная книга</td>
                        </#if>
                        <td>${dateBorrow}</td>
                        <td>${dateReturn}</td>
                        <td>
                            <form action="/extend" method="post">
                                <input type="hidden" name="bookId" value="${book.id}">
                                <button type="submit" class="button">Продлить</button>
                            </form>
                        </td>
                    </tr>

                </#list>
                </tbody>
            </table>
        </#if>
    </div>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>