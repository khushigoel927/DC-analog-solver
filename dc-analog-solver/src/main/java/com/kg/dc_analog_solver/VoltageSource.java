package com.kg.dc_analog_solver;

import java.util.Map;

public class VoltageSource extends Element {
    double V;

    VoltageSource(String name, Node n1, Node n2, double V) {
        super(name, n1, n2);
        this.V = V;
    }

    @Override
    void stamp(double[][] G, double[] I, Map<String, Integer> vsIndex) {
        int row = G.length - vsIndex.size() + vsIndex.get(name);

        int a = n1.id;
        int b = n2.id;

        if (a != 0) {
            G[a-1][row] += 1;
            G[row][a-1] += 1;
        }
        if (b != 0) {
            G[b-1][row] -= 1;
            G[row][b-1] -= 1;
        }

        I[row] += V;
    }
}
