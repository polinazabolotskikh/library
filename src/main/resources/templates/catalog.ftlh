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
        position: fixed;
        bottom: 0;
        width: 100%;
    }

    .card-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        justify-content: center;
    }

    .card {
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 16px;
        width: 200px;
        text-align: center;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .card img {
        max-width: 100%;
        border-radius: 8px 8px 0 0;
    }

    .card-title {
        font-size: 1.2em;
        margin: 8px 0;
    }
    .card-author{
        font-size: 1.1em;
        margin: 8px 0;
    }

    .card-line {
        color: #555;
    }

    .button{
        margin-top: 10px;
        padding: 8px 16px;
        font-size: 14px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 20px; 
        cursor: pointer;
        transition: background-color 0.3s ease;
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
    <h1>Каталог книг</h1>
    <#assign userType = userType! -1>
    <nav>
        <ul>
            <#if userType == 0>
                <li><a href="/admin">Главная</a></li>
                <li><a href="/catalog">Каталог</a></li>
                <li><a href="/reserve">Книги в брони</a></li>
                <li><a href="/tenders">Создать тендер</a></li>
                <li><a href="/requests">Тендеры на поставку</a></li>
                <li><a href="/supplier">Заявки на поставку</a></li>
                <li><a href="/register">Регистрация пользователей</a></li>
                <li><a href="/logout">Выйти</a></li>
            <#elseif userType == 1>
                <li><a href="/reader">Личный кабинет</a></li>
                <li><a href="/catalog">Каталог</a></li>
                <li><a href="/logout">Выйти</a></li>
            <#elseif userType == 2>
                <li><a href="/employee">Рабочий кабинет</a></li>
                <li><a href="/catalog">Каталог</a></li>
                <li><a href="/reserve">Книги в брони</a></li>
                <li><a href="/logout">Выйти</a></li>
            <#elseif userType == 3>
                <li><a href="/manager">Рабочий кабинет</a></li>
                <li><a href="/catalog">Каталог</a></li>
                <li><a href="/tenders">Создать тендер</a></li>
                <li><a href="/requests">Тендеры на поставку</a></li>
                <li><a href="/logout">Выйти</a></li>
                <#else>
                <li><a href="/">Главная</a></li>
                <li><a href="/requests">Тендеры на поставку</a></li>
            </#if>
        </ul>
    </nav>
</header>

<main>
    <p style="color: red;">${error!""}</p>
    <div class="card-container">
        <#list books as book>
            <#assign item_index = book.id-1>
            <#assign year = "year_"+item_index>
            <div class="card">
                <div class="card-author">${book.author}</div>
                <div class="card-title">"${book.nameBook}"</div>
                <div class="card-line">${year?eval}</div>
                <#if book.typeBook == "printed">
                <div class="card-line">печатная книга</div>
                <#elseif book.typeBook == "eBook">
                <div class="card-line">электронная книга</div>
                </#if>

                <#if userType == 1>
                        <form action="/catalog" method="post">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="hidden" name="libraryCard" value="${libraryCard}">
                            <button type="submit" class="button">Взять</button>
                        </form>
                    </#if>


            </div>
        </#list>
    </div>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>
