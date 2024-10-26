import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13904 {
    static class Work {
        int d;
        int w;

        public Work(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
    static int N;
    static PriorityQueue<Work> pq1, pq2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq1 = new PriorityQueue<>((o1, o2) -> o2.d - o1.d);
        pq2 = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq1.offer(new Work(d, w));
        }
        int ans = 0;
        for (int i = 1000; i >= 1; i--) {
            while (!pq1.isEmpty() && pq1.peek().d >= i) {
                pq2.offer(pq1.poll());
            }
            if (!pq2.isEmpty()) {
                ans += pq2.poll().w;
            }
        }
        System.out.println(ans);
    }
}
