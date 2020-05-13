import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;
import java.util.Scanner;

public class Manager_program {

    private List<Group> groups;
    private Collection<Student> students;
    private static Manager_program instance;

    // закрытый конструктор
    private Manager_program() {
        loadGroups();
        loadStudents();
    }

    // метод getInstance - проверяет, инициализирована ли статическая
    // переменная (в случае надобности делает это) и возвращает ее
    public static synchronized Manager_program getInstance() {
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
        printString("Вітаю вас в студентській системі 'Чапига' ");
        printString("Введіть своє ім`я та прізвище.");
        String name_new = scanner.nextLine();
        printString("Вітаю вас, " + name_new + "!");
        printString("Загальний список");
        printString("+++-----------------------------------------+++");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            printString(gi);
        }
        printString();

        // Просмотр полного списка студентов
        printString("Повний список студентів");
        printString("+++-----------------------------------------+++");
        Collection<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);

        }
        printString();
        // Вывод списков студентов по группам
        printString("Список студентів розподілений по групам");
        printString("+++-----------------------------------------+++");
        List<Group> groups = ms.getGroups();
        // Проверяем все группы
        printString("ІПП           Рік народження       Група         Рік вступу");
        for (Group gi : groups) {
            printString("---> Група:" + gi.getNameGroup() + "<---");
            Collection<Student> students = ms.getStudentsFromGroup(gi, 2019);
            for (Student si : students) {
                printString(si);
            }
        }
        printString();
        printString("Додавання нового студента");
        printString("Введіть ім`я студента");
        String first_name_stud_new = scanner.nextLine();
        printString("Введіть прізвище студента");
        String patronymic_stud_new = scanner.nextLine();
        printString("Введіть побатькові студента");
        String sur_name_stud_new = scanner.nextLine();
        printString("Введіть пол студента");
        String sex_new_student = scanner.nextLine();
        printString("Введіть рік народження студента");
        int year_birth = scanner.nextInt();
        printString("Введіть місяць народження студента");
        int mount_birth = scanner.nextInt();
        printString("Введіть день народження студента");
        int birthday = scanner.nextInt();
        printString("Введіть групу студента");
        int id_groups = scanner.nextInt();
        printString("Введіть рік вступу студента");
        int year_postup = scanner.nextInt();
        printString("Введіть id студента");
        int id_students = scanner.nextInt();
        printString("Введіть масу студента");
        int mass_students = scanner.nextInt();
        printString("Введіть ріст студента");
        int height_students = scanner.nextInt();
        // Создадим нового студента и добавим его в список
        Student s = new Student();
        s.setStudentId(id_students);
        s.setFirstName(first_name_stud_new);
        s.setPatronymic(patronymic_stud_new);
        s.setSurName(sur_name_stud_new);
        //и так сойдёт
        if(sex_new_student == "M" || sex_new_student == "m")
        {
            s.setSex('М');
        }
        if(sex_new_student == "Ж" || sex_new_student == "ж")
        {
            s.setSex('Ж');
        }
        Calendar c = Calendar.getInstance();
        c.set(year_birth, mount_birth, birthday);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(id_groups);
        s.setEducationYear(year_postup);
        s.setHeight(175);
        s.setMass_s(mass_students);
        printString("Додавання студента:" + s);
        printString("+++-----------------------------------------+++");
        ms.insertStudent(s);
        printString("--->> Повний список студентів після додавання нового студента <<---");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();
        //Редактирование информации студента
        printString("Редагування інформації студента");
        printString("Введіть id студента для редагування");
        int id_students_red = scanner.nextInt();
        printString("Введіть ім`я студента");
        String first_name_stud_red = scanner.nextLine();
        printString("Введіть прізвище студента");
        String patronymic_stud_red = scanner.nextLine();
        printString("Введіть побатькові студента");
        String sur_name_stud_red = scanner.nextLine();
        printString("Введіть пол студента");
        String sex_student_red = scanner.nextLine();
        printString("Введіть рік народження студента");
        int year_birth_red = scanner.nextInt();
        printString("Введіть місяць народження студента");
        int mount_birth_red = scanner.nextInt();
        printString("Введіть день народження студента");
        int birthday_red = scanner.nextInt();
        printString("Введіть групу студента");
        int id_groups_red = scanner.nextInt();
        printString("Введіть рік вступу студента");
        int year_postup_red = scanner.nextInt();
        printString("Введіть масу студента");
        int mass_students_red = scanner.nextInt();
        printString("Введіть ріст студента");
        int height_students_red = scanner.nextInt();
        // Изменим данные нашего нового студента
        // Ориентируемся на id
        s = new Student();
        // Изменяем данные пользователя у коророго id = 5
        s.setStudentId(id_students_red);
        s.setFirstName(first_name_stud_red);
        s.setPatronymic(patronymic_stud_red);
        s.setSurName(sur_name_stud_red);
        //и так сойдёт
        if(sex_student_red == "M" || sex_student_red == "m")
        {
            s.setSex('М');
        }
        if(sex_student_red == "Ж" || sex_student_red == "ж")
        {
            s.setSex('Ж');
        }
        c = Calendar.getInstance();
        c.set(year_birth_red, mount_birth_red, birthday_red);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(id_groups_red);
        s.setEducationYear(year_postup_red);
        s.setHeight(height_students_red);
        s.setMass_s(mass_students_red);
        printString("Редагування інформації студента:" + s);
        printString("+++-----------------------------------------+++");
        ms.updateStudent(s);
        printString("--->> Повний список студентів після редагування інформації студента <<---");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Высылаем человека в Сибирь(удаляем)
        printString("Видалення студента:" + s);
        printString("+++-----------------------------------------+++");
        ms.deleteStudent(s);
        printString("--->> Повний список студентів після видалення студента <<---");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        //Перевод из одной групы в другую
        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        printString("Переведення з однієї групи в іншу");
        printString("+++-----------------------------------------+++");
        ms.moveStudentsToGroup(g1, 2020, g2, 2019);
        printString("--->> Повний список студентів після переведення студентів <<---");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            printString(si);
        }
        printString();

        // Удаляем студентов из группы
        printString("Видалення студентів з группи:" + g2 );
        printString("+++-----------------------------------------+++");
            ms.removeStudentsFromGroup(g2, 2020);


        printString("--->> Повний список студентів після видалення студентів <<---");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            printString(i.next());
        }
        printString();
    }

    public void loadGroups() {
        if (groups == null) {
            groups = new ArrayList<Group>();
        } else {
            groups.clear();
        }
        Group g = null;

        g = new Group();
        g.setGroupId(208);
        g.setNameGroup("Перша група");
        g.setCurator("Дмитро Дядькович");
        g.setSpeciality("ІПЗ");
        groups.add(g);

        g = new Group();
        g.setGroupId(209);
        g.setNameGroup("Друга група");
        g.setCurator("Степан Тутаков");
        g.setSpeciality("Історик");
        groups.add(g);
    }

    public void loadStudents() {
        if (students == null) {
            students = new TreeSet<Student>();
        } else {
            students.clear();
        }

        Student s = null;
        Calendar c = Calendar.getInstance();

        // Вторая группа
        s = new Student();
        s.setStudentId(1);
        s.setFirstName("Семен");
        s.setPatronymic("Патуков");
        s.setSurName("Андреев");
        s.setSex('М');
        c.set(2005, 6, 30);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(209);
        s.setEducationYear(2020);
        s.setHeight(151);
        s.setMass_s(60);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Аліна");
        s.setPatronymic("Татаатта");
        s.setSurName("УУУулулуллулцф");
        s.setSex('Ж');
        c.set(2001, 3, 15);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(209);
        s.setEducationYear(2019);
        s.setHeight(200);
        s.setMass_s(90);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Паст");
        s.setSurName("Катука");
        s.setSex('М');
        c.set(2004, 8, 1);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2019);
        s.setGroupId(208);
        s.setHeight(162);
        s.setMass_s(72);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Ніка");
        s.setPatronymic("Фатал");
        s.setSurName("Чунчунмару");
        s.setSex('Ж');
        c.set(2005, 1, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2020);
        s.setGroupId(208);
        s.setHeight(155);
        s.setMass_s(60);
        students.add(s);
    }

    // Получить список групп
    public List<Group> getGroups() {
        return groups;
    }

    // Получить список всех студентов
    public Collection<Student> getAllStudents() {
        return students;
    }

    // Получить список студентов для определенной группы
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> l = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() == group.getGroupId() && si.getEducationYear() == year) {
                l.add(si);
            }
        }
        return l;
    }

    // Перевести студентов из одной группы с одним годом обучения в другую группу с другим годом обучения
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student si : students) {
            if (si.getGroupId() == oldGroup.getGroupId() && si.getEducationYear() == oldYear) {
                si.setGroupId(newGroup.getGroupId());
                si.setEducationYear(newYear);
            }
        }
    }

    // Удалить всех студентов из определенной группы
    public void removeStudentsFromGroup(Group group, int year) {
        // Создаём список студентов без тех, кто подвергается удалению.
        Collection<Student> tmp = new TreeSet<Student>();
        for (Student si : students) {
            if (si.getGroupId() != group.getGroupId() || si.getEducationYear() != year) {
                tmp.add(si);
            }
        }
        students = tmp;
    }

    // Добавить студента
    public void insertStudent(Student student) {
        // Просто добавляем объект в коллекцию
        students.add(student);
    }

    // Обновить данные о студенте
    public void updateStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и заменить поля
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    // Удалить челика
    public void deleteStudent(Student student) {
        // Найти нужного студента (по его ИД) и удалить
        Student delStudent = null;

        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

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