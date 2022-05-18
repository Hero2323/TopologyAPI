import modules.topology;

import java.util.ArrayList;

public class API {
    ArrayList<topology> topologies = new ArrayList<topology>();

    public void readTopologyFromJson(String pathToJsonFile) {
        topologies.add(new topology(pathToJsonFile));
    }

    public void writeTopologyToJson(int topIdx, String pathToWriteFile) {
        topologies.get(topIdx).writeTopologyToJson(pathToWriteFile);
    }

    public String[] getTopsInMem() {
        String[] topsInMem = new String[topologies.size()];
        for(int i = 0;i<topologies.size();i++)
            topsInMem[i] = topologies.get(i).getId();
        return topsInMem;
    }

    public void removeTopFromMem(int topIdx) {
        topologies.remove(topIdx);
    }

    public String[] getDevicesInTop(int topIdx) {
        return topologies.get(topIdx).getDevices();
    }
}
