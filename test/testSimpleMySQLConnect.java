

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class testSimpleMySQLConnect {
	private static SimpleMySQLConnect cut;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cut = new SimpleMySQLConnect();
	}
	@Test
	public void testSimpleParse() {
		assertTrue(cut.existDriver());
	}
}
