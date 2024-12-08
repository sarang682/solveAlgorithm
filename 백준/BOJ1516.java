import java.io.*;
import java.util.*;

public class BOJ1516 {

    static int N;
    static int[] time, waiting;
    static boolean[] visit;
    static int[] ans;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        ans = new int[N + 1];
        visit = new boolean[N + 1];
        waiting = new int[N + 1]; // 대기중
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while (true) {
                int prev = Integer.parseInt(st.nextToken());
                if (prev == -1) break;
                adj.get(prev).add(i);
                waiting[i]++;
            }
        }
        ArrayList<Integer> point = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (waiting[i] == 0) point.add(i);
        }
        for (int p : point) {
            ans[p] = time[p];
            dfs(p, ans[p]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++)
            sb.append(ans[i]).append("\n");
        System.out.println(sb);
    }

    public static void dfs(int v, int t) {
        for (int nxt : adj.get(v)) {
            waiting[nxt]--;
            ans[nxt] = Math.max(ans[nxt], t + time[nxt]);
            if (waiting[nxt] == 0) {
                dfs(nxt, ans[nxt]);
            }
        }
    }
}
