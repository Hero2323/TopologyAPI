package modules.components;

import org.json.JSONObject;

public class inductorComponent extends component {
    float[] inductance;

    public inductorComponent(String inputId, String[] inputNetlist, float[] inputInductance) {
        super(inputId, inputNetlist);
        inductance = inputInductance;
    }

    public void setId(String newId) {
        id = newId;
    }

    public void setNetlist(String[] newNetlist) {
        netlist  = newNetlist;
    }

    public void setInductance(float[] newInductance) {
        inductance = newInductance;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return "inductor";
    }

    public String[] getNetlist() {
        return netlist;
    }

    public float[] getInductance() {
        return inductance;
    }

    @Override
    public JSONObject createJson() {
        JSONObject InductanceJson = new JSONObject();
        InductanceJson.put("type", "inductor");
        InductanceJson.put("id", id);

        JSONObject netlistJson = new JSONObject();
        netlistJson.put("t1", netlist[0]);
        netlistJson.put("t2", netlist[1]);

        InductanceJson.put("netlist", netlistJson);

        JSONObject inductJson = new JSONObject();
        inductJson.put("default", inductance[0]);
        inductJson.put("min", inductance[1]);
        inductJson.put("max", inductance[2]);

        InductanceJson.put("inductance", inductJson);
        return InductanceJson;
    }
}
