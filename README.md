# TopologyAPI
A topology API program that can read topologies from json files and write topologies to disk as json files. It can also execute various queries on the topologies in memory.

# Design Choices and Assumptions
* I chose to create a base component class that all other components inherit from.
* I made 5 component classes that inherit from the base component class (nmos, pmos, resistor, inductor, capacitor).
* I made the base component class abstract and added several virtual functions that are overriden by the child class like getType(), createJson(), etc.
* I'm assuming that devices and components are the same.
