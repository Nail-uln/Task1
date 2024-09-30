package pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Ответ на запрос сущности
 */
@Setter
@Getter
@Builder
public class MyEntityResponse {

    /**
     * Поле: id
     */
    private String id;

    /**
     * Поле: Заголовок
     */
    private String title;

    /**
     * Поле: Верификация
     */
    private Boolean verified;

    /**
     * Поле: Важные числа
     */
    @SerializedName("important_numbers")
    private Integer[] importantNumbers;

    /**
     * Класс группы полей Дополнительно
     */
    @Setter
    @Getter
    public static class Addition {

        /**
         * Поле: Дополнительная информация
         */
        @SerializedName("additional_info")
        private String additionalInfo;

        /**
         * Поле: Дополнительное число
         */
        @SerializedName("additional_number")
        private Integer additionalNumber;

        public Addition(String additionalInfo, Integer additionalNumber) {
            this.additionalInfo = additionalInfo;
            this.additionalNumber = additionalNumber;
        }
    }

    /**
     * Поле: Дополнение
     */
    private Addition addition;
}
