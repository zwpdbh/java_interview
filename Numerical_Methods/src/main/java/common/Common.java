package common;

import java.util.ArrayList;
import java.util.List;

public class Common {
    /**Helper function relate the relative specified approximation error to
     * the number of significant figures in the approximation
     * @param n which specify at least n significant figures*/
    public static double getApproximationErrorFromNumberOfSignificantFigures(int n) {
        return (0.5 * Math.pow(10, 2 - n)) * 0.01;
    }

    /**Given a x and number of items to approximate, returns the list of results of approximation:
     * e^x = 1
     * e^x = 1 + x
     * e^x = 1 + x + x^2/2
     * e^x = 1 + x + x^2/2 + x^3/3!
     * ...
     * e^x = 1 + x + ... + x^n/n!
     * It stores all the result into an array*/
    public static List<Double> approximatedE(double x, int numberOfItems) {
        List<Double> result = new ArrayList<Double>();
        for (int i = 0; i < numberOfItems; i++) {
            if (i == 0) {
                result.add(1.0);
            } else {
                result.add((Math.pow(x, i) / factorial(i)) + result.get(result.size() - 1));
            }
        }
        return result;
    }

    /**Use loop to compute the approximated E^(x) with:
     * @param x is the parameter E^(x)
     * @param errorCriteria is the target relative approximateError measured with by %.
     * @param maxLoopNumber is the max loop will run in case the target approximationError is too small*/
    public static double iterComputeApproximatedE(double x, double errorCriteria, double maxLoopNumber) {
        double result = 1;
        double prevousResult;
        double ea;
        for (int k = 1; k <= maxLoopNumber; k++) {
            prevousResult = result;
            result = result + Math.pow(x, k) / factorial(k);
            ea = (Math.abs(result - prevousResult) / result ) * 100;
            if (ea <= errorCriteria) {
                System.out.println("k = " + k);
                double errorTruth = Math.abs(Math.exp(1) - result) / Math.exp(1) * 100;
                System.out.println("ea = " + ea);
                System.out.println("et = " + errorTruth);
                break;
            }
        }
        return result;
    }

    public static double factorial(int n) {
        double result = 1.0;
        for (int i = 1; i <= n; i++ ) {
            result = result * i;
        }
        return result;
    }

    public static Double relativeTruthError(double etruth, double eApproximation) {
        return (Math.abs(eApproximation - etruth) / etruth);
    }

    public static Double relativeApproximationError(double currentApproximation, double previousApproximation) {
        return (Math.abs(currentApproximation - previousApproximation) / currentApproximation);
    }
}
