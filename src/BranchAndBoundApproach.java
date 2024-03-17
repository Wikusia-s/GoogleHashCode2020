import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;



public class BranchAndBoundApproach {

    public static ArrayList<Library> libraries = new ArrayList<>();
    static Comparator<Library> itemComparator = (a, b) -> {
        double ratio1 = (double) a.getTotalBooksScore() / a.getSignUpProcess();
        double ratio2 = (double) b.getTotalBooksScore() / b.getSignUpProcess();

        return Double.compare(ratio2, ratio1);
    };

    static int bound(Node u, int n, int W, Library[] arr) {
        if (u.weight >= W)
            return 0;

        int profitBound = u.profit;
        int j = u.level + 1;
        float totalWeight = u.weight;

        while (j < n && totalWeight + arr[j].getSignUpProcess() <= W) {
            totalWeight += arr[j].getSignUpProcess();
            profitBound += arr[j].getTotalBooksScore();
            j++;
        }

        if (j < n)
            profitBound += (int) ((W - totalWeight) * arr[j].getTotalBooksScore() / arr[j].getSignUpProcess());

        return profitBound;
    }

    static int knapsack(int W, Library[] arr, int n) {
        Arrays.sort(arr, itemComparator);
        PriorityQueue<Node> priorityQueue =
                new PriorityQueue<>((a, b) -> Integer.compare(b.bound, a.bound));
        Node u, v;

        u = new Node(-1, 0, 0);
        priorityQueue.offer(u);

        int maxProfit = 0;
        Node optimalNode = null;

        while (!priorityQueue.isEmpty()) {
            u = priorityQueue.poll();

            if (u.level == -1)
                v = new Node(0, 0, 0);
            else if (u.level == n - 1)
                continue;
            else
                v = new Node(u.level + 1, u.profit, u.weight);

            v.weight += arr[v.level].getSignUpProcess();
            v.profit += arr[v.level].getTotalBooksScore();
            v.selectedItems.addAll(u.selectedItems); // Copy items from the parent node


            if (v.weight <= W && v.profit > maxProfit) {
                maxProfit = v.profit;
                optimalNode = v; // Update optimal node

            }

            v.bound = bound(v, n, W, arr);

            if (v.bound > maxProfit)
                priorityQueue.offer(v);

            v = new Node(u.level + 1, u.profit, u.weight);
            v.selectedItems.addAll(u.selectedItems); // Copy items from the parent node
            v.bound = bound(v, n, W, arr);

            if (v.bound > maxProfit)
                priorityQueue.offer(v);
        }

        // Print selected items in the optimal solution
        if (optimalNode != null) {
            System.out.println("Selected items: " + optimalNode.selectedItems);
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        int W = 10;
        Item[] arr = {
                new Item(2, 40),
                new Item(3.14f, 50),
                new Item(1.98f, 100),
                new Item(5, 95),
                new Item(3, 30)
        };
        int n = arr.length;

        Problem problem = new Problem("a_example.txt");

        Library[] array = new Library[problem.getLibraries().size()];
        array = problem.getLibraries().toArray(array);


        int maxProfit = knapsack(problem.getProcessDays(), array, array.length);
        System.out.println("Maximum possible profit = " + maxProfit);
    }
}
