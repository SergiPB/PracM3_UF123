import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Scanner;

public class Prac1M3 {
    static Scanner teclat = new Scanner(System.in);
    static String[] equips = new String[50];
    static int[][] puntuacions = new int[50][5];
    static int contador = 0;

    public static void main(String[] args) throws Exception {
        boolean sortir = false;
        lleguiFitxer();
        do {
            System.out.println("######## MENU ########");
            System.out.println("\n1.Llistar Equips");
            System.out.println("2.Afegir Equip");
            System.out.println("3.Modificar Equip");
            System.out.println("4.Lider i Cuer");
            System.out.println("5.Sortir");
            System.out.println("\n###################### ");

            int opcio = teclat.nextInt();

            switch (opcio) {
                case 1:
                    llistarEquips();
                    break;
                case 2:
                    afegiEquip();
                    break;
                case 3:
                    modificarEquip();
                    break;
                case 4:
                    liderIcuer();
                    break;
                case 5:
                    escriureFitxer();
                    sortir = true;
                    break;
                default:
                    System.out.println("Opcio no valida");
            }

        } while (!sortir);

    }

    static void lleguiFitxer() throws IOException {
        // Entrem al fitxer
        File fitxer = new File("Fitxers/Equips.txt");

        FileReader reader = new FileReader(fitxer);
        BufferedReader buffer = new BufferedReader(reader);

        // lleguim le linies de fitxer
        String linea;
        while ((linea = buffer.readLine()) != null) {

            int posSep = linea.indexOf(":");
            String nomEquip = linea.substring(0, posSep);
            String punt = linea.substring(posSep + 1);

            String[] value = punt.split(",");

            // escrivim en les arays
            for (int i = 0; i < value.length; i++) {
                puntuacions[contador][i] = Integer.parseInt(value[i]);
            }
            equips[contador] = nomEquip;

            contador++;

        }

    }

    static void llistarEquips() {
        System.out.println("LLISTAR EQUIPS");

        System.out.println("Equip: | Jugats | Guanyats | Empatats | Perdusts | Punts |");
        // llegim les arrys
        for (int i = 0; equips[i] != null; i++) {

            System.out.print("\n" + equips[i] + ":");

            for (int a = 0; a < puntuacions[i].length; a++) {
                System.out.print(" | " + puntuacions[i][a] + " |");
            }

        }
        System.out.println("");
    }

    static void afegiEquip() {
        System.out.println("AFEGIR EQUIPS");

        teclat.nextLine();
        // demanem el nom
        System.out.print("Nom:");
        String nom = teclat.nextLine();
        equips[contador] = nom;

        // demanem els partits jugats
        System.out.print("Jugats:");
        puntuacions[contador][0] = teclat.nextInt();

        // demanem els partits guanyats
        System.out.print("Guanyats:");
        int guanyats = teclat.nextInt();
        puntuacions[contador][1] = guanyats;

        // demanem els partits empatats
        System.out.print("Empatats:");
        int empatats = teclat.nextInt();
        puntuacions[contador][2] = empatats;

        // demanem els partits perduts
        System.out.print("Perduts:");
        puntuacions[contador][3] = teclat.nextInt();

        // fem el calcul des punts
        puntuacions[contador][4] = (guanyats * 3) + empatats;
        System.out.print("Puntuacio: " + puntuacions[contador][4]);

        System.out.println("");

    }

    static void modificarEquip() {
        System.out.println("MODIFICAR EQUIPS");

        System.out.println("Quin equip vols modifigar?");
        // llistem el equips que tenim
        int llistat = 1;
        for (int i = 0; equips[i] != null; i++) {
            System.out.println(llistat + ". " + equips[i]);

            llistat++;
        }
        int res = teclat.nextInt();
        int opsio = res - 1;

        System.out.println(equips[opsio] + ": " + puntuacions[opsio][0] + "," + puntuacions[opsio][1] + ","
                + puntuacions[opsio][2] + "," + puntuacions[opsio][3] + "," + puntuacions[opsio][4]);

        System.out.println("Vols modifigar el nom?");
        String mod = teclat.next();

        if (mod.equalsIgnoreCase("si")) {
            System.out.print("Nom: ");
            String nom = teclat.next();

            equips[opsio] = nom;
        }

        System.out.println("Vols modifigar partits jugats?");
        mod = teclat.next();

        if (mod.equalsIgnoreCase("si")) {
            System.out.print("Jugats: ");
            int part = teclat.nextInt();

            puntuacions[opsio][0] = part;
        }

        System.out.println("Vols modifigar partits guanyats?");
        mod = teclat.next();

        if (mod.equalsIgnoreCase("si")) {
            System.out.print("Guanyats: ");
            int part = teclat.nextInt();

            puntuacions[opsio][1] = part;
        }

        System.out.println("Vols modifigar partits empatats?");
        mod = teclat.next();

        if (mod.equalsIgnoreCase("si")) {
            System.out.print("Empatats: ");
            int part = teclat.nextInt();

            puntuacions[opsio][2] = part;
        }

        System.out.println("Vols modifigar partits perduts?");
        mod = teclat.next();

        if (mod.equalsIgnoreCase("si")) {
            System.out.print("Perduts: ");
            int part = teclat.nextInt();

            puntuacions[opsio][3] = part;
        }

        puntuacions[opsio][4] = (puntuacions[opsio][1] * 3) + puntuacions[opsio][2];

        // mostrem els canvis
        System.out.println("Resultat de la modificacio:");
        System.out.println(equips[opsio] + ": " + puntuacions[opsio][0] + " " + puntuacions[opsio][1] + " "
                + puntuacions[opsio][2] + " " + puntuacions[opsio][3] + " " + puntuacions[opsio][4]);
    }

    static void liderIcuer() {
        int max = puntuacions[0][4];

        String lider = equips[0];

        // buscem el lider (max punts)
        for (int i = 0; equips[i] != null; i++) {
            if (puntuacions[i][4] > max) {
                max = puntuacions[i][4];
                lider = equips[i];
            }
        }
        System.out.println("El lider es " + lider + " amb " + max + " punts");

        int min = puntuacions[0][4];

        String cuer = equips[0];

        // buscem el cuer (min punts)
        for (int i = 0; equips[i] != null; i++) {
            if (puntuacions[i][4] < min) {
                min = puntuacions[i][4];
                cuer = equips[i];
            }
        }
        System.out.println("El cuer es " + cuer + " amb " + min + " punts");
    }

    static void escriureFitxer() throws IOException {
        // entrem al fitxer per escriure
        FileWriter fitcher = new FileWriter("Fitxers/Equips.txt");
        BufferedWriter bf = new BufferedWriter(fitcher);
        PrintWriter escritor = new PrintWriter(bf);

        // escrivim les arrays al fitxer
        for (int i = 0; equips[i] != null; i++) {
            escritor.print(equips[i] + ":");

            for (int e = 0; e < puntuacions[i].length; e++) {

                escritor.print(puntuacions[i][e] + ",");

            }

            escritor.println("");

        }

        // tancem el fitxer
        escritor.close();
    }
}
