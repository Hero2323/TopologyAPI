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

public class topology {
    String id;
    ArrayList<component> components = new ArrayList<component>();

    public topology(String inputJsonFilePath) {
        try {
            createTopologyFromJson(inputJsonFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() { return id; }

    public String[] getDevices() {
        String[] deviceIds = new String[components.size()];
        for (int i = 0;i<components.size();i++)
            deviceIds[i] = components.get(i).getId();
        return deviceIds;
    }

    public ArrayList<String> getDevicesConnectedToNetlistNode(String netlistNode) {
        ArrayList<String> devices = new ArrayList<String>();
        for (int i = 0;i<components.size();i++) {
            String[] currCompNetList = components.get(i).getNetlist();
            for (int j = 0;j<currCompNetList.length;j++)
                if (currCompNetList[i].equals(netlistNode)) {
                    devices.add(components.get(i).getId());
                    break;
                }
        }
        return devices;
    }

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
