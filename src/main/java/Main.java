package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class Main {

    // Преобразование строк файла file в числовой список
    private static List<Integer> getListInt(File file) throws Exception {
        return Files.lines(Paths.get(file.getPath()))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    // Преобразование строк файла file в строковый список
    private static List<String> getListStr(File file) throws Exception {
        return Files.lines(Paths.get(file.getPath()))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    /*
     Многопоточная обработка файлов в папке folder с использованием ExecutorService
     typeData - тип содержимога файла. Доступные i - Integer, s - String
     typeSort - вид сортировки. Доступные a - ASC, d - DESC
     outPrefix - префикс к файлу с отсортированными данными
    */
    private static void processDirectoryExecutorService(Folder folder, String typeData, String typeSort, String outPrefix){

        ExecutorService service = Executors.newFixedThreadPool(3);

        for(File f : folder.getListFile()) {
            service.execute(() -> {
                if (!f.isFile()) {
                    return;
                }

                List listDataFile = null;
                try {
                    listDataFile = (typeData.equalsIgnoreCase("i")) ? getListInt(f) : getListStr(f);
                } catch (Exception e) {
                    System.out.println("Ошибка чтения файла: " + f.getName());
                    System.out.println(e.getMessage());
                }

                if (listDataFile != null && listDataFile.size() != 0) {
                    try {
                        if (typeSort.equalsIgnoreCase("a"))
                            InsertionSort.sortASC(listDataFile);
                        else if (typeSort.equalsIgnoreCase("d"))
                            InsertionSort.sortDESC(listDataFile);

                        File newFile = folder.createNewFile(outPrefix + f.getName());
                        FileWriter writer = new FileWriter(newFile.getPath(), false);
                        listDataFile.forEach(el -> {
                            try {
                                writer.write(el.toString() + "\r\n");
                            } catch (IOException e) {
                                System.out.println("Ошибка записи в файла: " + f.getName());
                                System.out.println(e.getMessage());
                            }
                        });
                        writer.flush();
                        System.out.println("Создан файл: " + newFile.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Файл: " + f.getName() + " не содержит данных или они не корректные");
                }
            });
        }

        try {
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
      Многопоточная обработка файлов в папке folder с использованием parallel у Stream API
      typeData - тип содержимога файла. Доступные i - Integer, s - String
      typeSort - вид сортировки. Доступные a - ASC, d - DESC
      outPrefix - префикс к файлу с отсортированными данными
     */
    static void processDirectoryStreamParallel(Folder folder, String typeData, String typeSort, String outPrefix){
        Arrays.stream(folder.getListFile()).parallel().forEach(f -> {
                    List listDataFile = null;
                    try {
                        listDataFile = (typeData.equals("i")) ? getListInt(f) : getListStr(f);
                    } catch (Exception e) {
                        System.out.println("Ошибка чтения файла: " + f.getName());
                        System.out.println(e.getMessage());
                    }

                    if (listDataFile != null) {
                        try {
                            if (typeSort.equals("a"))
                                InsertionSort.sortASC(listDataFile);
                            else if (typeSort.equals("d"))
                                InsertionSort.sortDESC(listDataFile);

                            File newFile = folder.createNewFile(outPrefix + f.getName());
                            FileWriter writer = new FileWriter(newFile.getPath(), false);
                            listDataFile.forEach(el -> {
                                try {
                                    writer.write(el.toString() + "\r\n");
                                } catch (IOException e) {
                                    System.out.println("Ошибка записи в файла: " + f.getName());
                                    System.out.println(e.getMessage());
                                }
                            });
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }


    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Не заданы параметры запуска.");
            System.out.println("Обязательные: Директория.");
            System.out.println("Опциональные: Префикс, Режим сортировки, Тип файла");
            return;
        }
        String pathDir = args[0];
        String outPrefix = "sorted_";
        String typeData = "i";
        String typeSort = "a";

        for (int i = 1; i < args.length; i++) {
            if (args[i].matches("^--out-prefix=\\S*")) {
                outPrefix = args[i].substring(args[i].indexOf('=') + 1);
            }
            if (args[i].matches("^--content-type=\\S*")) {
                typeData = args[i].substring(args[i].indexOf('=') + 1);
            }
            if (args[i].matches("^--sort-mode=\\S*")) {
                typeSort = args[i].substring(args[i].indexOf('=') + 1);
            }
        }

        if (!typeData.equalsIgnoreCase("i") && !typeData.equalsIgnoreCase("s")) {
            System.out.println("Задан не верный тип содержимого файла (Возможны i или s)");
            return;
        }
        if (!typeSort.equalsIgnoreCase("a") && !typeSort.equalsIgnoreCase("d")) {
            System.out.println("Задан не верный тип сортировки (Возможны a или d)");
            return;
        }


        Folder folder = new Folder(pathDir);

        if (folder.getDir().exists() && folder.getDir().isDirectory()) {
            processDirectoryExecutorService(folder, typeData, typeSort, outPrefix);
        } else {
            System.out.println("Не удалось найти директорию по указанному пути: " + folder.getDir());
        }


    }

}
