package com.kg.dc_analog_solver;
//uses the array from CircuitSolver to read the voltages, current, power etc


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircuitSolution {

    private final double[] x; // solution vector
    private final Map<String, Integer> nodeMap; // node name -> index
    private final Map<String, Integer> vsMap; // voltage source name -> index
    private final List<Element> elements = new ArrayList<>(); // store elements if needed for power

    public CircuitSolution(double[] x, Map<String, Integer> nodeMap, Map<String, Integer> vsMap) {
        this.x = x;
        this.nodeMap = nodeMap;
        this.vsMap = vsMap;
    }

    public double getNodeVoltage(String node) {
        int idx = nodeMap.get(node);
        return idx == 0 ? 0.0 : x[idx - 1];
    }

    public double getVoltageSourceCurrent(String vs) {
        int idx = vsMap.get(vs);
        return x[nodeMap.size() - 1 + idx];
    }

    public double getVoltageBetween(String nodeA, String nodeB) {
        return getNodeVoltage(nodeA) - getNodeVoltage(nodeB);
    }

    public double getVoltage(Element e) {
        return getNodeVoltage(e.n1.name) - getNodeVoltage(e.n2.name);
    }


    public double getPowerBetween(String nodeA, String nodeB, double resistance) {
        double voltageDiff = getVoltageBetween(nodeA, nodeB);
        double current = voltageDiff / resistance;
        return voltageDiff * current;
    }


    public double getResistanceBetween(String nodeA, String nodeB, double voltageApplied) {
        double voltageDiff = getVoltageBetween(nodeA, nodeB);
        double current = voltageApplied == 0 ? 0.0 : voltageDiff / voltageApplied;
        return current == 0 ? Double.POSITIVE_INFINITY : voltageDiff / current;
    }

    public double getTotalPower() {
        double total = 0.0;
        for (Element e : elements) {
            //total += e.getPower(this); //need to add get power to element class
        }
        return total;
    }
}


