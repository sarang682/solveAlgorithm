import java.io.*;
import java.util.*;

public class BOJ1717 {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sign = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (sign == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b))
                    sb.append("YES").append("\n");
                else
                    sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
