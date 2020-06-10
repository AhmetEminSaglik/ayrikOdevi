package ayrikmatematik;

public class HarfHesaplama {

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public char KarakterHesapla(int index) {

        return alphabet.charAt(index);
    }

    public String SatiraYazilacak(int index) {

        String text = "";
        if (index < 26) {

            text += KarakterHesapla(index);
        }
        /*
        } else {
            int BirinciBasamak = index;
            int sayacOnlar = -1;
            while (index > 26) {
                //text += KarakterHesapla((index / 26) - 1);
                sayacOnlar++;
                index /= 26;
            }
            text += KarakterHesapla(sayacOnlar);
            text += KarakterHesapla(BirinciBasamak % 26);

        }*/
        //System.out.println("text : " + text);
        return text;
    }
}
