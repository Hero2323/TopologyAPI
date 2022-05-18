package modules.components;

import org.json.JSONObject;

public class nmosComponent extends component {
    float[] characterization;

    public nmosComponent(String inputId, String[] inputNetlist, float[] inputCharacterization) {
        super(inputId, inputNetlist);
        characterization = inputCharacterization;
    }

    public void setId(String newId) {
        id = newId;
    }

    public void setNetlist(String[] newNetlist) {
        netlist  = newNetlist;
    }

    public void setCharacterization(float[] newCharacterization) {
        characterization = newCharacterization;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return "nmos";
    }

    public String[] getNetlist() {
        return netlist;
    }

    public float[] getCharacterization() {
        return characterization;
    }

    @Override
    public JSONObject createJson() {
        JSONObject nmosJson = new JSONObject();
        nmosJson.put("type", "nmos");
        nmosJson.put("id", id);

        JSONObject netlistJson = new JSONObject();
        netlistJson.put("drain", netlist[0]);
        netlistJson.put("gate", netlist[1]);
        netlistJson.put("source", netlist[2]);

        nmosJson.put("netlist", netlistJson);

        JSONObject characterizationJson = new JSONObject();
        characterizationJson.put("default", characterization[0]);
        characterizationJson.put("min", characterization[1]);
        characterizationJson.put("max", characterization[2]);

        nmosJson.put("m(l)", characterizationJson);

        return nmosJson;
    }
}
