package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Player extends Table {
    private String Name;
    private int Money;
    private int Bet;
    private boolean checkBet;
    private Till till;

    File file = new File(System.getProperty("user.dir")+"\\file\\Players.txt");

    public Player(String _name, Cards card, Till _till) {
        super(card);
        this.till = _till;
        this.Name = _name;
        this.Money = 1000;
        this.Bet = 0;
        this.checkBet = false;
    }

    public boolean check21(Till _till, Player _player) {
        this.checkScore();
        if (this.Score > 21) {
            this.Case = false;
            _till.setCase(true);
            return false;

        } else if (this.Score == 21 && _till.Score < 21) {
            _till.check21(_player);
            return false;
        } else if (this.Score == 21 && this.Score == _till.Score) {
            _till.Case = true;
            this.Case = true;
            return false;
        }
        return true;
    }

    public String getName() {
        return this.Name;
    }

    public int getMoney() {
        return this.Money;
    }

    public void setBet(Integer _bet) {
        this.Bet = _bet;
    }

    public int getBet() {
        return this.Bet;
    }

    public boolean getcheckBet() {
        return this.checkBet;
    }

    public void setcheckBet(boolean b) {
        this.checkBet = b;
    }

    public void setMoney(int bahis) {
        this.Money += bahis;
    }

    public void createFile() {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() {
        try {
            LocalDateTime d = LocalDateTime.now();
            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            FileOutputStream fos = new FileOutputStream(file, true);
            String yaz = "Ad: " + this.getName() + "\nPara: ₺" + this.getMoney() + "\n" + d.format(date) + "\n-\n";
            fos.write(yaz.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
