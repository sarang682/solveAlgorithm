import java.io.*;
import java.util.*;

public class BOJ19238 {
    static int N, M, fuel;
    static int[][] map;
    static int taxi_x, taxi_y;
    static int[][][] distance;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    static class Point {
        int from_x;
        int from_y;
        int to_x;
        int to_y;

        public Point(int from_x, int from_y, int to_x, int to_y) {
            this.from_x = from_x;
            this.from_y = from_y;
            this.to_x = to_x;
            this.to_y = to_y;
        }
    }

    static Point[] customer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi_x = Integer.parseInt(st.nextToken()) - 1;
        taxi_y = Integer.parseInt(st.nextToken()) - 1;
        customer = new Point[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            customer[i] = new Point(x1, y1, x2, y2);
        }
        Arrays.sort(customer, (o1, o2) -> {
            if (o1.from_x == o2.from_x)
                return o1.from_y - o2.from_y;
            return o1.from_x - o2.from_x;
        });
        // bfs
        distance = new int[M][N][N];
        for (int i = 0; i < M; i++) {
            bfs(i, customer[i].from_x, customer[i].from_y);
        }
        // 택시 출발
        boolean[] visit = new boolean[M];
        for (int i = 0; i < M; i++) {
            // 최단 거리 손님 찾기
            int target = -1;
            int min_dis = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                if (!visit[j]) {
                    int dis = distance[j][taxi_x][taxi_y]; // 택시 까지 거리
                    if (dis < min_dis) {
                        target = j;
                        min_dis = dis;
                    }
                }
            }
            //
            if (target == -1) { // 손님을 태우러 갈 수 없음
                System.out.println(-1);
                return;
            }
            int to_dis = distance[target][customer[target].to_x][customer[target].to_y];
            if (to_dis == Integer.MAX_VALUE) { // 목적지 갈 수 없음
                System.out.println(-1);
                return;
            }
            if (fuel < min_dis + to_dis) { // 이동중 연료 부족
                System.out.println(-1);
                return;
            }
            // 이동
            visit[target] = true;
            fuel -= min_dis + to_dis;
            taxi_x = customer[target].to_x;
            taxi_y = customer[target].to_y;
            fuel += to_dis * 2;
        }
        System.out.println(fuel);
    }


    public static void bfs(int idx, int x1, int y1) {
        for (int i = 0; i < N; i++) Arrays.fill(distance[idx][i], Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        distance[idx][x1][y1] = 0;
        queue.offer(new int[]{x1, y1, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int c = now[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] == 1) continue;
                if (distance[idx][nx][ny] != Integer.MAX_VALUE) continue;
                distance[idx][nx][ny] = c + 1;
                queue.offer(new int[]{nx, ny, c + 1});
            }
        }
    }
}
