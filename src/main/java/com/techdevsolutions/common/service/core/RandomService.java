package com.techdevsolutions.common.service.core;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class RandomService {
    public static Integer getRandomIntegerInRange(int min, int max) {
        return new RandomDataGenerator().nextInt(min, max);
    }

    public static Long getRandomLongInRange(long min, long max) {
        return new RandomDataGenerator().nextLong(min, max);
    }

    public static Double getRandomDoubleInRange(double min, double max) {
        return new RandomDataGenerator().nextUniform(min, max);
    }

    public static String getRandomStringInRange(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
