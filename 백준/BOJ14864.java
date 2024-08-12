import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14864 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] cards = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cards[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cards[a]++;
            cards[b]--;
        }
        boolean[] visit = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visit[cards[i]]) {
                System.out.println(-1);
                return;
            }
            visit[cards[i]] = true;
            sb.append(cards[i]).append(" ");
        }
        System.out.println(sb);
    }
}
