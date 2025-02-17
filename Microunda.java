public class Microunda {

    String model;
    String producator;
    int watt_putere;
    String timer_yn;
    String iluminare_yn;
    String culoare;
    double pret;
    double greutate;
    String tara_de_origine;
    int garantia_luni;
    String tip_mecanism;

    public Microunda(String model, String producator, int watt_putere,
                     String timer_yn, String iluminare_yn, String culoare,
                     double pret, double greutate, String tara_de_origine,
                     int garantia_luni, String tip_mecanism) {

        this.model = model;
        this.producator = producator;
        this.watt_putere = watt_putere;
        this.timer_yn = timer_yn;
        this.iluminare_yn = iluminare_yn;
        this.culoare = culoare;
        this.pret = pret;
        this.greutate = greutate;
        this.tara_de_origine = tara_de_origine;
        this.garantia_luni = garantia_luni;
        this.tip_mecanism = tip_mecanism;
    }

    public String toFileString() {
        return "\033[1m" + model + " " + producator + " " + watt_putere + " " + timer_yn + " " +
                iluminare_yn + " " + culoare + " " + pret + " " + greutate + " " +
                tara_de_origine + " " + garantia_luni + " " + tip_mecanism + "\033[0m";
    }

    public String toStringTable() {
        return String.format("\033[1m%-18s | %-14s | %-6d | %-5s | %-7s | %-8s | %-10.2f | %-13s | %-5d | %-12s | %.2f\033[0m",
                model, producator, watt_putere, timer_yn, iluminare_yn, culoare, greutate, tara_de_origine, garantia_luni, tip_mecanism, pret);
    }

    public String toString() {
        return "\033[1mModel: " + model + ", " + producator + ", " + watt_putere
                + ", " + timer_yn + ", " + iluminare_yn
                + ", " + culoare + ", " + greutate
                + ", " + tara_de_origine + ", " + garantia_luni
                + ", " + tip_mecanism + ", " + pret + ".\033[0m";
    }
}
