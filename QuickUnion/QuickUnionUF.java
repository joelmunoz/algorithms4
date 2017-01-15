import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class QuickUnionUF
{
    private int[] id;   // parent link (site indexed)
    private int count;  // number of components

    public QuickUnionUF (int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
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
        while (p != id[p])
        {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public int successor(int i)
    {
        return root(i + 1);
    }

    public void delete(int i)
    {
        union(i, i+1);
    }

    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);

        if (i == j) return;

        id[i] = j;

        count--;
    }

    public static void main(String[] args)
    {
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();                                // Read number of sites.
        QuickUnionUF uf = new QuickUnionUF(N);  // Initialize N components.
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
            StdOut.println("Node " + i + " --> Largest Node Connected: " + uf.root(i));
        }
    }

}
