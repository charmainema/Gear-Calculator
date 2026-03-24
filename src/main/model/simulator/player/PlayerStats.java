package main.model.simulator.player;

import java.util.HashMap;

public class PlayerStats {
    private int health;
    private int mana;
    private int powerPip;

    private HashMap<String, HashMap<String, Integer>> playerStats;

    // EFFECTS: constructs player stats with initial health, mana, and empty boost
    // stats
    public PlayerStats(int health, int mana) {
        this.health = health;
        this.mana = mana;
        playerStats = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: if type is "health", "mana", or "power pip", adds boost to this
    // boost
    // to boost
    // else if stat associated with type and school do not already exist in
    // playerStats,
    // adds new stat with type and school to stats, with value boost
    // else adds boost to stat associated with type and school
    public void updateStats(String type, String school, int boost) {
        if (type == "health") {
            health += boost;
        } else if (type == "mana") {
            mana += boost;
        } else if (type == "power pip") {
            powerPip += boost;
        } else {
            updateStat(type, school, boost);
        }
    }

    // MODIFIES: this
    // EFFECTS: if stat associated with type and school do not already exist in
    // playerStats,
    // adds new stat with type and school to stats, with value boost
    // else adds boost to stat associated with type and school
    private void updateStat(String type, String school, int boost) {
        HashMap<String, Integer> stat;
        if (!playerStats.containsKey(type)) {
            stat = new HashMap<>();
            playerStats.put(type, stat);
        } else {
            stat = playerStats.get(type);
        }
        modifyStat(stat, school, boost);
    }

    // MODIFIES: this
    // EFFECTS: if stat associated with school does not already exist in stat,
    // adds new school stat to stat, with value boost
    // else sums the current stat value with boost, and updates value
    private void modifyStat(HashMap<String, Integer> stat, String school, int boost) {
        if (!stat.containsKey(school)) {
            stat.put(school, boost);
        } else {
            stat.put(school, boost + stat.get(school));
        }
    }

    // EFFECTS: if type is "health", "mana", or "power pip", gets value of said type
    // otherwise if stat with associated type and school exists in playerStats,
    // returns boost value of associated stat
    // else returns 0
    public int getStat(String type, String school) {
        if (type == "health") {
            return health;
        } else if (type == "mana") {
            return mana;
        } else if (type == "power pip") {
            return powerPip;
        }
        return findStat(type, school);
    }

    // EFFECTS: if stat with associated type and school exists in playerStats,
    // returns boost value of associated stat
    // else returns 0
    private int findStat(String type, String school) {
        if (playerStats.containsKey(type)) {
            return findStat(playerStats.get(type), school);
        } else {
            return 0;
        }
    }

    // EFFECTS: if stat with associated school exists in stat, returns boost value
    // of associated stat
    // else returns 0
    private int findStat(HashMap<String, Integer> stat, String school) {
        if (stat.containsKey(school)) {
            return stat.get(school);
        } else {
            return 0;
        }
    }

    public void setStat(String type, String school, int boost) {
        if (type == "health") {
            health = boost;
        } else if (type == "mana") {
            mana = boost;
        } else {
            HashMap<String, Integer> stat = new HashMap<>();
            stat.put(school, boost);
            playerStats.put(type, stat);
        }
    }
}
