import java.io.*;
import java.util.*;

public class BOJ16236 {

    static int N;
    static int[][] map;
    static int size = 2, feed = 0, time = 0, r, c;
    static List<Fish> fishes;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static final int INF = 999999;

    static class Fish {
        int r;
        int c;
        int s;

        public Fish(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int in = Integer.parseInt(st.nextToken());
                if (in == 9) {
                    r = i;
                    c = j;
                    continue;
                }
                map[i][j] = in;
            }
        }
        fishes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    fishes.add(new Fish(i, j, map[i][j]));
                }
            }
        }
        boolean flg = true;
        while (flg) {
            flg = find();
        }
        System.out.println(time);
    }

    public static boolean find() {
        int idx = -1; // 가장 가까운 물고기!
        int dis = INF;
        for (int i = 0; i < fishes.size(); i++) {
            Fish f = fishes.get(i);
            if (f.s < size) {
                int d = distance(f.r, f.c);
                if (d < dis) {
                    dis = d;
                    idx = i;
                }
            }
        }
        if (idx == -1) return false;
        else {
            Fish f = fishes.get(idx);
            r = f.r;
            c = f.c;
            map[r][c] = 0;
            fishes.remove(f);
            feed++;
            if (size == feed) {
                size++;
                feed = 0;
            }
            time += dis;
            return true;
        }
    }

    public static int distance(int r1, int c1) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, 0});
        boolean[][] visit = new boolean[N][N];
        visit[r][c] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == r1 && now[1] == c1) {
                return now[2];
            }
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visit[nr][nc]) continue;
                if (map[nr][nc] > size) continue;
                visit[nr][nc] = true;
                queue.offer(new int[]{nr, nc, now[2] + 1});
            }
        }
        return INF;
    }
}
