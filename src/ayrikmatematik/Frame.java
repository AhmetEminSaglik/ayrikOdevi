package ayrikmatematik;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frame {

    JFrame jFrame;
    PanelDegerGirme pdg;
    int width = 1050, height = 650;
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
        getAsilTable().setBackground(Color.cyan);
        getSuankiTable().setBackground(Color.ORANGE);
        getOncekiTable().setBackground(Color.red);

    }

    public JTextField[][] TabloKopyalama(JTextField[][] first, JTextField[][] second) {
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                second[i][j].setText(first[i][j].getText());
            }
        }
        System.out.println("kopyalama kontrol ");
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[i].length; j++) {
                System.out.println("second[" + i + "][" + j + "]" + second[i][j].getText());
                System.out.println("first[" + i + "][" + j + "]" + first[i][j].getText());

            }
        }

        return second;
    }

    public void AddOtheTablesToFrame(JTextField dizi[][]) {
        getOncekiTable().TablePanelMatrisOlustur(dizi.length, dizi[0].length);
        getSuankiTable().TablePanelMatrisOlustur(dizi.length, dizi[0].length);
        TabloKopyalama(dizi, getOncekiTable().getTxtMatris());
        TabloKopyalama(dizi, getSuankiTable().getTxtMatris());

        getOncekiTable().DegerDegistir = false;
        getSuankiTable().DegerDegistir = false;
 
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
            jFrame.setBounds(50, 25, width, height);

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
