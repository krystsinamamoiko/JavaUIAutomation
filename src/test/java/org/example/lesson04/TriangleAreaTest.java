package org.example.lesson04;

import org.example.lesson04.exceptions.NegativeSideLengthException;
import org.example.lesson04.exceptions.NotTriangleException;
import org.example.lesson04.exceptions.ZeroSideLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleAreaTest {

    private static final Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);
    private final String NEGATIVE_LENGTH_ERROR_MSG = "Triangle sides can not have negative length";
    private final String ZERO_LENGTH_ERROR_MSG = "Triangle sides can not be equal to zero";
    private final String NOT_TRIANGLE_ERROR_MSG = "It is not a triangle";

    @Test
    @DisplayName("Verify rectangular, obtuse, acute-angle and equilateral triangles area calculation")
    @Tag("positiveTestCase")
    public void differentTriangleTypesTest() {
        logger.debug("Verifying positive test scenario...");
        assertAll(() -> {
            // rectangular triangle - прямоугольный
            assertEquals(
                6,
                TriangleArea.calculateArea(3, 4, 5),
                "Triangle area calculation is incorrect for rectangular triangles!"
            );
            // obtuse triangle - тупоугольный
            assertEquals(
                2.905,
                round(TriangleArea.calculateArea(2, 3, 4), 3),
                "Triangle area calculation is incorrect for obtuse triangles!"
            );
            // acute-angle triangle - остроугольный
            assertEquals(
                1942.928,
                round(TriangleArea.calculateArea(66, 67, 68),3),
                "Triangle area calculation is incorrect for acute-angle triangles!"
            );
            // isosceles triangle - равнобедренный
            assertEquals(
                4.146,
                round(TriangleArea.calculateArea(3, 3, 5),3),
                "Triangle area calculation is incorrect for isosceles triangles!"
            );
            // equilateral triangle - равносторонний
            assertEquals(
                15.588,
                round(TriangleArea.calculateArea(6, 6, 6),3),
                "Triangle area calculation is incorrect for equilateral triangles!"
            );
        });
    }

    @ParameterizedTest
    @CsvSource({ "5, 2, 1", "2, 5, 1", "2, 1,5" })
    @DisplayName("Verify triangle area calculation in case of not triangle side length")
    @Tag("negativeTestCase")
    public void notTriangleTest(int a, int b, int c) {
        logger.debug("Verifying not triangle case...");
        Throwable exception = assertThrows(NotTriangleException.class, () -> {
            TriangleArea.calculateArea(a, b, c);
        });
        assertEquals(exception.getMessage(), NOT_TRIANGLE_ERROR_MSG);
    }

    @ParameterizedTest
    @CsvSource({ "-6, 6, 6", "6, -6, 6", "6, 6, -6", "-6, -6, -6" })
    @DisplayName("Verify triangle area calculation in case of negative side length")
    @Tag("negativeTestCase")
    public void negativeSideLengthTest(int a, int b, int c) {
        logger.debug("Verifying negative side length case...");
        Throwable exception = assertThrows(NegativeSideLengthException.class, () -> {
            TriangleArea.calculateArea(a, b, c);
        });
        assertEquals(exception.getMessage(), NEGATIVE_LENGTH_ERROR_MSG);
    }

    @ParameterizedTest
    @CsvSource({ "0, 6, 6", "6, 0, 6", "6, 6, 0", "0, 0, 0" })
    @DisplayName("Verify triangle area calculation in case of zero side length")
    @Tag("negativeTestCase")
    void zeroSideLengthTest(int a, int b, int c) {
        logger.debug("Verifying zero side length case...");
        Throwable exception = assertThrows(ZeroSideLengthException.class, () -> {
            TriangleArea.calculateArea(a, b, c);
        });
        assertEquals(exception.getMessage(), ZERO_LENGTH_ERROR_MSG);
    }

    @Test
    @DisplayName("Verify triangle area calculation in case of max allowable side length")
    @Tag("positiveTestCase")
    public void longSideTest() {
        logger.debug("Verifying max allowable side length case...");
        assertEquals(
            1.99691862125803904E18,
            TriangleArea.calculateArea(2147483647, 2147483647, 2147483647),
            "Triangle area calculation is incorrect in case of max allowable side length!"
        );
    }

    /**
     * Round the given value {a} to the given {number} decimal places.
     * @param a
     * @param number
     * @return
     */
    private double round(double a, int number) {
        return Math.round(a * Math.pow(10, number)) / Math.pow(10, number);
    }
}
