import java.io.*;
import java.util.*;

public class BOJ16234 {

    static int N, L, R, ans = 0;
    static int[][] map, visit;
    static int[] population;
    static int[] dr = {0, 1, -1, 0}, dc = {1, 0, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (chk() > 0) ans++;
        System.out.println(ans);
    }

    public static int chk() {
        int number = 0;
        int find = 0;
        visit = new int[N][N];
        population = new int[2501];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j] != 0) continue;
                number++;
                int[] res = process(i, j, number);
                population[number] = res[0];
                if (res[1] > 1) find++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = population[visit[i][j]];
            }
        }
        return find;
    }

    public static int[] process(int r, int c, int number) {
        int sum = map[r][c];
        int cnt = 1;
        visit[r][c] = number;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visit[nr][nc] != 0) continue;
                int diff = Math.abs(map[now[0]][now[1]] - map[nr][nc]);
                if (diff < L || diff > R) continue;
                visit[nr][nc] = number;
                sum += map[nr][nc];
                cnt++;
                queue.offer(new int[]{nr, nc});
            }
        }
        return new int[]{sum / cnt, cnt};
    }
}
