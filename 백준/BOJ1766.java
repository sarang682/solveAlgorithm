import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1766 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] cnt;
    static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cnt = new int[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            cnt[b]++;
        }
        ans = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (cnt[i] == 0) pq.offer(i);
        }
        while (!pq.isEmpty()) {
            int now = pq.poll();
            ans.append(now).append(" ");
            for (int next : adj.get(now)) {
                if (--cnt[next] == 0)
                    pq.offer(next);
            }
        }
        System.out.println(ans);
    }
}
