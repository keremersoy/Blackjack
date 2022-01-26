package com.company;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Cards {
    private String NameAndNumber[][];
    private int Step;
    private boolean Case;

    public Cards() {
        this.NameAndNumber = new String[52][2];
        this.CreateDeck();
        this.ShuffleDeck();
    }

    public void CreateDeck() {
        this.Case = true;
        this.Step = 0;
        String card;
        int line = 0;

        try {
            File file = new File(System.getProperty("user.dir")+"\\file\\Cards.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                card = sc.nextLine();
                this.NameAndNumber[line][0] = card.substring(0, card.indexOf('-'));
                this.NameAndNumber[line][1] = card.substring(card.indexOf('-') + 1);
                line++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ShuffleDeck() {
        List<String> deck = new ArrayList<>();
        String s;

        for (int i = 0; i < this.NameAndNumber.length; i++) {
            s = this.NameAndNumber[i][0] + "-" + this.NameAndNumber[i][1];
            deck.add(s);
        }
        Collections.shuffle(deck);

        String item;
        for (int k = 0; k < NameAndNumber.length; k++) {
            item = String.valueOf(deck.get(k));
            this.NameAndNumber[k][0] = item.substring(0, item.indexOf('-'));
            this.NameAndNumber[k][1] = item.substring(item.indexOf('-') + 1);
        }
    }

    public boolean getCase() {
        return this.Case;
    }

    public void setCase(boolean b) {
        this.Case = b;
    }

    public String[] getaCard() {
        String card[] = new String[2];
        String cardName = null, cardNumber = null;

        if (this.Case && this.Step < 52) {
            cardName = this.NameAndNumber[this.Step][0];
            cardNumber = this.NameAndNumber[this.Step][1];
            card[0] = cardName;
            card[1] = cardNumber;
        } else {
            this.Case = false;
        }
        this.Step++;
        return card;
    }
}
