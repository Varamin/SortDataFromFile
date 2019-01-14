package main.java;

import java.io.File;

//Класс для работы с директорией
public class Folder {
    private File directory;

    Folder(String path) {
        directory = new File(path);
    }

    public File getDir() {
        return directory;
    }

    public File[] getListFile() {
        return directory.listFiles();
    }

    public File createNewFile(String nameFile) {
        return  new File(directory + "\\" + nameFile);
    }
}

