package modules.components;

import org.json.JSONObject;

/**
 * This is the base component class that all other components inherit from.
 *
 * @attribute id - the id of this component
 * @attribute netlist - array of strings containing the nodes that this component is connected to
 */
public abstract class component {
    String id;
    String[] netlist;

    /**
     * The constructor of the base component, all other components override this constructor.
     *
     * @param inputId      the id of this component
     * @param inputNetlist array of string containing the ids of nodes connected to this component.
     */
    component(String inputId, String[] inputNetlist) {
        id = inputId;
        netlist = inputNetlist;
    }

    /**
     * This function changes the id of this component.
     *
     * @param newId the new id to be given to this component.
     */
    public void setId(String newId) {
        id = newId;
    }

    /**
     * This function changes the netlist of this component.
     *
     * @param newNetlist the new netlist to be given to this component.
     */
    public void setNetlist(String[] newNetlist) {
        netlist = newNetlist;
    }

    /**
     * This function returns the type of the component, this function has no implementation in the base component class
     * and is overridden in all the other component classes to return the type.
     *
     * @return string containing the type of the component.
     */
    String getType() {
        return null;
    }

    /**
     * @return array of strings containing the netlist of the component.
     */
    public String[] getNetlist() {
        return netlist;
    }

    /**
     * @return string containing the id of the current component.
     */
    public String getId() {
        return id;
    }

    /**
     * This function returns a Json for the current component which contains its type, id, netlist & other information
     * depending on the component. This function has no implementation in the base component class and is overridden in
     * all other components.
     * @return Json object for the current component so that it can be nested inside the larger topology json.
     */
    public JSONObject createJson() {
        return null;
    }
}
