package ru.job4j.foodstore;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;

public class QualityControl {

    double getTimeDiffInPercent(LocalDateTime createDate, LocalDateTime expDate) {
        LocalDateTime now = LocalDateTime.now();
        double daysExp = DAYS.between(createDate, expDate);
        double daysLeft = DAYS.between(now, expDate);
        System.out.println("Срок годности: " + daysExp + " дней");
        /**TODO добавить метод валидации положительного значения срока годности
         */
        System.out.println("Осталось дней хранения: " + daysLeft + " дней");
        double result = 100 * (daysLeft / daysExp);
        System.out.println("Продукт испорчен на: " + result + " %");
        return result;
    }
}
