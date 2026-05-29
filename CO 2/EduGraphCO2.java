// Save as: EduGraphCO2.java
// Run:
// javac EduGraphCO2.java
// java EduGraphCO2

import java.util.*;


// ---------------- B+ TREE SIMULATION ----------------

class BPlusTree {

    TreeMap<Integer, String> data = new TreeMap<>();

    void insert(int id, String course) {
        data.put(id, course);
    }

    void display() {

        System.out.println("\nB+ Tree Course Records:");

        for (Map.Entry<Integer, String> entry : data.entrySet()) {

            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    void search(int id) {

        if (data.containsKey(id)) {

            System.out.println("\nB+ Tree Search Result:");
            System.out.println(id + " - " + data.get(id));

        } else {

            System.out.println("Course Not Found");
        }
    }
}


// ---------------- SEGMENT TREE ----------------

class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] arr) {

        n = arr.length;
        tree = new int[4 * n];

        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int start, int end) {

        if (start == end) {

            tree[node] = arr[start];

        } else {

            int mid = (start + end) / 2;

            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    int rangeQuery(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        int leftSum = rangeQuery(2 * node + 1, start, mid, l, r);
        int rightSum = rangeQuery(2 * node + 2, mid + 1, end, l, r);

        return leftSum + rightSum;
    }
}


// ---------------- FENWICK TREE ----------------

class FenwickTree {

    int[] bit;
    int n;

    FenwickTree(int size) {

        n = size;
        bit = new int[n + 1];
    }

    void update(int index, int value) {

        while (index <= n) {

            bit[index] += value;

            index += index & (-index);
        }
    }

    int query(int index) {

        int sum = 0;

        while (index > 0) {

            sum += bit[index];

            index -= index & (-index);
        }

        return sum;
    }
}


// ---------------- MAIN CLASS ----------------

public class EduGraphCO2 {

    public static void main(String[] args) {

        // ---------------- B+ TREE ----------------

        BPlusTree bpt = new BPlusTree();

        bpt.insert(101, "Python");
        bpt.insert(102, "Data Structures");
        bpt.insert(105, "Machine Learning");

        bpt.display();

        bpt.search(102);


        // ---------------- SEGMENT TREE ----------------

        int[] quizScores = {80, 75, 90, 85, 70};

        SegmentTree st = new SegmentTree(quizScores);

        System.out.println("\nSegment Tree Range Query:");
        System.out.println("Total Score from Index 1 to 3 = "
                + st.rangeQuery(0, 0, quizScores.length - 1, 1, 3));


        // ---------------- FENWICK TREE ----------------

        FenwickTree ft = new FenwickTree(5);

        ft.update(1, 80);
        ft.update(2, 75);
        ft.update(3, 90);
        ft.update(4, 85);
        ft.update(5, 70);

        System.out.println("\nFenwick Tree Prefix Sum:");
        System.out.println("Total Score till Index 3 = " + ft.query(3));
    }
}