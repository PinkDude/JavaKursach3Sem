package resources.Entities;

public class Equipment {
    private String Name;
    private int YearOfIssue;

    public Equipment(){

    }

    public Equipment(String name, int yearOfIssue){
        Name = name;
        YearOfIssue = yearOfIssue;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        Name = name;
    }

    public int getYearOfIssue(){
        return YearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue){
        YearOfIssue = yearOfIssue;
    }
}
