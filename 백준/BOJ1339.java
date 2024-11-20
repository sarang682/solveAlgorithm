import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1339 {

    static class Alpha {
        char ch;
        int cnt;

        public Alpha(char ch, int cnt) {
            this.ch = ch;
            this.cnt = cnt;
        }
    }

    static int N;
    static String[] str;
    static Alpha[] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = new String[N];
        memo = new Alpha[26];
        for (int i = 0; i < 26; i++) {
            memo[i] = new Alpha((char) ('A' + i), 0);
        }
        for (int i = 0; i < N; i++) {
            str[i] = br.readLine();
            for (int j = 1; j <= str[i].length(); j++) {
                char c = str[i].charAt(str[i].length() - j);
                memo[c - 'A'].cnt += (int) Math.pow(10, j);
            }
        }
        Arrays.sort(memo, (o1, o2) -> o2.cnt - o1.cnt);
        int[] val = new int[26];
        int v = 9;
        for (int i = 0; i < 26; i++) {
            if (memo[i].cnt == 0) break;
            val[memo[i].ch - 'A'] = v--;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                char c = str[i].charAt(j);
                if (c < 'A' || c > 'Z') continue;
                str[i] = str[i].replace(c, (char) ('0' + val[c - 'A']));
            }
            ans += Integer.parseInt(str[i]);
        }
        System.out.println(ans);
    }
}
