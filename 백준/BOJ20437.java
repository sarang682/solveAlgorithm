import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ20437 {

    static String str;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            str = br.readLine();
            K = Integer.parseInt(br.readLine());
            ArrayList<ArrayList<Integer>> idx = new ArrayList<>();
            for (int i = 0; i < 26; i++)
                idx.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++)
                idx.get(str.charAt(i) - 'a').add(i);
            int min = 10_001;
            int max = -1;
            for (ArrayList<Integer> list : idx) {
                if (list.size() < K) continue;
                for (int i = K - 1; i < list.size(); i++) {
                    int v = list.get(i) - list.get(i - K + 1);
                    min = Math.min(min, v);
                    max = Math.max(max, v);
                }
            }
            if (min == 10_001) {
                sb.append(-1).append("\n");
            } else {
                sb.append(min + 1).append(" ").append(max + 1).append("\n");
            }
        }
        System.out.println(sb);
    }
}
