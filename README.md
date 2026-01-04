# DC-analog-solver
A Java-based DC circuit solver that analyzes linear resistive circuits containing resistors, independent voltage sources, and independent current sources.
## Features
- Solve arbitrary DC circuits with:
  - Resistors
  - Independent voltage sources
  - Independent current sources
- Can handle:
  - Parallel components
  - Multi-node circuits
  - Multi-source ciruits
- Computes:
  - Node voltages
  - Voltage between two nodes
  - Current through an element
  - Power dissipation
- Seperate Classes for:
  - Circuit construction (`CircuitSolver`)
  - Results & queries (`CircuitSolution`)
- Uses EJML to solve matrixes
## Theory Background
The solver uses **Modified Nodal Analysis (MNA)**.
### Unknowns
- Node voltages (except ground)
- Currents through voltage sources
### Equations
- Kirchhoff’s Current Law (KCL) at each node
- Voltage constraints for voltage sources
These form a linear system: A*x=b
Such that:
- `A` = conductance and constraint matrix
- `x` = unknown node voltages and source currents
- `b` = source vector (RHS)
## Project Structure
```
dc-analog-solver/
├── pom.xml
├── README.md
├── LICENSE
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── kg/
    │               └── dc_analog_solver/
    │                   ├── Main.java
    │                   ├── CircuitSolver.java
    │                   ├── CircuitSolution.java
    │                   ├── Element.java
    │                   ├── Resistor.java
    │                   ├── VoltageSource.java
    │                   └── CurrentSource.java
    └── test/
        └── java/
            └── com/
                └── kg/
                    └── dc_analog_solver/
                        └── CircuitSolverTest.java
```
## Requirements
- Java 11 or newer
- Maven 3.8+
## EJML Dependency
Add the following to your pom.xml:
```
<dependency>
    <groupId>org.ejml</groupId>
    <artifactId>ejml-all</artifactId>
    <version>0.43</version>
</dependency>
```
## Queries
Node Voltages
```
sol.getNodeVoltage("N1");
sol.getVoltageBetween("N1", "N2");
```
Branch Currents
```
sol.branchCurrent("R1");
sol.voltageSourceCurrent("V1");
```
Power
```
sol.branchPower("R1");
```
Positive power → dissipated/
Negative power → delivered (sources)



## Example Usage
```ruby
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
```
### JUnit Tests (`CircuitSolverTest`) included that check: 
- Ohm’s law
- Parallel resistors
- Current source behavior
- Voltage dividers

## Limitations
- DC analysis only (no capacitors or inductors)
- Linear elements only
- No dependent sources (VCVS, CCCS, etc.)
## Author
Khushi Goel







