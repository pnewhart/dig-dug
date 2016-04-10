/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 6, 2016
 * Time: 4:24:57 PM *
 * Project: csci205FinalProject
 * Package: Model
 * File: Driller
 * Description:
 *
 * **************************************** */
package Model;

/**
 *
 * @author spg011
 */
public class Driller extends Character {

    private boolean isShooting;
    private Gun gun;

    public Driller(GameBoard board) {
        this.location = new Vector2(
                (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                (Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE);
        this.direction = Direction.RIGHT;
        this.speed = 0.5;
        this.isMoving = false;
        this.isShooting = false;
        this.gun = null;
        this.board = board;
    }

    // REMOVE THIS ONCE YOU GET THE CORRECT GAMEBOARD!!!!!!!!!!!
    public Driller() {
        this.location = new Vector2(
                (Vector2.NUM_TILE_HORIZONTAL / 2 - 1) * Vector2.DIVS_PER_TILE,
                (Vector2.NUM_TILE_VERTICAL / 2 - 1) * Vector2.DIVS_PER_TILE);
        this.direction = Direction.RIGHT;
        this.speed = 0.5;
        this.isMoving = false;
        this.isShooting = false;
        this.gun = null;
        board = null;
    }

    public Vector2 getLocation() {
        return new Vector2(location.getX(), location.getY());
    }

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isMoving() {
        return isMoving;
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any column it will move in its current
     * direction to the next column.
     */
    @Override
    public void goUp() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setX(this.location.getX() - speed);
        } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setX(this.location.getX() + speed);
        } else {
            location.setX(this.getTile().getX() * Vector2.DIVS_PER_TILE);
            location.setY(location.getY() - speed);
            if (location.getY() != 0.0) {
                this.direction = Direction.UP;
            }
        }
        this.isMoving = true;
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any column it will move in its current
     * direction to the next column.
     */
    @Override
    public void goDown() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.LEFT && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setX(this.location.getX() - speed);
        } else if (this.direction == Direction.RIGHT && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setX(this.location.getX() + speed);
        } else {
            location.setX(this.getTile().getX() * Vector2.DIVS_PER_TILE);
            location.setY(location.getY() + speed);
            if (location.getY() != 0.0) {
                this.direction = Direction.DOWN;
            }
        }
        this.isMoving = true;
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    @Override
    public void goLeft() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() - speed);
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() + speed);
        } else {
            location.setY(this.getTile().getY() * Vector2.DIVS_PER_TILE);
            location.setX(location.getX() - speed);
            if (this.location.getX() < 0) {
                this.location.setX(0);
            }
            this.direction = Direction.LEFT;
        }
        this.isMoving = true;
    }

    /**
     * This method will have the character goUp according to the individual
     * character's speed and location, changing its direction to up. When the
     * driller is not aligned with any row it will move in its current direction
     * to the next row.
     */
    @Override
    public void goRight() {
        //this.location.setX(Math.round(this.location.getX()));

        if (this.direction == Direction.UP && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() - speed);
        } else if (this.direction == Direction.DOWN && !Vector2Utility.isNearTile(
                this.location)) {
            this.location.setY(this.location.getY() + speed);
        } else {
            location.setY(this.getTile().getY() * Vector2.DIVS_PER_TILE);
            location.setX(location.getX() + speed);
            if (this.location.getX() < 0) {
                this.location.setX(0);
            }
            this.direction = Direction.RIGHT;
        }
        this.isMoving = true;
    }

    @Override
    public void stop() {
        this.isMoving = false;
    }

    public void shoot(boolean pump) {
        if (!isShooting) {
            isShooting = true;
            this.gun = new Gun(location, direction, board);
        } else {
            this.gun.shoot(pump);
        }
    }

    /**
     * Gets the location of the Driller, what cell is he most in.
     *
     * @return Vector2
     */
    public Vector2 getCurrentCell() {
        Vector2 curCell = new Vector2(0, 0);
        if (this.direction == Direction.DOWN || this.direction == Direction.UP) {
            curCell.setX(location.getX());
            //curCell[1] = (direction == Direction.UP) ? (int) Math.ceil(
            //        location.getY()) : (int) Math.floor(location.getY());
            curCell.setY(Math.round(location.getY()));
        } else {
            //curCell[0] = (direction == Direction.LEFT) ? (int) Math.ceil(
            //        location.getX()) : (int) Math.floor(location.getX());
            curCell.setX(Math.round(location.getX()));
            curCell.setY(location.getY());
        }
        return curCell;
    }

}
