public class Main {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();

        tree.add(100);
        tree.add(50);
        tree.add(156);
        tree.add(75);
        tree.add(34);
        tree.add(120);
        tree.add(222);

        tree.printInOrder();
        System.out.println(tree.exists(1));
    }
}
