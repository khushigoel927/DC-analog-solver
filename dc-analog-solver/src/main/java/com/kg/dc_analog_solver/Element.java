package com.kg.dc_analog_solver;

abstract class Element {
    int n1, n2; // node number (0=ground)

    Element(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
}
