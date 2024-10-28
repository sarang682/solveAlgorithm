import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2234 {

    static int M, N;
    static int[][] map, room;
    static int[] dr = {0, -1, 0, 1}, dc = {-1, 0, 1, 0}; // 서 북 동 남
    static int[] dir = {1, 2, 4, 8};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0; // 방의 개수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 0) {
                    cnt++;
                    bfs(i, j, cnt);
                }
            }
        }
        System.out.println(cnt);
        int[] area = new int[cnt + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                area[room[i][j]]++;
            }
        }
        int max = 0; // 가장 넓은 방의 넓이
        for (int i = 1; i <= cnt; i++) {
            max = Math.max(max, area[i]);
        }
        System.out.println(max);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j + 1 < M && room[i][j] != room[i][j + 1]) {
                    max = Math.max(max, area[room[i][j]] + area[room[i][j + 1]]);
                }
                if (i + 1 < N && room[i][j] != room[i + 1][j]) {
                    max = Math.max(max, area[room[i][j]] + area[room[i + 1][j]]);
                }
            }
        }
        System.out.println(max);
    }

    public static void bfs(int r, int c, int v) {
        Queue<int[]> queue = new LinkedList<>();
        room[r][c] = v;
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if ((map[now[0]][now[1]] & dir[i]) > 0) continue; // 벽이 있다
                if (room[nr][nc] != 0) continue; // 이미 방문
                room[nr][nc] = v;
                queue.offer(new int[]{nr, nc});
            }
        }
    }
}
