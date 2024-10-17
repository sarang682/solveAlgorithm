import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {

    static String str1, str2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str1.length(); i++) {
            stack.push(str1.charAt(i));
            if (stack.size() >= str2.length()) {
                int idx = str2.length() - 1;
                while (idx >= 0 && stack.peek() == str2.charAt(idx)) { // 같으면 뺀다
                    stack.pop();
                    idx--;
                }
                if (idx >= 0) {
                    for (int j = idx + 1; j < str2.length(); j++)
                        stack.push(str2.charAt(j));
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        System.out.println(sb.reverse());
    }
}
