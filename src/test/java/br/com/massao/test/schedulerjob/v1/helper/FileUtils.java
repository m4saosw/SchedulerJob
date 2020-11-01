package br.com.massao.test.schedulerjob.v1.helper;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String getFileNamePath(String fileName) {
        Path dir = Paths.get("src", "main", "resources", "massas");

        return dir + "\\" + fileName;
    }
}
