package com.korobko;

import java.sql.SQLException;

/**
 *     Разработать программу, обеспечивающую ввод и редактирование
 * информации об объектах в соответствии с заданной предметной областью.
 * Информация об объектах должна храниться в отдельной базе данных. Доступ к
 * данным осуществляется с использованием средств JDBC или ODBC.
 *     Программа не требует создания пользовательского интерфейса. Тестирование
 * работоспособности программы осуществляется на основе сценариев,
 * демонстрирующих возможности программы.
 *     Сведения об объектах хранятся в таблицах базы данных. Чтение и
 * редактирование данных осуществляется при помощи запросов SQL.
 * Характеристики автоматизируемых объектов определяются студентом
 * самостоятельно. Обязательной характеристикой объекта является его уникальный
 * идентификатор. Уникальность идентификаторов при выполнении операций
 * добавления и редактирования объектов должна обеспечиваться средствами СУБД
 * или средствами разрабатываемой программы.
 * <p>
 *     Программа должна поддерживать выполнение следующих операций с
 * данными:
 * - добавление нового объекта
 * - изменение параметров существующего объекта
 * - удаление объекта
 * - поиск объектов по заданным критериям и вывод информации об
 *   объектах
 * <p>
 * Вариант 11
 * Предметная область    Файловая система
 * Объекты               Папки, Файлы
 * Примечание            Имеется множество папок (независимых друг от друга).
 *                       Для каждой папки определено множество файлов.
 *
 * @author Vova Korobko
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        FileSystem fileSystem = new FileSystem(Factory.getConnection());

        fileSystem.clearDatabase();

        fileSystem.createTables();

        fileSystem.addDirectory("dao");
        fileSystem.addDirectory("orm");

        fileSystem.addFile("", "Main.java", "10KB");
        fileSystem.addFile(null, "Main.java", "10KB");

        fileSystem.addFile("dao", "GenericDao.java", "15KB");
        fileSystem.addFile("dao", "AbstractDao.java", "15KB");
        fileSystem.addFile("dao", "FileDao.java", "15KB");
        fileSystem.addFile("dao", "DirectoryDao.java", "20KB");

        fileSystem.showFileSystem();

        fileSystem.updateFile("FileDao.java", "500KB");

        fileSystem.findFileByName("FileDao.java");

        fileSystem.deleteFile("FileDao.java");
        fileSystem.deleteDirectory("dao");

        fileSystem.showFileSystem();

        fileSystem.stop();
    }
}
