package com.kg.dc_analog_solver;

class CurrentSource extends Element {
    double current; // flows n1 → n2

    CurrentSource(int n1, int n2, double current) {
        super(n1, n2);
        this.current = current;
    }
}
