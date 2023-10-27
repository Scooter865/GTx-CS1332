public class sequenceProblem {
    
    private static int[] testInput = new int[]{1, 7, 3, 5, 2, 8, 10, 24, -1, -5, 4};

    public static void main(String[] args) {
        System.out.print(sequenceCnt(testInput));
    }
    
    public static int sequenceCnt(int[] A) {
        int seqCnt = 0;
        
        for (int i = 0; i < A.length-1; i++) {
            
            for (int j = i+1; j < A.length; j++) {
                int greatest = A[i];
                
                for (int k = j; k < A.length; k++) { 
                    if (A[k] > greatest) {
                        seqCnt++;
                        greatest = A[k];
                    }
                }
            }
        }
        return seqCnt;
    }
}
