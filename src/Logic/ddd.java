package Logic;

import ayrikmatematik.PanelDegerGirme;
import ayrikmatematik.TablePanel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Action implements ActionListener {
    
    boolean ilerle;
    PanelDegerGirme pdg;
    TablePanel AsilTable;
    TablePanel OncekiTable;
    TablePanel SuankiTable;
    boolean AsilTabloDegerDegistirme;
    String btnNameNext = "ilerle";
    
    public Action(PanelDegerGirme pdg) {
        this.pdg = pdg;
    }
    
    public Action(TablePanel AsilTable) {
        this.AsilTable = AsilTable;
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (pdg != null) {
            if (!pdg.getBtnStartOrNext().getText().equals(btnNameNext)) {
                ilerle = false;
                AsilTabloDegerDegistirme = true;
                HataBastir("if'e  girdi  (ilerle false):" + pdg.getBtnStartOrNext().getText().equals(btnNameNext));
            } else {
                
                HataBastir("artık ileri  = true oldu");
                
                ilerle = true;
                AsilTabloDegerDegistirme = false;
                HataBastir(" 1 ilerle :" + ilerle);
            }
            HataBastir("2 ilerle :" + ilerle);
            if (e.getSource() == pdg.getBtnOlustur()
                    && InputIntegerControl(pdg.getTxtNDegeri().getText().trim())
                    && InputIntegerControl(pdg.getTxtMDegeri().getText().trim())) {
                HataBastir("3 ilerle :" + ilerle);
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
                        Integer.parseInt(pdg.getTxtNDegeri().getText().trim()) + 1,
                        Integer.parseInt(pdg.getTxtMDegeri().getText().trim()) + 1);

                /*   SuankiTable = new TablePanel(pdg.getjFrame().getWidth() / 2, 0, 0, 0);
                pdg.getjFrame().add(SuankiTable);
                OncekiTable = new TablePanel(pdg.getjFrame().getWidth() / 2, pdg.getjFrame().getHeight() / 2, 0, 0);
                pdg.getjFrame().add(OncekiTable);*/
                //  System.out.println(pdg);
                ////  AsilTable = new TablePanel(pdg.getjFrame(), 0, pdg.getjFrame().getHeight() / 2, 0, 0);
                // System.out.println(AsilTable);
                //pdg.getjFrame().add(AsilTable);
                //   HataBastir("tablonun eklenmiş olması lazım");
                AsilTabloDegerDegistirme = true;
            } else if (AsilTabloDegerDegistirme == true
                    && e.getSource() == pdg.getBtnStartOrNext()) {
                HataBastir("4 ilerle :" + ilerle);
                pdg.getFrame().AddOtheTablesToFrame(Integer.parseInt(pdg.getTxtNDegeri().getText().trim()),
                        Integer.parseInt(pdg.getTxtMDegeri().getText().trim()));
                AsilTabloDegerDegistirme = false;
                pdg.getBtnStartOrNext().setText(btnNameNext);
            } else if (ilerle == true
                    && e.getSource() == pdg.getBtnStartOrNext()) {
                // işleler yapılacak

            }
            
        }
        HataBastir("5 ilerle :" + ilerle);
        if (ilerle == false && AsilTable != null) {
            HataBastir("6 ilerle :" + ilerle);
            for (int i = 0; i < AsilTable.getTxtMatris().length; i++) {
                for (int j = 0; j < AsilTable.getTxtMatris()[i].length; j++) {
                    if (e.getSource() == AsilTable.getTxtMatris()[i][j]) {
                        
                        if (Integer.parseInt(AsilTable.getTxtMatris()[i][j].getText()) == 1) {
                            AsilTable.getTxtMatris()[i][j].setText("0");
                        } else if (Integer.parseInt(AsilTable.getTxtMatris()[i][j].getText()) == 0) {
                            AsilTable.getTxtMatris()[i][j].setText("1");
                            
                        }
                    }
                }
            }
        }
    }
    
}
