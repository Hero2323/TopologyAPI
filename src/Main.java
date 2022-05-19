import modules.API;
public class Main {
    public static void main(String[] args) {
        API topAPI = new API();
        // Functionality 1
        if (! topAPI.readTopologyFromJson("./topologies/topology.json"))
            System.out.println("Failed to read topology from Json file: topology.json");
        else
            System.out.println("Successfully read topology.json - id: " +
                    topAPI.getTopsInMem()[topAPI.getTopsInMem().length - 1]);
        if (! topAPI.readTopologyFromJson("./topologies/testJson.json"))
            System.out.println("Failed to read topology from Json file: testJson.json");
        else
            System.out.println("Successfully read testJson.json - id: " +
                    topAPI.getTopsInMem()[topAPI.getTopsInMem().length - 1]);
        String[] tops = topAPI.getTopsInMem();
        // Functionality 3
        System.out.println("Topologies in memory:");
        for (String top : tops) System.out.print(top + " ");
        System.out.println("");

        // Functionality 2
        if (! topAPI.writeTopologyToJson(1, "topologies/newTop.json"))
            System.out.println("Failed to write topology - " + topAPI.getTopsInMem()[1] +
                    "To file path - topologies/newTop.json");
        else
            System.out.println("Successfully wrote topology - " + topAPI.getTopsInMem()[1] +
                    "To file path - topologies/newTop.json");
        // Functionality 4

        if (topAPI.removeTopFromMem(1))
            System.out.println("The second topology was successfully removed from memory");
        else
            System.out.println("Failed to remove topology from memory");
        tops = topAPI.getTopsInMem();
        System.out.println("Topologies in memory:");
        for (String top : tops) System.out.print(top + " ");
        System.out.println("");

        // Functionality 5
        System.out.println("Components in Topology 1");
        String[] devicesInTop1 = topAPI.getDevicesInTop(0);
        if (devicesInTop1 != null) {
            for (String device : devicesInTop1) System.out.print(device + " ");
            System.out.println("");
        } else System.out.println("Failed to retrieve devices in the first topology");

        // Functionality 6
        System.out.println("Components connected to node n1 in Topology 1");
        String[] compsConnectedToNode1 = topAPI.getDevicesConnectedToNetlistNode(0, "n1");
        for (String comp : compsConnectedToNode1) System.out.print(comp + " ");
        System.out.println("");
    }
}
