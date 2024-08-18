import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17612 {

    static class Person {
        long id;
        int w;

        public Person(long id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static class Counter {
        int id;
        long person;
        int time;

        public Counter(int id, long person, int time) {
            this.id = id;
            this.person = person;
            this.time = time;
        }
    }

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Queue<Person> people = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long id = Long.parseLong(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            people.offer(new Person(id, w));
        }

        PriorityQueue<Integer> empty = new PriorityQueue<>(); // 빈 계산대
        PriorityQueue<Counter> counters = new PriorityQueue<>((o1, o2) -> { // 계산 중
            if (o1.time == o2.time)
                return o2.id - o1.id;
            return o1.time - o2.time;
        });

        // 초기값 세팅
        int time = 0;
        for (int i = 1; i <= K; i++) {
            empty.offer(i);
        }
        while (!empty.isEmpty() && !people.isEmpty()) { // 빈 계산대에 사람 넣어주기
            int id = empty.poll();
            Person p = people.poll();
            counters.offer(new Counter(id, p.id, time + p.w));
        }

        long ans = 0;
        long order = 1; // 나오는 순서
        while (!counters.isEmpty()) {
            time = counters.peek().time; // 계산 빨리 끝나는 곳
            while (!counters.isEmpty() && counters.peek().time == time) {
                Counter c = counters.poll();
                ans += order++ * c.person;
                empty.offer(c.id); // 빈 계산대에 넣어주기
            }

            while (!empty.isEmpty() && !people.isEmpty()) { // 빈 계산대에 사람 넣어주기
                int id = empty.poll();
                Person p = people.poll();
                counters.offer(new Counter(id, p.id, time + p.w));
            }
        }
        System.out.println(ans);
    }
}
