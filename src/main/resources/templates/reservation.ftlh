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
    <h1>Книги в брони</h1>
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
    <div class="container">
        <h1>Взятые книги</h1>
        <form action="/find_reader" method="post">
            <label for="readerId">Введите номер читательского билета:</label>
            <input type="text" id="readerId" name="readerId" required>
            <button type="submit">Поиск</button>
        </form>
    </div>
    <#if reader??>

        <div class="container">
            <h2>Забронированные книги для ${reader}</h2>

            <#if id??>
                <table>
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Дата бронирования</th>
                        <th>Дата возврата</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list reservations as reservation>
                    <tr>
                        <td>${reservation.bookName}</td>
                        <td>${reservation.dateBorrow}</td>
                        <td>${reservation.dateReturn}</td>
                        <td><form action="/cancel_reservation" method="post">
                                <input type="hidden" name="id" value="${id}">
                                <button type="submit">Отменить бронь</button>
                            </form></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>

            <#else>
                <p>Нет забронированных книг</p>
            </#if>
        </div>

    </#if>
</main>

<footer>
    <p>© 2024 Библиотека. Все права защищены.</p>
</footer>
</body>
</html>
