package main.model;

import java.util.ArrayList;
import java.util.HashMap;

import main.model.gear.GearItem;
import main.model.simulator.Simulator;

public class GearOptimizer {
    private ArrayList<HashMap<String, GearItem>> gearSets;
    private Simulator sim;

    public GearOptimizer() {
        // TODO
    }

    public void addGearSet(HashMap<String, GearItem> gearSet) {
        // TODO
    }

    // EFFECTS: Returns the best possible set of gear from gearSets
    public HashMap<String, GearItem> optimizeGear() {
        // TODO
        return new HashMap<>();
    }

    public ArrayList<HashMap<String, GearItem>> getGearSets() {
        return gearSets;
    }
}
