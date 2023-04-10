import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame{
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton aBACButton;
    private JButton lzeUdělatTrojúhelníkButton;
    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem otevrit;
    private JMenuItem ulozit;
    private int indexSeznamu = 0;
    private JFileChooser vyberSouboru = new JFileChooser();
    private List<Rozmery> rozmeryList = new ArrayList<>();

    public Form() {
        bar = new JMenuBar();
        setJMenuBar(bar);
        menu = new JMenu("sOUBOR");
        bar.add(menu);
        otevrit = new JMenuItem("Otevřít");
        ulozit = new JMenuItem("Uložit");
        menu.add(otevrit);
        menu.add(ulozit);
        otevrit.addActionListener(ActionListener -> otevirani());
        ulozit.addActionListener(ActionListener -> ukladani());
        aBACButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vypis();
            }
        });
        lzeUdělatTrojúhelníkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stranaA = textField1.getText();
                String stranaB = textField2.getText();
                String stranaC = textField3.getText();
                int a = (int) Double.parseDouble(stranaA);
                int b = (int) Double.parseDouble(stranaB);
                int c = (int) Double.parseDouble(stranaC);
                if (a + b > c && a + c > b && b + c > a) {
                    JOptionPane.showMessageDialog(panel, "Ze stran délek, A: " + a + ", B: " + b + ", C: " + c + " lze sestrojit trojúhelník");
                } else {
                    JOptionPane.showMessageDialog(panel, "Ze stran délek, A: " + a + ", B: " + b + ", C: " + c + " nelze sestrojit trojúhelník");
                }
            }
        });
    }

    public static void main(String[] args) {
        Form f = new Form();
        f.setContentPane(f.panel);
        f.setVisible(true);
        f.pack();
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void vypis(){
        textField2.setText(textField1.getText());
        textField3.setText(textField1.getText());
    }

    public void otevirani(){
        vyberSouboru.setFileFilter(new FileNameExtensionFilter("textové soubory", "txt"));
        int vysledek = vyberSouboru.showOpenDialog(this);
        if (vysledek == 0){
            PraceSeSouborem praceSeSouborem = new PraceSeSouborem();
            praceSeSouborem.vycti(vyberSouboru.getSelectedFile());
            rozmeryList = praceSeSouborem.getListRozmeru();
            indexSeznamu = 0;
            vypis();
        }
    }
    public void ukladani(){
        vyberSouboru.setFileFilter(new FileNameExtensionFilter("textové soubory", "txt"));
        int vysledek = vyberSouboru.showSaveDialog(this);
        if (vysledek == 0){
            PraceSeSouborem praceSeSouborem = new PraceSeSouborem();
            praceSeSouborem.setListRozmeru(rozmeryList);
            praceSeSouborem.zapis(vyberSouboru.getSelectedFile());
        }
    }
    }

