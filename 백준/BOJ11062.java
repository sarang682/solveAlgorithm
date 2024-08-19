import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11062 {

    static int N;
    static int[] cards;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            cards = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }
            dp = new int[N + 1][N + 1]; // 카드가 i~j 남았을 때 얻을 수 있는 최대 점수
            sb.append(play(1, N, 1)).append("\n");
        }
        System.out.println(sb);
    }

    public static int play(int s, int e, int order) {
        if (s > e) return 0;
        if(dp[s][e] > 0) return dp[s][e];

        if (order % 2 == 1) { // 근우
            dp[s][e] = Math.max(cards[s] + play(s + 1, e, order + 1), // 앞 카드 고르기
                    cards[e] + play(s, e - 1, order + 1)); // 뒷 카드 고르기
        } else { // 명우
            dp[s][e] = Math.min(play(s + 1, e, order + 1), // 앞 카드 고르기
                    play(s, e - 1, order + 1)); // 뒷 카드 고르기
        }
        return dp[s][e];
    }
}
