import java.io.*;
import java.util.*;

public class BOJ17142 {

    static int N, M, v_size, ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[][][] virus_spread;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        Queue<int[]> virus = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virus.offer(new int[]{i, j});
            }
        }
        v_size = virus.size();
        virus_spread = new int[v_size][N][N];
        for (int i = 0; i < v_size; i++) {
            for (int j = 0; j < N; j++)
                Arrays.fill(virus_spread[i][j], Integer.MAX_VALUE);
            int[] point = virus.poll();
            bfs(i, point[0], point[1]);
        }
        boolean[] visit = new boolean[v_size];
        combination(0, 0, visit);
        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static void combination(int start, int depth, boolean[] visit) {
        if (depth >= M) {
            int max_time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        int min_time = Integer.MAX_VALUE;
                        for (int k = 0; k < v_size; k++) {
                            if (visit[k]) {
                                min_time = Math.min(min_time, virus_spread[k][i][j]);
                            }
                        }
                        max_time = Math.max(max_time, min_time);
                    }
                }
            }
            ans = Math.min(ans, max_time);
            return;
        }
        for (int i = start; i < v_size; i++) {
            visit[i] = true;
            combination(i + 1, depth + 1, visit);
            visit[i] = false;
        }
    }

    public static void bfs(int idx, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        virus_spread[idx][r][c] = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_r = now[0];
            int now_c = now[1];
            int now_cnt = now[2];
            for (int i = 0; i < 4; i++) {
                int nxt_r = now_r + dr[i];
                int nxt_c = now_c + dc[i];
                if (nxt_r < 0 || nxt_r >= N || nxt_c < 0 || nxt_c >= N) continue;
                if (map[nxt_r][nxt_c] == 1) continue;
                if (virus_spread[idx][nxt_r][nxt_c] != Integer.MAX_VALUE) continue;
                virus_spread[idx][nxt_r][nxt_c] = now_cnt + 1;
                queue.offer(new int[]{nxt_r, nxt_c, now_cnt + 1});
            }
        }
    }
}
