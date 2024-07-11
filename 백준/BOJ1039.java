import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1039 {

    static class Info {
        int[] num;
        int depth;

        public Info(int[] num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] input = st.nextToken().toCharArray();
        int K = Integer.parseInt(st.nextToken());
        int M = input.length;
        if (M <= 1) {
            System.out.println(-1);
            return;
        }
        int[] nums = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            nums[i] = input[i - 1] - '0';
        }

        boolean[][] chk = new boolean[K + 1][1_000_001];
        int ans = -1;
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(nums, 0));
        while (!queue.isEmpty()) {
            Info now = queue.poll();
            if (now.depth >= K) {
                ans = Math.max(ans, getInt(now.num, M));
                continue;
            }
            for (int i = 1; i <= M; i++) {
                for (int j = i + 1; j <= M; j++) {
                    if (i == 1 && now.num[j] == 0) continue;
                    int[] copy = now.num.clone();
                    swap(i, j, copy);
                    int get = getInt(copy, M);
                    if (!chk[now.depth + 1][get]) {
                        chk[now.depth + 1][get] = true;
                        queue.offer(new Info(copy, now.depth + 1));
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static int getInt(int[] arr, int M) {
        int result = 0;
        int tmp = 1;
        for (int i = 0; i < M; i++) {
            result += arr[M - i] * tmp;
            tmp *= 10;
        }
        return result;
    }
}
