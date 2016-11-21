package krasjkurs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Audun on 21.11.2016.
 */
public class LandTest {
    //@Test (expected = IllegalArgumentException.class)
    @Test
    public void testRegBy() {
        Land testLand = new Land("Testland", "Hans-Jakob", 2);
        By testBy = new By("Testby", 1, 10, "Per");
        boolean result = testLand.regBy(testBy);

        Assert.assertTrue(result);
        Assert.assertTrue(testLand.finnes(testBy));

        result = testLand.regBy(testBy);
        Assert.assertFalse(result);

        try {
            By ugyldigBy = new By("Ugyldig", 10, 1, "Ole");
            Assert.fail();
        } catch (IllegalArgumentException e) {

        }

        By by2 = new By("By2", 1, 1, "Geir");
        By by3 = new By("By3", 1, 1, "Ove");
        result = testLand.regBy(by2);
        Assert.assertTrue(result);

        result = testLand.regBy(by3);
        Assert.assertFalse(result);
    }
}
