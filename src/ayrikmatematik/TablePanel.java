package ayrikmatematik;

import Logic.Action;
import java.awt.Color;
import java.awt.Cursor;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TablePanel extends JPanel {

    public boolean DegerDegistir = true;
    JTextField txtMatris[][];
    //String matris[][];
    JLabel panelBaslik;
    int satir, sutun;
    int leftSpace = 30, topSpace = 40;
    int txtWidth = 30, txtHeight = 20;
    Frame frame;
    public Action action = new Action(this);

    public TablePanel(Frame frame, int x, int y, int JFrameWidth, int JFrameHeight) {
        this.add(getPanelBaslik());
        //this.setBackground(new Color(116, 185, 255));
        //this.setBounds(x, y, JFrameWidth / 2, JFrameHeight / 2);
        this.setBounds(x, y, JFrameWidth, JFrameHeight);
        this.setLayout(null);
        this.setBackground(Color.red);
        this.setVisible(true);
        setFrame(frame);

//        JOptionPane.showMessageDialog(null, "");
    }

    public void TablePanelMatrisOlusturSifirlaBastirma(int satir, int sutun) {
        txtMatris = new JTextField[satir][sutun];
        this.satir = satir;
        this.sutun = sutun;

        TabloSıfırla(txtMatris);

    }

    public void TablePanelMatrisOlustur(int satir, int sutun) {
        txtMatris = new JTextField[satir][sutun];
        this.satir = satir;
        this.sutun = sutun;

        TabloSıfırla(txtMatris);

        TabloyuBastir();
    }

    public void TabloSıfırla(JTextField tablo[][]) {
        for (int i = 0; i < tablo.length; i++) {
            for (int j = 0; j < tablo[i].length; j++) {
                tablo[i][j] = new JTextField();
                tablo[i][j].setEditable(false);
                tablo[i][j].setFocusable(false);
                tablo[i][j].addMouseListener(action);
                tablo[i][j].setBackground(Color.white);
                if (i == 0) {

                    tablo[i][j].setText(Integer.toString(j));
                } else if (j == 0) {

                    tablo[i][j].setText(new HarfHesaplama().SatiraYazilacak(i - 1));

                } else {
                    if (j == tablo[i].length - 1 || i == tablo.length - 1) {

                        tablo[i][j].setText(" ");
                    } else {
                        tablo[i][j].setText(Integer.toString(0));
                      /*if (new Random().nextInt(3) > 1) {
                            tablo[i][j].setText(Integer.toString(1));
                        } else {
                            tablo[i][j].setText(Integer.toString(0));
                        }*/
                    }
                }
                if (i > 0 && j > 0) {
                    tablo[i][j].setCursor(new Cursor(12));
                }
                if (i == 0 && j == tablo[i].length - 1) {
                    tablo[i][j].setText("ağırlık");
                } else if (i == tablo.length - 1 && j == 0) {
                    tablo[i][j].setText("SA");
                }
            }
        }
        tablo[0][0].setText("");
    }

    public void TabloyuBastir() {

        for (int i = 0; i < getTxtMatris().length; i++) {
            for (int j = 0; j < getTxtMatris()[i].length; j++) {
                if (j == getTxtMatris()[i].length - 1) {
                    getTxtMatris()[i][j].setBounds(leftSpace + j * txtWidth, topSpace + i * txtHeight, txtWidth * 2, txtHeight);
                } else {
                    getTxtMatris()[i][j].setBounds(leftSpace + j * txtWidth, topSpace + i * txtHeight, txtWidth, txtHeight);
                }
                this.add(getTxtMatris()[i][j]);
            }
        }

    }

    /* public void TabloyuBastir(JTextField tablo[][]) {

        for (int i = 0; i < tablo.length; i++) {
            for (int j = 0; j < tablo[i].length; j++) {
                tablo[i][j].setBounds(leftSpace + j * txtWidth, topSpace + i * txtHeight, txtWidth, txtHeight);
                this.add(tablo[i][j]);
            }
        }
   
    }*/
 /* public JTextField getTxtBirim() {
        if (txtBirim == null) {
            txtBirim = new JTextField();
            txtBirim.setEditable(false);
            txtBirim.setBackground(Color.cyan);
            txtBirim.setFocusable(true);

        }
        return txtBirim;
    }

    public void setTxtBirim(JTextField txtBirim) {
        this.txtBirim = txtBirim;
    }
     */
    public Frame getFrame() {
        if (frame == null) {
            frame = new Frame();
        }
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public JTextField[][] getTxtMatris() {
        return txtMatris;
    }

    public void setTxtMatris(JTextField[][] txtMatris) {
        this.txtMatris = txtMatris;
    }

    public JLabel getPanelBaslik() {
        if (panelBaslik == null) {
            panelBaslik = new JLabel();
            panelBaslik.setBounds(0, 0, 150, 30);

        }
        return panelBaslik;
    }

    public void setPanelBaslik(JLabel panelBaslik) {
        this.panelBaslik = panelBaslik;
    }

}
