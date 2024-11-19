<style>

    header {
        background-color: #333;
        color: white;
        padding: 10px 20px;
        text-align: center;
    }

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

    main {
        padding: 20px;
        text-align: center;
    }

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

    footer {
        background-color: #333;
        color: white;
        text-align: center;
        padding: 10px 0;
        position: fixed;
        bottom: 0;
        width: 100%;
    }
    label{
        display: block;

    }
    .main{
        font-weight: bold;
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
    <h1>Создать тендер</h1>
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
                <li>У вас закончился срок сессии, зайдите заново</li>
                <li><a href="/logout">Выйти</a></li>
            </#if>
        </ul>
    </nav>
</header>

<main>
    <h2>Добавление тендера на поставку</h2>
    <#--<p style="color: red;">${error!""}</p>-->
    <form action="/add_tender" method="POST">
        <div class="line">
            <label class="main" for="title">Название книги:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="line">
            <label class="main" for="author" >Автор:</label>
            <input type="text" id="author" name="author" required>
        </div>
        <div class="line">
            <label class="main" for="year">Год:</label>
            <input type="text" id="year" name="year" required>
        </div>
        <div class="line">
            <label class="main" for="quantity">Необходимое количество:</label>
            <input type="text" id="quantity" name="quantity" required>
        </div>
        <button type="submit">Добавить тендер</button>
    </form>

</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>
