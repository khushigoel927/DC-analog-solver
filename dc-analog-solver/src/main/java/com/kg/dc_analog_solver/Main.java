package com.kg.dc_analog_solver;
import com.kg.dc_analog_solver.CircuitSolver;
import com.kg.dc_analog_solver.CircuitSolution;
/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        CircuitSolver solver = new CircuitSolver();
        // Simple DC circuit
        // 5V source feeding two resistors, one branch with a current source
        solver.addVoltageSource("V1", "N1", "GND", 5.0);
        solver.addResistor("R1", "N1", "GND", 1000); // 1 kΩ
        solver.addResistor("R2", "N1", "N2", 2000); // 2 kΩ
        solver.addCurrentSource("I1", "N2", "GND", 0.002); // 2 mA
        CircuitSolution sol = solver.solve();
        System.out.println("Node Voltages");
        System.out.println("V(N1) = " + sol.getNodeVoltage("N1") + " V");
        System.out.println("V(N2) = " + sol.getNodeVoltage("N2") + " V");
        System.out.println("\nBranch Currents");
        System.out.println("I(R1) = " + sol.branchCurrent("R1") + " A");
        System.out.println("I(R2) = " + sol.branchCurrent("R2") + " A");
        System.out.println("I(V1) = " + sol.voltageSourceCurrent("V1") + " A");
        System.out.println("\nPower");
        System.out.println("P(R1) = " + sol.branchPower("R1") + " W");
        System.out.println("P(R2) = " + sol.branchPower("R2") + " W");
    }

}
