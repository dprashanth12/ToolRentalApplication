package com.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private double dailyCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");

    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, int discountPercent) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;

        this.dailyCharge = tool.getType().getDailyCharge();
        this.dueDate = checkoutDate.plusDays(rentalDays);

        calculateCharges();
    }

    private void calculateCharges() {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 1; i <= rentalDays; i++) {
            dates.add(checkoutDate.plusDays(i));
        }

        this.chargeDays = (int) dates.stream()
                .filter(date -> {
                    if (isHoliday(date) && !tool.getType().isHolidayCharge()) {
                        return false;
                    }
                    if (isWeekend(date) && !tool.getType().isWeekendCharge()) {
                        return false;
                    }
                    return tool.getType().isWeekdayCharge();
                })
                .count();

        this.preDiscountCharge = this.chargeDays * this.dailyCharge;
        this.discountAmount = this.preDiscountCharge * this.discountPercent / 100;
        this.finalCharge = this.preDiscountCharge - this.discountAmount;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private boolean isHoliday(LocalDate date) {
        int year = date.getYear();
        LocalDate independenceDay = LocalDate.of(year, 7, 4);
        if (independenceDay.getDayOfWeek().getValue() == 6) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek().getValue() == 7) {
            independenceDay = independenceDay.plusDays(1);
        }
        LocalDate laborDay = LocalDate.of(year, 9, 1)
                .with(java.time.temporal.TemporalAdjusters.firstInMonth(java.time.DayOfWeek.MONDAY));

        return date.equals(independenceDay) || date.equals(laborDay);
    }

    public void printAgreement() {
        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType().name());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(DATE_FORMAT));
        System.out.println("Due date: " + dueDate.format(DATE_FORMAT));
        System.out.println("Daily rental charge: $" + String.format("%.2f", dailyCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
        System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
    }
}

