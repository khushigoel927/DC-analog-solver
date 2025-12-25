package com.kg.dc_analog_solver;
/* Solves G·x = I using EJML
 Returns the solution vector x.
 where:
  - G is a square matrix (size N x N)
  - x is the vector of unknowns
    [ node voltages | voltage-source currents ]
  - I is the RHS vector (sources)
 */
//Circuit solvers consolidates the elements into a circuit and uses the element's stamps to make the G matrix


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CircuitSolver {
    private Map<String, Integer> nodeMap = new HashMap<>();
    private Map<String, Integer> voltageSourceIndex = new HashMap<>();
    private List<Element> elements = new ArrayList<>();

    private int nextNodeIndex = 1; // node 0 = GND

    public CircuitSolver() {
        nodeMap.put("GND", 0);
    }
    // adds resistor to circuit
    public void addResistor(String name, String n1, String n2, double r) {
        elements.add(new Resistor(name, getNode(n1), getNode(n2), r));
    }
    //adds current source to circuit
    public void addCurrentSource(String name, String from, String to, double i) {
        elements.add(new CurrentSource(name, getNode(from), getNode(to), i));
    }
    //add voltage source to circuit
    public void addVoltageSource(String name, String np, String nm, double v) {
        int idx = voltageSourceIndex.size();
        voltageSourceIndex.put(name, idx);
        elements.add(new VoltageSource(name, getNode(np), getNode(nm), v));
    }
    private Node getNode(String name) {
        if (!nodeMap.containsKey(name)) {
            nodeMap.put(name, nextNodeIndex++);
        }
        return new Node(nodeMap.get(name), name);
    }
}
