package ayrikmatematik;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Frame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final double screenSizeWidth = screenSize.getWidth();
    final double screenSizeHeight = screenSize.getHeight();

    JFrame jFrame;
    PanelDegerGirme pdg;
    //int width = (int) screenSizeWidth, height = (int) screenSizeHeight;
    int width = (int) (screenSizeWidth * 0.951), height = (int) (screenSizeHeight * 0.872);
    TablePanel AsilTable;
    TablePanel OncekiTable;
    TablePanel SuankiTable;
    int heightF, widthF;

    public Frame() {
        getjFrame();

    }

    public void AddPaneltoFrame() {
        heightF = getjFrame().getHeight() / 2;;
        widthF = getjFrame().getWidth() / 2;;
        getjFrame().add(getPdg());
        getjFrame().add(getAsilTable());
        getjFrame().add(getSuankiTable());
        getjFrame().add(getOncekiTable());
        getAsilTable().setBackground(new Color(85, 230, 193));
        getSuankiTable().setBackground(Color.ORANGE);
        getOncekiTable().setBackground(new Color(253, 114, 114));

    }

    public JTextField[][] TabloKopyalama(JTextField[][] first, JTextField[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                second[i][j].setText(first[i][j].getText());
            }
        }
        /*  System.out.println("kopyalama kontrol ");
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                System.out.println("second[" + i + "][" + j + "]" + second[i][j].getText());
                System.out.println("first[" + i + "][" + j + "]" + first[i][j].getText());

            }
        }*/

        return second;
    }

    public void AddOtheTablesToFrame(JTextField dizi[][]) {

        getOncekiTable().TablePanelMatrisOlustur(dizi.length, dizi[0].length);
        getSuankiTable().TablePanelMatrisOlustur(dizi.length, dizi[0].length);
        TabloKopyalama(dizi, getOncekiTable().getTxtMatris());
        TabloKopyalama(dizi, getSuankiTable().getTxtMatris());

        getOncekiTable().DegerDegistir = false;
        getSuankiTable().DegerDegistir = false;
        getAsilTable().DegerDegistir = false;
        getOncekiTable().getPanelBaslik().setText("Eski Tablo");
        getSuankiTable().getPanelBaslik().setText("GüncelTablo");
        getAsilTable().getPanelBaslik().setText("İlk Girilen Tablo");
        /*
        getOncekiTable().setVisible(false);
        getOncekiTable().TabloyuBastir();
        getOncekiTable().setVisible(true);*/

 /* getSuankiTable().setTxtMatris(dizi);
        getSuankiTable().setVisible(false);
        getSuankiTable().TabloyuBastir();
        getSuankiTable().setVisible(true);*/
        //        getSuankiTable().setTxtMatris(dizi);
    }

    public PanelDegerGirme getPdg() {
        if (pdg == null) {
            pdg = new PanelDegerGirme(this, 0, 0);
        }
        return pdg;
    }

    public void setPdg(PanelDegerGirme pdg) {
        this.pdg = pdg;
    }

    public TablePanel getAsilTable() {
        if (AsilTable == null) {
            AsilTable = new TablePanel(this, widthF, 0, widthF, heightF);

        }
        return AsilTable;
    }

    public void setAsilTable(TablePanel AsilTable) {
        this.AsilTable = AsilTable;
    }

    public TablePanel getOncekiTable() {
        if (OncekiTable == null) {
            OncekiTable = new TablePanel(this, widthF, heightF, widthF, heightF);

        }
        return OncekiTable;
    }

    public void setOncekiTable(TablePanel OncekiTable) {
        this.OncekiTable = OncekiTable;
    }

    public TablePanel getSuankiTable() {
        if (SuankiTable == null) {
            SuankiTable = new TablePanel(this, 0, heightF, widthF, heightF);

        }
        return SuankiTable;
    }

    public void setSuankiTable(TablePanel SuankiTable) {
        this.SuankiTable = SuankiTable;
    }

    public JFrame getjFrame() {
        if (jFrame == null) {
            jFrame = new JFrame("Ayrik Matematik / Rota - Sezgizel Algoritma");
            jFrame.setLayout(null);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setBounds(0, 0, width, height);
            jFrame.setResizable(false);
            AddPaneltoFrame();
            jFrame.setVisible(true);
        }

        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public int getWidth() {

        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
