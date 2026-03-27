package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.model.GearOptimizer;
import main.model.simulator.player.PlayerGear;

public class GearOptimizerTest {
    private GearOptimizer optimizer;

    @BeforeEach
    void runBefore() {
        optimizer = new GearOptimizer(100, 100, 100);
    }

    @Test
    void testAddGearSetOneSet() {
        PlayerGear gear = new PlayerGear(optimizer.getMainPlayer());
        optimizer.addGearSet("set 1", gear);

        ArrayList<HashMap<String, PlayerGear>> gearSets = optimizer.getGearSets();
        assertEquals(1, gearSets.size());
        assertEquals(gear, gearSets.get(0));
    }
}
