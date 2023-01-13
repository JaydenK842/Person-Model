import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        ArrayList<String> list = new ArrayList<>();
        File selectedFile;
        String[] fields;
        String ID, FirstName, LastName, Title, rec;
        int YearOfBirth;

        try {
            File workingDirectory= new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while (reader.ready()) {
                    rec = reader.readLine();

                    list.add(rec);
                }
                reader.close();

                System.out.println("ID#       Firstname   Lastname   Title   YOB");
                System.out.println("============================================");

                for (String item : list) {
                    fields = item.split(",");

                    if (fields.length == 5) {
                        ID = fields[0].trim();
                        FirstName = fields[1].trim();
                        LastName = fields[2].trim();
                        Title = fields[3].trim();
                        YearOfBirth = Integer.parseInt(fields[4].trim());


                        System.out.printf("%-10s", ID);
                        System.out.printf("%-12s", FirstName);
                        System.out.printf("%-11s", LastName);
                        System.out.printf("%-8s", Title);
                        System.out.println(YearOfBirth);
                    }
                }
            } else {
                System.out.println("Failed to choose a file.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
