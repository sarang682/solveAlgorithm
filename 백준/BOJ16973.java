import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16973 {

    static int N, M, H, W, Sr, Sc, Fr, Fc;
    static boolean[][] map, visit;
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0}; // 상 하 좌 우

    static class Point {
        int y;
        int x;
        int cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                if (st.nextToken().equals("1"))
                    map[i][j] = true;
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken());
        Sc = Integer.parseInt(st.nextToken());
        Fr = Integer.parseInt(st.nextToken());
        Fc = Integer.parseInt(st.nextToken());
        //
        visit = new boolean[N + 1][M + 1];
        visit[Sr][Sc] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(Sr, Sc, 0));
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.x == Fc && now.y == Fr) {
                System.out.println(now.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx <= 0 || ny <= 0 || nx > M || ny > N) continue;
                if (visit[ny][nx]) continue;
                visit[ny][nx] = true;
                if (chk(i, ny, nx)) {
                    queue.offer(new Point(ny, nx, now.cnt + 1));
                }
            }
        }
        System.out.println(-1);
    }

    public static boolean chk(int dir, int r, int c) {
        if (dir == 1) { // 하
            if (r + H - 1 > N) return false;
            for (int i = 0; i < W; i++) {
                if (map[r + H - 1][c + i]) return false;
            }
        } else if (dir == 0) { // 상
            if (r <= 0) return false;
            for (int i = 0; i < W; i++) {
                if (map[r][c + i]) return false;
            }
        } else if (dir == 2) { // 좌
            if (c <= 0) return false;
            for (int i = 0; i < H; i++) {
                if (map[r + i][c]) return false;
            }
        } else { // 우
            if (c + W - 1 > M) return false;
            for (int i = 0; i < H; i++) {
                if (map[r + i][c + W - 1]) return false;
            }
        }
        return true;
    }
}
