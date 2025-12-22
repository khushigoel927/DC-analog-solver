package com.kg.dc_analog_solver;

class VoltageSource extends Element {
    double voltage;
    VoltageSource(int n1, int n2, double voltage) {
        super(n1, n2);
        this.voltage = voltage;
    }
}
