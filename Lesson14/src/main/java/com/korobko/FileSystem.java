package com.korobko;

import java.sql.*;

/**
 * @author Vova Korobko
 */
public class FileSystem {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public FileSystem(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();

    }

    public void createTables() throws SQLException {
        String dirSql = "CREATE TABLE directories(" +
                "dir_name VARCHAR(20) NOT NULL," +
                "PRIMARY KEY (dir_name))";
        String fileSql = "CREATE TABLE files(" +
                "file_name VARCHAR(20) NOT NULL," +
                "dir_name VARCHAR(20) NOT NULL," +
                "size VARCHAR(20) NOT NULL," +
                "PRIMARY KEY (file_name)," +
                "FOREIGN KEY (dir_name) REFERENCES directories(dir_name) ON DELETE CASCADE)";
    statement.executeUpdate(dirSql);
    statement.executeUpdate(fileSql);
    }

    public void clearDatabase() throws SQLException {
        String dirSql = "DROP TABLE IF EXISTS directories";
        String fileSql = "DROP TABLE IF EXISTS files";
        statement.executeUpdate(fileSql);
        statement.executeUpdate(dirSql);
    }

    public void stop() throws SQLException {
        if (statement != null) {
            statement.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }

    }

    public boolean addDirectory(String name) {
        String sql = "INSERT INTO directories (dir_name) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            System.out.println("Папка " + name + " успешно добавлена!");
            return true;
        } catch (SQLException e) {
            System.out.println("ОШИБКА! Папка " + name + " не добавлена!");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public boolean addFile(String directoryName, String fileName, String size) {
        String sql = "INSERT INTO files (file_name, dir_name, size) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, directoryName);
            preparedStatement.setString(3, size);
            preparedStatement.executeUpdate();
            System.out.println("Файл " + fileName + " успешно добавлен!");
            return true;
        } catch (SQLException e) {
            System.out.println("ОШИБКА! Файл " + fileName + " не добавлен!");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public boolean updateFile(String fileName, String size) {
        String sql = "UPDATE files SET size = ? WHERE file_name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, size);
            preparedStatement.setString(2, fileName);
            preparedStatement.executeUpdate();
            System.out.println("Файл " + fileName + " успешно обновлен!");
            return true;
        } catch (SQLException e) {
            System.out.println("ОШИБКА! Файл " + fileName + " не обновлен!");
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public void findFileByName(String name) {
        String sql = "SELECT * FROM files WHERE file_name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Найденные файлы:");
            while (resultSet.next()) {
                String fileName = resultSet.getString("file_name");
                String dir_Name = resultSet.getString("dir_name");
                String fileSize = resultSet.getString("size");
                System.out.println(String.format(" >> %s - %s - %s", fileName, dir_Name, fileSize));
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("Ошибка при получении файла " + name);
            System.out.println(" >> " + e.getMessage());

        }
    }

    public boolean deleteDirectory(String name) throws SQLException {
        String sql = "DELETE FROM directories WHERE dir_name = ?";
        this.preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1, name);
            boolean result = preparedStatement.execute();
            if (!result) {
                System.out.println("Папка " + name + " успешно удалена!");
                return true;
            } else {
                System.out.println("Папка " + name + " не найдена!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(
                    "ОШИБКА при удалении папки " + name);
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public boolean deleteFile(String name) throws SQLException {
        String sql = "DELETE FROM files WHERE file_name = ?";
        this.preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1, name);
            boolean result = preparedStatement.execute();
            if (!result) {
                System.out.println("Файл " + name + " успешно удален!");
                return true;
            } else {
                System.out.println("Файл " + name + " не найден!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(
                    "ОШИБКА при удалении файла " + name);
            System.out.println(" >> " + e.getMessage());
            return false;
        }
    }

    public void showFileSystem() {
        String sql = "(SELECT * FROM directories d LEFT JOIN files f ON d.dir_name = f.dir_name) UNION ALL (SELECT * FROM directories d RIGHT JOIN files f ON d.dir_name = f.dir_name WHERE d.dir_name IS NULL)";
        try {
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Файловая система:");
            while (rs.next()) {
                String dirName = rs.getString(1);
                String fileName = rs.getString(2);
                String dir_Name = rs.getString(3);
                String fileSize = rs.getString(4);
                System.out.println(String.format(" >> %s - %s - %s - %s", dirName, fileName, dir_Name, fileSize));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(
                    "ОШИБКА при получении файловой системы");
            System.out.println(" >> "+e.getMessage());
        }
    }

}
