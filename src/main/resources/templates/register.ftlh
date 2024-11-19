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
        /* position: fixed;
        bottom: 0;
        width: 100%; */
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
    <h1>Регистрация пользователей</h1>
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
    <h2>Добавление пользователя</h2>
    <p style="color: red;">${error!""}</p>
    <form action="/add_user" method="POST">
        <div class="line">
            <label class="main" for="login">Логин:</label>
            <input type="text" id="login" name="login" required>
        </div>
        <div class="line">
            <label class="main" for="password" >Пароль:</label>
            <input type="text" id="password" name="password" required>
        </div>
        <div class="line">
            <label class="main" for="type">Тип пользователя:</label>
            <label for="admin">Администратор</label>
            <input type="radio" id="admin" name="type" value="admin" required>
            <label for="reader">Читатель</label>
            <input type="radio" id="reader" name="type" value="reader" required>
            <label for="employee">Работник</label>
            <input type="radio" id="employee" name="type" value="employee" required>
            <label for="manager">Менеджер</label>
            <input type="radio" id="manager" name="type" value="manager" required>
        </div>
        <button type="submit">Добавить пользователя</button>
    </form>

    <h2>Добавление читателя</h2>
    <p style="color: red;">${error!""}</p>
    <form action="/add_reader" method="POST">
        <div class="line">
            <label class="main" for="libraryCard">Читательский билет:</label>
            <input type="text" id="libraryCard" name="libraryCard" required>
        </div>
        <div class="line">
            <label class="main" for="fio">ФИО:</label>
            <input type="text" id="fio" name="fio" required>
        </div>
        <div class="line">
            <label class="main" for="age" >Возраст:</label>
            <input type="text" id="age" name="age" required>
        </div>
        <div class="line">
            <label class="main" for="phone" >Телефон:</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div class="line">
            <label class="main" for="email" >Почта:</label>
            <input type="text" id="email" name="email" required>
        </div>
        <button type="submit">Добавить читателя</button>
    </form>

    <h2>Добавление работника</h2>
    <p style="color: red;">${error!""}</p>
    <form action="/add_employee" method="POST">
        <div class="line">
            <label class="main" for="fio">ФИО:</label>
            <input type="text" id="fio" name="fio" required>
        </div>
        <div class="line">
            <label class="main" for="phone" >Телефон:</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div class="line">
            <label class="main" for="function">Должность:</label>
            <label for="librarian">Библиотекарь</label>
            <input type="radio" id="librarian" name="function" value="librarian" required>
            <label for="purchasing_manager">Менеджер по закупкам</label>
            <input type="radio" id="purchasing_manager" name="function" value="purchasing_manager" required>
            <label for="programmer">Программист</label>
            <input type="radio" id="programmer" name="function" value="programmer" required>
        </div>
        <div class="line">
            <label class="main" for="shift">Смена:</label>
            <label for="first">Первая</label>
            <input type="radio" id="first" name="shift" value="first" required>
            <label for="second">Вторая</label>
            <input type="radio" id="second" name="shift" value="second" required>
        </div>
        <button type="submit">Добавить работника</button>
    </form>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>
