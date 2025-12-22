package com.kg.dc_analog_solver;

public class Resistor extends Element {
    double resistance;

    Resistor(int n1, int n2, double resistance) {
        super(n1, n2);
        this.resistance = resistance;
    }

    double conductance() {
        return 1.0 / resistance;
    }
}


