package modules;

import java.util.ArrayList;

/**
 * This class API acts as the interface between the user and all the functionalities that the user can use.
 * @attribute topologies - list of topologies currently held in memory, any topological operations
 * are carried out on topologies inside this list and adding/removing topologies happens through this list.
 */
public class API {
    ArrayList<topology> topologies = new ArrayList<topology>();

    /**
     * This function reads a topology from a topology file and adds it to the topologies attribute of this class.
     *
     * @param pathToJsonFile path to the Json file that contains the topology to be read.
     * @return True if the Json file was found and was read successfully, False otherwise.
     */
    public boolean readTopologyFromJson(String pathToJsonFile) {
        try {
            topologies.add(new topology(pathToJsonFile));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This function writes a topology to disk as a json file.
     *
     * @param topIdx          index of the topology to write to disk.
     * @param pathToWriteFile path of the file to write the topology in. If the file doesn't exist, it will create
     *                        it and if it does exist it will override the contents inside it.
     * @return True if it wrote successfully, False otherwise. If it fails, it's usually because the
     * directory in the file path doesn't exit.
     */
    public boolean writeTopologyToJson(int topIdx, String pathToWriteFile) {
        try {
            topologies.get(topIdx).writeTopologyToJson(pathToWriteFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This function returns the topologies currently in memory (i.e. in the topologies attribute of this class).
     *
     * @return String array with the ids of all topologies currently in memory.
     */
    public String[] getTopsInMem() {
        String[] topsInMem = new String[topologies.size()];
        for (int i = 0; i < topologies.size(); i++)
            topsInMem[i] = topologies.get(i).getId();
        return topsInMem;
    }

    /**
     * This function removes a topology from memory (i.e. the topologies attribute of this class).
     *
     * @param topIdx the index of the topology to be removed.
     * @return True if the topology was removed, False if it failed. Failure usually means that the given
     * index is outside the range of the array holding all topologies in memory.
     */
    public boolean removeTopFromMem(int topIdx) {
        try {
            topologies.remove(topIdx);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This function retrieves all the components that are inside a topology.
     *
     * @param topIdx the index of the topology that we want to retrieve its components
     * @return Array of strings containing component ids or null if the topology is not found
     * (i.e. index given is out of range).
     */
    public String[] getDevicesInTop(int topIdx) {
        try {
            return topologies.get(topIdx).getDevices();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This function gets all components that are connected to a certain netlist node within a certain topology.
     * @param topIdx the index of the topology to search in.
     * @param node the node that we all the components that are connected to it.
     * @return Array of strings containing all the component ids of the components connected
     * to the node that was given to this function or null if the index given is out of range (topology not found).
     */
    public String[] getDevicesConnectedToNetlistNode(int topIdx, String node) {
        try {
            ArrayList<String> devices = topologies.get(topIdx).getDevicesConnectedToNetlistNode(node);
            String[] outDevices = new String[devices.size()];
            for (int i = 0; i < outDevices.length; i++) outDevices[i] = devices.get(i);
            return outDevices;
        } catch (Exception e) {
            return null;
        }
    }
}
