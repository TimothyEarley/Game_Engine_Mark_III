package tests.de.earley.markIII.level.objects.loading;

import de.earley.markIII.level.objects.GameObject;
import de.earley.markIII.level.objects.loading.ObjectLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * The test for {@link ObjectLoader}
 *
 * Created by timmy on 01/02/16.
 */
public class ObjectLoaderTest {

	ArrayList<HashMap<String, GameObject>> expected;

	@Before
	public void before() {
		expected = new ArrayList<>();

		HashMap<String, GameObject> map1 = new HashMap<>();

		GameObject go1 = new GameObject();
		go1.addComponents(new TestComponent("go1"), new TestComponent("go1 - comp 2"));
		map1.put("Number 1", go1);

		GameObject go2 = new GameObject();
		go1.addComponents(new BlankTestComponent_A(), new BlankTestComponent_B());
		map1.put("Number 2", go2);


		expected.add(map1);

	}

	@Test
	public void testSaveLoad() {

		try {

			for (HashMap<String, GameObject> gameObjectHashMap : expected) {

				// save:

				ObjectLoader.saveGameObjects("test/gameObjects.json", gameObjectHashMap);

				// load:

				HashMap<String, GameObject> map = ObjectLoader.loadAll("test/gameObjects.json");

				Assert.assertEquals(map.size(), gameObjectHashMap.size());
			}

		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testPrototype() {

		try {
			HashMap<String, GameObject> map = ObjectLoader.loadAll("test/prototypeTest.json");

			Assert.assertEquals(map.get("extra").getComponents().size(), 1);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
}