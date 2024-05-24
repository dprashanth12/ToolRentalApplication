package com.rental;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ToolType {
    LADDER(1.99, true, true, false),
    CHAINSAW(1.49, true, false, true),
    JACKHAMMER(2.99, true, false, false);

    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;


    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}

