import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1918 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int L = str.length();
        Stack<Character> op = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < L; i++) {
            char c = str.charAt(i);
            if (c <= 'Z' && c >= 'A') {
                ans.append(c);
            } else if (c == '(') {
                op.push(c);
            } else if (c == '-' || c == '+') {
                if (!op.isEmpty() && (op.peek() == '/' || op.peek() == '*' || op.peek() == '-' || op.peek() == '+')) {
                    while (!op.isEmpty()) {
                        if (op.peek() == '(')
                            break;
                        ans.append(op.pop());
                    }
                }
                op.push(c);
            } else if (c == '*' || c == '/') {
                if (!op.isEmpty() && (op.peek() == '/' || op.peek() == '*')) {
                    while (!op.isEmpty()) {
                        if (op.peek() == '+' || op.peek() == '-')
                            break;
                        ans.append(op.pop());
                    }
                }
                op.push(c);
            } else if (c == ')') {
                while (!op.isEmpty()) {
                    char pop = op.pop();
                    if (pop == '(')
                        break;
                    ans.append(pop);
                }
            }
        }
        while (!op.isEmpty()) {
            ans.append(op.pop());
        }
        System.out.println(ans);
    }
}
