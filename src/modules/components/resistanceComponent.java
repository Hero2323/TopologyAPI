package modules.components;


import org.json.JSONObject;

/**
 * This is the resistor component class which inherits from the base component class
 * @attribute resistance - array of floats containing the default, minimum and maximum resistance that this
 * component can handle
 */
public class resistanceComponent extends component {
    float[] resistance;

    /**
     * The constructor of the resistor component. it overrides the base component constructor.
     *
     * @param inputId      the id of this component
     * @param inputNetlist array of string containing the ids of nodes connected to this component.
     * @param inputResistance  array of floats containing the default, minimum and maximum resistance that this
     * component can handle.
     */

    public resistanceComponent(String inputId, String[] inputNetlist, float[] inputResistance) {
        super(inputId, inputNetlist);
        resistance = inputResistance;
    }

    /**
     * @param newResistance array of floats containing the default, minimum and maximum resistance that this
     *      * component can handle.
     */
    public void setResistance(float[] newResistance) {
        resistance = newResistance;
    }
    public String getType() {
        return "resistor";
    }

    /**
     * @return Array of floats containing the resistance of this component.
     */
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
