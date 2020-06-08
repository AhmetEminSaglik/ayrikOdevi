package Logic;

import ayrikmatematik.Frame;
import ayrikmatematik.PanelDegerGirme;
import ayrikmatematik.TablePanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Action implements ActionListener, MouseListener {

    Color SiliniyorArkaPlanRenk = Color.red;
    Color SilinmisArkaPlanRenk = Color.BLACK;
    Color DusukSutunAgirligiRengi = new Color(52, 152, 219);
    Color IsaretliSutunlarinIsaretlenecekSatirRengi = new Color(243, 156, 18);
    Color KesisenIsaretliSutunVeSatirRengi = new Color(38, 222, 129);
    Color oncedenBoyanmis;
    int silinenSatir = 0;
    int silinenSutun = 0;
    boolean IslemYapıldı = true;
    int ClickCounter[] = new int[3];  // 0-> i için || j-> 1 içinn  ||  sayac -> 2  için
    final int MUTLAK_SATIR = 0, SATIR_KAPSAMA = 1, SUTUN_KAPSAMA = 2, SEZGIZSEL_ALGORITMA = 3;
    String btnNameNext = "ilerle";

    PanelDegerGirme pdg;
    TablePanel tabloMatrix;

    public Action(PanelDegerGirme pdg) {
        this.pdg = pdg;
    }

    public Action(TablePanel tabloMatrix) {
        this.tabloMatrix = tabloMatrix;
    }

    public void HataBastir(String text) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, text);

    }

    public void HataBastir(int number) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, number);

    }

    public boolean InputIntegerControl(String InputTextforInteger) {
        try {
            if (Integer.parseInt(InputTextforInteger) > 26) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            HataBastir("Lütfen N ve M değerlerine Tam Sayı giriniz");
            return false;
        } catch (NullPointerException e) {
            HataBastir("Lütfen N ve M değerlerini doldurun");
            return false;
        } catch (Exception ex) {
            HataBastir("Girebileceğiniz deger maksimum 26 dır");
            return false;
        }

        return true;
    }

    public void matrixKopyala(JTextField[][] first, JTextField[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {

            }
        }
    }

    public void EskiTabloyuGuncelle(TablePanel first, TablePanel second) {
        for (int i = 0; i < first.getTxtMatris().length; i++) {
            for (int j = 0; j < first.getTxtMatris()[i].length; j++) {
                second.getTxtMatris()[i][j].setText(first.getTxtMatris()[i][j].getText());
                second.getTxtMatris()[i][j].setVisible(first.getTxtMatris()[i][j].isVisible());
                second.getTxtMatris()[i][j].setBounds(
                        (int) first.getTxtMatris()[i][j].getBounds().getX(),
                        (int) first.getTxtMatris()[i][j].getBounds().getY(),
                        (int) first.getTxtMatris()[i][j].getBounds().getWidth(),
                        (int) first.getTxtMatris()[i][j].getBounds().getHeight()
                );
                second.getTxtMatris()[i][j].setBackground(first.getTxtMatris()[i][j].getBackground());
                /*
          
                    guncelTablo[i][j].setText(guncelTablo[i][j + 1].getText());
                    width = guncelTablo[i][j + 1].getBounds().getWidth();
                    height = guncelTablo[i][j + 1].getBounds().getHeight();

                    guncelTablo[i][j].setSize((int) width, (int) height);
                 */
            }
        }

    }

    public int SutunKapsamalariIcin2SutunuKarsilastir(JTextField[][] guncelTablo, int firstColumn, int secondColumn) {
        int birinciSilinecek = 1;
        int ikinciSilinecek = 2;
        int firstCounter = 0;
        int secondCounter = 0;
        // HataBastir("karşılaştırılacak satırlardaki sutunların sayısı : " + (DizideIslemYapılabilecekSutunSayisi(first) - 1 - 1));
        for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
            /*System.out.println("i : " + i + " > HATA -> " + first[i].getText().trim());
            System.out.println("silinen satir sayısı : " + silinenSatir + " / silinen sutun sayısı :" + silinenSutun);
            System.out.println(" DizideIslemYapılabilecekSatirSayisi(first)-1 :" + (DizideIslemYapılabilecekSatirSayisi(first) - 1));
             */

            int birinciIndex = Integer.parseInt(guncelTablo[i][firstColumn].getText().trim());
            int ikinciIndex = Integer.parseInt(guncelTablo[i][secondColumn].getText().trim());
            //  HataBastir("BİRİNCİ İNDEXİ GEÇTİK HATA YOK : i:" + i + " birinci  :" + firstColumn + " / ikinci :" + secondColumn);
            if (birinciIndex != ikinciIndex) {
                if (birinciIndex > ikinciIndex && secondCounter == 0) {
                    firstCounter++;
                } else if (ikinciIndex > birinciIndex && firstCounter == 0) {
                    secondCounter++;

                } else {
                    // HataBastir("else 118 ");
                    return 0;
                }
            }

        }
        if (firstCounter == secondCounter) {
            return 0;
        }
        if (firstCounter > 0) {
            return birinciSilinecek; // 1-> ilk dizi ikinciyi kapsar --- ikinci satir silinir
        }
        return ikinciSilinecek;  //--- birinci satir silinir

    }

    public int SatirKapsamalariIcin2SatırıKarsilastir(JTextField[] first, JTextField[] second) {
        //  HataBastir("i ->0 : "+first[0].getText()+"j ->0 : "+second[0].getText());

        int birIkinciyiKapsar = 1;
        int ikiBirinciyiKapsar = 2;
        int firstCounter = 0;
        int secondCounter = 0;
        // HataBastir("karşılaştırılacak satırlardaki sutunların sayısı : " + (DizideIslemYapılabilecekSutunSayisi(first) - 1 - 1));
        for (int i = 1; i < DizideIslemYapılabilecekSutunSayisi(first) - 1; i++) {
            /*  System.out.println("i : " + i + " > HATA -> " + first[i].getText().trim());
            System.out.println("silinen satir sayısı : " + silinenSatir + " / silinen sutun sayısı :" + silinenSutun);
            System.out.println(" DizideIslemYapılabilecekSatirSayisi(first)-1 :" + (DizideIslemYapılabilecekSatirSayisi(first) - 1));
             */ int birinciIndex = Integer.parseInt(first[i].getText().trim());
            int ikinciIndex = Integer.parseInt(second[i].getText().trim());
            //  HataBastir("BİRİNCİ İNDEXİ GEÇTİK HATA YOK : i:" + i);
            if (birinciIndex != ikinciIndex) {
                if (birinciIndex > ikinciIndex && secondCounter == 0) {
                    firstCounter++;
                } else if (ikinciIndex > birinciIndex && firstCounter == 0) {
                    secondCounter++;

                } else {
                    return 0;
                }
            }

        }
        if (secondCounter > 0) {
            return ikiBirinciyiKapsar; // 1-> ilk dizi ikinciyi kapsar --- ikinci satir silinir
        }
        return birIkinciyiKapsar;  //--- birinci satir silinir

    }

    public void SatirSil(JTextField[][] guncelTablo, int silinecekSatir) { // silme işlemini null atayarak yapacam
        //HataBastir("ŞimdiSatırSilinecek");
        double width, height; //x, y,
        //  HataBastir("silinecek sutun : "+silinecekSutun);
        // AsilTablodasilinenSutunuBoya(silinecekSatir);//+ silinenSutun
        HataBastir("silinecek satır : " + guncelTablo[silinecekSatir][0].getText().trim());
        for (int i = silinecekSatir; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
            for (int j = 0; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo); j++) {//guncelTablo[i].length - 1 - silinenSutun

                if (i != guncelTablo.length) { // +1 neden var bilmiyorum !!!

                    guncelTablo[i][j].setText(guncelTablo[i + 1][j].getText());
                    //width = guncelTablo[i][j].getBounds().getWidth(); // siliyorum çünkü son sadece son sutunu diğer sutunlara kaydırırken boyutlar uyuşmuyordu
                    //height = guncelTablo[i][j].getBounds().getHeight();

                    //guncelTablo[i][j].setSize((int) width, (int) height);
                    if (i == DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 2) {//guncelTablo[i].length - 2 - silinenSutun
                        guncelTablo[i + 1][j].setVisible(false);
                    }

                } else {
                    HataBastir("TRUEEE");
                    guncelTablo[i][j].setVisible(false);
                }

            }

        }

        silinenSatir++;
        //HataBastir("satır silindi.  --->  silinen satır sayısı : " + silinenSatir);


        /* for (int i = 0; i < guncelTablo.length; i++) {
            guncelTablo[i][silinecekSutun].setText("-1");
            guncelTablo[i][silinecekSutun].setVisible(false);
        }*/

 /* for (int i = 0; i < guncelTablo.length; i++) {
            for (int j = 0; j < guncelTablo[i].length; j++) {
                System.out.println(guncelTablo[i][j]);
            }
        }*/
    }

    public boolean SatirKapsamaFonk(JTextField[][] guncelTablo) {
        //  HataBastir("SatırKapsamyagirdik");
        for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
            for (int j = 1; j < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; j++) {
                if (i != j) {
                    //  HataBastir("karşılaştırılacak indexler i :" + i + " --- j :" + j);
                    int feedback = SatirKapsamalariIcin2SatırıKarsilastir(guncelTablo[i], guncelTablo[j]); // satırları gönderir
                    if (feedback == 1) { // 1. kapsar --- ikinci satir silinir
                        /*  HataBastir("feedback : 1 \n"
                                + "duracak satir birinci satir :" + i
                                + "silinecek satir ikinci satir : " + j);*/
                        SatirSil(guncelTablo, j);
                        return IslemYapıldı;
                    } else if (feedback == 2) { // 2. kapsar --- birinci satir silinir
                        /*   HataBastir("feedback : 2\n"
                                + "duracak satir birinci satir :" + j
                                + "silinecek satir ikinci satir : " + i);*/
                        SatirSil(guncelTablo, i);

                        return IslemYapıldı;
                    } else {
                        //HataBastir("birinci satir : :" + i + "ikinci satir :" + j + " --> Silinen olmadı");
                    }

                }
            }
        }
        /* yazılacak fonk.'da karşılaştırılan iki satırın sutunlarındaki 1'lerin yeri aynı ise 
        ve bir tanesinde fazla ise  */
 /*
        iki satırı burda belirlesem  fonk. atsam ;
        satırların sutunlarını karşlaştırsam
        aynı yerde 1'ler; farklı yerde 1'ler;  aynı yerde + fazlası olan 1'ler
        aynı yerde 1'ler ise fonk false dönsün;
        diğer 2 seçenekten biri olursa true dönsün;
        eğer true dönerse  ikinci satır alt satır olur ve o silinir
         */
        // islem tamamsa true döner // 
        // HataBastir("SATIR KAPSAMA BAŞARISIZ --> SUTUN KAPSAMAYA GEÇİLECEKTİR");
        return false;
    }

    public int DiziUzunluguSatir(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }

    public int DiziUzunluguSutun(JTextField[][] guncelTablo) {
        return guncelTablo[0].length - silinenSutun;
    }

    public boolean SutunKapsamaFonk(JTextField[][] guncelTablo) {
        //  HataBastir("islem yapılacak sutun sayısı :" + (DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 2));
        for (int i = 1; i < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; i++) {
            for (int j = 1; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {
                if (i != j) {
                    System.out.println("i --> last index önemli :" + i);
                    System.out.println("j --> last index önemli :" + j);
                    // HataBastir("karşılaştırılacak indexler i :" + i + " --- j :" + j);
                    int feedback = SutunKapsamalariIcin2SutunuKarsilastir(guncelTablo, i, j); // satırları gönderir
                    if (feedback == 1) { // 1. kapsar --- ikinci satir silinir
                        /*  HataBastir("feedback : 1 \n"
                                + "duracak satir birinci satir :" + i
                                + "silinecek satir ikinci satir : " + j);*/
                        SutunSil(guncelTablo, i);
                        return IslemYapıldı;
                    } else if (feedback == 2) { // 2. kapsar --- birinci satir silinir
                        /* HataBastir("feedback : 2\n"
                                + "duracak satir birinci satir :" + j
                                + "silinecek satir ikinci satir : " + i);*/
                        SutunSil(guncelTablo, j);

                        return IslemYapıldı;
                    } else {
                        //HataBastir("birinci satir : :" + i + "ikinci satir :" + j + " --> Silinen olmadı");
                    }

                }
            }
        }
        return false; // islem tamamsa true döner
    }

    public void Sutunlardaki_1_OlanSatirlariIsaretle(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {

        for (int j = 1; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {
            if (Integer.parseInt(
                    guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1][j]
                            .getText().trim()) == enDusukSutunAgirligi) {
                for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {

                    // i satırının butun j lerini boya  sonra ortak noktasını yeşil yap 
                    for (int k = 0; k < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; k++) {
                        if (!guncelTablo[i][k].getText().trim().equals("0")) {
                            if (guncelTablo[i][k].getBackground() == DusukSutunAgirligiRengi) {
                                guncelTablo[i][k].setBackground(KesisenIsaretliSutunVeSatirRengi);
                            } else if (guncelTablo[i][k].getBackground() != KesisenIsaretliSutunVeSatirRengi) {
                                guncelTablo[i][k].setBackground(IsaretliSutunlarinIsaretlenecekSatirRengi);
                            }
                        }
                    }
                }
            }
        }
    }

    public void DusukSutunlariIsaretle(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {
        for (int j = 1; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {
            if (Integer.parseInt(
                    guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1][j]
                            .getText().trim()) == enDusukSutunAgirligi) {
                for (int i = 0; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
                    if (!guncelTablo[i][j].getText().trim().equals("0")) {
                        guncelTablo[i][j].setBackground(DusukSutunAgirligiRengi);
                    }
                }
            }
        }
    }

    public void SatirAgirliklariniHesapla(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {
        /*
        kb ->kullanabilecek satırların
        toplam=0;
        for (i)->{  toplam=0; for(j) ->  { if ((j)-> sa  == dusuk sutun ağırlı) {  [i][j]= 1  olduğu yerde -> toplam+=1/(sutunağırlı) } i-> en sağa toplamı yazdır} 
        
         */
        double toplam = 0;
        for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
            toplam = 0;
            for (int j = 1; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {

                if (Integer.parseInt(guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1][j]
                        .getText().trim()) == enDusukSutunAgirligi
                        && Integer.parseInt(guncelTablo[i][j].getText().trim()) == 1) {
                    //enDusukSutunAgirligi ile sutun ağırlığı arasında fark olduğunu sanmıyorum ama diğer türlü yapacam

                    double sutunAgirligi = Double.parseDouble(guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1][j].getText().trim());
                    toplam += 1 / sutunAgirligi;
                }
            }
            toplam *= DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 2;
            guncelTablo[i][DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1].setText(Double.toString(toplam));
        }
    }

    public void EndusukSatirAgiriniSil(JTextField[][] guncelTablo) {
        int dusukSatirAgirininIndexi = 0;
        double satirAgirligiDusukolanSecilecek = Double.MAX_VALUE;
        double satirAgirligi = 0;
        for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
            satirAgirligi = Double.parseDouble(guncelTablo[i][DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1].getText().trim());
            if (satirAgirligiDusukolanSecilecek > satirAgirligi) {
                satirAgirligiDusukolanSecilecek = satirAgirligi;
                dusukSatirAgirininIndexi = i;
            }
        }
        SatirSil(guncelTablo, dusukSatirAgirininIndexi);
    }

    public boolean SezgiselAlgoritmaFonk(JTextField[][] guncelTablo) {
        /*
        en düşük sutunlarıı bul ve işaretle
        işaretli sutunların satırlarını işaretle
        işaretli satırdaki keşisen sutunların 1/(sutun ağırlır)+1/(sutun ağırlır) ... 
        toplam*=o anki satır ağırlığı
                
         */
        MutlakSatirSutunToplaminiYaz(guncelTablo);// belki bunu silebilirim pek gerek kalmayabilir.
        int dusukSutunAgirligi
                = enDusukSutunAgirligiDegeri(
                        guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1]
                );
        DusukSutunlariIsaretle(guncelTablo, dusukSutunAgirligi);
        Sutunlardaki_1_OlanSatirlariIsaretle(guncelTablo, dusukSutunAgirligi);
        SatirAgirliklariniHesapla(guncelTablo, dusukSutunAgirligi);
        EndusukSatirAgiriniSil(guncelTablo);


        /*,
        kb ->kullanabilecek satırların
        toplam=0;
        for (i)->{  toplam=0; for(j) ->  { if ((j)-> sa  == dusuk sutun ağırlı) {  [i][j]= 1  olduğu yerde -> toplam+=1/(sutunağırlı) } i-> en sağa toplamı yazdır} 
        }
        
        
        
        düşük sutunların 1 olduğu satırdaki işaretli satırların
        satır ağırlığı =  o satırların ()
         */
        return IslemYapıldı; // islem tamamsa true döner
    }

    /* public void SuankiDiziyiBaştanOluşturma_SutunSilme(JTextField[][] guncelTablo, int Satir_Sutun_silindi) {
        switch (Satir_Sutun_silindi) {
            case 0: //satır silinmesi
                break;
            case 1:// sutun silinmesi

                TablePanel YeniTablo = new TablePanel(pdg.getFrame(),
                        tabloMatrix.getFrame().getWidth() / 2,
                        tabloMatrix.getFrame().getHeight() / 2,
                        tabloMatrix.getFrame().getWidth(),
                        tabloMatrix.getFrame().getHeight());
                YeniTablo.TablePanelMatrisOlusturSifirlaBastirma(guncelTablo.length, guncelTablo[0].length - 1);
                
                for (int i = 0; i < guncelTablo.length; i++) {
                    for (int j = 0; j < guncelTablo[i].length - 1; j++) {
                        YeniTablo.getTxtMatris()[i][j].setText(guncelTablo[i][j].getText());
                    }
                    
                }
                tabloMatrix.setTxtMatris(YeniTablo.getTxtMatris());
                break;
        }
        
    }*/
    public void AsilTabladaKirmizilariSiyahaBoya() {
        System.out.println("KIRMIZILARI SİYAHA BOYA İNAKTİF");

        /*JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                if (dizi[i][j].getBackground() == Color.RED) {
                    dizi[i][j].setBackground(Color.black);
                }

            }
        }*/
    }

    public void AsilTablodasilinenSutunuBoya(int sutun) {
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 0; i < dizi.length; i++) {
            dizi[i][sutun].setBackground(SiliniyorArkaPlanRenk);

        }
    }

    public void AsilTablodaSilinenSatiriBoya(int satir) {
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 0; i < dizi[satir].length; i++) {
            dizi[satir][i].setBackground(SiliniyorArkaPlanRenk);

        }
    }

    public void SutunSil(JTextField[][] guncelTablo, int silinecekSutun) { // silme işlemini null atayarak yapacam
        //  HataBastir("Silinecek sutun " + silinecekSutun);
        double width, height; //x, y,
        //  HataBastir("silinecek sutun : "+silinecekSutun);
        AsilTablodasilinenSutunuBoya(silinecekSutun);//+ silinenSutun
        HataBastir("silinecek sutun : " + guncelTablo[0][silinecekSutun].getText().trim());
        for (int i = 0; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo); i++) {
            for (int j = silinecekSutun; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {//guncelTablo[i].length - 1 - silinenSutun

                if (j != guncelTablo[i].length + 1) {

                    guncelTablo[i][j].setText(guncelTablo[i][j + 1].getText());
                    width = guncelTablo[i][j + 1].getBounds().getWidth();
                    height = guncelTablo[i][j + 1].getBounds().getHeight();

                    guncelTablo[i][j].setSize((int) width, (int) height);

                    if (j == DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 2) {//guncelTablo[i].length - 2 - silinenSutun
                        guncelTablo[i][j + 1].setVisible(false);
                        //     HataBastir("Silinen index -> i :" + i + " ; j:" + j);

                    }

                } else {
                    HataBastir("TRUEEE");
                    guncelTablo[i][j].setVisible(false);
                }

                /* if (j == guncelTablo[i].length) {

                } else {
                    HataBastir("j :" + j + " , yeri :" + (guncelTablo[i].length - silinenSutun));
                }*/
 /* if (j == guncelTablo[i].length - 2 - silinenSutun) {
                    HataBastir("if te --> " + "hedef : j:" + j + " hesaplanabilen : " + (guncelTablo[i].length - 2 - silinenSutun));
                    guncelTablo[i][j].setVisible(false);  //  HataBastir("if : uzunluk :" + guncelTablo[i].length + " j :" + j + "\n -(silinenSutun+2)" + (-silinenSutun - 2));
                } else {
                    HataBastir("hedef : j:" + j + " hesaplanabilen : " + (guncelTablo[i].length));
                    //HataBastir("else uzunluk :" + guncelTablo[i].length + ": j :  " + j + "\n -(silinenSutun+2)" + (-silinenSutun - 2));
                }*/
            }

        }

        silinenSutun++;
        //HataBastir("sutun silindi.  --->  silinen sutun sayısı : " + silinenSutun);

        /* for (int i = 0; i < guncelTablo.length; i++) {
            guncelTablo[i][silinecekSutun].setText("-1");
            guncelTablo[i][silinecekSutun].setVisible(false);
        }*/

 /* for (int i = 0; i < guncelTablo.length; i++) {
            for (int j = 0; j < guncelTablo[i].length; j++) {
                System.out.println(guncelTablo[i][j]);
            }
        }*/
    }
    int SAYAC = 0;

    public int DizideIslemYapılabilecekSatirSayisi(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }

    public int DizideIslemYapılabilecekSatirSayisi(JTextField[][] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }

    public int DizideIslemYapılabilecekSutunSayisi(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSutun;
    }

    public int DizideIslemYapılabilecekSutunSayisi(JTextField[][] guncelTablo) {
        return guncelTablo[0].length - silinenSutun;
    }

    public void MutlakSatirdaElemeIslemi(JTextField[][] guncelTablo, int sutun) {

// şimdi hangi satırda 1 ise  o satırı bulup
// aynı satırda 1 olan tüm sutunları silecez
        int satir = 0;
        for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo); i++) {//guncelTablo.length - silinenSatir
            if (Integer.parseInt(guncelTablo[i][sutun].getText()) == 1) {

                satir = i;
                //   HataBastir("satır belirlendi satır : " + satir + "/ sutun :" + sutun);
                break;
            }
        }
        //HataBastir("Belirnen satır :" + satir);
        for (int i = 1; i < DizideIslemYapılabilecekSutunSayisi(guncelTablo); i++) {//guncelTablo[satir].length - silinenSutun

            /*(guncelTablo[satir][i].getBackground() != Color.RED
                    || guncelTablo[satir][i].getBackground() != Color.black)
                    &&*/
            if (guncelTablo[satir][i].getText().trim().equals("1")) { // degeri 1 ise o sutunu sil
                //   HataBastir("aynı satırdaki sutundaki 1 lerin kontrolu -> i :" + i + " / satir :" + satir);
                // HataBastir("satırdaki : " + satir + " sutun silinecek :" + i);
                SutunSil(guncelTablo, i);
                // AsilTablodaSilinenSatiriBoya(satir+);
                i--;
                //  HataBastir("sutun silindi");

            }
        }
        SatirSil(guncelTablo, satir);
    }

    public boolean MutlakSatirdaElemeFonk(JTextField[][] guncelTablo) {

        for (int i = 1; i < guncelTablo[0].length - 1; i++) {
            if (guncelTablo[guncelTablo.length - silinenSatir - 1][i].getText().trim().equals("1")) {

                MutlakSatirdaElemeIslemi(guncelTablo, i);// i -> ağırlığı = 1 olan sutun
                return IslemYapıldı;
            }

        }
        return false;
    }

    public void MutlakSatirSutunToplaminiYaz(JTextField[][] guncelTablo) {
        // HataBastir("Sutun toplamları yazılacak");

        int Sutundaki_1_lerinToplami = 0;
        int sutunSayisi = guncelTablo[0].length - 1;// -1 ağırlık kısmınn alınmaması için
        for (int sayac = 1; sayac < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; sayac++) {
            for (int i = 1; i < DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1; i++) {
                for (int j = 0; j < DizideIslemYapılabilecekSutunSayisi(guncelTablo) - 1; j++) {//guncelTablo[i].length - 1 - silinenSutun

                    if (sayac == j
                            && guncelTablo[i][j] != null
                            && guncelTablo[i][j].getText().trim().equals("1") && guncelTablo[i][j].getText().trim().equals("1")//&& Integer.parseInt(guncelTablo[i][j].getText()) == 1 // &&guncelTablo[i][j].getText().trim().equals("1")
                            ) {
                        Sutundaki_1_lerinToplami++;

                    }
                }
            }

            guncelTablo[DizideIslemYapılabilecekSatirSayisi(guncelTablo) - 1][sayac].
                    setText(Integer.toString(Sutundaki_1_lerinToplami));
            Sutundaki_1_lerinToplami = 0;

        }
    }

    public void AsilTabloSutunlariBoya(JTextField[][] asilDizi, int sutun) {
        for (int i = 1; i < asilDizi.length; i++) {
            asilDizi[i][sutun].setBackground(SiliniyorArkaPlanRenk);
        }
    }

    public void MutlakSatirAsilTablo_boyanacakSutunlariBelirle(JTextField[][] asilDizi, int satir) {
        HataBastir("HATA SANIYORUM ŞURDA  OLUYOR :MutlakSatirAsilTablo_boyanacakSutunlariBelirle");
        for (int i = 1; i < asilDizi[0].length; i++) {
            if (asilDizi[satir][i].getText().trim().equals("1")) {
                HataBastir("boyanacak : i :" + satir + " j :" + i);
                AsilTabloSutunlariBoya(asilDizi, i);

            }
        }
    }

    public void MutlakSatirdaBoyamaAsilTablo() {
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 1; i < dizi.length - 1; i++) {
            for (int j = 1; j < dizi[i].length - 1; j++) {
                if (dizi[i][j].getText().trim().equals("1")) {
                    HataBastir("i :" + i + "j : " + j);
                    MutlakSatirAsilTablo_boyanacakSutunlariBelirle(dizi, i);// i -> ağırlığı = 1 olan sutun

                }
            }

        }
    }

    public boolean MutlakSatirFonk(JTextField[][] guncelTablo) {

        MutlakSatirSutunToplaminiYaz(guncelTablo);

        if (MutlakSatirdaElemeFonk(guncelTablo)) {
            return true;
        }
        //   MutlakSatirSutunToplaminiYaz(pdg.getFrame().getAsilTable().getTxtMatris());     
        //     MutlakSatirdaBoyamaAsilTablo(); 

        return false; // islem tamamsa true döner
    }

    /* public boolean matrixIndexIslemYapilabilir(JTextField index) {
        if (index.getBackground() != SiliniyorArkaPlanRenk
                || index.getBackground() != SilinmisArkaPlanRenk) {
            return true;
        }
        return false;
    }*/
    public void diziBastir(JTextField[][] guncelTablo) {
        System.out.println("*******************************************");
        for (int i = 0; i < guncelTablo.length; i++) {

            if (!guncelTablo[i][0].getText().trim().equals(guncelTablo[i][guncelTablo[i].length - 1].getText().trim())) {
                for (int j = 0; j < guncelTablo[i].length; j++) {

                    System.out.print("[" + i + "][" + j + "] = " + guncelTablo[i][j].getText().trim() + "  ");

                }
                System.out.println("");
            }
        }
    }

    public void islemler(JTextField[][] guncelTablo, int islem) {

        //HataBastir("silinen Satır sayısı : " + silinenSatir);
        //  HataBastir("silinen Sutun sayısı : " + silinenSutun);
        // diziBastir(guncelTablo);
        MutlakSatirSutunToplaminiYaz(guncelTablo);
        EskiTabloyuGuncelle(pdg.getFrame().getSuankiTable(),
                pdg.getFrame().getOncekiTable());
        switch (islem) {
            case MUTLAK_SATIR:
                if (MutlakSatirFonk(guncelTablo)) {
                    return;
                }
            /* if (MutlakSatirFonk(guncelTablo)) {
                    diziBastir(guncelTablo);
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }*/
            case SATIR_KAPSAMA:

                if (SatirKapsamaFonk(guncelTablo)) {

                    return;
                }
            /* if (SatirKapsamaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }*/
            case SUTUN_KAPSAMA:
                // HataBastir("Sutun Kapsamasına giriyoruz");
                if (SutunKapsamaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }
            case SEZGIZSEL_ALGORITMA:
                /*
                1-)ilk başta sutun ağırlıkları hesaplanacak
                2-) ardından
                 */
                HataBastir("Sezgisele giriyoruz");
                if (SezgiselAlgoritmaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                } else {
                    HataBastir("İşlemler Tamamlanmıştır");
                }
        }
        MutlakSatirSutunToplaminiYaz(guncelTablo); // işlem bitince sutun ağırlıklarını günceller

    }

    public int enDusukSutunAgirligiDegeri(JTextField[] sonsatir) {
        HataBastir("ilk index : " + sonsatir[0].getText().trim());
        int dusukDeger = Integer.MAX_VALUE;
        for (int i = 1; i < DizideIslemYapılabilecekSutunSayisi(sonsatir) - 1; i++) {
            if (dusukDeger > Integer.parseInt(sonsatir[i].getText().trim())) {
                dusukDeger = Integer.parseInt(sonsatir[i].getText().trim());
            }
        }
        return dusukDeger;
    }

    public void SonrakiAdimaIlerlemeFonksiyonu(JTextField[][] guncelTablo) {

        islemler(guncelTablo, MUTLAK_SATIR);
    }

    public void YeniTabloyuGuncelle() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (pdg != null) {
            if (e.getSource() == pdg.getBtnOlustur()
                    && InputIntegerControl(pdg.getTxtNDegeri().getText().trim())
                    && InputIntegerControl(pdg.getTxtMDegeri().getText().trim())) {

                pdg.getTxtNDegeri().setEditable(false);
                pdg.getTxtNDegeri().setFocusable(false);
                pdg.getTxtMDegeri().setEditable(false);
                pdg.getTxtNDegeri().setFocusable(false);
                //pdg.getLblMDegeri().setEnabled(false);
                pdg.getBtnOlustur().setEnabled(false);
                pdg.getBtnStartOrNext().setEnabled(true);
                pdg.getBtnGoResult().setEnabled(true);
                pdg.getBtnRestart().setEnabled(true);

                pdg.getFrame().getAsilTable().TablePanelMatrisOlustur(
                        Integer.parseInt(pdg.getTxtNDegeri().getText().trim()) + 2,
                        Integer.parseInt(pdg.getTxtMDegeri().getText().trim()) + 2);

                pdg.setAsilTabloDegerDegistirme(true);

            } else if (pdg.isAsilTabloDegerDegistirme() == true // deger değiştirme açıkken ve ilerle butonuna dönüşmemiş se ilerleye dönüştüriyoruz
                    && e.getSource() == pdg.getBtnStartOrNext()) {
                pdg.getFrame().AddOtheTablesToFrame(pdg.getFrame().getAsilTable().getTxtMatris());
                pdg.setAsilTabloDegerDegistirme(false);
                pdg.getBtnStartOrNext().setText(btnNameNext);
            }/* else if (pdg.isIlerle() == true //ilerle butonu açıksa her bastığında bir sonraki işleme geçecek
                    && e.getSource() == pdg.getBtnStartOrNext()) {
            }*/
            if (pdg.isAsilTabloDegerDegistirme() == false
                    && e.getSource() == pdg.getBtnStartOrNext()) {
                AsilTabladaKirmizilariSiyahaBoya();
                //MutlakSatirSutunToplaminiYaz(pdg.getFrame().getSuankiTable().getTxtMatris());

                SonrakiAdimaIlerlemeFonksiyonu(pdg.getFrame().getSuankiTable().getTxtMatris());

                /* pdg.getFrame().getSuankiTable().setVisible(false);
                pdg.getFrame().getSuankiTable().setVisible(true);*/
            } else if (e.getSource() == pdg.getBtnRestart()) {

                pdg.getFrame().getjFrame().setVisible(false);
                new Frame();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (tabloMatrix != null && tabloMatrix.DegerDegistir) {

            for (int i = 0; i < tabloMatrix.getTxtMatris().length - 1; i++) {
                for (int j = 0; j < tabloMatrix.getTxtMatris()[i].length - 1; j++) {

                    if (i > 0 && j > 0) {

                        if (e.getSource() == tabloMatrix.getTxtMatris()[i][j]) {
                            if (Integer.parseInt(tabloMatrix.getTxtMatris()[i][j].getText()) == 1) {
                                tabloMatrix.getTxtMatris()[i][j].setText("0");

                            } else if (Integer.parseInt(tabloMatrix.getTxtMatris()[i][j].getText()) == 0) {
                                tabloMatrix.getTxtMatris()[i][j].setText("1");
                            }
                            /*if (ClickCounter[0] == i && ClickCounter[1] == j) {
                                ClickCounter[2]++;
                                if (ClickCounter[2] == 2) {
                                    if (Integer.parseInt(tabloMatrix.getTxtMatris()[i][j].getText()) == 1) {
                                        tabloMatrix.getTxtMatris()[i][j].setText("0");
                                    } else if (Integer.parseInt(tabloMatrix.getTxtMatris()[i][j].getText()) == 0) {
                                        tabloMatrix.getTxtMatris()[i][j].setText("1");
                                    }
                                    ClickCounter[2] = 0;
                                    brimBoyama(Color.ORANGE, tabloMatrix.getTxtMatris()[i][j]);
                                } else {
                                    brimBoyama(Color.cyan, tabloMatrix.getTxtMatris()[i][j]);
                                }
                            } else {
                                ClickCounter[0] = i;
                                ClickCounter[1] = j;
                                ClickCounter[2] = 1;
                                brimBoyama(Color.CYAN, tabloMatrix.getTxtMatris()[i][j]);
                            }*/
                        }
                    }
                }
            }

        }
    }

    public void brimBoyama(Color color, JTextField alan) {
        alan.setBackground(color);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        for (int i = 0; i < tabloMatrix.getTxtMatris().length; i++) {
            for (int j = 0; j < tabloMatrix.getTxtMatris()[i].length; j++) {
                if (e.getSource() == tabloMatrix.getTxtMatris()[i][j]) {
                    oncedenBoyanmis = tabloMatrix.getTxtMatris()[i][j].getBackground();
                    brimBoyama(Color.GREEN, tabloMatrix.getTxtMatris()[i][j]);

                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < tabloMatrix.getTxtMatris().length; i++) {
            for (int j = 0; j < tabloMatrix.getTxtMatris()[i].length; j++) {
                if (e.getSource() == tabloMatrix.getTxtMatris()[i][j]) {

                    if (tabloMatrix.getTxtMatris()[i][j].getText().equals("1")) {

                        tabloMatrix.getTxtMatris()[i][j].setBackground(Color.cyan);
                    } else {

                        brimBoyama(Color.WHITE, tabloMatrix.getTxtMatris()[i][j]);
                    }
                }
            }
        }
    }
    /*  public void mouseExited(MouseEvent e) {
        for (int i = 0; i < tabloMatrix.getTxtMatris().length; i++) {
            for (int j = 0; j < tabloMatrix.getTxtMatris()[i].length; j++) {
                if (e.getSource() == tabloMatrix.getTxtMatris()[i][j]) {

                    brimBoyama(oncedenBoyanmis, tabloMatrix.getTxtMatris()[i][j]);
                }
            }
        }
    }*/

}
