package modules.components;

import org.json.JSONObject;

public class capacitorComponent extends component {
    float[] capacitance;

    public capacitorComponent(String inputId, String[] inputNetlist, float[] inputCapacitance) {
        super(inputId, inputNetlist);
        capacitance = inputCapacitance;
    }

    public void setId(String newId) {
        id = newId;
    }

    public void setNetlist(String[] newNetlist) {
        netlist  = newNetlist;
    }

    public void setCapacitance(float[] newCapacitance) {
        capacitance = newCapacitance;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return "capacitor";
    }

    public String[] getNetlist() {
        return netlist;
    }

    public float[] getCapacitance() {
        return capacitance;
    }

    @Override
    public JSONObject createJson() {
        JSONObject CapacitanceJson = new JSONObject();
        CapacitanceJson.put("type", "capacitor");
        CapacitanceJson.put("id", id);

        JSONObject netlistJson = new JSONObject();
        netlistJson.put("t1", netlist[0]);
        netlistJson.put("t2", netlist[1]);

        CapacitanceJson.put("netlist", netlistJson);

        JSONObject capJson = new JSONObject();
        capJson.put("default", capacitance[0]);
        capJson.put("min", capacitance[1]);
        capJson.put("max", capacitance[2]);

        CapacitanceJson.put("capacitance", capJson);
        return CapacitanceJson;
    }
}
