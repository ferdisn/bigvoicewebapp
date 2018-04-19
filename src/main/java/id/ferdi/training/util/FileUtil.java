package id.ferdi.training.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {


    public static void saveFile(String prmFileName, InputStream prmFile) {
        //harus cari cara supaya dinamis
        String folderName = "D:/data";
        Path store = Paths.get(folderName + "/" + prmFileName);
        try {
            Files.write(store, prmFile.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
