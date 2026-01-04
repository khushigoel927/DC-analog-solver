package com.kg.dc_analog_solver;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class CircuitSolverTest {
    @Test
    void testSingleResistorAndVoltageSource() {
        CircuitSolver solver = new CircuitSolver();
        solver.addVoltageSource("V1", "N1", "GND", 10.0);
        solver.addResistor("R1", "N1", "GND", 1000); // 1 kΩ
        CircuitSolution sol = solver.solve();
        assertEquals(10.0, sol.getNodeVoltage("N1"), 1e-9);
        assertEquals(0.01, sol.branchCurrent("R1"), 1e-9);
        assertEquals(0.1, sol.branchPower("R1"), 1e-9);
    }
    @Test
    void testParallelResistors() {
        CircuitSolver solver = new CircuitSolver();
        solver.addVoltageSource("V1", "N1", "GND", 12.0);
        solver.addResistor("R1", "N1", "GND", 1000);
        solver.addResistor("R2", "N1", "GND", 1000);
        CircuitSolution sol = solver.solve();
        assertEquals(12.0, sol.getNodeVoltage("N1"), 1e-9);
        assertEquals(0.012, sol.branchCurrent("R1"), 1e-9);
        assertEquals(0.012, sol.branchCurrent("R2"), 1e-9);
        assertEquals(-0.024, sol.voltageSourceCurrent("V1"), 1e-9);
    }
    @Test
    void testCurrentSourceOnly() {
        CircuitSolver solver = new CircuitSolver();
        solver.addCurrentSource("I1", "N1", "GND", 0.005); // 5 mA
        solver.addResistor("R1", "N1", "GND", 1000);
        CircuitSolution sol = solver.solve();
        assertEquals(-5.0, sol.getNodeVoltage("N1"), 1e-9);
        assertEquals(-0.005, sol.branchCurrent("R1"), 1e-9);
    }
    @Test
    void testTwoNodeVoltageDifference() {
        CircuitSolver solver = new CircuitSolver();
        solver.addVoltageSource("V1", "N1", "GND", 10.0);
        solver.addResistor("R1", "N1", "N2", 1000);
        solver.addResistor("R2", "N2", "GND", 1000);
        CircuitSolution sol = solver.solve();
        assertEquals(5.0, sol.getNodeVoltage("N2"), 1e-9);
        assertEquals(5.0, sol.getVoltageBetween("N1", "N2"), 1e-9);
    }
}