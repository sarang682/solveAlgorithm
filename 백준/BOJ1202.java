import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> jewel = new PriorityQueue<>((o1, o2) -> {
            return o1[0] - o2[0];
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewel.offer(new int[]{m, v});
        }
        int[] bag = new int[K];
        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);
        PriorityQueue<int[]> candi = new PriorityQueue<>((o1, o2) -> {
            return o2[1] - o1[1];
        });
        long ans = 0;
        for (int i = 0; i < K; i++) {
            while (!jewel.isEmpty() && jewel.peek()[0] <= bag[i]) {
                candi.offer(jewel.poll());
            }
            if (!candi.isEmpty()) {
                ans += candi.poll()[1];
            }
        }
        System.out.println(ans);
    }
}
