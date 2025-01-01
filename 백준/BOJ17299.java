import java.io.*;
import java.util.*;

public class BOJ17299 {

    static int N;
    static int[] arr, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        cnt = new int[1_000_001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> ans = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while (true) {
                if (stack.isEmpty()) {
                    ans.push(-1);
                    break;
                }
                int top = stack.peek();
                if (cnt[top] > cnt[arr[i]]) {
                    ans.push(top);
                    break;
                } else {
                    stack.pop();
                }
            }
            stack.push(arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        while (!ans.isEmpty())
            sb.append(ans.pop()).append(" ");
        System.out.println(sb);
    }
}
