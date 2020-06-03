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

    public void EskiTabloyuGuncelle(JTextField[][] first, JTextField[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                second[i][j].setText(first[i][j].getText());
            }
        }
    }

    public boolean SatirKapsamaFonk(JTextField[][] guncelTablo) {
        return IslemYapıldı; // islem tamamsa true döner
    }

    public boolean SutunKapsamaFonk(JTextField[][] guncelTablo) {
        return IslemYapıldı; // islem tamamsa true döner
    }

    public boolean SezgiselAlgoritmaFonk(JTextField[][] guncelTablo) {
        return IslemYapıldı; // islem tamamsa true döner
    }

    public void MutlakSatirdaElemeIslemi(JTextField[][] guncelTablo) {
    }

    public boolean MutlakSatirFonk(JTextField[][] guncelTablo) {
        int Sutundaki_1_lerinToplami = 0;
        int sutunSayisi = guncelTablo[0].length - 1;// -1 ağırlık kısmınn alınmaması için
        for (int sayac = 1; sayac < sutunSayisi; sayac++) {
            for (int i = 1; i < guncelTablo.length - 1; i++) {
                for (int j = 0; j < guncelTablo[i].length - 1; j++) {
                    if (sayac == j
                            && Integer.parseInt(guncelTablo[i][j].getText()) == 1) {
                        Sutundaki_1_lerinToplami++;

                    }
                }
            }

            guncelTablo[guncelTablo.length - 1][sayac].setText(Integer.toString(Sutundaki_1_lerinToplami));
            Sutundaki_1_lerinToplami = 0;

        }
        for (int i = 1; i < guncelTablo[0].length - 1; i++) {
            if (Integer.parseInt(guncelTablo[guncelTablo.length - 1][i].getText()) == 1) {
                MutlakSatirdaElemeIslemi(guncelTablo);
                return IslemYapıldı;
            }

        }
        return false; // islem tamamsa true döner
    }

    public void islemler(JTextField[][] guncelTablo, int islem) {
        switch (islem) {
            case MUTLAK_SATIR:
                MutlakSatirFonk(guncelTablo);
                if (MutlakSatirFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }
            case SATIR_KAPSAMA:
                if (SatirKapsamaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }
            case SUTUN_KAPSAMA:
                if (SutunKapsamaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                }
            case SEZGIZSEL_ALGORITMA:
                if (SezgiselAlgoritmaFonk(guncelTablo)) {
                    return; // MutlakSatırda işlem yapıldıysa bitir
                } else {
                    HataBastir("İşlemler Tamamlanmıştır");
                }
        }
    }

    public void SonrakiAdimaIlerlemeFonksiyonu(JTextField[][] guncelTablo) {
        islemler(guncelTablo, MUTLAK_SATIR);
    }

    public void YeniTabloyuGuncelle() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (pdg != null) {
            System.out.println("tabloMatrix : " + tabloMatrix);
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
                System.out.println("geliiii");
                pdg.getFrame().AddOtheTablesToFrame(pdg.getFrame().getAsilTable().getTxtMatris());
                pdg.setAsilTabloDegerDegistirme(false);
                pdg.getBtnStartOrNext().setText(btnNameNext);
            } else if (pdg.isIlerle() == true //ilerle butonu açıksa her bastığında bir sonraki işleme geçecek
                    && e.getSource() == pdg.getBtnStartOrNext()) {
            }
            if (pdg.isAsilTabloDegerDegistirme() == false
                    && e.getSource() == pdg.getBtnStartOrNext()) {
                EskiTabloyuGuncelle(pdg.getFrame().getSuankiTable().getTxtMatris(),
                        pdg.getFrame().getOncekiTable().getTxtMatris());

                SonrakiAdimaIlerlemeFonksiyonu(pdg.getFrame().getSuankiTable().getTxtMatris());

            } else if (e.getSource() == pdg.getBtnRestart()) {

                pdg.getFrame().getjFrame().setVisible(false);
                new Frame();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (tabloMatrix != null && tabloMatrix.DegerDegistir) {
            System.out.println("action da  degerdegistir : " + tabloMatrix.DegerDegistir);

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

                    brimBoyama(Color.white, tabloMatrix.getTxtMatris()[i][j]);
                }
            }
        }
    }

}
