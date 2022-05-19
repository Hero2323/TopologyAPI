package Tests;

import modules.API;

import java.util.Arrays;

import static java.util.Arrays.sort;

class APITest {

    @org.junit.jupiter.api.Test
    void readTopologyFromJson() {
        API topAPI = new API();
        boolean result1 = topAPI.readTopologyFromJson("./topologies/topology.json");
        assert(result1);
        boolean result2 = topAPI.readTopologyFromJson("./topologies/testJson.json");
        assert(result2);
        boolean result3 = topAPI.readTopologyFromJson("./topologies/nonExistent.json");
        assert(!result3);

        String[] top4Comps = topAPI.getDevicesInTop(4);
        assert(top4Comps == null);

        String[] top1Comps = topAPI.getDevicesInTop(0);
        assert(Arrays.equals(top1Comps, new String[]{"res1", "m1"}));

        boolean result4 = topAPI.readTopologyFromJson("./topologies/newTop.json");
        assert(!result4);

    }

    @org.junit.jupiter.api.Test
    void writeTopologyToJson() {
        API topAPI = new API();
        topAPI.readTopologyFromJson("./topologies/topology.json");
        topAPI.readTopologyFromJson("./topologies/testJson.json");

        boolean result1 = topAPI.writeTopologyToJson(1, "topologies/testTop.json");
        assert(result1);
        topAPI.readTopologyFromJson("./topologies/testTop.json");
        String[] top2devices = topAPI.getDevicesInTop(1);
        String[] top3devices = topAPI.getDevicesInTop(2);
        sort(top2devices);
        sort(top3devices);

        assert(Arrays.equals(top2devices, top3devices));
    }

    @org.junit.jupiter.api.Test
    void getTopsInMem() {
        API topAPI = new API();
        topAPI.readTopologyFromJson("./topologies/topology.json");
        topAPI.readTopologyFromJson("./topologies/testJson.json");
        topAPI.readTopologyFromJson("./topologies/testTop.json");

        assert(Arrays.equals(topAPI.getTopsInMem(), new String[]{"top1", "testJson", "testJson"}));

        topAPI.removeTopFromMem(1);

        assert(Arrays.equals(topAPI.getTopsInMem(), new String[]{"top1", "testJson"}));

    }

    @org.junit.jupiter.api.Test
    void removeTopFromMem() {
        API topAPI = new API();
        topAPI.readTopologyFromJson("./topologies/topology.json");
        topAPI.readTopologyFromJson("./topologies/testJson.json");
        topAPI.readTopologyFromJson("./topologies/testTop.json");
        assert(Arrays.equals(topAPI.getTopsInMem(), new String[]{"top1", "testJson", "testJson"}));

        topAPI.removeTopFromMem(1);
        assert(Arrays.equals(topAPI.getTopsInMem(), new String[]{"top1", "testJson"}));

        topAPI.removeTopFromMem(1);

        assert(Arrays.equals(topAPI.getTopsInMem(), new String[]{"top1"}));

        topAPI.removeTopFromMem(0);

        assert(Arrays.equals(topAPI.getTopsInMem(), new String[0]));
    }

    @org.junit.jupiter.api.Test
    void getDevicesInTop() {
        API topAPI = new API();
        topAPI.readTopologyFromJson("./topologies/topology.json");
        topAPI.readTopologyFromJson("./topologies/testJson.json");
        assert(Arrays.equals(topAPI.getDevicesInTop(0), new String[]{"res1", "m1"}));
        assert(Arrays.equals(topAPI.getDevicesInTop(1), new String[]{"cap1", "m2"}));
    }

    @org.junit.jupiter.api.Test
    void getDevicesConnectedToNetlistNode() {
        API topAPI = new API();
        topAPI.readTopologyFromJson("./topologies/topology.json");
        topAPI.readTopologyFromJson("./topologies/testJson.json");
        assert(Arrays.equals(topAPI.getDevicesConnectedToNetlistNode(0, "n1"), new String[]{"res1", "m1"}));
        assert(Arrays.equals(topAPI.getDevicesConnectedToNetlistNode(1, "n1"), new String[]{"cap1", "m2"}));
    }
}