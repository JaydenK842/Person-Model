import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        String[] fields;
        boolean over = false;
        Scanner sc = new Scanner(System.in);
        String info, fileName;

        while (!over) {
            info = SafeInput.getNonZeroLenString(sc, "Please enter data one person at a time (E to exit): ");
            if (info.equalsIgnoreCase("e")) {
                over = true;
            } else {
                data.add(info);
            }
        }

        fileName = SafeInput.getNonZeroLenString(sc, "What will be the name of the file?: ");

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try {
        OutputStream out =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(out));

        for(String rec : data) {
            fields = rec.split(",");

            if (fields.length == 5) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }

        }

        writer.close();
        System.out.println("Your file has been wrote!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}