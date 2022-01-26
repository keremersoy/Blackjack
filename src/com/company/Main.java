package com.company;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String name;
        while (true) {

            Scanner sc = new Scanner(System.in);
            Cards card = new Cards();
            System.out.print("Adinizi Giriniz : ");
            name = sc.nextLine().toUpperCase(Locale.ROOT);
            cls();
            Till till = new Till(card);
            Player player = new Player(name, card, till);
            String choice = "";

            while (!(player.getMoney() <= 0)) {

                while (!player.getcheckBet()) {

                    int bet = 0;
                    System.out.print(player.getName() + "\nKalan Paranız=₺" + player.getMoney() + "\nBahis=");
                    bet = Integer.valueOf(sc.next());
                    player.setBet(bet);

                    if (player.getBet() <= player.getMoney() && player.getBet() > 0) {
                        player.setMoney(bet * (-1));
                        player.setcheckBet(true);
                    }
                    else {
                        System.out.println("gecerli bir deger giriniz...");
                    }

                }

                while (player.check21(till, player)) {

                    cls();
                    getTable(till, player);
                    System.out.print("1-->Kart Cek\n2-->Pas\n3-->Cikis\nSecim=");
                    choice = sc.next().substring(0, 1);
                    cls();

                    if (choice.equals("1")) {
                        cls();
                        player.getaCard();
                        if(!player.check21(till, player))
                            break;
                        if (player.getScore() > 21)
                            break;
                    }
                    else if (choice.equals("2")) {
                        cls();
                        till.check21(player);
                        break;
                    }
                    else if (choice.equals("3")) {
                        cls();
                        player.setBet(player.getBet());
                        break;
                    }
                    else {
                        System.out.println("Hatali Giris");
                    }
                }

                if (choice.equals("3"))
                    break;

                if (player.Case == true && till.Case == false) {
                    getOpenTable(till, player);
                    System.out.println("Kazandınız!");
                    player.setMoney(player.getBet() * 2);
                    System.out.println("+₺" + player.getBet());
                    System.out.println("Kalan Paranız=₺" + player.getMoney());
                }
                else if (player.Case == false && till.Case == true) {
                    getOpenTable(till, player);
                    System.out.println("Kaybettiniz!");
                    System.out.println("-₺" + player.getBet());
                    System.out.println("Kalan Paranız=₺" + player.getMoney());
                }
                else if (player.Case == true && till.Case == true) {
                    getOpenTable(till, player);
                    System.out.println("Berabere...");
                    player.setMoney(player.getBet());
                    System.out.println("Kalan Paranız=₺" + player.getMoney());
                }

                player.setBet(0);
                player.setcheckBet(false);
                String choice2 = "";

                boolean c = true;
                while (c) {

                    System.out.print("1-->Yeni oyun\n2-->Cikis\nTercih=");
                    choice2 = sc.next().substring(0, 1);

                    if (choice2.equals("1")) {
                        cls();
                        cardsReset(player, till);
                        c = false;
                    }
                    else if (choice2.equals("2")) {
                        cls();
                        choice = "3";
                        c = false;
                        break;
                    }
                }
                if (choice.equals("3"))
                    break;
            }
            player.createFile();
            player.writeToFile();
            if (player.getMoney() <= 0)
                System.out.print(player.getName() +"\nIFLAS!" +  "\nYeni Oyun Icin ");

        }
    }

    static void getTable(Till till, Player player) {
        System.out.print("KASA=");
        till.getTillCards();
        System.out.println();
        System.out.print(player.getName() + "=");
        player.getCards();
        System.out.println("-->" + player.getScore());
        System.out.println("Kalan Paranız=₺" + player.getMoney());
        System.out.println("Bahis=₺" + player.getBet());
    }

    static void getOpenTable(Till till, Player player) {
        cls();
        till.getCards();
        System.out.println("-->" + till.getScore());
        player.getCards();
        System.out.println("-->" + player.getScore());
    }

    static void cardsReset(Player player, Till till) {
        player.cardReset();
        till.cardReset();
    }

    static void cls() {
        for (int i = 0; i < 500; i++)
            System.out.println("\b");
    }
}
