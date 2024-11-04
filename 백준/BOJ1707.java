import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1707 {

    static int V, E;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] team;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj.get(a).add(b);
                adj.get(b).add(a);
            }
            team = new int[V + 1];
            flag = false;
            for (int i = 1; i <= V; i++) {
                if (team[i] == 0) {
                    dfs(i, 1);
                }
                if(flag) break;
            }
            if (flag) System.out.println("NO");
            else {
                System.out.println("YES");
            }
        }
    }

    public static void dfs(int v, int g) {
        if (team[v] != 0) {
            if (team[v] != g) {
                flag = true;
            }
            return;
        }
        team[v] = g;
        for (int nxt : adj.get(v)) {
            dfs(nxt, g * -1);
        }
    }
}
