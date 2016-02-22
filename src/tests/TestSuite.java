package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.loading.ObjectLoaderTest;
import tests.subject.TestSubject;
import tests.vector.Vector2iTest;

/**
 * Created 22/02/16
 *
 * @author Timothy Earley
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
		ObjectLoaderTest.class,
		TestSubject.class,
		Vector2iTest.class
})
public class TestSuite {

}
