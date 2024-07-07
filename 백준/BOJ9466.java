import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ9466 {

    static int[] student;
    static boolean[] visit;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            student = new int[N + 1];
            visit = new boolean[N + 1];
            ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                student[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                if (visit[i]) continue;
                Stack<Integer> stack = new Stack<>();
                dfs(i, stack);
            }
            System.out.println(N - ans);
        }
    }

    static public void dfs(int now, Stack<Integer> stack) {
        if (visit[now]) {
            int cnt = 0;
            while (!stack.isEmpty()) {
                cnt ++;
                if (stack.pop() == now) {
                    ans += cnt;
                    break;
                }
            }
            return;
        }
        visit[now] = true;
        stack.push(now);
        dfs(student[now], stack);
    }
}
