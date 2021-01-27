package resources.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import resources.Collections.DoublyListWorkShops;
import resources.Collections.StackEquipments;

import java.util.ArrayList;

public class CompanyExport {
    private Company Data;

    private WorkShopStackEquipments Head;
    private WorkShopStackEquipments Tail;
    private ArrayList<WorkShopStackEquipments> WorkShops;
    private int Count;

    public CompanyExport(){

    }

    public CompanyExport(DoublyListWorkShops company){
        Data = company.getCompany();
        Count = company.getCount();

        if(company.getHead() != null) {
            Head = new WorkShopStackEquipments(
                    company.getHead().getWorkShop(),
                    company.getHead().getData()) ;

            var current = company.getHead();
            WorkShops = new ArrayList<WorkShopStackEquipments>(company.getCount());
            while (current != null){
                WorkShops.add(new WorkShopStackEquipments(current.getWorkShop(), current.getData()));
                current = current.getNext();
            }
        }

        if(company.getTail() != null) {
            Tail = new WorkShopStackEquipments(
                    company.getTail().getWorkShop(),
                    company.getTail().getData());
        }
    }

    public Company getData(){
        return Data;
    }

    public void setData(Company company){
        Data = company;
    }

    public WorkShopStackEquipments getHead(){
        return Head;
    }

    public void setHead(WorkShopStackEquipments head){
        Head = head;
    }

    public WorkShopStackEquipments getTail(){
        return Tail;
    }

    public void setTail(WorkShopStackEquipments tail){
        Tail = tail;
    }

    public ArrayList<WorkShopStackEquipments> getEquipments(){
        return WorkShops;
    }

    public void setEquipments(ArrayList<WorkShopStackEquipments> equipments){
        WorkShops = equipments;
    }

    public int getCount(){
        return Count;
    }

    public void setCount(int count){
        Count = count;
    }
}
