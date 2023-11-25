import java.util.Scanner;

/**
 * A class to simulate the game of Life by John Conway's rules.
 */
public class GameOfLife {
    private final int rows;
    private final int cols;
    private int[][] grid;

    /**
     * Constructor of the GameOfLife class.
     * @param rows grid row count
     * @param cols number of columns in the grid
     */
    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    /**
     * A method for setting the cell value at a given position.
     * @param row cell line
     * @param col cell column
     * @param value cell value (0 or 1)
     */
    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    /**
     * A method for counting living neighbors for a given cell.
     * @param row cell line
     * @param col cell column
     * @return number of living neighbors of the cell
     */
    public int countAliveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * A method for generating the next generation of cells based on the current state of the mesh.
     */
    public void generateNextGeneration() {
        int[][] nextGenGrid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countAliveNeighbors(i, j);
                if (grid[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextGenGrid[i][j] = 0; // Cell dies
                    } else {
                        nextGenGrid[i][j] = 1; // Cell lives
                    }
                } else {
                    if (neighbors == 3) {
                        nextGenGrid[i][j] = 1; // Cell reproduces
                    }
                }
            }
        }
        grid = nextGenGrid;
    }

    /**
     * A method to print the current state of the grid.
     */
    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * A method main for executing a life game simulation program.
     */
    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(5, 5);
        game.setCell(1, 2, 1);
        game.setCell(2, 3, 1);
        game.setCell(3, 1, 1);
        game.setCell(3, 2, 1);
        game.setCell(3, 3, 1);

        System.out.println("Initial Generation:");
        game.printGrid();

        int numOfGen;
        System.out.print("Enter the number of generations: ");
        try(Scanner scanner = new Scanner(System.in)) {
            numOfGen = scanner.nextInt();
        }

        for (int i = 0; i  < numOfGen; i++) {
            System.out.println("Next Generation:");
            game.generateNextGeneration();
            game.printGrid();
        }
    }
}
