package lr2;

public enum Posts {
    TechnicalDirector(6000,10),
    ProductionDirector(7000, 15),
    ChiefEngineer(4500,7),
    HeadOfHumanResources(4299.99,30),
    ChiefAccountant(5000,30),
    HeadOfTrade(5500,21),
    HeadOfProcurement(3500,40),
    HeadOfPublicRelations(3000, 40);

    private double officialSalary;
    private int vacationDays;

    Posts(double officialSalary, int vacationDays) {
        this.officialSalary = officialSalary;
        this.vacationDays = vacationDays;
    }

    public double getOfficialSalary(){
        return officialSalary;
    }

    public void setOfficialSalary(double officialSalary){
        this.officialSalary = officialSalary;
    }

    public int getVacationDays(){
        return vacationDays;
    }

    public void setVacationDays(int vacationDays){
        this.vacationDays = vacationDays;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "officialSalary=" + officialSalary +
                ", vacationDays=" + vacationDays +
                '}';
    }
}