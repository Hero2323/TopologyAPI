package modules.components;


import org.json.JSONObject;

public class resistanceComponent extends component {
    float[] resistance;

    public resistanceComponent(String inputId, String[] inputNetlist, float[] inputResistance) {
        super(inputId, inputNetlist);
        resistance = inputResistance;
    }

    public void setId(String newId) {
        id = newId;
    }

    public void setNetlist(String[] newNetlist) {
        netlist  = newNetlist;
    }

    public void setResistance(float[] newResistance) {
        resistance = newResistance;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return "resistor";
    }

    public String[] getNetlist() {
        return netlist;
    }

    public float[] getResistance() {
        return resistance;
    }

    @Override
    public JSONObject createJson() {
        JSONObject resistanceJson = new JSONObject();
        resistanceJson.put("type", "resistor");
        resistanceJson.put("id", id);

        JSONObject netlistJson = new JSONObject();
        netlistJson.put("t1", netlist[0]);
        netlistJson.put("t2", netlist[1]);



        resistanceJson.put("netlist", netlistJson);

        JSONObject resJson = new JSONObject();
        resJson.put("default", resistance[0]);
        resJson.put("min", resistance[1]);
        resJson.put("max", resistance[2]);

        resistanceJson.put("resistance", resJson);
        return resistanceJson;
    }
}
