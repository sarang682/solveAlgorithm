import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                ans++;
            }
            if (stack.isEmpty() || stack.peek() < y) {
                if (y != 0) stack.push(y);
            }
        }
        ans += stack.size();
        System.out.println(ans);
    }
}
