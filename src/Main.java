import modules.topology;

public class Main {
    public static void main(String[] args) {
        topology top1 = new topology("./topologies/modules.topology.json");
        top1.writeTopologyToJson("topologies/testJson.json");
    }
}
