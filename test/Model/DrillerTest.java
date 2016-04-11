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
    public void testSteps() {
        System.out.println("testSteps");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        Vector2 loc1 = driller.getDiv();
        for (int i = 0; i < 25; i++) {
            driller.goUp();
        }
        Vector2 loc2 = driller.getDiv();
        for (int i = 0; i < 25; i++) {
            driller.goRight();
        }
        Vector2 loc3 = driller.getDiv();
        for (int i = 0; i < 25; i++) {
            driller.goDown();
        }
        Vector2 loc4 = driller.getDiv();
        for (int i = 0; i < 25; i++) {
            driller.goLeft();
        }
        Vector2 loc5 = driller.getDiv();
        //System.out.printf("%s, %s, %s, %s, %s\n", loc1, loc2, loc3, loc4, loc5);
    }

    /**
     * Test of goUp method, of class Driller.
     */
    @Test
    public void testGoUp() {
        System.out.println("goUp");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        //double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        int steps = 80;
        for (int i = 0; i < steps; i++) {
            driller.goUp();
        }
        //double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldY - steps * speed, newY, Vector2Utility.EPSILON);
        //assertEquals(oldX, newX, Vector2Utility.EPSILON);
    }

    /**
     * Test of goDown method, of class Driller.
     */
    @Test
    public void testGoDown() {
        System.out.println("goDown");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        //double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        int steps = 80;
        for (int i = 0; i < steps; i++) {
            driller.goDown();
        }
        //double newX = driller.getLocation().getX();
        double newY = driller.getLocation().getY();
        assertEquals(oldY + steps * speed, newY, Vector2Utility.EPSILON);
        //assertEquals(oldX, newX, Vector2Utility.EPSILON);
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
        //double oldY = driller.getLocation().getY();
        int steps = 80;
        for (int i = 0; i < steps; i++) {
            driller.goLeft();
        }
        double newX = driller.getLocation().getX();
        //double newY = driller.getLocation().getY();
        //assertEquals(oldY + steps * speed, newY, Vector2Utility.EPSILON);
        assertEquals(oldX - steps * speed, newX, Vector2Utility.EPSILON);
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
        //double oldY = driller.getLocation().getY();
        int steps = 80;
        for (int i = 0; i < steps; i++) {
            driller.goRight();
        }
        double newX = driller.getLocation().getX();
        //double newY = driller.getLocation().getY();
        //assertEquals(oldY + steps * speed, newY, Vector2Utility.EPSILON);
        assertEquals(oldX + steps * speed, newX, Vector2Utility.EPSILON);
    }

    @Test
    public void testGoLeftEdge() {
        System.out.println("goLeftEdge");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        //double oldY = driller.getLocation().getY();
        int steps = 340;
        for (int i = 0; i < steps; i++) {
            driller.goLeft();
        }
        double newX = driller.getTile().getX();
        //double newY = driller.getLocation().getY();
        //assertEquals(oldY + steps * speed, newY, Vector2Utility.EPSILON);
        assertEquals(0, newX, Vector2Utility.EPSILON);

    }

    @Test
    public void testGoRightEdge() {
        System.out.println("goRightEdge");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        double oldX = driller.getLocation().getX();
        //double oldY = driller.getLocation().getY();
        int steps = 340;
        for (int i = 0; i < steps; i++) {
            driller.goRight();
        }
        double newX = driller.getTile().getX();
        //double newY = driller.getLocation().getY();
        //assertEquals(oldY + steps * speed, newY, Vector2Utility.EPSILON);
        assertEquals(Vector2.MAX_X, newX, Vector2Utility.EPSILON);
    }

    @Test
    public void testGoDownEdge() {
        System.out.println("goDownEdge");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        //double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        int steps = 500;
        for (int i = 0; i < steps; i++) {
            driller.goDown();
        }
        //double newX = driller.getTile().getX();
        double newY = driller.getTile().getY();
        assertEquals(Vector2.MAX_Y, newY, Vector2Utility.EPSILON);
        //assertEquals(Vector2.MAX_X, newX, Vector2Utility.EPSILON);
    }

    @Test
    public void testGoUpEdge() {
        System.out.println("goUpEdge");
        Driller driller = new Driller();
        double speed = driller.getSpeed();
        //double oldX = driller.getLocation().getX();
        double oldY = driller.getLocation().getY();
        int steps = 500;
        for (int i = 0; i < steps; i++) {
            driller.goUp();
        }
        //double newX = driller.getTile().getX();
        double newY = driller.getTile().getY();
        assertEquals(0, newY, Vector2Utility.EPSILON);
        //assertEquals(Vector2.MAX_X, newX, Vector2Utility.EPSILON);
    }

    @Test
    public void testDirectionInitial() {
        System.out.println("testDirectionInitial");
        Driller driller = new Driller();
        assertEquals(driller.direction, Direction.RIGHT);
    }
}
