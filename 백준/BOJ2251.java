import java.io.*;
import java.util.*;

public class BOJ2251 {

    static int[] size;
    static boolean[][] visit;
    static TreeSet<Integer> ans;
    static int[][] pair = {{0, 1}, {1, 0}, {0, 2}, {2, 0}, {1, 2}, {2, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = new int[3];
        size[0] = Integer.parseInt(st.nextToken());
        size[1] = Integer.parseInt(st.nextToken());
        size[2] = Integer.parseInt(st.nextToken());
        visit = new boolean[201][201];
        ans = new TreeSet<>();
        visit[0][size[2]] = true;
        ans.add(size[2]);
        dfs(0, size[2]);
        StringBuilder sb = new StringBuilder();
        for (int it : ans) sb.append(it).append(" ");
        System.out.println(sb);
    }

    public static void dfs(int b, int c) {
        for (int i = 0; i < 6; i++) {
            int[] rt = pour(b, c, pair[i][0], pair[i][1]);
            if (visit[rt[1]][rt[2]]) continue;
            visit[rt[1]][rt[2]] = true;
            if (rt[0] == 0) ans.add(rt[2]);
            dfs(rt[1], rt[2]);
        }
    }

    public static int[] pour(int b, int c, int idx_1, int idx_2) { // 1을 2한테 붓다
        int[] capacity = new int[]{size[2] - b - c, b, c};
        if (size[idx_1] >= capacity[idx_2] + capacity[idx_1]) {
            capacity[idx_1] += capacity[idx_2];
            capacity[idx_2] = 0;
        } else {
            capacity[idx_2] -= size[idx_1] - capacity[idx_1];
            capacity[idx_1] = size[idx_1];
        }
        return capacity;
    }
}
