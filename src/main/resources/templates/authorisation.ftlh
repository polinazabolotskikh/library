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
    <h1>Авторизация</h1>
    <nav>
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/catalog">Каталог</a></li>
            <li><a href="/requests">Тендеры на поставку</a></li>
        </ul>
    </nav>
</header>

<main>
    <p style="color: red;">${error!""}</p>
    <h2>Авторизация</h2>
    <form action="/auto" method="POST">
        <label for="login">Логин</label>
        <input type="text" name="login" id="login" required><br>
        <br>
        <label for="password">Пароль</label>
        <input type="password" name="password" id="password" required><br>
        <br><button name="button">Войти</button>
        </form>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>

