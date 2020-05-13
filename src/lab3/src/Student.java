

import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Student implements Comparable {

    // поле id студента
    private int studentId;
    // поле имя
    private String firstName;
    // поле фамилия
    private String surName;
    // поле отчество
    private String patronymic;
    // поле дата рождения
    private Date dateOfBirth;
    // поле пол
    private char sex;
    // поле id группы
    private int groupId;
    // поле год обучения
    private int educationYear;
    // поле рост
    private int height;
    // поле вес
    private int mass_s;

    // get/set для даты рождения
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    // get/set для года обучения
    public int getEducationYear() {

        return educationYear;
    }

    public void setEducationYear(int educationYear) {

        this.educationYear = educationYear;
    }

    // get/set для id группы
    public int getGroupId() {

        return groupId;
    }

    public void setGroupId(int groupId) {

        this.groupId = groupId;
    }

    // get/set для id студента
    public int getStudentId() {

        return studentId;
    }

    public void setStudentId(int studentId) {

        this.studentId = studentId;
    }

    // get/set для роста студента
    public int getHeight() {

        return height;
    }

    public void setHeight(int height) {

        this.height = height;
    }

    // get/set для веса студента
    public int getMass_s() {

        return mass_s;
    }

    public void setMass_s(int mass_s) {

        this.mass_s = mass_s;
    }



    // get/set для имени студента
    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    // get/set для отчества студента
    public String getPatronymic() {

        return patronymic;
    }

    public void setPatronymic(String patronymic) {

        this.patronymic = patronymic;
    }

    // get/set для фамилии студента
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    // get/set для пола
    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    // DateFormat - класс для преобразования даты
    public String toString() {
        return "Id студента: " + studentId + ", " + surName + " " + firstName + " " + patronymic + ", "
                + DateFormat.getDateInstance(DateFormat.SHORT).format(dateOfBirth)
                + ", Група: " + groupId + ", " + "Рік:" + educationYear + ", " + "Стать:" + sex
                + ", " +"Вага студента: " + mass_s + ", " + "Ріст Студента: " + height;
    }

    public int compareTo(Object obj) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), obj.toString());
    }
}