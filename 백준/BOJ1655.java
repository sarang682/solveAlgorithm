import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (lo.isEmpty() || n <= lo.peek())
                lo.offer(n);
            else
                hi.offer(n);
            if (lo.size() > hi.size() + 1)
                hi.offer(lo.poll());
            else if (hi.size() > lo.size())
                lo.offer(hi.poll());

            sb.append(lo.peek()).append("\n");
        }
        System.out.println(sb);
    }
}
