package modules.components;

import org.json.JSONObject;

/**
 * This is the pmos component class which inherits from the base component class
 * @attribute characterization - array of floats containing the default, minimum and maximum characterization that this
 * component can handle
 */
public class nmosComponent extends component {
    float[] characterization;

    /**
     * The constructor of the pmos component. it overrides the base component constructor.
     *
     * @param inputId      the id of this component
     * @param inputNetlist array of string containing the ids of nodes connected to this component.
     * @param inputCharacterization  array of floats containing the default, minimum and maximum
     *                              characterization that this component can handle.
     */

    public nmosComponent(String inputId, String[] inputNetlist, float[] inputCharacterization) {
        super(inputId, inputNetlist);
        characterization = inputCharacterization;
    }

    /**
     * @param newCharacterization array of floats containing the default, minimum and maximum characterization that this
     *      * component can handle.
     */
    public void setCharacterization(float[] newCharacterization) {
        characterization = newCharacterization;
    }
    public String getType() { return "nmos"; }

    /**
     * @return Array of floats containing the characterization of this component.
     */
    public float[] getCharacterization() { return characterization; }

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
