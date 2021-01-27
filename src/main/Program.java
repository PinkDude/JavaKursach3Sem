package main;

import main.Services.MainService;

public class Program {
    public static void main(String args[]){
        RunMainService();
    }

    private static void RunMainService(){
        var service = new MainService();
        service.Main();
    }
}
