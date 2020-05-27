package lr6;

import java.io.InvalidObjectException;
import java.util.*;

public class Student {
    String surname;
    Double weight;
    Double height;
    Integer group;

    static HashSet<Student> data = new HashSet<>();

    static {
        data.add(new Student("Ivanov", 100.66, 180.01, 208));
        data.add(new Student("Petrov", 85.67, 170.01, 209));
        data.add(new Student("Alekseev", 90.66, 150.01, 208));
    }

    public Student() {
    }

    public Student(String surname, Double weight, Double height, Integer group) {
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.group = group;
    }

    public static HashSet<Student> getAllStudents(){
        return new HashSet<Student>(data);
    }

    public boolean checkStudent(Student student){
        return student.height < 0 && student.weight < 0 && student.group == 0 && student.surname.equals("");
    }

    public ArrayList<Student> sortGroup(Comparator<Student> cStud, int groupCriteria) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student item : data) {
            if (item.group == groupCriteria) {
                students.add(item);
            }
        }
        students.sort(cStud);
        return students;
    }

    public void addStudent(String surname, Double weight, Double height, Integer group) {
        data.add(new Student(surname, weight, height, group));
    }

    public Integer inputGroup(int group) {
        return group;
    }

    public void changeGroup(String surname, Integer group) {
        for (Student item : data) {
            if (item.surname.equals(surname)) {
                System.out.println("Current group: " + item.group + "\nNew group: " + group);
                item.group = group;
            }
        }
    }

    public Student findStudent(String surnameCriteria) throws InvalidObjectException {
        for (Student item : data) {
            if (item.surname.equals(surnameCriteria)) {
                return new Student(item.surname, item.weight, item.height, item.group);
            }
        }
        throw new InvalidObjectException("Student don't find");
    }

    public String getSurname() {
        return surname;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Integer getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Student otherStudent = (Student) obj;
        return surname.equals(otherStudent.getSurname()) &&
                weight.equals(otherStudent.getWeight()) &&
                height.equals(otherStudent.getHeight()) &&
                group.equals(otherStudent.getGroup());
    }

    @Override
    public int hashCode() {
        return surname.hashCode() + weight.hashCode() + height.hashCode() + group.hashCode();
    }
}
