import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {
    static int N, M, area;
    static int[][] map;
    static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        area = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) area++;
            }
        }
        int time = 0;
        while (area > 0) {
            time++;
            // 빙하 녹이기
            int[][] cnt = new int[N][M]; // 근처 바닷물
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) { // 빙하
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                            if (map[nr][nc] == 0) cnt[i][j]++;
                        }
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) { // 빙하
                        map[i][j] = Math.max(0, map[i][j] - cnt[i][j]);
                        if (map[i][j] == 0) area--;
                    }
                }
            }
            // bfs
            boolean chk = false;
            for (int i = 0; i < N; i++) {
                if (chk) break;
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) { // 빙산 발견
                        if (area != bfs(i, j)) { // 갈라짐
                            System.out.println(time);
                            return;
                        } else { // 안갈라짐
                            chk = true;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }

    public static int bfs(int r, int c) {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 1;
        visit[r][c] = true;
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] <= 0) continue;
                if (visit[nr][nc]) continue; // 이미 방문
                visit[nr][nc] = true;
                cnt++;
                queue.offer(new int[]{nr, nc});
            }
        }
        return cnt;
    }
}
