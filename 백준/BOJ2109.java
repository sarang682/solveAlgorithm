import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2109 {

    static int N;
    static PriorityQueue<int[]> pq1, pq2; // p원 d일

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq1 = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        pq2 = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq1.offer(new int[]{p, d});
            max = Math.max(max, d);
        }
        int ans = 0;
        for (int i = max; i > 0; i--) {
            while (!pq1.isEmpty() && pq1.peek()[1] >= i) {
                pq2.offer(pq1.poll());
            }
            if (!pq2.isEmpty())
                ans += pq2.poll()[0];
        }
        System.out.println(ans);
    }
}
