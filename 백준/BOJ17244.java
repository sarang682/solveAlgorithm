import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17244 {
    static class Info {
        int x, y, time, thing;

        public Info(int y, int x, int time, int thing) {
            this.y = y;
            this.x = x;
            this.time = time;
            this.thing = thing;
        }
    }
    static char[][] map;
    static int N, M;
    static int startX, startY, endX, endY, cnt;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        Queue<Info> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    startY = i;
                    startX = j;
                } else if (map[i][j] == 'E') {
                    endY = i;
                    endX = j;
                } else if (map[i][j] == 'X') {
                    map[i][j] = (char) (++cnt + '0');
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        boolean[][][] visit = new boolean[1 << cnt][M][N];
        visit[0][startY][startX] = true;
        queue.offer(new Info(startY, startX, 0, 0));
        while (!queue.isEmpty()) {
            Info now = queue.poll();
            if (map[now.y][now.x] == 'E' && now.thing == (1 << cnt) - 1) {
                ans = Math.min(ans, now.time);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (map[ny][nx] == '#') continue;
                if (map[ny][nx] >= '1' && map[ny][nx] <= '5') {
                    int th = map[ny][nx] - '0';
                    int nxt_thing = now.thing | 1 << (th - 1);
                    if (!visit[nxt_thing][ny][nx]) {
                        visit[nxt_thing][ny][nx] = true;
                        queue.offer(new Info(ny, nx, now.time + 1, nxt_thing));
                    }
                } else {
                    if (!visit[now.thing][ny][nx]) {
                        visit[now.thing][ny][nx] = true;
                        queue.offer(new Info(ny, nx, now.time + 1, now.thing));
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
