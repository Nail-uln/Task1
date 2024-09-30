package pojo;

import com.google.gson.annotations.SerializedName;
import helpers.ConfigProperties;
import helpers.Helpers;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Сущность
 */
@Setter
@Getter
@Builder
public class MyEntity {

    /**
     * Поле: Заголовок
     */
    @Builder.Default
    private String title = "%s-%s".formatted(ConfigProperties.getProperty("entity.title"), new Helpers().generateRandomPostCode(2));

    /**
     * Поле: Верификация
     */
    @Builder.Default
    private Boolean verified =  Boolean.valueOf(ConfigProperties.getProperty("entity.verify"));

    /**
     * Поле: Важные числа
     */
    @SerializedName("important_numbers")
    @Builder.Default
    private Integer[] importantNumbers = {Integer.valueOf(new Helpers().generateRandomPostCode(2)),
            Integer.valueOf(new Helpers().generateRandomPostCode(2)),
            Integer.valueOf(new Helpers().generateRandomPostCode(1)),
            Integer.valueOf(new Helpers().generateRandomPostCode(2)),
            Integer.valueOf(new Helpers().generateRandomPostCode(2))};

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
    @Builder.Default
    private Addition addition = new Addition(ConfigProperties.getProperty("entity.additional_info"), Integer.valueOf(new Helpers().generateRandomPostCode(3)));
}
