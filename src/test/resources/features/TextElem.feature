# language: ru
Функция: Проверка текста элемента

  Предыстория:
    Дано открыта главная страница
    Когда кликаю "Войти"
    И перехожу на открытую вкладку
    И кликаю "Demo"
    И перехожу на открытую вкладку

  Сценарий: Проверка текста элемента в Личном Кабинете
    Когда кликаю "Войти в лк"
    И кликаю "Войти синяя"
    Тогда текст внутри "Имя пользователя" равен "Королёва Ольга"