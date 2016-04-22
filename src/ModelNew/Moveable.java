/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2016 *
 * Name: Patrick Newhart, Levi Adair, Sam Greenberg, Tom Ficcadenti
 * Date: Apr 19, 2016
 * Time: 10:53:00 PM *
 * Project: csci205FinalProject
 * Package: ModelNew
 * File: Moveable
 * Description:
 *
 * **************************************** */
package ModelNew;

import java.awt.image.BufferedImage;

/**
 *
 * @author tjf010
 */
public abstract class Moveable {
    private GameBoard gameBoard;
    private Tile currentTile;
    private BufferedImage[] moveAnimation; //contains the images that cycle when the moveable moves
    private BufferedImage stationaryImage;
    private BufferedImage currentImage;
    private Box moveBox;
    private CollisionBox collisionBox;

    public Moveable(int locX, int locY, GameBoard gameBoard) {
        this.moveBox = new Box(Vector2.DIVS_PER_TILE, Vector2.DIVS_PER_TILE,
                               new DivLocation(locX,
                                               locY));
        this.gameBoard = gameBoard;
        this.currentTile = this.gameBoard.getBoard()[locX / Vector2.DIVS_PER_TILE][locY / Vector2.DIVS_PER_TILE];
    }

    public void move(Direction dir, int divsPerMove) throws Exception {
        this.moveBox.moveBox(dir, divsPerMove);
        this.collisionBox.moveBox(dir, divsPerMove);
        updateTileLoc(dir);
    }

    public void updateTileLoc(Direction dir) throws Exception {
        boolean isInOneTile = this.moveBox.getBoxLoc().equals(
                this.currentTile.getBoundary().getBoxLoc());
        if (!isInOneTile) {
            Direction tileSwitch;
            if (dir == Direction.EAST && moveBox.getEastX() > currentTile.getBoundary().getEastX()) {
                tileSwitch = Direction.EAST;
            } else if (dir == Direction.WEST && moveBox.getWestX() < currentTile.getBoundary().getWestX()) {
                tileSwitch = Direction.WEST;
            } else if (dir == Direction.SOUTH && moveBox.getSouthY() > currentTile.getBoundary().getSouthY()) {
                tileSwitch = Direction.SOUTH;
            } else if (dir == Direction.NORTH && moveBox.getNorthY() < currentTile.getBoundary().getNorthY()) {
                tileSwitch = Direction.NORTH;
            } else {
                throw new Exception(
                        "The current tile of the moveable didn't update correctly");
            }

            int newTileX = this.currentTile.getBoardX() + tileSwitch.getVector().getX();
            int newTileY = this.currentTile.getBoardY() + tileSwitch.getVector().getY();

            currentTile = this.gameBoard.getBoard()[newTileX][newTileY];
        }
    }

    public void setStationaryImage(BufferedImage stationaryImage) {
        this.stationaryImage = stationaryImage;
    }

    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public BufferedImage[] getMoveAnimation() {
        return moveAnimation;
    }

    public BufferedImage getStationaryImage() {
        return stationaryImage;
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public Box getMoveBox() {
        return moveBox;
    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

}
