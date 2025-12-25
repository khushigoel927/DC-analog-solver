package com.kg.dc_analog_solver;

import java.util.Map;

public class CurrentSource extends Element {
    double current; // flows n1 → n2

    CurrentSource(String name, Node n1, Node n2, double current) {
        super(name, n1, n2);
        this.current = current;
    }
    void stamp(double[][] G, double[] I, Map<String, Integer> vsIndex) {
        int a = n1.id;
        int b = n2.id;
        //gnd node not included in matrix
        if (a != 0) I[a-1] -= current;
        if (b != 0) I[b-1] += current;
    }

}
