import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1781 {

    static class Problem {
        int dead;
        long cnt;

        public Problem(int dead, long cnt) {
            this.dead = dead;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Problem> problems = new PriorityQueue<>((o1, o2) -> o2.dead - o1.dead); // 기간 많이 남은 순서
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            problems.offer(new Problem(d, c));
        }
        long ans = 0;
        PriorityQueue<Problem> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2.cnt, o1.cnt)); // 컵라면 많은 순서
        for (int i = N; i >= 1; i--) {
            while (!problems.isEmpty() && problems.peek().dead >= i)
                pq.offer(problems.poll());
            if (!pq.isEmpty())
                ans += pq.poll().cnt;
        }
        System.out.println(ans);
    }
}
