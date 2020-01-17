package com.azeevg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class TestStatisticsLogger {
    private static StatisticsLogger logger;

    public static StatisticsLogger getLogger() {
        return logger;
    }

    public static void setLogger(StatisticsLogger logger) {
        TestStatisticsLogger.logger = logger;
    }
}
