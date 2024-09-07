import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2812 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String nums = br.readLine();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            int n = nums.charAt(i) - '0';
            while (K > 0 && !stack.isEmpty() && n > stack.peek()) {
                stack.pop();
                K--;
            }
            stack.push(n);
        }
        while (K > 0) {
            stack.pop();
            K--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.reverse());
    }
}
