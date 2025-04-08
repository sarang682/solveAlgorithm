import java.io.*;
import java.util.*;

public class BOJ17140 {

    static int r, c, k;
    static int row_cnt = 3, col_cnt = 3;
    static int[][] map = new int[101][101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        if(map[r][c]==k) {
            System.out.println(time);
            return;
        }
        while (true) {
            time++;
            if (time > 100) break;
            if (oprt()) break;
        }

        if (time > 100) {
            time = -1;
        }
        System.out.println(time);
    }

    public static boolean oprt() {
        if (row_cnt >= col_cnt) {
            play_r();
        } else {
            play_c();
        }
        if (map[r][c] == k)
            return true;
        else return false;
    }

    public static void play_r() {
        col_cnt = 0;
        for (int i = 1; i <= row_cnt; i++) {
            int[] count = new int[101];
            for (int j = 1; j <= 100; j++) {
                count[map[i][j]]++;
            }
            List<int[]> arr = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                if (count[j] > 0) {
                    arr.add(new int[]{j, count[j]});
                }
            }
            arr.sort((o1, o2) -> {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            });
            Arrays.fill(map[i], 0);
            for (int j = 0; j < arr.size(); j++) {
                int idx = 1 + j * 2;
                if (idx >= 101) break;
                map[i][idx] = arr.get(j)[0];
                map[i][idx + 1] = arr.get(j)[1];
            }
            int max_size = Math.min(arr.size() * 2, 100);
            col_cnt = Math.max(col_cnt, max_size);
        }
    }

    public static void play_c() {
        row_cnt = 0;
        for (int i = 1; i <= col_cnt; i++) {
            int[] count = new int[101];
            for (int j = 1; j <= 100; j++) {
                count[map[j][i]]++;
            }
            List<int[]> arr = new ArrayList<>();
            for (int j = 1; j <= 100; j++) {
                if (count[j] > 0) {
                    arr.add(new int[]{j, count[j]});
                }
            }
            arr.sort((o1, o2) -> {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            });
            for(int j=1;j<=100;j++) map[j][i] = 0;
            for (int j = 0; j < arr.size(); j++) {
                int idx = 1 + j * 2;
                if (idx >= 101) break;
                map[idx][i] = arr.get(j)[0];
                map[idx + 1][i] = arr.get(j)[1];
            }
            int max_size = Math.min(arr.size() * 2, 100);
            row_cnt = Math.max(row_cnt, max_size);
        }
    }
}
