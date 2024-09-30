package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}