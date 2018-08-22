package common;

public class Common {
    /**Helper function relate the relative specified approximation error to
     * the number of significant figures in the approximation
     * @param n which specify at least n significant figures*/
    public static double specifiedApproximationErrorFrom(int n) {
        return (0.5 * Math.pow(10, 2 - n));
    }
}
