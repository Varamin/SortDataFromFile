# SortDataFromFile
Сортировка данных из файла методом вставки

# Запуск
Для выполнения программы необходимо в консоле выполнить команды:
  * Скомпилировать *javac -sourcepath ./src -d ./out src/main/java/Main.java*
  * Запустить *java -classpatch ./out main.Java.Main **'directory'** **'optional parameters'***  
  где:  
  **'directory'** - обязательный параметр. Директория с обрабатываемыми файлами  
  **'optional parameters'** - необязательные параметры. При запуске можно указать ряд не обязательных параметров:  
  *--out-prefix=* префикс для имени файлов с сортированными данными, по умолчанию *sorted_*  
  *--content-type=* тип данных содержащихся в указанной <directory>, по умолчанию *i*  
  *--sort-mode=* тип выполняемой сортировки. По умолчанию *a*  
  Например: *java -classpatch ./out main.Java.Main C:\files --content-type=s --out-prefix=new_ --sort-mode=d*  
# Сборка программы
Для упаковки программы в jar файл, необходимо выполнить команду *jar cmf manifest.mf sort-it.jar -C out .*  
Выполнение: *java -jar sort-it.jar C:\files*
