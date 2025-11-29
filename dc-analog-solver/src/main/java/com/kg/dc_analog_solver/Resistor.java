package com.kg.dc_analog_solver;

public class Resistor {
    public final int n1; // node number (0 = ground)
    public final int n2;
    public final double resistance; // ohms

    public Resistor(int n1, int n2, double resistance) {
        this.n1 = n1; this.n2 = n2; this.resistance = resistance;
    }
}

