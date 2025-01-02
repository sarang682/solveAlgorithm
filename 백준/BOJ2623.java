import java.io.*;
import java.util.*;

public class BOJ2623 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] in;
    static Queue<Integer> ans, queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        in = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int j = 1; j < n; j++) {
                int now = Integer.parseInt(st.nextToken());
                adj.get(prev).add(now);
                in[now]++;
                prev = now;
            }
        }
        ans = new LinkedList<>();
        queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            ans.offer(now);
            for (int nxt : adj.get(now)) {
                in[nxt]--;
                if (in[nxt] == 0) queue.offer(nxt);
            }
        }
        if (ans.size() != N) { // 사이클 검사
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (!ans.isEmpty())
            sb.append(ans.poll()).append("\n");
        System.out.println(sb);
    }
}
