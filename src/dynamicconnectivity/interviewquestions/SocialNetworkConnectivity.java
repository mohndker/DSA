package dynamicconnectivity.interviewquestions;

public class SocialNetworkConnectivity {
    private int[] parent;
    private int[] size;
    private int componentCount;

    public SocialNetworkConnectivity(int n) {
        if (n <= 0) throw new IllegalArgumentException("Number (n) of elements should greater than 0");
        parent = new int[n];
        size = new int[n];
        componentCount = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int root(int child) {
        if (child <= 0 || child > parent.length)
            throw new IllegalArgumentException("parent of element should be greater than 0 and less than n: "
                    + parent.length + " where n is the size of the elements array");

        while (child != parent[child]) {
            parent[child] = parent[parent[child]];
            child = parent[child];
        }

        return child;
    }

    public boolean connected(int person1, int person2) {
       validateParams(person1, person2);

        return parent[person1] == parent[person2];
    }

    public void union(int person1, int person2) {
        validateParams(person1, person2);

        int person1Root = root(person1);
        int person2Root = root(person2);

        if (person1Root == person2Root) return;

        if (size[person1Root] < size[person2Root]) {
            parent[person1Root] = person2Root;
            size[person2Root] += size[person1Root];
        } else {
            parent[person2Root] = person1Root;
            size[person1Root] += size[person2Root];
        }

        componentCount--;
    }

    public int getComponentCount() {
        return componentCount;
    }

    public boolean isAllConnected() {
        return componentCount == 1;
    }

    private void validateParams(int person1, int person2) {
        if ((person1 <= 0 || person1 > parent.length)
                || (person2 <= 0 || person2 > parent.length))
            throw new IllegalArgumentException("Parameters to be connected should be in range from 0 to n: "
                    + parent.length + " where n is length of elements array");
    }

    public static void main(String[] args) {
        
    }
}
