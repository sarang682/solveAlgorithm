import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ20191 {
    static String S, T;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            list.get(ch - 'a').add(i);
        }
        for (int i = 0; i < 26; i++) {
            Collections.sort(list.get(i));
        }
        int ans = 1;
        int idx = -1;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            ArrayList<Integer> li = list.get(ch - 'a');
            if (li.isEmpty()) {
                System.out.println(-1);
                return;
            }
            // binarySearch
            int lo = 0;
            int hi = li.size();
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (li.get(mid) <= idx) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            if (lo == li.size()) {
                ans++;
                idx = li.get(0);
            } else {
                idx = li.get(lo);
            }
        }
        System.out.println(ans);
    }
}
