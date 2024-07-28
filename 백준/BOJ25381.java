import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ25381 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        Queue<Integer> a = new LinkedList<>();
        Queue<Integer> b = new LinkedList<>();
        Queue<Integer> c = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'A') a.offer(i);
            else if (str[i] == 'B') b.offer(i);
            else c.offer(i);
        }
        int ans = 0;
        while (!c.isEmpty()) {
            int cIdx = c.poll();
            if (!b.isEmpty() && b.peek() < cIdx) {
                b.poll();
                ans++;
            }
        }
        while (!b.isEmpty()) {
            int bIdx = b.poll();
            if (!a.isEmpty() && a.peek() < bIdx) {
                a.poll();
                ans++;
            }
        }
        System.out.println(ans);
    }
}
