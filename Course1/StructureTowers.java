import java.util.ArrayList;

public class StructureTowers {
    
    private static ArrayList<Integer> left;
    private static ArrayList<Integer> mid;
    private static ArrayList<Integer> right;
    private static int moves;
    private static int rings;
    
    // Constructor to reset towers with discs on "left" with smallest on top and biggest on bottom
    public StructureTowers(int n) {
        rings = n;
        moves = 0;
        
        left = new ArrayList<Integer>();
        mid = new ArrayList<Integer>();
        right = new ArrayList<Integer>();
        
        for (int i = n; i > 0; i--) {
            left.add(i);
        }
    }
    
    public void solver(int n, ArrayList<Integer> start, ArrayList<Integer> extra, ArrayList<Integer> end) {
        // f(1) Move the top ring off the left stack
        if (n == 1) {
            end.add(start.get(start.size()-1));
            start.remove(start.size()-1);
            moves++;
            //this.towersPrinter();
            return;
        }
        // Assume f(n-1) works. Move n-1 rings from start to extra.
        solver(n-1, start, end, extra);
        // Move a ring
        end.add(start.get(start.size()-1));
        start.remove(start.size()-1);
        moves++;
        //this.towersPrinter();
        // Solve on n-1 rings that are on extra
        solver(n-1, extra, start, end);
    }
    
    public void towersPrinter() {
        System.out.println("left: " + left);
        System.out.println("mid: " + mid);
        System.out.println("right: " + right);
        System.out.println();
    }
    
    public static void main(String[] args) {
        StructureTowers example = new StructureTowers(20);
        //example.towersPrinter();
        example.solver(rings, left, mid, right);
        System.out.println(example.moves + " moves");
        //example.towersPrinter();
    }
}
