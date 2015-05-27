package teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdminTest.class, AnStudiuTest.class, AnuntTest.class,
		FormaFinantareTest.class, GrupeTest.class, LoginTest.class,
		MateriiTest.class, NoteTest.class, SetariStudentTest.class,
		SetariTest.class, ValidatorTest.class })
public class AllTests {

}
