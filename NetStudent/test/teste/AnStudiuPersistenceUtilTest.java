package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import delphi.netstudent.business.AnStudiuPersistenceUtil;

public class AnStudiuPersistenceUtilTest {
	
	@Test
	public void testAddAnStudiuNull() {
		try {
			AnStudiuPersistenceUtil.addAnStudiu(null);
			fail("Trebuia sa arunce o exceptie.");
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddAnStudiuStringGol() {
		AnStudiuPersistenceUtil.addAnStudiu("");
	}

	@Test
	public void testGetAniStudiu() {
		assertNotNull(AnStudiuPersistenceUtil.getAniStudiu());
	}

	@Test
	public void testGetAnStudiu() {
		AnStudiuPersistenceUtil.getAnStudiu(1);
	}

}
