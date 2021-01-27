package resources.Entities;

import resources.Collections.StackEquipments;

public class WorkShopStackEquipments {
    private WorkShop workShop;
    private StackEquipments equipments;

    public WorkShopStackEquipments(){

    }

    public WorkShopStackEquipments(WorkShop workShop, StackEquipments equipments){
        this.workShop = workShop;
        this.equipments = equipments;
    }

    public WorkShop getWorkShop(){
        return workShop;
    }

    public void setWorkShop(WorkShop workShop){
        this.workShop = workShop;
    }

    public StackEquipments getEquipments(){
        return  equipments;
    }

    public void setEquipments(StackEquipments equipments){
        this.equipments = equipments;
    }
}
