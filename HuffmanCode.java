import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {

    public static void main(String[] args) {
        // Your existing main method code...
    }
    public static int[] getCharacterFrequency(String text) {
        int[] counts = new int[256];
    
        for (int i = 0; i < text.length(); i++) {
            counts[(int) text.charAt(i)]++;
        }
    
        return counts;
    }

    public static Tree getHuffmanTree(int[] counts) {
        PriorityQueue<Tree> heap = new PriorityQueue<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char) i));
        }

        while (heap.size() > 1) {
            Tree t1 = heap.poll();
            Tree t2 = heap.poll();
            heap.add(new Tree(t1, t2));
        }

        return heap.poll();
    }

    public static class Tree implements Comparable<Tree> {
        Node root;

        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }

        @Override
        public int compareTo(Tree t) {
            return Integer.compare(root.weight, t.root.weight);
        }

        public static class Node {
            char element;
            int weight;
            Node left;
            Node right;
            String code = "";

            public Node() {
            }

            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }

        public static class Codes {
            private final Map<Character, String> codeTable = new HashMap<>();

            public Codes(Node root) {
                buildCodeTable(root, "");
            }

            private void buildCodeTable(Node node, String code) {
                if (node != null) {
                    if (node.left == null && node.right == null) {
                        codeTable.put(node.element, code);
                    }
                    buildCodeTable(node.left, code + "0");
                    buildCodeTable(node.right, code + "1");
                }
            }

            

            public Map<Character, String> getCodeTable() {
                return codeTable;
            }
        }
        
    }
}
