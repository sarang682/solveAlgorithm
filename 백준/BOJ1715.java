import java.io.*;
import java.util.*;

public class BOJ1715 {

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) pq.offer(Integer.parseInt(br.readLine()));
        int ans = 0;
        while (pq.size() >= 2) {
            int a = pq.poll();
            int b = pq.poll();
            ans += a + b;
            pq.offer(a + b);
        }
        System.out.println(ans);
    }
}
