package tests.vector;

import de.earley.markIII.utils.Vector2i;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created 22/02/16
 *
 * @author Timothy Earley
 */
public class Vector2iTest {

	@Test
	public void testVector() {

		Vector2i a = new Vector2i(1, 2);
		Vector2i b = new Vector2i(2, 3);
		a.mult(3);
		b.clone().mult(10);
		Assert.assertTrue(a.biggerThan(b.x, b.y));
		Assert.assertEquals(a.y, 6);
	}

}
