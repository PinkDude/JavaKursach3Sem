package resources.Collections;

import resources.Entities.Equipment;

import java.util.ArrayList;

public class StackEquipments {
    private static int DefaultSize = 10;

    private Equipment[] items;
    private int count;

    public StackEquipments(){
        items = new Equipment[DefaultSize];
    }

    public StackEquipments(int length){
        items = new Equipment[length];
    }

    public Equipment[] getEquipments(){
        return items;
    }

    public void setEquipments(Equipment[] equipments){
        items = equipments;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public boolean IsEmpty(){
        return count == 0;
    }

    public boolean IsFull(){
        return count == items.length;
    }

    public void Push(Equipment equipment){
        if(IsFull()){
            System.out.println("Стек переполнен!");
            return;
        }

        items[count++] = equipment;
    }

    public Equipment Pop(){
        if(IsEmpty()){
            System.out.println("Стек пуст!");
            return null;
        }

        var equipment = items[--count];

        items[count] = null;

        return equipment;
    }

    public ArrayList<Equipment> GetList(){
        var list = new ArrayList<Equipment>(getCount());

        for(var i = 0; i< getCount(); i++){
            list.add(items[i]);
        }

        return list;
    }
}
