import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class WeightedQuickUnionUFLarger {
    private int[] id;   // parent link (site indexed)
    private int[] sz;   // size of component for roots (site indexed)
    private int[] lg;   // largest node connected
    private int count;  // number of components

    public WeightedQuickUnionUFLarger(int N)
    {
        count = N;
        id = new int[N];
        sz = new int[N];
        lg = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            lg[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int i)
    {
        return lg[root(i)];
    }

    public int count()
    {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    private int root(int p)
    { // Follow links to find a root.
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);

        if (i == j) return;

        int lgp = lg[i]; //Simulating find(i)
        int lgq = lg[j];

        // Make smaller root point to larger one.
        if (sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];

            if (lgp > lgq)
            {
                lg[j] = lgp;
            }
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
            if (lgq > lgp)
            {
                lg[i] = lgq;
            }
        }

        count--;
    }

    public static void main(String[] args)
    {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();                                // Read number of sites.
        WeightedQuickUnionUFLarger uf = new WeightedQuickUnionUFLarger(N);  // Initialize N components.
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();            // Read pair to connect.
            StdOut.println(p + " " + q);        // and print connection.

            if (uf.connected(p, q)) continue;   // Ignore if connected.

            uf.union(p, q);                     // Combine components
        }
        StdOut.println(uf.count() + " components");

        StdOut.println("\n>>>> List of of Larger Nodes");
        int i;
        for (i = 0; i < N; i++)
        {
            StdOut.println("Node " + i + " --> Largest Node Connected: " + uf.find(i));
        }
    }
}
