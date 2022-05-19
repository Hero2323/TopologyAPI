package modules.components;

import org.json.JSONObject;

/**
 * This is the inductor component class which inherits from the base component class
 * @attribute inductance - array of floats containing the default, minimum and maximum capacitance that this
 * component can handle
 */
public class inductorComponent extends component {
    float[] inductance;

    /**
     * The constructor of the inductor component. it overrides the base component constructor.
     *
     * @param inputId      the id of this component
     * @param inputNetlist array of string containing the ids of nodes connected to this component.
     * @param inputInductance  array of floats containing the default, minimum and maximum inductance that this
     * component can handle.
     */
    public inductorComponent(String inputId, String[] inputNetlist, float[] inputInductance) {
        super(inputId, inputNetlist);
        inductance = inputInductance;
    }

    /**
     * @param newInductance array of floats containing the default, minimum and maximum inductance that this
     *      * component can handle.
     */
    public void setInductance(float[] newInductance) {
        inductance = newInductance;
    }
    public String getType() {
        return "inductor";
    }

    /**
     * @return Array of floats containing the inductance of this component.
     */
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
