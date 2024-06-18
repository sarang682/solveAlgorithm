import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ2457 {
    static class Flower {
        int open;
        int close;

        public Flower(int open, int close) {
            this.open = open;
            this.close = close;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Flower> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.open));
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int open = Integer.parseInt(input[0]) * 100 + Integer.parseInt(input[1]);
            int close = Integer.parseInt(input[2]) * 100 + Integer.parseInt(input[3]);
            pq.offer(new Flower(open, close));
        }

        int nDay = 301;
        int ans = 0;
        while (!pq.isEmpty()) {
            int tDay = nDay;
            while (!pq.isEmpty()) {
                Flower f = pq.peek();
                if (nDay < f.open) {
                    if (tDay <= nDay) {
                        System.out.println(0);
                        return;
                    }
                    break;
                }
                pq.poll();
                if (f.close > tDay) {
                    tDay = f.close;
                }
            }
            ans++;
            if (tDay > 1130)
                break;
            nDay = tDay;
            if (pq.isEmpty()) ans = 0;
        }
        System.out.println(ans);
    }
}
