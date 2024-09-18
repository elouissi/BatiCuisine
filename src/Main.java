import Config.Connection_DB;
import View.ClientView;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ClientView clientView = new ClientView();
        Connection_DB connectionDb =new Connection_DB();
        connectionDb.Connect_to_DB("BatiCuisine","GreenPulse","");






    }
}