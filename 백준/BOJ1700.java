import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] order = new int[K];
        int[] nxt_idx = new int[K + 1];
        ArrayList<ArrayList<Integer>> item = new ArrayList<>();
        for (int i = 0; i <= K; i++) {
            item.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            order[i] = n;
            item.get(n).add(i); // item 이 몇 번째 인덱스에 나왔는지
        }

        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> nxt_idx[o2] - nxt_idx[o1]);
        for (int i = 0; i < K; i++) {
            item.get(order[i]).remove(0);
            if (item.get(order[i]).isEmpty()) {
                nxt_idx[order[i]] = Integer.MAX_VALUE;
            } else {
                nxt_idx[order[i]] = item.get(order[i]).get(0);
            }
            if (pq.size() >= N && !pq.contains(order[i])) {
                pq.poll();
                ans++;
            }
            pq.remove(order[i]);
            pq.offer(order[i]);
        }
        System.out.println(ans);
    }
}
