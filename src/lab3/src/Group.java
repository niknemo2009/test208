

public class Group {

    // поле id группы
    private int groupId;
    // поле имя группы
    private String nameGroup;
    // поле куратор
    private String curator;
    // поле специальность
    private String speciality;

    // get/set для куратор
    public String getCurator() {

        return curator;
    }

    public void setCurator(String curator) {

        this.curator = curator;
    }

    // get/set для id группы
    public int getGroupId() {

        return groupId;
    }

    public void setGroupId(int groupId) {

        this.groupId = groupId;
    }

    // get/set для имени группы
    public String getNameGroup() {

        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {

        this.nameGroup = nameGroup;
    }

    // get/set для специальности
    public String getSpeciality() {

        return speciality;
    }

    public void setSpeciality(String speciality) {

        this.speciality = speciality;
    }

    public String toString() {

        return nameGroup + "\n" + "Куратор: " + curator +", " + "Спеціальність: " + speciality;
    }
}