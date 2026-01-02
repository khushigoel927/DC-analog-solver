package com.kg.dc_analog_solver;

import java.util.Map;

abstract class Element {
    String name;
    Node n1, n2; // node number (0=ground)

    Element(String name, Node n1, Node n2) {
        this.name = name;
        this.n1 = n1;
        this.n2 = n2;
    }
    abstract void stamp(
            double[][] G,
            double[] I,
            Map<String, Integer> voltageSourceIndex
    );
    abstract double branchCurrent(CircuitSolution sol);
}
