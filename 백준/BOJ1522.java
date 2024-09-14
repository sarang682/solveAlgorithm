import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1522 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int cnt = 0; // 총 a개수
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a') cnt++;
        }
        // 초기값 설정
        int max = 0;
        for (int i = 0; i < cnt; i++) {
            if (str.charAt(i) == 'a') max++;
        }
        int now = max;
        // 슬라이딩 윈도우
        for (int i = 1; i < str.length(); i++) {
            int end = (i + cnt - 1) % str.length();
            if (str.charAt(i - 1) == 'a') now--;
            if (str.charAt(end) == 'a') now++;
            max = Math.max(max, now);
        }
        System.out.println(cnt - max);
    }
}
