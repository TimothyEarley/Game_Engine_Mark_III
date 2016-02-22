package tests.subject;

import de.earley.markIII.utils.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created 22/02/16
 *
 * @author Timothy Earley
 */
public class TestSubject {

	private static boolean called, called2;

	@Test
	public void testSubject() {

		Subject subject = new Subject();

		subject.registerAction(() -> called = true);
		subject.registerAction(() -> called2 = true);

		called = called2 = false;
		subject.trigger();
		Assert.assertTrue(called && called2);

	}

}
