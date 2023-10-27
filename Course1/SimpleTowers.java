public class SimpleTowers {

    static int steps = 0;

    static void stepPrinter(int n, char start, char extra, char end) {
        steps++;

        // Base case, move the single disc
        if(n == 1) {
            System.out.println("Move disc " + n + " from " + start + " to " + end);
            return;
        }
    
        /* Move n-1 discs to the extra pole to expose the nth disc
         * (Recursive leap of faith assuming f(n-1) works) */
        stepPrinter(n-1, start, end, extra);

        /* Actually move the nth disc */
        System.out.println("Move disc " + n + " from " + start + " to " + end);

        /* Now "restart" recursion to move the now n-1 stack from the extra to end.
         * This is like the original function call from the driver to move a "new" tower on a different pole
         * Still kind of working on faith than f(n-1) works but this with the previous step is the f(n) solution */
        stepPrinter(n-1, extra, end, start);
    }

    public static void main(String args[]) {
        int discs = 4;
        stepPrinter(discs, 'A', 'B', 'C');
        System.out.println(steps + " steps");
    }
}
