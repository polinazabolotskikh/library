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
    <h1>Рабочий кабинет</h1>
    <nav>
        <ul>
            <li><a href="/employee">Рабочий кабинет</a></li>
            <li><a href="/catalog">Каталог</a></li>
            <li><a href="/reserve">Книги в брони</a></li>
            <li><a href="/logout">Выйти</a></li>
        </ul>
    </nav>
</header>

<main>
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