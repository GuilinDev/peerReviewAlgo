import java.util.Scanner;

public class _004HailstoneSequence {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.printf("Enter a Positive Integer:  ");
        _004HailstoneSequence test = new _004HailstoneSequence();
        try {
            int number = inputScanner.nextInt();
            test.calcHaistoneSequence(number);
        } catch (Exception e) {
            System.out.println("Not a Number!!");
        }
    }

    /**
     * If n is 1 then the sequence ends.
     * If n is even then the next n of the sequence = n/2
     * If n is odd then the next n of the sequence = (3 * n) + 1
     *
     * @param num
     */
    private void calcHaistoneSequence(int num) {
        assert num > 0 : "Input should be positive number";
        int steps = 0;
        while (num != 1) {
            if ((num & 1) == 0) {
                System.out.println(num + " is even, so I take half: " + num / 2);
                num = num >> 1;
            } else {
                System.out.println(num + " is odd, so I make 3n + 1: " + (num * 3 + 1));
                num = num * 3 + 1;
            }
            steps++;
        }
        System.out.println("The process took " + steps + (steps < 2 ? " step" : " steps") + " to reach 1");
    }
}
