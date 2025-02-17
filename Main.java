import javax.swing.*;
import java.awt.*;

import static java.awt.Font.*;

public class Main extends JFrame {

    JTextField inputField;
    JButton submitButton;
    JLabel resultLabel;
    static Utils utils = new Utils();

    public Main() {

        // Setări ale ferestrei
        setTitle("Bine ai venit, utilizator!");
        setSize(600, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Titlul (sus)
        JLabel titleLabel = new JLabel("Alegeți din lista de mai jos operațiunea: ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(232, 232, 232));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Meniul
        JTextArea menu = new JTextArea (
               "1. Introducerea datelor unui cuptor nou cu microunde;\n" + "2. Afisarea datelor;\n" +
               "3. Afisarea celui mai ieftin produs;\n" + "4. Afisarea cuptoarelor produse intr-o anumita tara;\n" +
               "5. Afisarea pretului mediu al tuturor produselor;\n" +
               "6. Afisarea listei cu microunde de o culoare/greutate anumita,\n" + "   cuprinse intr-un interval de pret anumit;\n" +
               "7. Afisarea produselor Gorenje cu timer si iluminare interna;\n" + "8. Inchiderea programului.\n"
        );

        menu.setFont(new Font("Times New Roman", Font.BOLD, 20));
        menu.setForeground(Color.BLACK);
        menu.setBackground(Color.WHITE);
        menu.setEditable(false);
        menu.setMargin(new Insets(15, 10, 10, 10));
        add(menu, BorderLayout.CENTER);

        // Panel pentru input, buton și rezultat
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.WHITE);

        // Panel pentru input și buton
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(new Color(232, 232, 232)); // Setează culoarea de fundal a panoului
        inputPanel.setOpaque(true);

        // Creează JLabel pentru textul "Introduceți opțiunea:"
        JLabel inputLabel = new JLabel("Introduceți opțiunea:");
        inputLabel.setFont(new Font("Times New Roman", Font.BOLD, 15)); // Setează fontul
        inputLabel.setForeground(Color.BLACK); // Setează culoarea textului

        // Câmpul de input
        inputField = new JTextField(5);
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);
        inputField.setCaretColor(Color.BLACK);

        // Butonul "Executa"
        submitButton = new JButton("Executa");
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        submitButton.setBackground(new Color(255, 255, 255));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFocusPainted(false);

        // Adaugă componentele la panou
        inputPanel.add(inputLabel); // Adaugă JLabel cu fontul setat
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        // Adăugăm inputPanel în partea de sus a southPanel
        southPanel.add(inputPanel, BorderLayout.SOUTH);

        // Label pentru rezultat
        resultLabel = new JLabel("Rezultatul va aparea in consola...", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Times New Roman", BOLD, 20));
        resultLabel.setOpaque(true);
        resultLabel.setBackground(new Color(232, 232, 232));
        resultLabel.setForeground(Color.BLACK);
        southPanel.add(resultLabel, BorderLayout.NORTH);

        // Border pentru text
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));

        // Adăugăm southPanel în partea de jos a ferestrei
        add(southPanel, BorderLayout.SOUTH);

        // Ascultător pentru buton
        submitButton.addActionListener(e -> {

            String input = inputField.getText().trim();
            try {
                int option = Integer.parseInt(input);
                if (option < 1 || option > 8) {
                    resultLabel.setText("Optiune invalida. Alege intre 1 si 8.");
                    return;
                }

                switch (option) {
                    case 1:
                        resultLabel.setText("Ai ales: Introducerea datelor.");
                        utils.introducereDateNoi();
                        break;
                    case 2:
                        resultLabel.setText("Ai ales: Afisarea datelor.");
                        utils.outputToConsole();
                        break;
                    case 3:
                        resultLabel.setText("Ai ales: Cel mai ieftin produs.");
                        utils.outputMinPrice();
                        break;
                    case 4:
                        resultLabel.setText("Ai ales: Cuptoare dintr-o anumita tara.");
                        utils.outputManufactCountry();
                        break;
                    case 5:
                        resultLabel.setText("Ai ales: Pretul mediu al produselor.");
                        utils.arithmeticMeanOfPrices();
                        break;
                    case 6:
                        resultLabel.setText("Ai ales: Filtrare dupa culoare/greutate si pret.");
                        utils.outputPretIntervalAndCuloare_Greutate();
                        break;
                    case 7:
                        resultLabel.setText("Ai ales: Produse Gorenje cu timer si iluminare.");
                        utils.outputGorenjeMicrowaves();
                        break;
                    case 8:
                        resultLabel.setText("Ai ales: Inchiderea programului.");
                        dispose();
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Introduceti doar numere intre 1 si 8.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        utils.readMicrowaves();
        new Main();
    }
}