import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class FlatTest {
    private Flat flat;

    @Before
    public void initFlat() {
        flat = new Flat(20);
    }

    @Test
    public void get_sqrt() {
        assertEquals(20,flat.get_sqrt(),0);
    }

    @Test
    public void getNumber() {
        assertEquals(0,flat.getNumber());
    }
}