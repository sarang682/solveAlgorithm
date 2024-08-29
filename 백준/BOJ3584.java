import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ3584 {

    static int N, a, b;
    static int[] parent;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> child;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            parent = new int[N + 1];
            visit = new boolean[N + 1];
            child = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                child.add(new ArrayList<>());
            }
            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                child.get(p).add(c);
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            while (!chk(a)) {
                a = parent[a];
            }
            sb.append(a).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean chk(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visit[v] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == b)
                return true;
            for (int nxt : child.get(now)) {
                if (!visit[nxt]) {
                    visit[nxt] = true;
                    queue.offer(nxt);
                }
            }
        }
        return false;
    }
}
