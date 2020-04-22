package lr4;
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
        data.add(new Student("Zabelenkov", 100.16, 120.01, 208));
        data.add(new Student("Skrypnik", 85.66, 170.01, 209));
        data.add(new Student("Mokrivskiy", 90.11, 150.01, 208));
    }
    public Student() { }

    public Student(String surname, Double weight, Double height, Integer group) {
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.group = group;
    }

    public ArrayList<Student> sortGroup(Comparator<Student> cStud, int groupCriteria){
       ArrayList<Student> students = new ArrayList<>();
        for (Student item : data){
            if(item.group == groupCriteria){
                students.add(item);
            }
        }
        students.sort(cStud);
        return students;
    }

    public void addStudent(String surname, Double weight, Double height, Integer group) {
        data.add(new Student(surname, weight, height, group));
    }

    public Integer inputGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input group: ");
        this.group = scanner.nextInt();
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

    public ArrayList<Student> findStudent(String surnameCriteria){
        ArrayList<Student> students = new ArrayList<>();
        for(Student item : data){
            if(item.surname.equals(surnameCriteria)){
                students.add(item);
            }
        }
        return students;
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
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass()!= this.getClass()){
            return false;
        }

        Student otherStudent = (Student)obj;
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
