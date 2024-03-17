import java.util.ArrayList;
import java.util.List;

class Node {
    int level, profit, bound;
    float weight;

    List<Library> selectedItems;
    Node(int level, int profit, float weight) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.selectedItems = new ArrayList<>();
    }
}