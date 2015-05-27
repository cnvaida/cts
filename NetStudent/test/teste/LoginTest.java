package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

import delphi.netstudent.controller.Login;

public class LoginTest {

	private Login login;

	@Before
	public void setUp() throws Exception {
		login = new Login();
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setupAddParameter("action", "test");
		MockHttpServletResponse response = new MockHttpServletResponse();
		try {
			login.doGetMethod(request, response);
			assertTrue(true);
		} catch (ServletException | IOException e) {
			fail("Exceptie servlet!");
		}
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		try {
			login.doPostMethod(request, response);
			assertTrue(true);
		} catch (ServletException | IOException e) {
			fail("Exceptie servlet!");
		}
	}

}
