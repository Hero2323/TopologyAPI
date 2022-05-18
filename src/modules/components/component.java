package modules.components;

import org.json.JSONObject;

public abstract class component {
    String id;
    String[] netlist;

    component(String inputId, String[] inputNetlist) {
        id = inputId;
        netlist = inputNetlist;
    }

    public void setId(String newId) {

    }

    public void setNetlist(String[] newNetlist) {

    }

    public String getType() {
        return null;
    }

    public String[] getNetlist() {
        return null;
    }

    public String getId() {
        return null;
    }

    public JSONObject createJson() {
        return null;
    }
}
