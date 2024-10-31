import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1445 {
    static class Point {
        int r;
        int c;
        int cnt; // 쓰레기 지나는 횟수
        int side; // 칸을 지난 횟수

        public Point(int r, int c, int cnt, int side) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.side = side;
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int sr = 0, sc = 0; // 시작위치
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }
        // bfs
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.cnt == o2.cnt)
                return o1.side - o2.side;
            return o1.cnt - o2.cnt;
        });
        visit = new boolean[N][M];
        visit[sr][sc] = true;
        pq.offer(new Point(sr, sc, 0, 0));
        while (!pq.isEmpty()) {
            Point now = pq.poll();
            if (map[now.r][now.c] == 'F') {
                System.out.println(now.cnt + " " + now.side);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int next_trash = now.cnt;
                int next_side = now.side;
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visit[nr][nc]) continue; // 이미 방문
                visit[nr][nc] = true;
                if (map[nr][nc] == 'g') {
                    next_trash += 1;
                } else if (map[nr][nc] == '.' && chk(nr, nc)) {
                    next_side += 1;
                }
                pq.offer(new Point(nr, nc, next_trash, next_side));
            }
        }
    }

    public static boolean chk(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (map[nr][nc] == 'g') return true;
        }
        return false;
    }
}
