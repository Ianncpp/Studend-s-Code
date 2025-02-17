import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    List<Microunda> microwaves = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    Scanner doubleInput = new Scanner(System.in);
    Scanner strInput = new Scanner(System.in);
    Font font = new Font("Times New Roman", Font.BOLD, 14);
    FromFile fromFile;
    {
        try {
            fromFile = new FromFile(new BufferedReader(new FileReader("C:\\Users\\Ianush\\Documents\\Java Repos\\Studiul.Individual.Nr.1\\src\\microunde.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void readMicrowaves(){
        if(fromFile == null) {
            System.out.println("\033[1m-> File-ul este gol. ");
            return;
        }
        this.microwaves = fromFile.Lines();
        System.out.println("\033[1m-> Datele citite cu succes! ");
    }

    private boolean writeToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Ianush\\Documents\\Java Repos\\Studiul.Individual.Nr.1\\src\\microunde.txt"))){
            for (Microunda micro : microwaves) {

                if (micro != null) {
                    bw.write(micro.toFileString());
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    void introducereDateNoi() {
        try {
            System.out.print("Model: ");
            String model = strInput.nextLine();
            System.out.print("Producător: ");
            String producator = strInput.nextLine();
            System.out.print("Putere (W): ");
            int putere = Integer.parseInt(strInput.nextLine());
            System.out.print("Are timer (da/nu): ");
            String areTimer = strInput.nextLine();
            System.out.print("Are iluminare internă (da/nu): ");
            String areIluminareInterna = strInput.nextLine();
            System.out.print("Culoare: ");
            String culoare = strInput.nextLine();
            System.out.print("Preț: ");
            double pret = Double.parseDouble(strInput.nextLine());
            System.out.print("Greutate (kg): ");
            double greutate = Double.parseDouble(strInput.nextLine());
            System.out.print("Țara de origine: ");
            String taraDeOrigine = strInput.nextLine();
            System.out.print("Garanție (luni): ");
            int garantie = Integer.parseInt(strInput.nextLine());
            System.out.print("Tip administrare (electronică/mecanică): ");
            String tipAdministrare = strInput.nextLine();

            microwaves.add(new Microunda(model, producator, putere, areTimer, areIluminareInterna, culoare, pret, greutate, taraDeOrigine, garantie, tipAdministrare));
            System.out.println("\033[1m-> Cuptor adăugat cu succes!\033[0m");

            if (!writeToFile()) {
                System.out.println("\033[1m-> Datele noi inscrise in file cu succes!\033[0m");
            } else {
                System.out.println("\033[1m-> Eroare scrierea in file.\033[0m");
            }

        } catch (Exception e) {
            System.out.println("\033[1mEroare la introducerea datelor: \033[0m" + e.getMessage());
        }
    }

    void outputToConsole() {

        try {
            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");
            System.out.println("|  # | Model                        | Producator         | W    | Timer | Lumina | Culoare       | Greutate  | Origine       | Garantie   | Pret    |");
            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");

            int i = 1;
            for (Microunda microunda : microwaves) {

                System.out.printf("| %-2d | %-28s | %-18s | %-4d | %-5s | %-6s | %-13s | %-9.2f | %-13s | %-10d | %-7.2f |\n", i,
                        microunda.model, microunda.producator, microunda.watt_putere, microunda.timer_yn, microunda.iluminare_yn,
                        microunda.culoare, microunda.greutate, microunda.tara_de_origine, microunda.garantia_luni, microunda.pret);
                i++;
            }

            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");
            System.out.println("\033[1m-> Afisare cu succes! \033[0m");

        } catch (Exception e) {

            System.out.printf("\033[1mEroare la afisare:\033[0m %s\n", e.getMessage());
        }
    }

    void outputManufactCountry() {

        System.out.print("\033[1m-> Introduceti tara de cautare: \033[0m");
        String country = strInput.nextLine();

        try {
            System.out.println("\033[1mMicroundele produse în aceeași țară: \033[0m");

            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");
            System.out.println("|  # | Model                        | Producator         | W    | Timer | Lumina | Culoare       | Greutate  | Origine       | Garantie   | Pret    |");
            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");

            boolean found = false;

            for (int i = 0; i < microwaves.size(); i++) {
                if (microwaves.get(i).tara_de_origine.equalsIgnoreCase(country)) {
                    found = true;
                    System.out.printf("| %-2d | %-28s | %-18s | %-4d | %-5s | %-6s | %-13s | %-9.2f | %-13s | %-10d | %-7.2f |%n", i,
                            microwaves.get(i).model, microwaves.get(i).producator, microwaves.get(i).watt_putere, microwaves.get(i).timer_yn, microwaves.get(i).iluminare_yn,
                            microwaves.get(i).culoare, microwaves.get(i).greutate, microwaves.get(i).tara_de_origine, microwaves.get(i).garantia_luni, microwaves.get(i).pret);
                }
            }

            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");

            if (!found) {
                System.out.println("\033[1m-> Nu s-au găsit cuptoare produse în țara specificată.\033[0m");
            } else {
                System.out.println("\033[1m-> Afișare cu succes!\033[0m");
            }

        } catch (NullPointerException n) {
            System.out.println("->\033-> Eroare la parcurgerea obiectelor: " + n.getMessage());
        }
    }

    void outputMinPrice() {
        double min = Integer.MAX_VALUE;
        String cuptorul = "";
        for (Microunda microunda : microwaves) {
            if (microunda.pret < min) {
                min = microunda.pret;
                cuptorul = microunda.model;
            }
        }
        System.out.println("\033[1m-> Microunda cu cel mai mic pret este: " + cuptorul + ", cu pretul de " + min + "\033[0m");
    }

    void arithmeticMeanOfPrices() {
        try {
            double sum = 0;
            for (Microunda microunde : this.microwaves) {
                sum += microunde.pret;
            }
            double medie = sum / this.microwaves.size();
            System.out.println("\033[1m-> Pretul mediu al tuturor produselor: " + (int)medie + "\033[0m");

        } catch (NullPointerException n) {
            System.out.println("\033[1mUn obiect din array este null.\033[0m");
        } catch (Exception e) {
            System.out.println("\033[1mEroare neprevazuta: \033[0m" + e.getMessage());
        }
    }

    void outputPretIntervalAndCuloare_Greutate() {

        System.out.print("\033[1m-> Introduceti intervalul de pret: \033[0m");
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.print("\033[1m-> Introduceti greutatea: \033[0m");
        double greutate = doubleInput.nextDouble();
        System.out.print("\033[1m-> Introduceti culoarea: \033[0m");
        String culoare = strInput.nextLine().toLowerCase();

        try {

            System.out.println("\033[1mMicroundele/microunda ce satisfac conditia: \033[0m");

            System.out.println();
            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");
            System.out.println("|  # | Model                        | Producator         | W    | Timer | Lumina | Culoare       | Greutate  | Origine       | Garantie   | Pret    |");
            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");

            boolean found = false;

            for (Microunda micro : microwaves) {
                if (micro.pret >= a && micro.pret <= b) {

                    boolean greutateMatch = Math.abs(micro.greutate - greutate) < 0.001;
                    boolean culoareMatch = micro.culoare.toLowerCase().equals(culoare);

                    if (greutateMatch && culoareMatch) {
                        found = true;
                        System.out.println("\033[1mModel: " + micro.model + ", pret: " + micro.pret + ", culoare: "
                                + micro.culoare + ", greutate: " + micro.greutate + "\033[0m");
                    }
                }

            }

            System.out.println("+----+------------------------------+--------------------+------+-------+--------+---------------+-----------+---------------+------------+---------+");

            if (!found) {
                System.out.println("\033[1m-> Nu s-au găsit cuptoare care să satisfacă condițiile.\033[0m");
            } else {
                System.out.println("\033[1m-> Afișare cu succes!\033[0m");
            }

        } catch (Exception e) {
            System.out.println("\033[1mEroare: \033[0m" + e.getMessage());
        }
    }

    void outputGorenjeMicrowaves() {

        boolean found = false;
        try {
            for (Microunda micro : microwaves) {
                if ("electronic".equalsIgnoreCase(micro.tip_mecanism) && "Gorenje".equalsIgnoreCase(micro.producator) &&
                        "da".equalsIgnoreCase(micro.iluminare_yn) && "da".equalsIgnoreCase(micro.timer_yn)) {
                    found = true;

                    if (found) {
                        System.out.println("\033[1mMicroundele produse de Gorenje, cu iluminare interna, timer si mecanism electronic: \033[0m");
                        System.out.println("Model: " + micro.model + ", Tara de origine: " + micro.tara_de_origine +
                                ", Iluminare interna: " + micro.iluminare_yn + ", Prezenta timer-ului: " + micro.timer_yn);
                    }
                }
            }
            if (!found) {
                System.out.println("\033[1mNu s-au gasit microunde Gorenje care sa indeplineasca toate criteriile.\033[0m");
            }

        } catch (NullPointerException e) {
            System.out.println("\033[1mUn obiect din lista este null.\033[0m");
        } catch (Exception e) {
            System.out.println("\033[1mEroare: \033[0m" + e.getMessage());
        }
    }

    private static String[] getStrings(String linie) {
        return linie.trim().split("\\s");
    }
}
