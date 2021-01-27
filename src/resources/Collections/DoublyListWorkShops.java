package resources.Collections;

import resources.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class DoublyListWorkShops {
    private Company Data;

    private DoublyItemWorkShop<StackEquipments> Head;
    private DoublyItemWorkShop<StackEquipments> Tail;
    private int Count;

    public DoublyListWorkShops(){

    }

    public DoublyListWorkShops(CompanyExport companyExport){
        Data = companyExport.getData();
        Count = companyExport.getCount();

        Head = new DoublyItemWorkShop<StackEquipments>(
                companyExport.getHead().getWorkShop(),
                companyExport.getHead().getEquipments());
        Head.setNext(new DoublyItemWorkShop<StackEquipments>(
                companyExport.getEquipments().get(1).getWorkShop(),
                companyExport.getEquipments().get(1).getEquipments()));
        Head.getNext().setPrevious(Head);

        var current = Head.getNext();

        for(var i = 2; i < companyExport.getCount(); i++){
            current.setNext(new DoublyItemWorkShop<StackEquipments>(
                    companyExport.getEquipments().get(i).getWorkShop(),
                    companyExport.getEquipments().get(i).getEquipments()));
            current.getNext().setPrevious(current);
        }

        Tail = current;
    }

    public Company getCompany(){
        return Data;
    }

    public void setData(Company data){
        Data = data;
    }

    public int getCount(){
        return Count;
    }

    public DoublyItemWorkShop<StackEquipments> getHead(){
        return Head;
    }

    public void setHead(DoublyItemWorkShop<StackEquipments> head){
        Head = head;
    }

    public DoublyItemWorkShop<StackEquipments> getTail(){
        return Tail;
    }

    public void setTail(DoublyItemWorkShop<StackEquipments> tail){
        Tail = tail;
    }

    public List<StackEquipments> GetList(){
        var list = new ArrayList<StackEquipments>(Count);

        var current = Head;
        while (current != null){
            list.add(current.getData());
            current = current.getNext();
        }

        return list;
    }

    public DoublyItemWorkShop<StackEquipments> Find(int numberWorkShop){
        var current = getHead();

        while (current != null){
            if(current.getWorkShopNumber() == numberWorkShop)
                break;

            current = current.getNext();
        }

        return current;
    }

    public void Add(int numberWorkShop, int lengthWorkShop){
        var workShopEntity = new WorkShop(numberWorkShop);
        var stackEquipments = new StackEquipments(lengthWorkShop);
        var workShop = new DoublyItemWorkShop<StackEquipments>(workShopEntity, stackEquipments);

        if(getHead() == null){
            setHead(workShop);
        }
        else{
            getTail().setNext(workShop);
            workShop.setPrevious(getTail());
        }

        setTail(workShop);
        Count++;
    }

    public void AddInWorkShop(int numberWorkShop, Equipment equipment){
        var currentWorkShop = Find(numberWorkShop);

        currentWorkShop.getData().Push(equipment);
    }

    public DoublyItemWorkShop<StackEquipments> Remove(int numberWorkShop){
        var current = Find(numberWorkShop);

        if(current != null){
            if(current.getNext() != null){
                current.getNext().setPrevious(current.getPrevious());
            }
            else{
                setTail(current.getPrevious());
            }

            if(current.getPrevious() != null){
                current.getPrevious().setNext(current.getNext());
            }
            else{
                setHead(current.getNext());
            }

            Count--;
            return current;
        }

        return null;
    }

    public Equipment RemoveInWorkShop(int numberWorkShop){
        var current = Find(numberWorkShop);

        var equipment = current.getData().Pop();

        return equipment;
    }
}
