package org.example.lesson04;

import org.example.lesson04.exceptions.NegativeSideLengthException;
import org.example.lesson04.exceptions.NotTriangleException;
import org.example.lesson04.exceptions.ZeroSideLengthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleArea {

    private static final Logger logger = LoggerFactory.getLogger(TriangleArea.class);

    /**
     * Calculates area of a triangle using length of its 3 sides
     */
    public static double calculateArea(int a, int b, int c) {
        isTriangleValid(a, b, c);
        Double aSide = Double.valueOf(a);
        Double bSide = Double.valueOf(b);
        Double cSide = Double.valueOf(c);
        double p = (aSide + bSide + cSide)/2;
        return Math.sqrt(p * (p - aSide) * (p - bSide) * (p - cSide));
    }

    /**
     * Checks whether the 3 sides can form a triangle.
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean isTriangleValid(int a, int b, int c) {
        Double aSide = Double.valueOf(a);
        Double bSide = Double.valueOf(b);
        Double cSide = Double.valueOf(c);
        if((aSide < 0)  ||  (bSide < 0) ||  (cSide < 0)) {
            logger.error("Triangle has negative side length(s): {}, {}, {}.", a, b, c);
            throw new NegativeSideLengthException("Triangle sides can not have negative length");
        } else if((aSide == 0) || (bSide == 0) || (cSide == 0)) {
            logger.error("Triangle has zero side length(s): {}, {}, {}.", a, b, c);
            throw new ZeroSideLengthException("Triangle sides can not be equal to zero");
        } else if((aSide + bSide) <= cSide || (aSide + cSide) <= bSide || (bSide + cSide) <= aSide) {
            // The sum of any two sides must be greater than the other side
            logger.error("It is not a triangle in case of side length(s): {}, {}, {}.", a, b, c);
            throw new NotTriangleException("It is not a triangle");
        }
        return true;
    }
}
