package modules;

import modules.components.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import static java.nio.file.Files.writeString;

/**
 * This topology class contains a list of components and can apply various operations on them including adding/removing
 * components, retrieving components connected to certain nodes.
 * @attribute id - id of this topology
 * @attribute components - array of component objects that holds all components in this topology
 */
class topology {
    String id;
    ArrayList<component> components = new ArrayList<component>();

    /**
     * The constructor for the topology class, needs a json file to read and parse the information inside it to use
     * in constructing the topology and its components.
     * @param inputJsonFilePath the path to the json file containing all the topology information.
     */
    public topology(String inputJsonFilePath) {
        try {
            createTopologyFromJson(inputJsonFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return id of this topology
     */
    public String getId() { return id; }

    /**
     * This function returns all the components inside this topology.
     * @return Array of string containing the ids of all the components inside this topology.
     */
    public String[] getDevices() {
        String[] deviceIds = new String[components.size()];
        for (int i = 0;i<components.size();i++)
            deviceIds[i] = components.get(i).getId();
        return deviceIds;
    }

    /**
     * This function returns all the components that are connected to a certain node in this topology.
     * @param netlistNode the node that we want all the components that it is connected to.
     * @return Array of strings that contain the ids of all the components connected to the node sent to this function.
     */
    public ArrayList<String> getDevicesConnectedToNetlistNode(String netlistNode) {
        ArrayList<String> devices = new ArrayList<String>();
        for (int i = 0;i<components.size();i++) {
            String[] currCompNetList = components.get(i).getNetlist();
            for (int j = 0;j<currCompNetList.length;j++)
                if (currCompNetList[j].equals(netlistNode)) {
                    devices.add(components.get(i).getId());
                    break;
                }
        }
        return devices;
    }

    /**
     * This function parses a json file and uses the information inside it to construct the topology
     * and the components inside it.
     * It's implemented using a switch case with 5 cases for the 5 components.
     * @param jsonFilePath the path to the json file containing the topology's information.
     * @throws Exception most likely FileNotFoundException in the case that the filepath doesn't point to a json file.
     */
    private void createTopologyFromJson(String jsonFilePath) throws Exception {
        File jsonFile = new File(jsonFilePath);
        String jsonContent = FileUtils.readFileToString(jsonFile, "utf-8");

        JSONObject jsonTopology = new JSONObject(jsonContent);

        id = jsonTopology.getString("id");

        JSONArray jsonComponents = jsonTopology.getJSONArray("components");

        for (int i = 0; i < jsonComponents.length(); i++) {
            JSONObject currJsonObject = (JSONObject) jsonComponents.get(i);

            switch (currJsonObject.get("type").toString()) {
                case "resistor":
                    String resId = currJsonObject.getString("id");
                    String[] resNetlist = new String[2];
                    resNetlist[0] = currJsonObject.getJSONObject("netlist").getString("t1");
                    resNetlist[1] = currJsonObject.getJSONObject("netlist").getString("t2");
                    float[] resistance = new float[3];
                    resistance[0] = currJsonObject.getJSONObject("resistance").
                            getFloat("default");
                    resistance[1] = currJsonObject.getJSONObject("resistance").
                            getFloat("min");
                    resistance[2] = currJsonObject.getJSONObject("resistance").
                            getFloat("max");
                    components.add(new resistanceComponent(resId, resNetlist, resistance));
                    break;
                case "capacitor":
                    String capId = currJsonObject.getString("id");
                    String[] capNetlist = new String[2];
                    capNetlist[0] = currJsonObject.getJSONObject("netlist").getString("t1");
                    capNetlist[1] = currJsonObject.getJSONObject("netlist").getString("t2");
                    float[] capacitance = new float[3];
                    capacitance[0] = currJsonObject.getJSONObject("capacitance").
                            getFloat("default");
                    capacitance[1] = currJsonObject.getJSONObject("capacitance").
                            getFloat("min");
                    capacitance[2] = currJsonObject.getJSONObject("capacitance").
                            getFloat("max");
                    components.add(new capacitorComponent(capId, capNetlist, capacitance));
                    break;
                case "inductor":
                    String inductId = currJsonObject.getString("id");
                    String[] inductNetList = new String[2];
                    inductNetList[0] = currJsonObject.getJSONObject("netlist").getString("t1");
                    inductNetList[1] = currJsonObject.getJSONObject("netlist").getString("t2");
                    float[] inductance = new float[3];
                    inductance[0] = currJsonObject.getJSONObject("inductance").
                            getFloat("default");
                    inductance[1] = currJsonObject.getJSONObject("inductance").
                            getFloat("min");
                    inductance[2] = currJsonObject.getJSONObject("inductance").
                            getFloat("max");
                    components.add(new inductorComponent(inductId, inductNetList, inductance));
                    break;
                case "nmos":
                    String nmosId = currJsonObject.getString("id");
                    String[] nmosNetList = new String[3];
                    nmosNetList[0] = currJsonObject.getJSONObject("netlist").getString("drain");
                    nmosNetList[1] = currJsonObject.getJSONObject("netlist").getString("gate");
                    nmosNetList[2] = currJsonObject.getJSONObject("netlist").getString("source");
                    float[] nmosCharacterization = new float[3];
                    nmosCharacterization[0] = currJsonObject.getJSONObject("m(l)").
                            getFloat("default");
                    nmosCharacterization[1] = currJsonObject.getJSONObject("m(l)").
                            getFloat("min");
                    nmosCharacterization[2] = currJsonObject.getJSONObject("m(l)").
                            getFloat("max");
                    components.add(new nmosComponent(nmosId, nmosNetList, nmosCharacterization));
                    break;
                case "pmos":
                    String pmosId = currJsonObject.getString("id");
                    String[] pmosNetList = new String[3];
                    pmosNetList[0] = currJsonObject.getJSONObject("netlist").getString("drain");
                    pmosNetList[1] = currJsonObject.getJSONObject("netlist").getString("gate");
                    pmosNetList[2] = currJsonObject.getJSONObject("netlist").getString("source");
                    float[] pmosCharacterization = new float[3];
                    pmosCharacterization[0] = currJsonObject.getJSONObject("m(l)").
                            getFloat("default");
                    pmosCharacterization[1] = currJsonObject.getJSONObject("m(l)").
                            getFloat("min");
                    pmosCharacterization[2] = currJsonObject.getJSONObject("m(l)").
                            getFloat("max");
                    components.add(new pmosComponent(pmosId, pmosNetList, pmosCharacterization));
                    break;
            }
        }
    }

    /**
     * This function converts this topology into a json file and writes it disk. It uses each component's createJson
     * function.
     * @param writeFilePath the path where the file is to be written. If the file doesn't exist, it will create it,
     *                      otherwise, it will overwrite the contents inside it.
     */
    public void writeTopologyToJson(String writeFilePath) {
        JSONObject topJson = new JSONObject();

        topJson.put("id", id);

        JSONArray compJson = new JSONArray();
        for (modules.components.component component : components) compJson.put(component.createJson());


        topJson.put("components", compJson);
        try {
            writeString(Paths.get(writeFilePath), topJson.toString(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
