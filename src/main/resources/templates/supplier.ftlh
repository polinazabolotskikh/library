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
    <h1>Заявки на поставку</h1>
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


    <#if userType == -1>
    <h2>Добавление заявки на поставку книги "${nameBook}"</h2>
    <form action="/add_supply" method="POST">
        <div class="line">
            <label class="main" for="name">Наименование поставщика:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="line">
            <label class="main" for="phone" >Телефон:</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div class="line">
            <label class="main">Необходимое количество: ${quantity}</label>
        </div>
        <button type="submit">Оставить заявку</button>
    </form>
    <#else>
    <div class="card-container">
        <#list supplies as supply>

            <div class="card">
                <div class="card-title">Номер тендера: ${supply.request}</div><br>
                <div class="card-author">Поставщик:${supply.provider}</div>
                <div class="card-line">Телефон:${supply.phone}</div>

            </div>
        </#list>
    </#if>

</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>
