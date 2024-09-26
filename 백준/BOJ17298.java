import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> ans = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while (true) {
                if (stack.isEmpty()) {
                    ans.push(-1);
                    break;
                }
                if (stack.peek() <= nums[i]) {
                    stack.pop();
                } else {
                    ans.push(stack.peek());
                    break;
                }

            }
            stack.push(nums[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (!ans.isEmpty()) {
            sb.append(ans.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
