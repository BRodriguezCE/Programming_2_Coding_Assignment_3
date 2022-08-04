//=============================================================================
// PROGRAMMER: Bernardo Rodriguez
// PANTHER ID: 6128967
//
// CLASS: COP3337
// SECTION: RVDC
// SEMESTER: Summer 2022
// CLASSTIME: Wednesday 18:30, Online
//
// Project: Coding Assignment 3
// DUE: July 10, 2022
//
// CERTIFICATION: I understand FIU’s academic policies, and I certify that this work is my
//                own and that none of it is the work of any other person.
//=============================================================================
import java.util.Arrays;

public class Sequence {

    //Holds the numbers to reduce redundant computation
    private static int[] sequenceCache;
    //Sequence given to group 6
    private static final int[] givenSequence = new int[] {1, 1, 2, -1, 35, -308, 3079, -30203, 297074, -2920861, 28719791, -282389276, -1518352581, -1531473815, -2141023102, 1929057527, -1637875925, 2089314364, -932383201, 1207368797};

    public static void main(String[] args){

        int sequenceLength = givenSequence.length;

        sequenceCache = new int[sequenceLength + 1];

        System.out.println("\nGiven sequence:     " + Arrays.toString(givenSequence));

        System.out.print("Sequence generated: [");
        for (int i = 0; i < sequenceLength; i++){

            if(i < sequenceLength - 1)
                System.out.print(seq(i) + ", ");
            if(i == sequenceLength - 1)
                System.out.print(seq(i) + "]\n");

        }

        int[] c;
        c = sequenceCoefficients();

        System.out.println("\nMultipliers ->  x = " + c[0] + ",  y = " + c[1] + ",  z = " + c[2]);

    }

    //Code that gives coefficient of any sequence
    private static int[] sequenceCoefficients() {

        int[] a = new int[3];
        int[] newSequence = new int[givenSequence.length];

        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                for (int z = -10; z <= 10; z++) {

                    for(int i = 0; i < givenSequence.length; i++){
                        if(i <= 1){
                            newSequence[i] = 1;
                        }else if(i == 2){
                            newSequence[i] = 2;
                        }else{
                            int first = 1;
                            int second = 1;
                            int third = 2;
                            int temp;

                            //non-recursive equivalent allowing to find coefficients
                            for(int k = 3; k < givenSequence.length; k++){
                                temp = x*first + y*second + z*third;
                                first = second;
                                second = third;
                                third = temp;
                                newSequence[k] = temp;
                            }
                        }
                    }
                    if(Arrays.equals(newSequence, givenSequence)){
                        a[2] = x;
                        a[1] = y;
                        a[0] = z;
                    }
                }
            }
        }
        return a;
    }

    //recursive function to print out the sequence
    private static int seq(int n) {

        int[] x = sequenceCoefficients();

        if(n <= 1)
            return 1;
        if(n == 2)
            return n;
        if(sequenceCache[n] != 0)
            return sequenceCache[n];

        int nthNumber = (x[0] * seq(n - 1) + x[1] * seq(n - 2) + x[2] * seq(n - 3));
        sequenceCache[n] = nthNumber;
        return nthNumber;

    }

}
