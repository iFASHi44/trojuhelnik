import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PraceSeSouborem {
    private static final String ODDELOVAC = ";";
    private List<Rozmery>listRozmeru = new ArrayList<>();
    public void vycti(File soubor) {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(soubor)));
            listRozmeru.clear();
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                String[] casti = radek.split(ODDELOVAC);
                int rozmerA = Integer.parseInt(casti[0]);
                int rozmerB = Integer.parseInt(casti[1]);
                int rozmerC = Integer.parseInt(casti[2]);
                Rozmery list = new Rozmery(rozmerA, rozmerB, rozmerC);
                listRozmeru.add(list);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
        public void zapis(File soubor){
            try {
                PrintWriter  printWriter = new PrintWriter(new BufferedWriter(new FileWriter(soubor)));
                for (Rozmery aktualniSeznam : listRozmeru){
                    printWriter.println(aktualniSeznam.getRozmerA() + ODDELOVAC + aktualniSeznam.getRozmerB() + ODDELOVAC + aktualniSeznam.getRozmerC());
                }
                printWriter.flush();
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }

        }

    public List<Rozmery> getListRozmeru() {
        return listRozmeru;
    }

    public void setListRozmeru(List<Rozmery> listRozmeru) {
        this.listRozmeru = listRozmeru;
    }
}
