import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17420 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (Long.compare(o1[1], o2[1]) == 0) {
                return Long.compare(o1[0], o2[0]);
            }
            return Long.compare(o1[1], o2[1]);
        });
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long a = Long.parseLong(st1.nextToken());
            long b = Long.parseLong(st2.nextToken());
            pq.offer(new long[]{a, b});
        }

        long ans = 0;
        long day = 0;
        long prev = 0;
        long max = 0;

        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            if (day < now[1]) {
                day = now[1];
                prev = max;
            }
            if (now[0] < now[1]) {
                long cnt = 0; // 연장 횟수
                cnt += (now[1] - now[0]) / 30;
                if ((now[1] - now[0]) % 30 > 0) cnt += 1;
                now[0] += cnt * 30;
                ans += cnt;
            }
            if (now[0] < prev) {
                long cnt = 0; // 연장 횟수
                cnt += (prev - now[0]) / 30;
                if ((prev - now[0]) % 30 > 0) cnt += 1;
                now[0] += cnt * 30;
                ans += cnt;
            }
            max = Math.max(max, now[0]);
        }
        System.out.println(ans);
    }
}
