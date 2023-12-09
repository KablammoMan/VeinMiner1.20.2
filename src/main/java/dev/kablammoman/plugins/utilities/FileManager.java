package dev.kablammoman.plugins.utilities;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {
    /**
     * A method to write or append data to a specified file
     *
     * @param path The path to the file that the operation is to be done on.
     * @param data The data to be written or appended to the file.
     * @param overwriteData A boolean to determine whether to overwrite or append data.
     * @exception RuntimeException When the file does not exist and cannot be created, the file is a directory, or the file cannot be opened.
     * */
    public static void writeFile(String path, String fileName, String data, boolean overwriteData) {
        try {
            File file = new File(path);
            if(file.mkdirs()) Bukkit.getLogger().info("Successfully Created Required Directories for " + path);
            FileWriter fileWriter = new FileWriter(path + File.separator + fileName, !overwriteData);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * A method to read data from a specified file
     *
     * @param path The path to the file that is to be read.
     * @return An ArrayList with each element containing one line of the file.
     * @exception RuntimeException When the specified wile cannot be found.
    */
    public static ArrayList<String> readFile(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            ArrayList<String> data = new ArrayList<>();
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
    /**
     * A method to delete a specified file or directory
     *
     * @param path The path to the file or directory to be deleted.
     * @param directory A boolean to determine if the path is a directory or not.
     * @return true if deletion was successful, false if otherwise.
    */
    public static boolean deleteFile(String path, boolean directory) {
        File file = new File(path);
        if (directory) {
            String[] fList = file.list();
            if (Objects.requireNonNull(fList).length == 0) return file.delete();
            for (String s : fList) {
                if (!deleteFile(path + "/" + s, false)) return false;
            }
        }
        return file.delete();
    }
}
