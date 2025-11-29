package com.kg.dc_analog_solver;

public class CurrentSource {
    public final int from; // current flows from -> to
    public final int to;
    public final double current; // amps

    public CurrentSource(int from, int to, double current) {
        this.from = from; this.to = to; this.current = current;
    }
}
