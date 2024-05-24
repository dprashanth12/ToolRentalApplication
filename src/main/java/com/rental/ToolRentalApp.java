package com.rental;

import java.time.LocalDate;

import com.rental.exceptions.InvalidDiscountException;
import com.rental.exceptions.InvalidRentalDayCountException;

public class ToolRentalApp {
    public static void main(String[] args) throws InvalidDiscountException, InvalidRentalDayCountException {
        Tool tool = new Tool("LADW", ToolType.LADDER, "Werner");
        RentalAgreement agreement = new RentalAgreement(tool, 3, LocalDate.of(2020, 7, 2), 10);
        agreement.printAgreement();
    }
}

