import java.io.*;
import java.util.*;

public class BOJ1101 {

    static int N, M; // 박스, 색상 수
    static int[][] box;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                if (box[i][j] > 0) cnt++;
            }
            if (cnt == 1) {
                for (int j = 0; j < M; j++) {
                    if (box[i][j] > 0) one.add(j);
                }
            } else if (cnt > 1) two.add(i);
        }
        int dup = 0;
        boolean[] visit = new boolean[M];
        for (int b : one) {
            if (visit[b]) dup++;
            else visit[b] = true;
        }
        if (two.isEmpty()) {
            if (dup <= 0) {
                System.out.println(0);
            } else {
                System.out.println(dup - 1);
            }
        } else {
            System.out.println(two.size() - 1 + dup);
        }
    }
}
