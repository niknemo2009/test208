

import java.text.Collator;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class User implements Comparable {

    // поле дата записи
    private Date dateOfBirth;
    private String first_name;
    private String psev;

    private int sum_denga;
    private int id_man;
    private int filtr;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPsev() {
        return psev;
    }

    public void setPsev(String psev) {
        this.psev = psev;
    }

    public int getId_man() {
        return id_man;
    }

    public void setId_man(int id_man) {
        this.id_man = id_man;
    }

    public int getSum_denga() {
        return sum_denga;
    }

    public void setSum_denga(int sum_denga) {
        this.sum_denga = sum_denga;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public int getFiltr() {
        return filtr;
    }

    public void setFiltr(int filtr) {
        this.filtr = filtr;
    }

    public String toString() {

            return   "ИФ:" + first_name + " " + psev + " Id: " + id_man +" Сумма за парковку: " + sum_denga;


    }

    public int compareTo(Object obj) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), obj.toString());
    }
}