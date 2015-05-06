import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description AllTestst is a JUnit Test Suite to test all of the JUnit test
 *              cases created for the battle ship project
 */
@RunWith(Suite.class)
@SuiteClasses({ BattleShipGameTest.class, BoardTest.class, BSAskNameTest.class,
		BSAskShipsTest.class, BSButtonTest.class, GameBoardTest.class,
		ShipTest.class })
public class AllTests {

}
