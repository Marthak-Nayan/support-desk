package com.minisupportdesk.common.utils;


import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateRangeUtil {

    private DateRangeUtil() {
    }


    public static LocalDateTime getStartOfToday() {
        return LocalDate.now().atStartOfDay();
    }

    public static LocalDateTime getEndOfToday() {
        return LocalDate.now().plusDays(1).atStartOfDay();
    }

    public static LocalDateTime getStartOfWeek() {
        return LocalDate.now()
                .with(DayOfWeek.MONDAY)
                .atStartOfDay();
    }

    public static LocalDateTime getEndOfWeek() {
        return LocalDate.now()
                .with(DayOfWeek.SUNDAY)
                .plusDays(1)
                .atStartOfDay();
    }

}
