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
    <h1>Рабочий кабинет</h1>
    <nav>
        <ul>
            <li><a href="/manager">Рабочий кабинет</a></li>
            <li><a href="/catalog">Каталог</a></li>
            <li><a href="/tenders">Создать тендер</a></li>
            <li><a href="/requests">Тендеры на поставку</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
    </nav>
</header>

<main>
    <h2>Список поданных заявок на поставку</h2>
    <div class="card-container">
        <#list supplies as supply>

            <div class="card">
                <div class="card-title">Номер тендера: ${supply.request}</div>
                <div class="card-author">Поставщик:<br>${supply.provider}</div>
                <div class="card-line">Телефон:<br>${supply.phone}</div>

            </div>
        </#list>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>