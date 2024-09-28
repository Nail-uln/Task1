package helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс со вспомогательными методами
 */
public class Helpers {

    /**
     * Генерация случайным образом почтового индекса заданной длины
     * @param countSymbol -задаваемая длина почтового индекса
     * @return  почтовый индекс
     */
    public String generateRandomPostCode(int countSymbol) {
        StringBuilder postCode = new StringBuilder();
        while (postCode.length() < 10) {
            postCode.append((int) (Math.random() * 10));
        }
        return  postCode.toString();
    }

    /**
     * Метод, разбивающий строку на подстроки заданной длины
     *
     * @param str    строка которую требуется разбить на подстроки
     * @param length длина каждой подстроки
     * @return список подстрок
     */
    public List<String> splitStringByLength(String str, int length) {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < str.length(); i += length) {
            parts.add(str.substring(i, Math.min(i + length, str.length())));
        }
        return parts;
    }
}
