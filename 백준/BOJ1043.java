import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1043 {

    static int N, M;
    static boolean[] party, person;
    static List<List<Integer>> person_join, party_members;
    static Queue<Integer> truth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        truth = new LinkedList<>();
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) truth.offer(Integer.parseInt(st.nextToken()));
        // 파티정보
        person_join = new ArrayList<>();
        party_members = new ArrayList<>();
        for (int i = 0; i <= N; i++) person_join.add(new ArrayList<>());
        for (int i = 0; i <= M; i++) party_members.add(new ArrayList<>());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int p = Integer.parseInt(st.nextToken());
                party_members.get(i).add(p);
                person_join.get(p).add(i);
            }
        }
        //
        party = new boolean[M + 1];
        person = new boolean[N + 1];
        while (!truth.isEmpty()) {
            int p = truth.poll();
            if (person[p]) continue;
            person[p] = true;
            for (int prt : person_join.get(p)) { // 진실을 아는 사람이 가입한 파티
                if (party[prt]) continue;
                party[prt] = true;

                for (int m : party_members.get(prt)) { // 파티 멤버
                    if (person[m]) continue;
                    truth.add(m);
                }

            }
        }
        int ans = 0;
        for (int i = 1; i <= M; i++)
            if (!party[i]) ans++;
        System.out.println(ans);
    }

}
