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
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Action implements ActionListener, MouseListener {
    
    boolean virgulEkle = false;
    boolean GoToResultTiklanildi = false;
    Color SiliniyorArkaPlanRenk = Color.red;
    Color SilinmisArkaPlanRenk = Color.BLACK;
    Color SilinmisinYaziRengi = Color.WHITE;
    Color SiyahYaziRengi = Color.black;
    Color DusukSutunAgirligiRengi = new Color(52, 152, 219);
    Color IsaretliSutunlarinIsaretlenecekSatirRengi = new Color(243, 156, 18);
    Color KesisenIsaretliSutunVeSatirRengi = new Color(38, 222, 129);
    Color oncedenBoyanmisArkaPlan;
    Color oncedenBoyanmisYaziRengi;
    int silinenSatir = 0;
    int silinenSutun = 0;
    boolean IslemYapildi = true;
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
    
    public void AsamaBildirimi(String text) {
        JOptionPane.showMessageDialog(null, text);
    }
    
    public boolean InputIntegerControl(String InputTextforInteger) {
        try {
            if (Integer.parseInt(InputTextforInteger) > 25) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            HataBastir("Lütfen N ve M değerlerine Tam Sayi giriniz");
            return false;
        } catch (NullPointerException e) {
            HataBastir("Lütfen N ve M değerlerini doldurun");
            return false;
        } catch (Exception ex) {
            HataBastir("Girebileceğiniz deger maksimum 25 dir");
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
                
            }
        }
        
    }
    
    public int SutunKapsamalariIcin2SutunuKarsilastir(JTextField[][] guncelTablo, int firstColumn, int secondColumn) {
        int birinciSilinecek = 1;
        int ikinciSilinecek = 2;
        int firstCounter = 0;
        int secondCounter = 0;
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            
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
        if (firstCounter == secondCounter + 1) {
            return birinciSilinecek;
        } else if (firstCounter + 1 == secondCounter) {
            return ikinciSilinecek;
        }
        
        return 0;
        
    }
    
    public int SatirKapsamalariIcin2SatiriKarsilastir(JTextField[] first, JTextField[] second) {
        //  HataBastir("i ->0 : "+first[0].getText()+"j ->0 : "+second[0].getText());

        int birIkinciyiKapsar = 1;
        int ikiBirinciyiKapsar = 2;
        int firstCounter = 0;
        int secondCounter = 0;
        // HataBastir("karşilaştirilacak satirlardaki sutunlarin sayisi : " + (DizideIslemYapilabilecekSutunSayisi(first) - 1 - 1));
        for (int i = 1; i < DizideIslemYapilabilecekSutunSayisi(first) - 1; i++) {
            /*  System.out.println("i : " + i + " > HATA -> " + first[i].getText().trim());
            System.out.println("silinen satir sayisi : " + silinenSatir + " / silinen sutun sayisi :" + silinenSutun);
            System.out.println(" DizideIslemYapilabilecekSatirSayisi(first)-1 :" + (DizideIslemYapilabilecekSatirSayisi(first) - 1));
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
        // HataBastir("Silinecek Satir : "+guncelTablo[silinecekSatir][0].getText().trim());
        double width, height; //x, y,
        AsilTablodaSilinenSatiriBoya(satirIlkIndexStringiBul(guncelTablo, silinecekSatir));
        //  HataBastir("silinecek sutun : "+silinecekSutun);
        // AsilTablodasilinenSutunuBoya(silinecekSatir);//+ silinenSutun
        //HataBastir("silinecek satir : " + guncelTablo[silinecekSatir][0].getText().trim() + " / degeri : " + guncelTablo[silinecekSatir][0].getText().trim());
        for (int i = silinecekSatir; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            for (int j = 0; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo); j++) {//guncelTablo[i].length - 1 - silinenSutun

                if (i != guncelTablo.length) { // +1 neden var bilmiyorum !!!

                    guncelTablo[i][j].setText(guncelTablo[i + 1][j].getText());
                    guncelTablo[i][j].setBackground(guncelTablo[i + 1][j].getBackground());

                    //width = guncelTablo[i][j].getBounds().getWidth(); // siliyorum çünkü son sadece son sutunu diğer sutunlara kaydirirken boyutlar uyuşmuyordu
                    //height = guncelTablo[i][j].getBounds().getHeight();
                    //guncelTablo[i][j].setSize((int) width, (int) height);
                    if (i == DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 2) {//guncelTablo[i].length - 2 - silinenSutun
                        guncelTablo[i + 1][j].setVisible(false);
                    }
                    
                }
                //    System.out.println("HATA VERİRSE 197 BAK Bİ ORADA ELSEYİ SİLDİM AMA HİÇ GİRMEMİŞTİ ZATEN");
                /* else {
                    HataBastir("TRUEEE");
                    guncelTablo[i][j].setVisible(false);
                }*/
                
            }
            
        }
        
        silinenSatir++;
        
    }
    
    public boolean SatirKapsamaFonk(JTextField[][] guncelTablo) {
        //  HataBastir("SatirKapsamyagirdik");
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            for (int j = 1; j < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; j++) {
                if (i != j) {
                    //  HataBastir("karşilaştirilacak indexler i :" + i + " --- j :" + j);
                    int feedback = SatirKapsamalariIcin2SatiriKarsilastir(guncelTablo[i], guncelTablo[j]); // satirlari gönderir

                    String KalacakSatirKarakteri = "";
                    String SilinecekSatirKarakteri = "";
                    if (feedback == 1) { // 1. kapsar --- ikinci satir silinir
                        /*     HataBastir("feedback : 1 \n"
                                + "duracak satir birinci satir :" + i
                                + "silinecek satir ikinci satir : " + j);*/
                        KalacakSatirKarakteri = satirIlkIndexStringiBul(guncelTablo, i);
                        SilinecekSatirKarakteri = satirIlkIndexStringiBul(guncelTablo, j);
                        AsamaBildirimi(KalacakSatirKarakteri + ". satır " + SilinecekSatirKarakteri + ". satırı kapsar");
                        SatirSil(guncelTablo, j);
                        return IslemYapildi;
                    } else if (feedback == 2) { // 2. kapsar --- birinci satir silinir
                        /* HataBastir("feedback : 2\n"
                                + "duracak satir birinci satir :" + j
                                + "silinecek satir ikinci satir : " + i);*/
                        KalacakSatirKarakteri = satirIlkIndexStringiBul(guncelTablo, j);
                        SilinecekSatirKarakteri = satirIlkIndexStringiBul(guncelTablo, i);
                        AsamaBildirimi(KalacakSatirKarakteri + ". satır " + SilinecekSatirKarakteri + ". satırı kapsar");
                        SatirSil(guncelTablo, i);
                        
                        return IslemYapildi;
                    } else {
                        //HataBastir("birinci satir : :" + i + "ikinci satir :" + j + " --> Silinen olmadi");
                    }
                    
                }
            }
        }
        
        return false;
    }
    
    public int DiziUzunluguSatir(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }
    
    public int DiziUzunluguSutun(JTextField[][] guncelTablo) {
        return guncelTablo[0].length - silinenSutun;
    }
    
    public boolean SutunKapsamaFonk(JTextField[][] guncelTablo) {
        //  HataBastir("islem yapilacak sutun sayisi :" + (DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 2));
        for (int i = 1; i < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; i++) {
            for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {
                if (i != j) {
                    /* System.out.println("i --> last index önemli :" + i);
                    System.out.println("j --> last index önemli :" + j);*/
                    // HataBastir("karşilaştirilacak indexler i :" + i + " --- j :" + j);
                    int feedback = SutunKapsamalariIcin2SutunuKarsilastir(guncelTablo, i, j); // satirlari gönderir
                    if (feedback == 1) { // 1. kapsar --- ikinci satir silinir
                        /*  HataBastir("feedback : 1 \n"
                                + "duracak satir birinci satir :" + j
                                + "silinecek satir ikinci satir : " + i);
                        HataBastir("buradan 1 ");*/
                        AsamaBildirimi(i + ". sutun " + j + ". sutunu kapsar. --> " + i + " sutun silinecek");
                        SutunSil(guncelTablo, i);
                        return IslemYapildi;
                    } else if (feedback == 2) { // 2. kapsar --- birinci satir silinir
                        /*   HataBastir("feedback : 2\n"
                                + "duracak satir birinci satir :" + i
                                + "silinecek satir ikinci satir : " + j);
                        HataBastir("buradan 2 ");*/
                        AsamaBildirimi(j + ". sutun " + i + ". sutunu kapsar. --> " + j + " sutun silinecek");
                        SutunSil(guncelTablo, j);
                        
                        return IslemYapildi;
                    } else {
                        //HataBastir("birinci satir : :" + i + "ikinci satir :" + j + " --> Silinen olmadi");
                    }
                    
                }
            }
        }
        return false; // islem tamamsa true döner
    }
    
    public void Sutunlardaki_1_OlanSatirlariIsaretle(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {
        for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {//  j=3  -> 2. sutundaki 
            if (Integer.parseInt( // SA= en düşükse
                    guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1][j]
                            .getText().trim()) == enDusukSutunAgirligi) {
                for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {// satırdan başla

                    // i satirinin butun j lerini boya  sonra ortak noktasini yeşil yap 
                    if (guncelTablo[i][j].getText().trim().equals("1")) {
                        guncelTablo[i][0].setBackground(IsaretliSutunlarinIsaretlenecekSatirRengi);
                        guncelTablo[i][j].setBackground(KesisenIsaretliSutunVeSatirRengi);
                        
                    }
                    
                }
            }
        }
        
    }
    
    public boolean DusukSutunlariIsaretle(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {
        boolean sutunlarIsaretlendi = false;
        for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {
            if (Integer.parseInt(
                    guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1][j]
                            .getText().trim()) == enDusukSutunAgirligi) {
                for (int i = 0; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
                    if (!guncelTablo[i][j].getText().trim().equals("0")) {
                        guncelTablo[i][j].setBackground(DusukSutunAgirligiRengi);
                        sutunlarIsaretlendi = true;
                    }
                }
            }
        }
        return sutunlarIsaretlendi;
    }
    
    public void SatirAgirliklariniHesapla(JTextField[][] guncelTablo, int enDusukSutunAgirligi) {
        
        double toplam = 0;
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            toplam = 0;
            if (guncelTablo[i][0].getBackground() == IsaretliSutunlarinIsaretlenecekSatirRengi) {
                for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {
                    if (Integer.parseInt(guncelTablo[i][j].getText().trim()) == 1) //enDusukSutunAgirligi ile sutun ağirliği arasinda fark olduğunu sanmiyorum ama diğer türlü yapacam
                    {
                        double sutunAgirligi = Double.parseDouble(guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1][j].getText().trim());
                        //   HataBastir("i : " + i + " Eklenen sayilar " + (1 / sutunAgirligi));
                        toplam += 1 / sutunAgirligi;
                    }
                    
                }
                toplam *= DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 2;
                if (toplam != 0) {
                    ;
                    //guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1].setText();

                    String ToplamText = new DecimalFormat("#.###").format(toplam);//Double.toString(toplam);
                    ToplamText = ToplamText.replace(',', '.');
                    guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1].setText(ToplamText);
                    //   HataBastir("Sonuç :" + toplam);
                }
            }
            //  HataBastir("toplam : " + toplam + " / * " + (DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 2));

        }
        
    }
    
    public void EndusukSatirAgiriniSil(JTextField[][] guncelTablo) {
        //HataBastir("En düşük satır ağırlığı silinecek");
        int dusukSatirAgirininIndexi = 0;
        double satirAgirligiDusukolanSecilecek = Double.MAX_VALUE;
        double satirAgirligi = 0;
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            if (!guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1].getText().trim().equals("")) {
                satirAgirligi = Double.parseDouble(guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1]
                        .getText().
                        trim());
                if (satirAgirligiDusukolanSecilecek > satirAgirligi) {
                    
                    satirAgirligiDusukolanSecilecek = satirAgirligi;
                    dusukSatirAgirininIndexi = i;
                    //HataBastir("Şuanki Seçilen : " + dusukSatirAgirininIndexi);
                }
            } else {
                System.out.println(i + "-) son indexte : " + guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1].getText().trim().equals(""));
            }
        }
        AsamaBildirimi("Satır Ağırlıkları Hesaplandı." + dusukSatirAgirininIndexi + ". satırın ağırlığı en düşük satır ağırlığı olduğu için silinecek ");
        
        SatirSil(guncelTablo, dusukSatirAgirininIndexi);
    }
    
    public boolean SezgiselAlgoritmaFonk(JTextField[][] guncelTablo) {
        
        int dusukSutunAgirligi = enDusukSutunAgirligiDegeri(guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1]);
        if (DusukSutunlariIsaretle(guncelTablo, dusukSutunAgirligi)) {
            
            Sutunlardaki_1_OlanSatirlariIsaretle(guncelTablo, dusukSutunAgirligi);
            SatirAgirliklariniHesapla(guncelTablo, dusukSutunAgirligi);
            EndusukSatirAgiriniSil(guncelTablo);
            return IslemYapildi;
        }
        return false;
        
    }
    
    public void AsilTabladaKirmizilariSiyahaBoya() {
        
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < dizi[i].length; j++) {
                if (dizi[i][j].getBackground() == SiliniyorArkaPlanRenk) {
                    dizi[i][j].setBackground(SilinmisArkaPlanRenk);
                    dizi[i][j].setForeground(SilinmisinYaziRengi);
                    
                }
                
            }
        }
    }
    
    public String satirIlkIndexStringiBul(JTextField[][] guncelTablo, int index) {
        
        return guncelTablo[index][0].getText().trim();
    }
    
    public String sutundakiIlkIndexStringiBul(JTextField[][] guncelTablo, int index) {
        return guncelTablo[0][index].getText().trim();
    }
    
    public int satirIndexiBul(String satirIlkIndexIsmi) {
        JTextField[][] Dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int i = 0; i < Dizi.length; i++) {
            if (Dizi[i][0].getText().trim().equals(satirIlkIndexIsmi)) {
                return i;
            }
            
        }
        
        return 0;
    }
    
    public int sutunIndexiBul(String sutunIlkIndexIsmi) {
        //HataBastir("gelen String :"+sutunIlkIndexIsmi);
        JTextField[][] Dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        for (int j = 1; j < Dizi[0]
                .length; j++) {
            if (Dizi[0][j].getText().trim().equals(sutunIlkIndexIsmi)) {;
                //      HataBastir("Gelen Sutun ismi : " + sutunIlkIndexIsmi + "/ index :" + j + " dizideki ismi : " + Dizi[0][j].getText().trim());
                return j;
            }
        }
        
        return 0;
    }
    
    public void AsilTablodasilinenSutunuBoya(String sutunIlkIndexIsmi) {
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        // HataBastir("gelen isim : " + sutunIlkIndexIsmi);
        int sutun = sutunIndexiBul(sutunIlkIndexIsmi);
        //HataBastir("boyama aşaması : " + sutun);
        //  HataBastir("BOYAMA SON AŞAMA SUTUN :" + sutunIlkIndexIsmi + " -> " + sutun);
        for (int i = 0; i < dizi.length; i++) {
            if (dizi[i][sutun].getBackground() != SilinmisArkaPlanRenk) {
                //  HataBastir("sutun boyaniyor :" + i + " / j :" + sutun);
                dizi[i][sutun].setBackground(SiliniyorArkaPlanRenk);
                //   dizi[i][sutun].setForeground(SilinmisinYaziRengi);
            }
            
        }
    }
    
    public void AsilTablodaSilinenSatiriBoya(String SatirIlkIndexIsmi) {
        JTextField[][] dizi = pdg.getFrame().getAsilTable().getTxtMatris();
        int satir = satirIndexiBul(SatirIlkIndexIsmi);
        for (int i = 0; i < dizi[satir].length; i++) {
            if (dizi[satir][i].getBackground() != SilinmisArkaPlanRenk) {
                dizi[satir][i].setBackground(SiliniyorArkaPlanRenk);
            }
            
        }
    }
    
    public void SutunSil(JTextField[][] guncelTablo, int silinecekSutun) { // silme işlemini null atayarak yapacam
        double width, height; //x, y,
        
        AsilTablodasilinenSutunuBoya(sutundakiIlkIndexStringiBul(guncelTablo, silinecekSutun));//+ silinenSutun
        for (int i = 0; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo); i++) {
            for (int j = silinecekSutun; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {//guncelTablo[i].length - 1 - silinenSutun

                if (j != guncelTablo[i].length + 1) {
                    
                    guncelTablo[i][j].setText(guncelTablo[i][j + 1].getText());
                    width = guncelTablo[i][j + 1].getBounds().getWidth();
                    height = guncelTablo[i][j + 1].getBounds().getHeight();
                    
                    guncelTablo[i][j].setSize((int) width, (int) height);
                    guncelTablo[i][j].setBackground(guncelTablo[i][j + 1].getBackground());
                    if (j == DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 2) {//guncelTablo[i].length - 2 - silinenSutun
                        guncelTablo[i][j + 1].setVisible(false);
                        //     HataBastir("Silinen index -> i :" + i + " ; j:" + j);

                    }
                    
                }
                
            }
            
        }
        
        silinenSutun++;
        
    }
    int SAYAC = 0;
    
    public int DizideIslemYapilabilecekSatirSayisi(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }
    
    public int DizideIslemYapilabilecekSatirSayisi(JTextField[][] guncelTablo) {
        return guncelTablo.length - silinenSatir;
    }
    
    public int DizideIslemYapilabilecekSutunSayisi(JTextField[] guncelTablo) {
        return guncelTablo.length - silinenSutun;
    }
    
    public int DizideIslemYapilabilecekSutunSayisi(JTextField[][] guncelTablo) {
        return guncelTablo[0].length - silinenSutun;
    }
    
    public void MutlakSatirdaElemeIslemi(JTextField[][] guncelTablo, int sutun) {
        
        int satir = 0;
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo); i++) {//guncelTablo.length - silinenSatir
            if (Integer.parseInt(guncelTablo[i][sutun].getText()) == 1) {
                
                satir = i;
                break;
            }
        }
        for (int i = 1; i < DizideIslemYapilabilecekSutunSayisi(guncelTablo); i++) {//guncelTablo[satir].length - silinenSutun

            if (guncelTablo[satir][i].getText().trim().equals("1")) { // degeri 1 ise o sutunu sil
                
                String satirKarakteri = satirIlkIndexStringiBul(guncelTablo, satir);
                String sutunKarakteri = sutundakiIlkIndexStringiBul(guncelTablo, i);
                AsamaBildirimi(satirKarakteri + ". satir  Mutlak Satır olduğundan " + sutunKarakteri + ". sutunu siler");
                SutunSil(guncelTablo, i);
                
                i--;;
                
            }
        }
        String bosluk = " ";
        if (virgulEkle) {
            bosluk = ", ";
        } else {
            virgulEkle = true;
        }
        
        pdg.getLblKapsamalar().setText(pdg.getLblKapsamalar().getText().trim() + bosluk + guncelTablo[satir][0].getText().trim());
        SatirSil(guncelTablo, satir);
    }
    
    public boolean MutlakSatirdaElemeFonk(JTextField[][] guncelTablo) {
        
        for (int i = 1; i < guncelTablo[0].length - 1; i++) {
            if (guncelTablo[guncelTablo.length - silinenSatir - 1][i].getText().trim().equals("1")) {
                
                MutlakSatirdaElemeIslemi(guncelTablo, i);// i -> ağirliği = 1 olan sutun
                return IslemYapildi;
            }
            
        }
        return false;
    }
    
    public void MutlakSatirSutunToplaminiYaz(JTextField[][] guncelTablo) {
        // HataBastir("Sutun toplamlari yazilacak");

        int Sutundaki_1_lerinToplami = 0;
        int sutunSayisi = guncelTablo[0].length - 1;// -1 ağirlik kisminn alinmamasi için
        for (int sayac = 1; sayac < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; sayac++) {
            for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
                for (int j = 0; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {//guncelTablo[i].length - 1 - silinenSutun

                    if (sayac == j
                            && guncelTablo[i][j] != null
                            && guncelTablo[i][j].getText().trim().equals("1") && guncelTablo[i][j].getText().trim().equals("1")//&& Integer.parseInt(guncelTablo[i][j].getText()) == 1 // &&guncelTablo[i][j].getText().trim().equals("1")
                            ) {
                        Sutundaki_1_lerinToplami++;
                        
                    }
                }
            }
            
            guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1][sayac].
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
                    MutlakSatirAsilTablo_boyanacakSutunlariBelirle(dizi, i);// i -> ağirliği = 1 olan sutun

                }
            }
            
        }
    }
    
    public boolean MutlakSatirFonk(JTextField[][] guncelTablo) {
        
        MutlakSatirSutunToplaminiYaz(guncelTablo);
        
        if (MutlakSatirdaElemeFonk(guncelTablo)) {
            return true;
        }
        
        return false; // islem tamamsa true döner
    }
    
    public void diziBastir(JTextField[][] guncelTablo) {
        System.out.println("*******************************************");
        for (int i = 0; i < guncelTablo.length; i++) {
            
            if (!guncelTablo[i][0].getText().trim().equals(guncelTablo[i][guncelTablo[i].length - 1].getText().trim())) {
                for (int j = 0; j < guncelTablo[i].length; j++) {
                    
                }                
            }
        }
    }
    
    public void SatirAgirliklariniTemizle(JTextField[][] guncelTablo) {
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            guncelTablo[i][DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1].setText("");
        }
    }
    
    public void tablonunBoyalariniTemizle(JTextField[][] guncelTablo) {
        for (int i = 0; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo); i++) {
            for (int j = 0; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo); j++) {
                guncelTablo[i][j].setBackground(Color.WHITE);
            }
        }
    }
    
    public void islemlerIleri(JTextField[][] guncelTablo, int islem) {
        
        EskiTabloyuGuncelle(pdg.getFrame().getSuankiTable(),
                pdg.getFrame().getOncekiTable());
        TabloyaCekiDuzenVer(guncelTablo);
        MutlakSatirSutunToplaminiYaz(guncelTablo);
        
        switch (islem) {
            case MUTLAK_SATIR:
                if (MutlakSatirFonk(guncelTablo)) {
                    HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
                    return;
                }
            
            case SATIR_KAPSAMA:
                
                if (SatirKapsamaFonk(guncelTablo)) {
                    HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
                    return;
                }
            
            case SUTUN_KAPSAMA:
                if (SutunKapsamaFonk(guncelTablo)) {
                    HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
                    return;
                }
            case SEZGIZSEL_ALGORITMA:
                if (SezgiselAlgoritmaFonk(guncelTablo)) {
                    HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
                    return;
                } else {
                    HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
                    HataBastir("İşlemler Tamamlanmiştir");
                    pdg.getBtnGoResult().setEnabled(false);
                    pdg.getBtnStartOrNext().setEnabled(false);
                    
                }
        }
        MutlakSatirSutunToplaminiYaz(guncelTablo); // işlem bitince sutun ağirliklarini günceller

    }
    
    public void islemlerSonuc(JTextField[][] guncelTablo, int islem) {
        AsilTabladaKirmizilariSiyahaBoya();
        EskiTabloyuGuncelle(pdg.getFrame().getSuankiTable(),
                pdg.getFrame().getOncekiTable());
        TabloyaCekiDuzenVer(guncelTablo);
        
        SatirAgirliklariniTemizle(guncelTablo);
        switch (islem) {
            case MUTLAK_SATIR:
                if (MutlakSatirFonk(guncelTablo)) {
                    islemlerSonuc(guncelTablo, MUTLAK_SATIR);
                    return;
                }
            
            case SATIR_KAPSAMA:
                
                if (SatirKapsamaFonk(guncelTablo)) {
                    islemlerSonuc(guncelTablo, MUTLAK_SATIR);
                    return;
                }
            
            case SUTUN_KAPSAMA:
                if (SutunKapsamaFonk(guncelTablo)) {
                    islemlerSonuc(guncelTablo, MUTLAK_SATIR);
                    return;
                }
            case SEZGIZSEL_ALGORITMA:
                if (SezgiselAlgoritmaFonk(guncelTablo)) {
                    islemlerSonuc(guncelTablo, MUTLAK_SATIR);
                    return;
                } else {
                    HataBastir("İşlemler Tamamlanmiştir");
                    pdg.getBtnGoResult().setEnabled(false);
                    pdg.getBtnStartOrNext().setEnabled(false);
                    return;
                }
        }
        MutlakSatirSutunToplaminiYaz(guncelTablo);        
        
    }
    
    public void HicDegerGirilmeyenSatirSutunVarsaSil(JTextField[][] guncelTablo) {
        boolean SatirDaDegerVar = false;
        boolean SutunDaDegerVar = false;
        
        for (int i = 1; i < DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1; i++) {
            SatirDaDegerVar = false;
            for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {
                if (Integer.parseInt(guncelTablo[i][j].getText().trim()) == 1) {
                    SatirDaDegerVar = true;
                }
            }
            if (SatirDaDegerVar == false) {
                SatirSil(guncelTablo, i);
                HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
            }
        }
        MutlakSatirSutunToplaminiYaz(guncelTablo);
        
        for (int j = 1; j < DizideIslemYapilabilecekSutunSayisi(guncelTablo) - 1; j++) {
            if (Integer.parseInt(guncelTablo[DizideIslemYapilabilecekSatirSayisi(guncelTablo) - 1][j].getText().trim()) == 0) {
                SutunSil(guncelTablo, j);
                HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
            }
        }
    }
    
    public void TabloyaCekiDuzenVer(JTextField[][] guncelTablo) {
        tablonunBoyalariniTemizle(guncelTablo);
        HicDegerGirilmeyenSatirSutunVarsaSil(guncelTablo);
        SatirAgirliklariniTemizle(guncelTablo);
    }
    
    public int enDusukSutunAgirligiDegeri(JTextField[] sonsatir) {
        int dusukDeger = Integer.MAX_VALUE;
        for (int i = 1; i < DizideIslemYapilabilecekSutunSayisi(sonsatir) - 1; i++) {
            if (dusukDeger > Integer.parseInt(sonsatir[i].getText().trim())) {
                dusukDeger = Integer.parseInt(sonsatir[i].getText().trim());
            }
        }
        return dusukDeger;
    }
    
    public void SonrakiAdimaIlerlemeFonksiyonu(JTextField[][] guncelTablo) {
        
        islemlerIleri(guncelTablo, MUTLAK_SATIR);
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
                
                pdg.getBtnRestart().setEnabled(true);
                
                pdg.getFrame().getAsilTable().TablePanelMatrisOlustur(
                        Integer.parseInt(pdg.getTxtNDegeri().getText().trim()) + 2,
                        Integer.parseInt(pdg.getTxtMDegeri().getText().trim()) + 2);
                
                pdg.setAsilTabloDegerDegistirme(true);
                
            } else if (pdg.isAsilTabloDegerDegistirme() == true && e.getSource() == pdg.getBtnStartOrNext()) {
                pdg.getFrame().AddOtheTablesToFrame(pdg.getFrame().getAsilTable().getTxtMatris());
                pdg.setAsilTabloDegerDegistirme(false);
                pdg.getBtnStartOrNext().setText(btnNameNext);
            }
            if (pdg.isAsilTabloDegerDegistirme() == false) {
                
                if (e.getSource() == pdg.getBtnStartOrNext()) {
                    pdg.getBtnGoResult().setEnabled(true);
                    pdg.getFrame().getAsilTable().getPanelBaslik().setLocation(pdg.getFrame().getAsilTable().getWidth() / 3, pdg.getFrame().getAsilTable().getWidth() / 60);
                    pdg.getFrame().getOncekiTable().getPanelBaslik().setLocation(pdg.getFrame().getOncekiTable().getWidth() / 3, pdg.getFrame().getOncekiTable().getWidth() / 60);
                    pdg.getFrame().getSuankiTable().getPanelBaslik().setLocation(pdg.getFrame().getSuankiTable().getWidth() / 3, pdg.getFrame().getSuankiTable().getWidth() / 60);
                    AsilTabladaKirmizilariSiyahaBoya();
                    SonrakiAdimaIlerlemeFonksiyonu(pdg.getFrame().getSuankiTable().getTxtMatris());
                    
                } else if (e.getSource() == pdg.getBtnGoResult()) {
                    islemlerSonuc(pdg.getFrame().getSuankiTable().getTxtMatris(), MUTLAK_SATIR);
                    
                }
            }
            if (e.getSource() == pdg.getBtnRestart()) {
                
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
                            
                            if (ClickCounter[0] == i && ClickCounter[1] == j) {
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
                            }
                        }
                    }
                }
            }
            
        }
    }
    
    public void brimBoyama(Color color, JTextField alan) {
        alan.setBackground(color);
    }
    
    public void brimYazi(Color color, JTextField alan) {
        alan.setForeground(color);
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
                    oncedenBoyanmisArkaPlan = tabloMatrix.getTxtMatris()[i][j].getBackground();
                    oncedenBoyanmisYaziRengi = tabloMatrix.getTxtMatris()[i][j].getForeground();
                    brimBoyama(Color.GREEN, tabloMatrix.getTxtMatris()[i][j]);
                    brimYazi(SiyahYaziRengi, tabloMatrix.getTxtMatris()[i][j]);
                }
            }
        }
    }
    
    @Override/*
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
    }*/
    public void mouseExited(MouseEvent e) {
        for (int i = 0; i < tabloMatrix.getTxtMatris().length; i++) {
            for (int j = 0; j < tabloMatrix.getTxtMatris()[i].length; j++) {
                if (e.getSource() == tabloMatrix.getTxtMatris()[i][j]) {
                    
                    brimBoyama(oncedenBoyanmisArkaPlan, tabloMatrix.getTxtMatris()[i][j]);
                    brimYazi(oncedenBoyanmisYaziRengi, tabloMatrix.getTxtMatris()[i][j]);
                    
                }
            }
        }
    }
    
}
