import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ22866 {
    static int N;
    static int[] height, cnt, dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        height = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        cnt = new int[N + 1];
        dis = new int[N + 1]; // 가장 가까운 거리
        Stack<int[]> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack.isEmpty() && stack.peek()[0] <= height[i])
                stack.pop();
            if (!stack.isEmpty()) dis[i] = i - stack.peek()[1];
            cnt[i] += stack.size();
            stack.push(new int[]{height[i], i});
        }
        stack = new Stack<>();
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek()[0] <= height[i])
                stack.pop();
            if (!stack.isEmpty()) {
                if (dis[i] == 0 || dis[i] > Math.abs(i - stack.peek()[1]))
                    dis[i] = i - stack.peek()[1];
            }
            cnt[i] += stack.size();
            stack.push(new int[]{height[i], i});
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(cnt[i]);
            if (cnt[i] > 0) sb.append(" ").append(i - dis[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
