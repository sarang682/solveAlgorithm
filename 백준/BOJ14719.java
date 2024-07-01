import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H, W;
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int max = 0;
        int ans = 0;
        int[] height = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            if (height[i] > max) {
                max = height[i];
            } else if (height[i] < max) {
                ans += max - height[i];
            }
        }
        int idx = W - 1;
        int tmp_max = height[W - 1];
        while (height[idx] < max) {
            if (tmp_max < height[idx]) {
                tmp_max = height[idx];
            }
            ans -= max - tmp_max;
            idx--;
        }
        System.out.println(ans);
    }
}
