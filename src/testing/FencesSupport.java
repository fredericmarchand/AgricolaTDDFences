/**
 * 
 */
package testing;



import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import agricola.AgricolaController;

/**
 * @author Fred
 *
 */
public class FencesSupport {

	private static AgricolaController agricolaController;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		agricolaController = new AgricolaController(1);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildFencesAction() {
		agricolaController.getPlayer(0).wood = 5;
		int retValue = agricolaController.buildFencesAction();
		assertEquals(0, retValue);
		agricolaController.getPlayer(0).fenceCount = 15;
		retValue = agricolaController.buildFencesAction();
		assertEquals(2, retValue);
		agricolaController.getPlayer(0).fenceCount = 0;
		agricolaController.getPlayer(0).wood = 0;
		retValue = agricolaController.buildFencesAction();
		assertEquals(1, retValue);
	}
	
	@Test
	public void testClickBuildFences() {
		/*for (int i = 0; i < 3; i++) {
			agricolaController.b_food.doClick();
			agricolaController.b_reed.doClick();
		}
		agricolaController.update(false);
		agricolaController.b_fences.doClick();
		agricolaController.getPlayer(0).wood = 5;
		agricolaController.update(false);
		agricolaController.b_fences.doClick();
		agricolaController.getPlayer(0).fenceCount = 15;
		agricolaController.update(false);
		agricolaController.b_fences.doClick();*/
	}
	
	@Test 
	public void testFarmClick() {
		agricolaController.wFences = true;
		agricolaController.getPlayer(0).wood = 5;
		agricolaController.view.getButtons()[0][1].doClick();
		while(true);
	}
	
}
