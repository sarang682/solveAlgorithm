import java.util.*;
import java.io.*;

public class BOJ2146 {

    static int N;
    static int[][] map;
    static int[][] mark_land;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 표시
        mark_land = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(mark_land[i], -1);
        int land_cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && mark_land[i][j] == -1) {
                    land_cnt++;
                    Queue<int[]> q = new LinkedList<>();
                    mark_land[i][j] = land_cnt;
                    q.offer(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] now = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = now[0] + dr[d];
                            int nc = now[1] + dc[d];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                            if (map[nr][nc] != 1) continue;
                            if (mark_land[nr][nc] != -1) continue;
                            mark_land[nr][nc] = land_cnt;
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        int ans = 999999;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mark_land[i][j] > 0) {
                    ans = Math.min(ans, bfs(mark_land[i][j], i, j));
                }
            }
        }
        System.out.println(ans);
    }

    public static int bfs(int land, int y, int x) {
        boolean[][] visit = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x, 0});
        visit[y][x] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (mark_land[nr][nc] == land) continue;
                if (mark_land[nr][nc] > 0) return now[2];
                if (visit[nr][nc]) continue;
                visit[nr][nc] = true;
                queue.offer(new int[]{nr, nc, now[2] + 1});
            }
        }
        return 999999;
    }
}
