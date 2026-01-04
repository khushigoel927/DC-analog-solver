package com.kg.dc_analog_solver;
import com.kg.dc_analog_solver.CircuitSolver;
import com.kg.dc_analog_solver.CircuitSolution;
/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        CircuitSolver solver = new CircuitSolver();
        // Build a DC circuit
        solver.addVoltageSource("V1", "N1", "GND", 10.0);
        solver.addResistor("R1", "N1", "N2", 1000);   // 1 kΩ
        solver.addResistor("R2", "N2", "GND", 2000);  // 2 kΩ
        solver.addCurrentSource("I1", "N2", "GND", 0.002); // 2 mA
        // Solve the circuit
        CircuitSolution sol = solver.solve();

        System.out.println("Node Voltages");
        System.out.println("V(N1) = " + sol.getNodeVoltage("N1") + " V");
        System.out.println("V(N2) = " + sol.getNodeVoltage("N2") + " V");
        System.out.println("V(GND) = " + sol.getNodeVoltage("GND") + " V");

        System.out.println("\nVoltage Differences");
        System.out.println("V(N1, N2) = " + sol.getVoltageBetween("N1", "N2") + " V");
        System.out.println("V(N2, GND) = " + sol.getVoltageBetween("N2", "GND") + " V");

        System.out.println("\nBranch Currents");
        System.out.println("I(R1) = " + sol.branchCurrent("R1") + " A");
        System.out.println("I(R2) = " + sol.branchCurrent("R2") + " A");

        System.out.println("\nSource Currents");
        System.out.println("I(V1) = " + sol.getVoltageSourceCurrent("V1") + " A");

        System.out.println("\nPower");
        System.out.println("P(R1) = " + sol.branchPower("R1") + " W");
        System.out.println("P(R2) = " + sol.branchPower("R2") + " W");
    }

}
