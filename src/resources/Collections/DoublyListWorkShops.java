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

    public DoublyItemWorkShop<StackEquipments> FindReverse(int numberWorkShop){
        var current = getTail();

        while (current != null){
            if(current.getWorkShopNumber() == numberWorkShop)
                break;

            current = current.getPrevious();
        }

        return current;
    }

    public void Add(int numberWorkShop, int lengthWorkShop){
        var stackWorkShop = InitDoublyItemWorkShop(
                numberWorkShop,
                lengthWorkShop);

        if(getHead() == null){
            setHead(stackWorkShop);
        }
        else{
            getTail().setNext(stackWorkShop);
            stackWorkShop.setPrevious(getTail());
        }

        setTail(stackWorkShop);
        Count++;
    }

    public void AddAfter(
            int numberWorkShop,
            int lengthWorkShop,
            int numberFindWorkShop){
        if(Head == null){
            Add(numberWorkShop, lengthWorkShop);
            return;
        }

        var current = Find(numberFindWorkShop);

        if(current != null){
            var stackWorkShop = InitDoublyItemWorkShop(
                    numberWorkShop,
                    lengthWorkShop);
            if(current.getNext() != null){
                stackWorkShop.setNext(current.getNext());
                current.getNext().setPrevious(stackWorkShop);
            }
            else{
                setTail(stackWorkShop);
            }

            current.setNext(stackWorkShop);
            stackWorkShop.setPrevious(current);
        }
        else{
            System.out.println("Заданного цеха не существует!");
        }
    }

    public void AddBefore(
            int numberWorkShop,
            int lengthWorkShop,
            int numberFindWorkShop){
        if(Head == null){
            Add(numberWorkShop, lengthWorkShop);
            return;
        }

        var current = FindReverse(numberFindWorkShop);

        if(current != null){
            var stackWorkShop = InitDoublyItemWorkShop(
                    numberWorkShop,
                    lengthWorkShop);
            if(current.getPrevious() != null){
                stackWorkShop.setPrevious(current.getPrevious());
                current.getPrevious().setNext(stackWorkShop);
            }
            else{
                setHead(stackWorkShop);
            }

            current.setPrevious(stackWorkShop);
            stackWorkShop.setNext(current);
        }
        else{
            System.out.println("Заданного цеха не существует!");
        }
    }

    private DoublyItemWorkShop<StackEquipments> InitDoublyItemWorkShop(
            int numberWorkShop,
            int lengthWorkShop){
        var workShop = new WorkShop(numberWorkShop);
        var equipments = new StackEquipments(lengthWorkShop);
        var stackWorkShop = new DoublyItemWorkShop<StackEquipments>(workShop, equipments);
        return stackWorkShop;
    }

    public void AddInWorkShop(int numberWorkShop, Equipment equipment){
        var currentWorkShop = Find(numberWorkShop);

        if(currentWorkShop == null){
            System.out.println("Заданного цеха не существует!");
            return;
        }

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
        else{
            System.out.println("Цеха с заданным номером не существует!");
        }

        return null;
    }

    public Equipment RemoveInWorkShop(int numberWorkShop){
        var current = Find(numberWorkShop);

        if(current == null){
            System.out.println("Заданного цеха не существует!");
            return null;
        }

        var equipment = current.getData().Pop();

        return equipment;
    }
}
