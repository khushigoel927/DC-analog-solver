package com.kg.dc_analog_solver;

public class VoltageSource {
    public final int positive; // node number (0 = ground)
    public final int negative;
    public final double voltage; // volts

    public VoltageSource(int positive, int negative, double voltage) {
        this.positive = positive; this.negative = negative; this.voltage = voltage;
    }
}
