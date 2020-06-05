
//45) Створіть слідуючу модель- є приватна автостоянка. Кожного місяця, водіям , які паркували авто,
//видається ракунок за використання послугами стоянки. Звіт можливо сортувати або по авто,
//або по власникам. Можлива ситуація коли 1 власник має декілько авто. Повинна бути можливість
//отримати список усіх авто які є на стоянці зараз, та журнал обліку виїздів-заїздів за період.
//Потрібна можливість формування звіту по окремому авто або власнику.Використовуйте колекції, графічний інтерфейс не потрібен.
import com.company.Avto;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Manager_program {
    private List<Avto> avtomob;
    private Collection<User> users;

    // Для шаблона Singletone статическая переменная
    private static Manager_program instance;

    // закрытый конструктор
    private Manager_program() {
        loadAvto();
        loadusers();
    }

    // метод getInstance - проверяет, инициализирована ли статическая
    // переменная (в случае надобности делает это) и возвращает ее
    public static Manager_program getInstance() {
        if (instance == null) {
            instance = new Manager_program();
        }
        return instance;
    }

    // Метод, который вызывается при запуске класса
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager_program ms = Manager_program.getInstance();
        //Аля личный кабинет
        printString("Журнал автопарка");
        printString("Введіть своє ім`я");
        String name_new = scanner.nextLine();
        printString("Вітаю вас, " + name_new + "!");
        printString("Загальний список");

            printString("+++-----------------------------------------+++");
            printString("Повний список припаркованих автомобілей");
            printString("+++-----------------------------------------+++");
            Collection<User> allAvtopark = ms.getAllusers();
            for (User si : allAvtopark) {
                printString(si);
            }
            printString();
        List<Avto> allAvto = ms.getAvto();

        printString("Имя: ");
        String firs_name = scanner.nextLine();
        printString("Фамилия");
        String psev = scanner.nextLine();
        printString("Модель автомобиля: ");
        String model_av = scanner.nextLine();
        printString("Id пользователя: ");
        int id_user = scanner.nextInt();
        printString("Сумма за месяц: ");
        int sum_denga = scanner.nextInt();
        printString("Дата: ");
        int day = scanner.nextInt();
        printString("Месяц: ");
        int mes = scanner.nextInt();
        printString("Год: ");
        int year = scanner.nextInt();

        // Регистрируем нового челика
        User user = new User();
        user.setFirst_name(firs_name);
        user.setPsev(psev);
        user.setId_man(id_user);
        user.setSum_denga(sum_denga);
        Calendar c = Calendar.getInstance();
        c.set(day, mes, year);
        printString("Додавання водія:" + user);
        printString("+++-----------------------------------------+++");
        ms.insertUser(user);
        printString("--->> Повний список водіїв після додавання нового водія <<---");
        allAvtopark = ms.getAllusers();
        for (User si : allAvtopark) {
            printString(si);
        }
        printString();

        printString("Редактируем данные: ");
        // Возможность редактировать журнал
        user = new User();
        user.setId_man(1);
        user.setFirst_name("Дима");
        user.setPsev("Тереблов");
        user.setSum_denga(1000);
        c = Calendar.getInstance();
        c.set(12, 5, 2020);

        ms.updateUser(user);
        allAvtopark = ms.getAllusers();
        for (User si : allAvtopark) {
            printString(si);
        }
        printString();

        printString("Автомобили которые присутствуют в данный момент: ");
        // Список автомобилей
        for (User si : allAvtopark) {
            si.setFiltr(2);
            printString(si);
        }
        printString();


    }
    public void loadAvto() {
        if (avtomob == null) {
            avtomob = new ArrayList<Avto>();
        } else {
            avtomob.clear();
        }
        Avto avt = null;

        avt = new Avto();
        avt.setCol_viezd(5);
        avt.setModel_avto("Жигули");

        avtomob.add(avt);

        avt = new Avto();
        avt.setCol_viezd(22);
        avt.setModel_avto("Лада");
        avtomob.add(avt);

        avt = new Avto();
        avt.setCol_viezd(12);
        avt.setModel_avto("Москвич");

        avtomob.add(avt);
    }
    // Загрузка юзеров
    // Проверим - может быть там пусто
    public void loadusers() {
        if (users == null) {
            // Коллекция автоматически сортирует свои елементы
            users = new TreeSet<User>();
        } else {
            users.clear();
        }

        User user = null;
        Calendar c = Calendar.getInstance();

        user = new User();
        user.setFirst_name("Суго");
        user.setPsev("Чанк");
        user.setId_man(3);
        user.setSum_denga(1000);
        c = Calendar.getInstance();
        c.set(12, 5, 2020);
        users.add(user);

        user = new User();
        user.setFirst_name("Симон");
        user.setPsev("Пухтинский");
        user.setId_man(2);
        user.setSum_denga(1400);
        c = Calendar.getInstance();
        c.set(12, 5, 2020);
        users.add(user);

        user = new User();
        user.setFirst_name("Дмитрий ");
        user.setPsev("Потужный");
        user.setId_man(1);
        user.setSum_denga(1000);
        c = Calendar.getInstance();
        c.set(12, 5, 2020);
        users.add(user);
    }

    // Получить список всех водителей
    public Collection<User> getAllusers() {
        return users;
    }

    public List<Avto> getAvto() {
        return avtomob;
    }
    // Добавить водителя
    public void insertUser(User user) {
        // Просто добавляем объект в коллекцию
        users.add(user);
    }


    // Обновляем информации о владельце
    public void updateUser(User user1) {
        // Надо найти нужного владельца (по его ИД) и заменить поля
        User user_v = null;
        for (User si : users) {
            if (si.getId_man() == user1.getId_man()) {
                // Вот этот владелец - запоминаем его и прекращаем цикл
                user_v = si;
                break;
            }
        }
        user_v.setFirst_name(user1.getFirst_name());
        user_v.setPsev(user1.getPsev());
        user_v.setId_man(user1.getId_man());
        user_v.setDateOfBirth(user1.getDateOfBirth());
        user_v.setSum_denga(user1.getSum_denga());
    }


    // Этот код позволяет нам изменить кодировку
    // Такое может произойти если используется IDE - например NetBeans.
    // Тогда будут одни знаки вопроса
    public static void printString(Object s) {
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1251"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public static void printString() {
        System.out.println();
    }
}