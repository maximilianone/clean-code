package com.epam.training.exercise2;

import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to increment date.
 */
@Slf4j
public final class DateUtil {
    /**
     * day.
     */
    private static final int DAY = 10;

    /**
     * month.
     */
    private static final int MONTH = 10;

    /**
     * year.
     */
    private static final int YEAR = 2014;

    private DateUtil() {
    }

    /**
     * Method to increment date.
     *
     * @param date      - date
     * @param direction - increment/decrement identifier
     */
    private static void increment(final Date date, final boolean direction) {
        final Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, direction ? 1 : -1);

        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        date.setTime(calendar.getTime().getTime());
    }

    /**
     * Main to create date.
     *
     * @param year  - year
     * @param month - month
     * @param day   - day
     * @return date
     */
    private static Date create(final int year, final int month, final int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * Main method to increment date.
     *
     * @param args - starting values
     */
    public static void main(final String[] args) {
        final Date date = new Date();
        increment(date, false);
        log.info(date.toString());

        log.info(DateUtil.create(YEAR, MONTH, DAY).toString());
    }

}
