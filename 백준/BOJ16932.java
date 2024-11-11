import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16932 {

    static int N, M, cnt, cnt_2;
    static int[][] map, team;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        team = new int[N][M];
        cnt = 0; // 영역 개수
        ArrayList<Integer> size = new ArrayList<>();
        size.add(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && team[i][j] == 0) {
                    bfs(i, j, ++cnt);
                    size.add(cnt_2);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    int area = 1;
                    HashSet<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) { // 4방향 탐색
                        int nxt_r = i + dr[k];
                        int nxt_c = j + dc[k];
                        if (nxt_r < 0 || nxt_r >= N || nxt_c < 0 || nxt_c >= M) continue;
                        if (map[nxt_r][nxt_c] == 0) continue;
                        set.add(team[nxt_r][nxt_c]);
                    }
                    for (int t : set) {
                        area += size.get(t);
                    }
                    max = Math.max(max, area);
                }
            }
        }
        System.out.println(max);
    }

    public static void bfs(int r, int c, int t) {
        Queue<int[]> queue = new LinkedList<>();
        team[r][c] = t; // 방문 표시
        cnt_2 = 1;
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nxt_r = now[0] + dr[i];
                int nxt_c = now[1] + dc[i];
                if (nxt_r < 0 || nxt_r >= N || nxt_c < 0 || nxt_c >= M) continue;
                if (map[nxt_r][nxt_c] == 0) continue;
                if (team[nxt_r][nxt_c] > 0) continue; // 이미 방문
                team[nxt_r][nxt_c] = t;
                cnt_2++;
                queue.offer(new int[]{nxt_r, nxt_c});
            }
        }
    }
}
