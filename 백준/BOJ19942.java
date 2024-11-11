import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ19942 {

    static int N, min;
    static int[][] food;
    static boolean[] visit;
    static int[] nutrition;
    static ArrayList<String> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nutrition = new int[4];
        food = new int[N + 1][5];
        visit = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) nutrition[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                food[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = 999999;
        ans = new ArrayList<>();
        dfs(1, 0, 0, 0, 0, 0);
        if (min == 999999) {
            System.out.println(-1);
        } else {
            System.out.println(min);
            Collections.sort(ans);
            System.out.println(ans.get(0));
        }
    }

    public static void dfs(int depth, int a, int b, int c, int d, int cost) {
        if (depth > N) {
            if (a < nutrition[0]) return;
            if (b < nutrition[1]) return;
            if (c < nutrition[2]) return;
            if (d < nutrition[3]) return;
            if (min >= cost) {
                if (min > cost) ans = new ArrayList<>();
                min = cost;
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= N; i++) {
                    if (visit[i]) sb.append(i).append(" ");
                }
                ans.add(sb.toString());
            }
            return;
        }
        // 넣기
        visit[depth] = true;
        dfs(depth + 1, a + food[depth][0], b + food[depth][1], c + food[depth][2], d + food[depth][3], cost + food[depth][4]);
        visit[depth] = false;
        // 안넣기
        dfs(depth + 1, a, b, c, d, cost);
    }
}
