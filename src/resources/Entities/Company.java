package resources.Entities;

public class Company {
    private static String DefaultNameCompany = "Default";
    private String Name;

    public Company(){
        Name = DefaultNameCompany;
    }

    public Company(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        Name = name;
    }
}
