import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ20440 {

    static int N;
    static TreeMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (map.containsKey(s)) {
                map.get(s);
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
            if (map.containsKey(e)) {
                map.get(e);
                map.put(e, map.get(e) - 1);
            } else {
                map.put(e, -1);
            }
        }
        int s = 0;
        int e = 0;
        int now = 0;
        int max = 0;
        boolean f = false;
        for (int key : map.keySet()) {
            now += map.get(key);
            if (f) {
                e = key;
                f = false;
                if (now == max) {
                    f = true;
                    continue;
                }
            }
            if (now > max) {
                max = now;
                s = key;
                f = true;
            }
        }
        System.out.println(max);
        System.out.println(s + " " + e);
    }
}
