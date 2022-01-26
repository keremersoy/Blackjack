package com.company;

import java.util.Scanner;

public class Table {
    private String Card[][];
    protected Cards c;
    private int howmanyCards;
    protected boolean Case;
    protected int Score;

    public Table(Cards _card) {
        this.Card = new String[20][2];
        this.c = _card;
        this.Score = 0;
        this.howmanyCards = 0;
        this.Case = true;
        this.dealCards();
    }

    public void getaCard() {
        String newCard[] = this.c.getaCard();

        if (!(newCard[0] == null) && this.c.getCase()) {
            this.Card[this.howmanyCards] = newCard;
            howmanyCards++;
        } else {
                Scanner sc=new Scanner(System.in);
                System.out.println("Deste bitti");
                System.out.print("Yeni Deste Hazırlanıyor...\nHerhangi bir harf giriniz : ");
                sc.next();
                this.c.CreateDeck();
                this.c.ShuffleDeck();
                getaCard();
        }
        checkScore();
    }

    public void getCards() {
        for (int i = 0; i < this.howmanyCards; i++) {
            System.out.print(this.Card[i][0] + "/");
        }
    }

    public void getTillCards() {
        System.out.print(this.Card[0][0] + "/");
        System.out.print("X" + "/");

    }

    public void cardReset() {
        for (int i = 0; i < this.Card.length; i++) {
            for (int j = 0; j < 2; j++) {
                this.Card[i][j] = null;
            }
        }
        this.howmanyCards = 0;
        this.dealCards();
    }

    public int getScore() {
        checkScore();
        return this.Score;
    }

    public void checkScore() {
        int A = 0;
        if (this.c.getCase()) {
            this.Score = 0;
            int s = 0;
            for (int i = 0; i < this.howmanyCards; i++) {
                if (this.Card[i][0].contains("A")) {
                    A++;
                }
                s += Integer.valueOf(this.Card[i][1]);
            }

            if (A > 0) {
                for (int i = 0; i < A; i++) {
                    if (s <= 21)
                        break;
                    s -= 10;
                }
            }

            this.Score = s;
        }
    }

    public void dealCards() {
        if (c.getCase()) {
            getaCard();
            getaCard();
        }
    }

    protected void setCase(boolean b) {
        this.Case = b;
    }
}
