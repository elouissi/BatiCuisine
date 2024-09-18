package View;

import Service.ClientService;

import java.util.Scanner;

public class ClientView {

    Scanner scanner = new Scanner(System.in);
    public void test(){
        ClientService service = new ClientService(scanner);

    }


}
