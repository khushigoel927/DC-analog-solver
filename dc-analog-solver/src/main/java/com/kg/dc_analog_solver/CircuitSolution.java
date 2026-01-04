package com.kg.dc_analog_solver;
//uses the array from CircuitSolver to read the voltages, current, power etc

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircuitSolution {

    private final double[] x; // solution vector
    private final Map<String, Integer> nodeMap; // node name -> index
    private final Map<String, Integer> vsMap; // voltage source name -> index
    private final List<Element> elements;

    public CircuitSolution(double[] x, Map<String, Integer> nodeMap, Map<String, Integer> vsMap, List<Element> elements) {
        this.x = x;
        this.nodeMap = nodeMap;
        this.vsMap = vsMap;
        this.elements = elements;
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

    double branchCurrent(String elementName) {
        for (Element e : elements) {
            if (e.name.equals(elementName)) {
                return e.branchCurrent(this);
            }
        }
        throw new IllegalArgumentException("Unknown element: " + elementName);
    }

    double branchPower(String elementName) {
        for (Element e : elements) {
            if (e.name.equals(elementName)) {
                double v = getVoltageBetween(e.n1.name, e.n2.name);
                return v * e.branchCurrent(this);
            }
        }
        throw new IllegalArgumentException("Unknown element: " + elementName);
    }

    double voltageSourceCurrent(String name) {
        int idx = vsMap.get(name);
        int offset = nodeMap.size() - 1;
        return x[offset + idx];
    }

}


