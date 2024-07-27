import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16472 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();

        int[] alpha = new int[26];
        int s = 0, e = 0, cnt = 0, ans = 0;

        while (e < str.length) {
            if (alpha[str[e] - 'a'] == 0) { // 첫 방문
                cnt++;
            }
            alpha[str[e] - 'a']++;
            if (cnt > N) {
                cnt--;
                while (true) {
                    if(--alpha[str[s++] - 'a'] <= 0) break;
                }
            }
            ans = Math.max(ans, e - s + 1);
            e++;
        }
        System.out.println(ans);
    }
}
