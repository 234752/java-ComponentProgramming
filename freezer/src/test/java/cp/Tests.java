package cp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    public Tests() {
    }

    @Test
    public void testMethod1() {
        Freezer fr1 = new Freezer(100);
        assertTrue(fr1.add(new Mylk(30)));
        assertTrue(fr1.add(new Eggz(30)));
        assertTrue(fr1.add(new Meet(30)));
        assertEquals(fr1.getOccupiedSpace(), 90);
        assertEquals(fr1.getFreeSpace(), 10);
        assertFalse(fr1.add(new Mylk(30)));
    }

    @Test
    public void testMethod2() {
        Freezer fr1 = new Freezer(100);
        Mylk mylk = new Mylk(20);
        assertTrue(fr1.add(mylk));
        assertTrue(fr1.add(new Eggz(40)));
        assertTrue(fr1.add(new Meet(30)));
        assertEquals(fr1.getOccupiedSpace(), 90);
        assertEquals(fr1.getFreeSpace(), 10);
        assertFalse(fr1.add(new Mylk(30)));
        assertTrue(fr1.remove(mylk));
        assertEquals(fr1.getOccupiedSpace(), 70);
        assertEquals(fr1.getFreeSpace(), 30);
    }


}
