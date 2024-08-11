import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16120 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'P') {
                stack.push('P');
            } else {
                if (stack.size() >= 2 && i!= str.length -1 && str[i+1] == 'P') {
                    stack.pop();
                    stack.pop();
                } else {
                    System.out.println("NP");
                    return;
                }
            }
        }
        if(stack.size() == 1) {
            System.out.println("PPAP");
        }
        else {
            System.out.println("NP");
        }
    }
}
