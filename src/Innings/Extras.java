package Innings;

import java.util.HashMap;

public class Extras {
    public String teamName;
    public int runsInExtras;
    public HashMap<String, Integer> extrasMap;

    public Extras(String teamName) {
        this.teamName = teamName;
        this.runsInExtras = 0;
        this.extrasMap = new HashMap<>();
    }

    public void initMap() {
        this.extrasMap.put("Wd", 0);
        this.extrasMap.put("Nb", 0);
        this.extrasMap.put("Lb", 0);
        this.extrasMap.put("By", 0);
    }

    public void addExtra(String extra) {
        this.extrasMap.put(extra, this.extrasMap.get(extra) + 1);
        ++this.runsInExtras;
    }

    public void wideBall() {
        this.extrasMap.put("Wd", this.extrasMap.get("Wd") + 1);
        ++this.runsInExtras;
    }

    public void noBall() {
        this.extrasMap.put("Nb", this.extrasMap.get("Nb") + 1);
        ++this.runsInExtras;
    }

    public void legBy() {
        this.extrasMap.put("Lb", this.extrasMap.get("Lb") + 1);
        ++this.runsInExtras;
    }

    public void By() {
        this.extrasMap.put("By", this.extrasMap.get("By") + 1);
        ++this.runsInExtras;
    }


}
