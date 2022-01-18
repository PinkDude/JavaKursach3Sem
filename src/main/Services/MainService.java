package main.Services;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import resources.Collections.DoublyListWorkShops;
import resources.Collections.StackEquipments;
import resources.Entities.*;

import java.io.*;
import java.util.Scanner;

public class MainService {
    private static String FileName = "D://MyDevelop//Java//outObject.txt";

    public void Main(){
        var company = InitCompany();
        boolean exit = false;

        while (true){
            WriteMenu();
            var answer = GetAnswerInt();

            switch (answer){
                case 1:
                    WriteCompany(company);
                    break;
                case 2:
                    AddWorkShop(company);
                    break;
                case 3:
                    RemoveWorkShop(company);
                    break;
                case 4:
                    AddEquipment(company);
                    break;
                case 5:
                    RemoveEquipment(company);
                    break;
                case 6:
                    RenameCompany(company);
                    break;
                case 7:
                    SaveInFile(company);
                    break;
                case 8:
                    var companyNew = LoadFromFile();
                    if(companyNew != null)
                        company = companyNew;
                    break;
                case 9:
                    AddWorkShopBefore(company);
                    break;
                case 10:
                    AddWorkShopAfter(company);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Такого значения нет!");
                    break;
            }

            if(exit)
                break;
        }
    }

    private void AddWorkShopAfter(DoublyListWorkShops company){
        System.out.println("После какого цеха добавить?");
        var numberFindWorkShop = GetAnswerInt();

        System.out.println("Какой цех добавить?");
        var numberWorkShop = GetAnswerInt();

        System.out.println("Сколько максмально может быть оборудования в этом цехе?");
        var lengthWorkShop = GetAnswerInt();
        if(lengthWorkShop < 1){
            System.out.println("Минимальное количество оборудования в цехе - 1!");
            return;
        }

        company.AddAfter(numberWorkShop, lengthWorkShop, numberFindWorkShop);
    }

    private void AddWorkShopBefore(DoublyListWorkShops company){
        System.out.println("До какого цеха добавить?");
        var numberFindWorkShop = GetAnswerInt();

        System.out.println("Какой цех добавить?");
        var numberWorkShop = GetAnswerInt();

        System.out.println("Сколько максмально может быть оборудования в этом цехе?");
        var lengthWorkShop = GetAnswerInt();
        if(lengthWorkShop < 1){
            System.out.println("Минимальное количество оборудования в цехе - 1!");
            return;
        }

        company.AddBefore(numberWorkShop, lengthWorkShop, numberFindWorkShop);
    }

    private DoublyListWorkShops LoadFromFile(){
        try {
            var objectMapper = new ObjectMapper();
            var companyExport = objectMapper.readValue(new File(FileName), CompanyExport.class);
            var company = new DoublyListWorkShops(companyExport);
            return company;
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private void SaveInFile(DoublyListWorkShops company){
        try {
            CompanyExport companyExport = new CompanyExport(company);
            var objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(FileName), companyExport);
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void RenameCompany(DoublyListWorkShops company){
        System.out.println("Какое новое название будет у компании?");
        var companyName = GetAnswerString();
        if(companyName == null)
            return;

        company.getCompany().setName(companyName);
    }

    private void RemoveEquipment(DoublyListWorkShops company){
        System.out.println("Из какого цеха удалить?");
        var numberWorkShop = GetAnswerInt();

        company.RemoveInWorkShop(numberWorkShop);
    }

    private void AddEquipment(DoublyListWorkShops company){
        System.out.println("В какой цех добавить?");
        var numberWorkShop = GetAnswerInt();

        System.out.println("Какое имя у оборудования?");
        var equipmentName = GetAnswerString();
        if(equipmentName == null)
            return;

        System.out.println("Какой год выпуска у этого оборудования?");
        var equipmentYear = GetAnswerInt();
        var length = (int) (Math.log10(equipmentYear) + 1);
        if(length != 4){
            System.out.println("Год должен состоять из четырех цифр!");
            return;
        }

        var equipment = new Equipment(equipmentName, equipmentYear);
        company.AddInWorkShop(numberWorkShop, equipment);
    }

    private void RemoveWorkShop(DoublyListWorkShops company){
        System.out.println("Удать цех с каким номером?");

        var answer = GetAnswerInt();

        company.Remove(answer);
    }

    private void AddWorkShop(DoublyListWorkShops company){
        System.out.println("Какой цех добавить?");
        var numberWorkShop = GetAnswerInt();

        System.out.println("Сколько максмально может быть оборудования в этом цехе?");
        var lengthWorkShop = GetAnswerInt();
        if(lengthWorkShop < 1){
            System.out.println("Минимальное количество оборудования в цехе - 1!");
            return;
        }

        company.Add(numberWorkShop, lengthWorkShop);
    }

    private void WriteCompany(DoublyListWorkShops company){
        System.out.println("Company - " + company.getCompany().getName());

        var currentWorkShop = company.getHead();
        while (currentWorkShop != null){
            System.out.println("\tЦех №" + currentWorkShop.getWorkShopNumber());

            if(currentWorkShop.getData() != null && !currentWorkShop.getData().IsEmpty()) {
                var equipments = currentWorkShop.getData().getEquipments();

                for (var i = 0; i < equipments.length && equipments[i] != null; i++) {
                    System.out.println("\t\tНаименование оборудования - " + equipments[i].getName() +
                            " Год выпуска - " + equipments[i].getYearOfIssue());
                }
            }

            currentWorkShop = currentWorkShop.getNext();
        }
    }

    private int GetAnswerInt(){
        var console = new Scanner(System.in);
        System.out.print("Введите число: ");

        var answer = console.nextInt();

        return answer;
    }

    private String GetAnswerString(){
        var console = new Scanner(System.in);
        System.out.print("Введите значение: ");

        var answer = console.nextLine();

        if(answer == null || "".equals(answer.trim())){
            System.out.println("Значение не может быть пустым!");
            return null;
        }

        return answer;
    }

    private void WriteMenu(){
        System.out.println("Выберите действия:\n" +
                "1) Вывести предприятие\n" +
                "2) Добавить цех\n" +
                "3) Удалить цех\n" +
                "4) Добавить оборудование в цех\n" +
                "5) Удалить оборудование из цеха\n" +
                "6) Изменить название компании\n" +
                "7) Сохранить\n" +
                "8) Загрузить\n" +
                "9) Добавить цех до\n" +
                "10) Добавить цех после\n" +
                "0) Выход\n");
    }

    private DoublyListWorkShops InitCompany(){
        var company = new DoublyListWorkShops();
        company.setData(new Company());
        return company;
    }
}
