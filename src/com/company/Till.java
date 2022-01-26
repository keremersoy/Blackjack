package com.company;

public class Till extends Table {
    public Till(Cards _card) {
        super(_card);
    }

    public void check21(Player player) {
        while (true) {
            this.checkScore();
            if (this.Score > 21) {
                this.Case = false;
                player.setCase(true);
                break;
            } else if (this.Score > player.getScore()) {
                this.Case = true;
                player.setCase(false);
                break;
            } else if (this.Score == player.getScore()) {
                this.Case = true;
                player.setCase(true);

                if (this.Score >= 17)
                    break;
                else
                    this.getaCard();
            } else if (this.Score < player.getScore()) {
                this.Case = false;
                player.setCase(true);
                this.getaCard();
            }


        }
    }
}
