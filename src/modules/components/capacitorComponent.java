package modules.components;

import org.json.JSONObject;

/**
 * This is the capacitor component class which inherits from the base component class
 * @attribute capacitance - array of floats containing the default, minimum and maximum capacitance that this
 * component can handle
 */
public class capacitorComponent extends component {
    float[] capacitance;

    /**
     * The constructor of the capacitor component. it overrides the base component constructor.
     *
     * @param inputId      the id of this component
     * @param inputNetlist array of string containing the ids of nodes connected to this component.
     * @param inputCapacitance  array of floats containing the default, minimum and maximum capacitance that this
     * component can handle.
     */
    public capacitorComponent(String inputId, String[] inputNetlist, float[] inputCapacitance) {
        super(inputId, inputNetlist);
        capacitance = inputCapacitance;
    }

    /**
     * @param newCapacitance array of floats containing the default, minimum and maximum capacitance that this
     *      * component can handle.
     */
    public void setCapacitance(float[] newCapacitance) {
        capacitance = newCapacitance;
    }
    public String getType() {
        return "capacitor";
    }

    /**
     * @return Array of floats containing the capacitance of this component.
     */
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
