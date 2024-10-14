import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11657 {

    static int N, M;
    static int[][] edge;
    static long[] dis;
    static final int INF = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edge = new int[M][3];
        dis = new long[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }
        // 벨만 포드
        Arrays.fill(dis, INF);
        dis[1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) { // 모든 간선 탐색
                if (dis[edge[j][0]] == INF) continue;
                if (dis[edge[j][1]] > dis[edge[j][0]] + edge[j][2]) {
                    if (i == N) { // N번째에 갱신
                        System.out.println(-1);
                        return;
                    }
                    dis[edge[j][1]] = dis[edge[j][0]] + edge[j][2];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (dis[i] == INF) sb.append(-1);
            else sb.append(dis[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
