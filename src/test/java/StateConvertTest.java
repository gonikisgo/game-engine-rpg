import cz.cvut.fel.pjv.screen.GamePanel;
import cz.cvut.fel.pjv.utils.StateConvert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateConvertTest {
    GamePanel gamePanel = new GamePanel("src/test/java/test.json");
    private StateConvert mockStateConvert;

    @BeforeEach
    void setUp() {
        mockStateConvert = new StateConvert(gamePanel);
    }

    @Test
    public void testConvertAllState() {
        mockStateConvert.convertAllState();

        assertEquals(mockStateConvert.outputDict.get("Tree").get(0).getX(), 0);
        assertEquals(mockStateConvert.outputDict.get("Tree").get(0).getY(), 0);
        assertEquals(mockStateConvert.outputDict.get("Tree").get(0).getHealth(), -1);

        assertEquals(mockStateConvert.outputDict.get("Water").get(0).getX(), 1);
        assertEquals(mockStateConvert.outputDict.get("Water").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Key").get(0).getX(), 2);
        assertEquals(mockStateConvert.outputDict.get("Key").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Potion").get(0).getX(), 3);
        assertEquals(mockStateConvert.outputDict.get("Potion").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Door").get(0).getX(), 4);
        assertEquals(mockStateConvert.outputDict.get("Door").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Coin").get(0).getX(), 5);
        assertEquals(mockStateConvert.outputDict.get("Coin").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Player").get(0).getX(), 8);
        assertEquals(mockStateConvert.outputDict.get("Player").get(0).getY(), 0);
        assertEquals(mockStateConvert.outputDict.get("Player").get(0).getHealth(), 100);

        assertEquals(mockStateConvert.outputDict.get("Monster").get(0).getX(), 6);
        assertEquals(mockStateConvert.outputDict.get("Monster").get(0).getY(), 0);
        assertEquals(mockStateConvert.outputDict.get("Monster").get(0).getHealth(), 100);
        assertEquals(mockStateConvert.outputDict.get("Monster").get(0).getOptions(), "Monster");

        assertEquals(mockStateConvert.outputDict.get("Elf").get(0).getX(), 7);
        assertEquals(mockStateConvert.outputDict.get("Elf").get(0).getY(), 0);

        assertEquals(mockStateConvert.outputDict.get("Biom").get(0).getOptions(), "Forest");

        assertEquals(mockStateConvert.outputDict.get("LevelType").get(0).getOptions(), "NoBoss");

        assertEquals(mockStateConvert.outputDict.get("PlayerSpeed").get(0).getX(), 5);

        assertEquals(mockStateConvert.outputDict.get("PlayerKey").get(0).getX(), 0);

        assertEquals(mockStateConvert.outputDict.get("PlayerPotion").get(0).getX(), 0);

        assertEquals(mockStateConvert.outputDict.get("PlayerCoin").get(0).getX(), 0);

        assertEquals(mockStateConvert.outputDict.get("PlayerWeapon").get(0).getX(), -1);
        assertEquals(mockStateConvert.outputDict.get("PlayerWeapon").get(0).getY(), -1);

        assertEquals(mockStateConvert.outputDict.get("Progress").get(0).getX(), 0);
        assertEquals(mockStateConvert.outputDict.get("Progress").get(0).getY(), 100);
    }
}
