package Q1Folder;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by paypaytr on 5/2/18.
 */
public class Question1MainTest {
    private OpenAdressQ1 map;

    public void init() {
        map = new OpenAdressQ1();
    }

    @Test
    public void testMapForCasualInput() {
        init();

        map.putter(0,0);
        map.putter(1,1);
        map.putter(2,2);
        map.putter(3,3);

        map.printer();
    }

    @Test
    public void testMapForDuplicates1() {
        init();
        map.putter(0, 0);
        map.putter(4, 4);
        map.printer();

    }

    @Test
    public void testMapForDuplicates2() {
        init();

        map.putter(0, 0);
        map.putter(1, 1);
        map.putter(8, 8);
        map.printer();

    }

    @Test
    public void testMapForEmptyValues() {
        init();

        map.putter(0, 0);
        map.printer();
    }

    @Test
    public void testMapSpecialValues() {
        init();
        map.putter(0, 0);
        map.putter(1,1);
        map.putter(2,2);
        map.putter(3,3);
        map.putter(4,4);
        map.putter(5,5);

        map.printer();
    }

    @Test
    public void testMapForRehashing() {
        init();
        for (int i = 0; i < 125_000; i++) {
            map.putter(i, i);
        }
        map.printer();
    }

}
