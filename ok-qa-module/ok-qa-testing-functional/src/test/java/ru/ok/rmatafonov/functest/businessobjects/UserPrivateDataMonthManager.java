package ru.ok.rmatafonov.functest.businessobjects;

import java.util.HashMap;
import java.util.Map;

public class UserPrivateDataMonthManager {

    private Map<String, String> monthsOrigToChangedMap = new HashMap<>();
    private Map<String, Integer> monthsNumbersMap = new HashMap<>();

    public void addMonthsPair(Integer monthNumber, String monthOrig, String monthChanged) {
        monthsOrigToChangedMap.put(monthOrig, monthChanged);
        monthsNumbersMap.put(monthOrig, monthNumber);
    }

    public String getChangedMonth(String monthOrig) {
        return monthsOrigToChangedMap.get(monthOrig);
    }

    public Integer getMonthNumber(String monthOrig) {
        return monthsNumbersMap.get(monthOrig);
    }
}
