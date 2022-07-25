package com.epam.task.exceptions;

import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.DayOfWeek;

public class EuroDependencyInjectionTest {
    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names ={"THURSDAY", "TUESDAY"})
    public void testDayOfWeek(DayOfWeek day){
        assertTrue(day.toString().startsWith("T"));
    }
}
