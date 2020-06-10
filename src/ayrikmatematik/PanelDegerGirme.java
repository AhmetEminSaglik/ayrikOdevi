package ayrikmatematik;

import Logic.Action;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDegerGirme extends JPanel {

    JTextField txtNDegeri, txtMDegeri;
    JButton btnOlustur, btnRestart, btnStartOrNext, btnGoResult;
    JLabel lblKapsamalar, lblNDegeri, lblMDegeri;
    int spaceTop = 15, spaceLeft = 40;
    int spaceWidth = 50, spaceHeight = 10;

    int lblWidth = 100, lblHeight = 20;
    int txtWidth = 100, txtHeight = 20;
    int btnWidth = 150, btnHeight = 40;

    int WidthSayac = 1, HeightSayac = 1;
    public Action action = new Action(this);
    boolean AsilTabloDegerDegistirme = true;
    boolean ilerle = false;
    Frame Frame;

    public PanelDegerGirme(Frame frame, int x, int y) {

        setFrame(frame);

        this.setBackground(new Color(116, 185, 255));

        this.setBounds(x, 0, getFrame().getWidth() / 2, getFrame().getHeight() / 2);
        this.setLayout(null);

        this.setVisible(true);
        AddComponents();

    }

    public void AddComponents() {
        this.add(getLblNDegeri());
        this.add(getTxtNDegeri());
        this.add(getLblMDegeri());
        this.add(getTxtMDegeri());
        this.add(getBtnOlustur());
        this.add(getLblKapsamalar());
        this.add(getBtnRestart());
        this.add(getBtnStartOrNext());
        this.add(getBtnGoResult());

    }
 

    public JLabel getLblNDegeri() {

        if (lblNDegeri == null) {

            lblNDegeri = new JLabel("N Değeri");
            lblNDegeri.setBounds(spaceLeft, spaceTop, lblWidth, lblHeight);
        }

        return lblNDegeri;
    }

    public void setLblNDegeri(JLabel lblNDegeri) {
        this.lblNDegeri = lblNDegeri;
    }

    public JTextField getTxtNDegeri() {

        if (txtNDegeri == null) {

            txtNDegeri = new JTextField("");
            txtNDegeri.setBounds(spaceLeft + lblWidth,
                    spaceTop, txtWidth, txtHeight);

        }
        return txtNDegeri;
    }

    public void setTxtNDegeri(JTextField txtNDegeri) {
        this.txtNDegeri = txtNDegeri;
    }

    public JLabel getLblMDegeri() {
        if (lblMDegeri == null) {
            lblMDegeri = new JLabel("M Değeri");
            lblMDegeri.setBounds(spaceLeft, spaceTop + lblHeight + spaceHeight, lblWidth, lblHeight);
        }
        return lblMDegeri;
    }

    public void setLblMDegeri(JLabel lblMDegeri) {
        this.lblMDegeri = lblMDegeri;
    }

    public JTextField getTxtMDegeri() {
        if (txtMDegeri == null) {
            txtMDegeri = new JTextField("");
            txtMDegeri.setBounds(spaceLeft + lblWidth,
                    spaceTop + txtHeight + spaceHeight,
                    txtWidth, txtHeight);

        }
        return txtMDegeri;
    }

    public void setTxtMDegeri(JTextField txtMDegeri) {
        this.txtMDegeri = txtMDegeri;
    }

    public JButton getBtnOlustur() {

        if (btnOlustur == null) {
            btnOlustur = new JButton("Oluştur");
            btnOlustur.setBounds(spaceLeft + lblWidth + spaceWidth + txtWidth, spaceTop, btnWidth, btnHeight);
            btnOlustur.addActionListener(action);

        }
        return btnOlustur;
    }

    public void setBtnOlustur(JButton btnOlustur) {
        this.btnOlustur = btnOlustur;
    }

    public JButton getBtnRestart() {
        if (btnRestart == null) {
            btnRestart = new JButton("Baştan Başla");
            btnRestart.setBounds(spaceLeft, spaceTop + (lblHeight + spaceHeight) * 3, btnWidth, btnHeight);
            btnRestart.addActionListener(action);
            btnRestart.setEnabled(false);

        }
        return btnRestart;
    }

    public void setBtnRestart(JButton btnRestart) {
        this.btnRestart = btnRestart;
    }

    public JButton getBtnStartOrNext() {
        if (btnStartOrNext == null) {
            btnStartOrNext = new JButton("Başla");
            btnStartOrNext.setBounds(spaceLeft + btnWidth + spaceWidth,
                    spaceTop + (lblHeight + spaceHeight) * 3, btnWidth, btnHeight);
            btnStartOrNext.addActionListener(action);
            btnStartOrNext.setEnabled(false);

        }

        return btnStartOrNext;
    }

    public void setBtnStartOrNext(JButton btnStart) {
        this.btnStartOrNext = btnStart;
    }

    public JButton getBtnGoResult() {
        if (btnGoResult == null) {
            btnGoResult = new JButton("Sonuca Git");
            btnGoResult.setBounds(spaceLeft,
                    spaceTop + (lblHeight + spaceHeight) * 3 + btnHeight + spaceHeight, (int) (btnWidth * 2.5), btnHeight);
            btnGoResult.addActionListener(action);
            btnGoResult.setEnabled(false);

        }
        return btnGoResult;
    }

    public void setBtnGoResult(JButton btnGoResult) {
        this.btnGoResult = btnGoResult;
    }

    public JLabel getLblKapsamalar() {
        if (lblKapsamalar == null) {
            lblKapsamalar = new JLabel("Kapsamalar = ");
            lblKapsamalar.setBounds(spaceLeft, spaceTop + (lblHeight + spaceHeight) * 4 + btnHeight * 2 + spaceHeight, lblWidth*3, lblHeight);

        }

        return lblKapsamalar;
    }

    public void setLblKapsamalar(JLabel Kapsamalar) {
        this.lblKapsamalar = Kapsamalar;
    }

    public Frame getFrame() {
        if (Frame == null) {
            Frame = new Frame();

        }
        return Frame;
    }

    public void setFrame(Frame Frame) {
        this.Frame = Frame;
    }

    public boolean isAsilTabloDegerDegistirme() {
        return AsilTabloDegerDegistirme;
    }

    public void setAsilTabloDegerDegistirme(boolean AsilTabloDegerDegistirme) {
        this.AsilTabloDegerDegistirme = AsilTabloDegerDegistirme;
    }

    public boolean isIlerle() {
        return ilerle;
    }

    public void setIlerle(boolean ilerle) {
        this.ilerle = ilerle;
    }

}
