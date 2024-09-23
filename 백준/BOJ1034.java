import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1034 {

    static int N, M, K;
    static String[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new String[N];
        int[] zero = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line[i].charAt(j) == '0') zero[i]++;
            }
        }
        K = Integer.parseInt(br.readLine());
        int ans = 0;
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (zero[i] <= K && zero[i] % 2 == K % 2 && !visit[i]) {
                int cnt = 0;
                for (int j = i; j < N; j++) { // 같은 모양 세기
                    if (line[i].equals(line[j])) {
                        visit[j] = true;
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        System.out.println(ans);
    }
}
