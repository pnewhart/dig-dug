/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 5:59:19 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: DrillerTest
 * Description:
 *
 * **************************************** */
package Model;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author spg011
 */
public class DrillerTest {

    public DrillerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test not exact location
     */
    @Test
    public void testNotExact() {
        System.out.println("goUp");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        Vector2 loc1 = driller.getCurrentCell();
        for (int i = 0; i < 85; i++) {
            driller.goUp();
        }
        Vector2 loc2 = driller.getCurrentCell();
        for (int i = 0; i < 85; i++) {
            driller.goRight();
        }
        Vector2 loc3 = driller.getCurrentCell();
        for (int i = 0; i < 85; i++) {
            driller.goDown();
        }
        Vector2 loc4 = driller.getCurrentCell();
        for (int i = 0; i < 85; i++) {
            driller.goLeft();
        }
        Vector2 loc5 = driller.getCurrentCell();
        System.out.printf("%s, %s, %s, %s, %s\n", loc1, loc2, loc3, loc4, loc5);
    }

    /**
     * Test of goUp method, of class Driller.
     */
    @Test
    public void testGoUp() {
        System.out.println("goUp");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        for (int i = 0; i < 100; i++) {
            driller.goUp();
        }
        double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldY - 100 * speed, newY, Vector2Utility.EPSILON);
        assertEquals(oldX, newX, Vector2Utility.EPSILON);
    }

    /**
     * Test of goDown method, of class Driller.
     */
    @Test
    public void testGoDown() {
        System.out.println("goDown");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        for (int i = 0; i < 100; i++) {
            driller.goDown();
        }
        double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldY + 100 * speed, newY, Vector2Utility.EPSILON);
        assertEquals(oldX, newX, Vector2Utility.EPSILON);
    }

    /**
     * Test of goLeft method, of class Driller.
     */
    @Test
    public void testGoLeft() {
        System.out.println("goLeft");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        for (int i = 0; i < 100; i++) {
            driller.goLeft();
        }
        double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldX - 100 * speed, newX, Vector2Utility.EPSILON);
        assertEquals(oldY, newY, Vector2Utility.EPSILON);
    }

    /**
     * Test of goRight method, of class Driller.
     */
    @Test
    public void testGoRight() {
        System.out.println("goRight");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        for (int i = 0; i < 100; i++) {
            driller.goRight();
        }
        double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldX + 100 * speed, newX, Vector2Utility.EPSILON);
        assertEquals(oldY, newY, Vector2Utility.EPSILON);
    }
}
