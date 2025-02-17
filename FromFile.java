import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FromFile {

    private BufferedReader br;

    public FromFile(BufferedReader br) {
        this.br = br;
    }

    public Microunda nextLine() {
        if (br == null) {
            return null;
        }

        try {
            String line = br.readLine();
            if (line == null || line.isEmpty()) {
                return null;
            }

            String[] tokens = line.split(" ");
            if (tokens.length < 11) {
                throw new IllegalArgumentException("Invalid line format, expected at least 11 fields.");
            }

            return new Microunda(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5],
                    Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]), tokens[8], Integer.parseInt(tokens[9]), tokens[10]
            );
        } catch (IOException e) {
            throw new RuntimeException("Error reading line from Buffered Reader", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in input line", e);
        }
    }

    public List<Microunda> Lines() {

        List<Microunda> buf = new ArrayList<>();
        Microunda mc;
        while ((mc = nextLine()) != null) {
            buf.add(mc);
        }
        return buf;
    }
}
