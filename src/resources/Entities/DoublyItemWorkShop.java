package resources.Entities;

public class DoublyItemWorkShop<T> {
    private T Data;
    private WorkShop workShop;

    private DoublyItemWorkShop<T> Next;
    private DoublyItemWorkShop<T> Previous;

    public DoublyItemWorkShop(){
        Data = null;
    }

    public DoublyItemWorkShop(T data){
        Data = data;
    }

    public DoublyItemWorkShop(WorkShop workShop, T data){
        this.workShop = workShop;
        Data = data;
    }

    public int getWorkShopNumber(){
        return workShop.getNumber();
    }

    public void setWorkShopNumber(int number){
        workShop.setNumber(number);
    }

    public WorkShop getWorkShop(){
        return workShop;
    }

    public void setWorkShop(WorkShop workShop){
        this.workShop = workShop;
    }

    public T getData(){
        return Data;
    }

    public void setData(T data){
        Data = data;
    }

    public DoublyItemWorkShop<T> getNext(){
        return Next;
    }

    public void setNext(DoublyItemWorkShop<T> next){
        Next = next;
    }

    public DoublyItemWorkShop<T> getPrevious(){
        return Previous;
    }

    public void setPrevious(DoublyItemWorkShop<T> previous){
        Previous = previous;
    }
}
