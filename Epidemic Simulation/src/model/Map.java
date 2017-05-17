package model;

import model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Oguz on 29/11/2016.
 */
public class Map {

    private Country[][] grid;
    private int size;

    private Random random; //Get a random country from the map

    public Map(int size) {
        this.size = size;
        random = new Random();
        grid = new Country[size][size];
        init();
        setNeighbors();
    }

    public Country getRandomCountry() {
        int i = random.nextInt(size);
        int j = random.nextInt(size);
        return grid[i][j];
    }

    // Add person to specific country
    public void addPersonTo(Country c, Person p) {
        c.addPerson(p);
        p.setCountry(c);
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Country();
            }
        }
    }

    /**
     * Connect countries with their neighbors
     */
    private void setNeighbors() {
        Country c;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                c = grid[i][j];

                if (i == 0) { // Top aligned country
                    addNeighbor(c, Direction.NORTH, size - 1, j);
                }

                if (i == size - 1) { // Bottom aligned country
                    addNeighbor(c, Direction.SOUTH, 0, j);
                }

                if (j == 0) { // Left aligned country
                     addNeighbor(c, Direction.WEST, i, size - 1);
                }

                if (j == size - 1) { // Right aligned country
                    addNeighbor(c, Direction.EAST, i, 0);
                }

                addNeighbor(c, Direction.EAST, i, j + 1);
                addNeighbor(c, Direction.WEST, i, j - 1);
                addNeighbor(c, Direction.NORTH, i - 1, j);
                addNeighbor(c, Direction.SOUTH, i + 1, j);

            }
        }
    }

    /**
     * A private helper method
     * Adds a neighbor to specified country, if the specified country is in bounds
     * @param  c   Country object, that another Country object may be attached to this object as neighbor
     * @param  dir The direction of the neighbor, can be one of them: EAST, WEST, NORTH, SOUTH
     * @param  i   y coordinate of the map: row
     * @param  j   x coordinate of the map: col
     * @see   Country
     */
    private void addNeighbor(Country c, Direction dir, int i, int j) {
        if (inBounds(i, j))
            c.addNeighbour(dir, grid[i][j]);
    }

    /**
     * A private helper method
     * Checks whether the specified coordinates are in bounds
     * @param  i   X coordinate of the map
     * @param  j   Y coordinate of the map
     * @return     true if the specified parameters are in bounds, false otherwise
     */
    private boolean inBounds(int i, int j) {
        return (i >= 0 && i <= size - 1 && j >= 0 && j <= size - 1);
    }

    public static boolean areNeighbors(Country c1, Country c2) {
        List<Country> neighbors = new ArrayList<>(c1.getNeighbours().values());
        for (Country c : neighbors) {
            if (c.equals(c2)) {
                return true;
            }
        }
        return false;
    }

    public Country[][] getMap() {
        return grid;
    }

}
