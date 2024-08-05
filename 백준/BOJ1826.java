import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1826 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> oil = new PriorityQueue<>(Comparator.comparing(o -> o[0]));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            oil.offer(new int[]{a, b});
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        int ans = 0;
        while (P < L) {
            while (!oil.isEmpty() && oil.peek()[0] <= P) {
                pq.offer(oil.poll());
            }
            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }
            P += pq.poll()[1];
            ans++;
        }
        System.out.println(ans);
    }
}
