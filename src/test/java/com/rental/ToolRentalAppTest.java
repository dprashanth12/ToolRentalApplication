package com.rental;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalDate;

public class ToolRentalAppTest {

    @Test
    public void testRentalAgreementForLadder() {
        Tool tool = new Tool("LADW", ToolType.LADDER, "Werner");
        RentalAgreement agreement = new RentalAgreement(tool, 3, LocalDate.of(2020, 7, 2), 10);
        agreement.printAgreement();

        assertEquals("LADW", agreement.getTool().getCode());
        assertEquals(ToolType.LADDER, agreement.getTool().getType());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), agreement.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
        assertEquals(1.99, agreement.getDailyCharge(), 0.01);
        assertEquals(2, agreement.getChargeDays());
        assertEquals(3.98, agreement.getPreDiscountCharge(), 0.01);
        assertEquals(10, agreement.getDiscountPercent());
        assertEquals(0.40, agreement.getDiscountAmount(), 0.01);
        assertEquals(3.58, agreement.getFinalCharge(), 0.01);
    }

}
