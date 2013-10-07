/**
 * 
 */
package testing;

import static org.junit.Assert.*;
import junit.framework.Assert;

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
	public void testDifferentPromptsforFence() {
		//optional test to view the prompts
		for (int i = 0; i < 3; i++) {
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
		agricolaController.b_fences.doClick();
	}
	
	@Test 
	public void testBuildFence() {
		agricolaController.wFences = true;
		agricolaController.getPlayer(0).wood = 5;
		agricolaController.view.getButtons()[0][1].doClick();
		agricolaController.view.getButtons()[1][0].doClick();
		agricolaController.view.getButtons()[1][2].doClick();
		agricolaController.view.getButtons()[2][1].doClick();
		agricolaController.createPastures();
		agricolaController.updateFarm(false);
		assertEquals('p', agricolaController.farm[0][1][1].getType());
	}
	
	@Test 
	public void testMoreThan15Fences() {
		agricolaController.wFences = true;
		agricolaController.getPlayer(0).wood = 16;
		agricolaController.view.getButtons()[1][2].doClick();
		agricolaController.view.getButtons()[1][4].doClick();
		agricolaController.view.getButtons()[1][6].doClick();
		agricolaController.view.getButtons()[0][3].doClick();
		agricolaController.view.getButtons()[0][5].doClick();
		agricolaController.view.getButtons()[2][3].doClick();
		agricolaController.view.getButtons()[2][5].doClick();
		agricolaController.view.getButtons()[0][7].doClick();
		agricolaController.view.getButtons()[2][7].doClick();
		agricolaController.view.getButtons()[1][8].doClick();
		agricolaController.view.getButtons()[0][9].doClick();
		agricolaController.view.getButtons()[2][9].doClick();
		agricolaController.view.getButtons()[1][10].doClick();
		agricolaController.view.getButtons()[3][4].doClick();
		agricolaController.view.getButtons()[3][6].doClick();
		agricolaController.view.getButtons()[3][8].doClick();
		agricolaController.createPastures();
		agricolaController.updateFarm(false);
		assertEquals(15, agricolaController.getPlayer(0).getFenceCount());
	}
	
	public void test1WoodFencePrice() {
		agricolaController.wFences = true;
		agricolaController.getPlayer(0).wood = 1;
		agricolaController.view.getButtons()[1][2].doClick();
		assertEquals(0, agricolaController.getPlayer(0).wood);
	}
	
	@Test 
	public void testBuildPastureAroundField() {
		agricolaController.getPlayer(0).wood = 5;
		agricolaController.b_field.doClick();
		agricolaController.view.getButtons()[1][1].doClick();
		agricolaController.wFences = true;
		agricolaController.view.getButtons()[0][1].doClick();
		agricolaController.view.getButtons()[1][0].doClick();
		agricolaController.view.getButtons()[1][2].doClick();
		agricolaController.view.getButtons()[2][1].doClick();
		agricolaController.createPastures();
		agricolaController.updateFarm(false);
		assertNotSame('p', agricolaController.farm[0][1][1].getType());
	}

	@Test 
	public void testOrthogonalAdjacancy() {
		agricolaController.getPlayer(0).wood = 5;
		agricolaController.wFences = true;
		agricolaController.view.getButtons()[0][1].doClick();
		agricolaController.updateFarm(false);
		agricolaController.view.getButtons()[0][7].doClick();
		agricolaController.updateFarm(false);
		assertNotSame(agricolaController.view.getButtons()[0][1].getBackground(), agricolaController.view.getButtons()[0][7].getBackground());
	}
	
	@Test
	public void testAnimalPopulation() {
		for (int i = 0; i < 9; ++i) {
			agricolaController.b_wood.doClick();
			agricolaController.b_reed.doClick();
		}
		agricolaController.b_cattle.doClick();
		assertEquals(1, agricolaController.getPlayer(0).getCattle());
		assertEquals(1, agricolaController.getPlayer(0).getTotalAnimals());
		
		agricolaController.wFences = true;
		agricolaController.view.getButtons()[0][1].doClick();
		agricolaController.view.getButtons()[1][0].doClick();
		agricolaController.view.getButtons()[1][2].doClick();
		agricolaController.view.getButtons()[2][1].doClick();
		agricolaController.wFences = false;
		agricolaController.b_food.doClick();
		agricolaController.b_sheep.doClick();
		/* You will be prompted to consume animals until you respect the maximum amount capacity
		 * for 1 pasture + the default of 1 animal the house
		 * This will render the condition below true 
		 */
		Assert.assertTrue(agricolaController.getPlayer(0).getTotalAnimals() <= 3);
	}
	
}
