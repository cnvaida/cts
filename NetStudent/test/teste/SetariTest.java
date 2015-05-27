package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

import delphi.netstudent.controller.Setari;

public class SetariTest {
	private Setari setari;

	@Before
	public void setUp() throws Exception {
		setari = new Setari();
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setupAddParameter("action", "test");
		MockHttpServletResponse response = new MockHttpServletResponse();
		try {
			setari.doPostMethod(request, response);
			assertTrue(true);
		} catch (ServletException | IOException e) {
			fail("Exceptie servlet!");
		}
	}

}
