package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.GearItem;
import main.model.StatBoost;
import main.model.simulator.Simulator;
import main.model.simulator.Spell;
import main.model.simulator.player.Player;
import main.model.simulator.player.PlayerGear;

public class SimulatorTest {
    Simulator sim;
    Player enemy1;
    PlayerGear gear;
    Spell lifeSpell1;
    Spell stormSpell1;

    @BeforeEach
    void runBefore() {
        enemy1 = new Player(30, 1500, 800);
        sim = new Simulator(60, 4000, 1000);
        lifeSpell1 = new Spell("Leprechaun", "life", 300, 0, 0, 0, 3, 10, 95, false);
        stormSpell1 = new Spell("Storm Shark", "storm", 500, 0, 0, 0, 3, 10, 70, false);
    }

    @Test
    void testFizzleNoAccuracy() {
        Spell noAccuracy = new Spell("fizzle", "life", 0, 0, 0, 0, 0, 0, 0, false);
        sim.getPlayer().updateStats("accuracy", "life", 0);
        assertEquals(0, sim.getPlayer().getPlayerStats().getStat("accuracy", "life"));
        assertTrue(sim.fizzle(sim.getPlayer(), noAccuracy, Math.random()));
    }

    @Test
    void testFizzleNoFizzle() {
        sim.getPlayer().updateStats("accuracy", "life", 10);
        assertFalse(sim.fizzle(sim.getPlayer(), lifeSpell1, Math.random()));
    }

    @Test
    void testFizzlePossibleFizzleNoFizzle() {
        assertFalse(sim.fizzle(sim.getPlayer(), stormSpell1, 0.7));
        assertFalse(sim.fizzle(sim.getPlayer(), stormSpell1, 0.69));
    }

    @Test
    void testFizzlePossibleFizzleWillFizzle() {
        assertTrue(sim.fizzle(sim.getPlayer(), stormSpell1, 0.71));
        assertTrue(sim.fizzle(sim.getPlayer(), stormSpell1, 0.9));
    }

    @Test
    void testSetGear() {
        HashMap<String, GearItem> gearItems = new HashMap<>();

        GearItem hat = new GearItem("cool hat", "hat");
        hat.addStatBoost(new StatBoost(10, "life", "pierce"));

        GearItem mount = new GearItem("cool mount", "mount");
        mount.addStatBoost(new StatBoost(30, "ice", "resist"));

        gearItems.put("hat", hat);
        gearItems.put("mount", mount);
        
        sim.setGear(gearItems);

        assertEquals(10, sim.getPlayer().getPlayerStats().getStat("pierce", "life"));
        assertEquals(30, sim.getPlayer().getPlayerStats().getStat("resist", "ice"));
    }
}
