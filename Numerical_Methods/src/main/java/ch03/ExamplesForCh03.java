package ch03;

import common.Common;

import java.util.ArrayList;
import java.util.List;

public class ExamplesForCh03 {

    public static void main(String[] args) {

        exampleErrorEstimatesForLterativeMethods();

        System.out.println();
        double estimatedResult = Common.iterComputeApproximatedE(1, 0.000001, 100);
        System.out.println("estimated_E = " + estimatedResult);
    }

    /**Example from example 3.2: Error Estimates for iterative methods
     * compute e^0.5 conforming to three significant figures*/
    private static void exampleErrorEstimatesForLterativeMethods() {
        // from 3 significant figures
        double errorS = Common.getApproximationErrorFromNumberOfSignificantFigures(3);
        System.out.println(errorS);

        List<Double> approximationResult = Common.approximatedE(0.5, 10);
        System.out.println(approximationResult);

        System.out.println("Terms\tResult\terror_truth\terror_approximation");
        List<Double> relativeTruthErrors = new ArrayList<Double>();
        for (int i = 1; i <= approximationResult.size(); i++) {
            relativeTruthErrors.add(Common.relativeTruthError(Math.exp(0.5), approximationResult.get(i-1)));
        }
        List<Double> relativeApproximationErrors = new ArrayList<Double>();
        for (int i = 0; i < relativeTruthErrors.size(); i++) {
            if (i == 0) {
                relativeApproximationErrors.add(-1.0);
            } else {
                relativeApproximationErrors.add(
                        Common.relativeApproximationError(approximationResult.get(i), approximationResult.get(i-1)));
            }
        }
        for (int i = 0; i < relativeApproximationErrors.size(); i++) {
            System.out.println((i+1) + "\t" + approximationResult.get(i) + "\t"
                    + relativeTruthErrors.get(i) * 100 + "%" + "\t"
                    + relativeApproximationErrors.get(i) * 100 + "%");
        }
    }
}
