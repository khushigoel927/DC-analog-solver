package com.kg.dc_analog_solver;

import java.util.Map;

public class Resistor extends Element {
    double R;

    Resistor(String name, Node n1, Node n2, double R) {
        super(name, n1, n2);
        this.R = R;
    }

    @Override
    void stamp(double[][] G, double[] I, Map<String, Integer> voltageSourceIndex) {
        double g = 1.0 / R;
        int a = n1.id;
        int b = n2.id;
        //gnd node not included in matrix
        if (a != 0) G[a - 1][a - 1] += g;
        if (b != 0) G[b - 1][b - 1] += g;

        if (a != 0 && b != 0) {
            G[a - 1][b - 1] -= g;
            G[b - 1][a - 1] -= g;
        }
    }
    @Override
    double branchCurrent(CircuitSolution sol) {
        double v = sol.getVoltageBetween(n1.name, n2.name);
        return v /R;
    }
}

