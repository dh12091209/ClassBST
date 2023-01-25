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
        tree.add(74);
        tree.add(34);
        tree.add(38);
        tree.add(40);
        tree.add(37);
        tree.add(22);


//        tree.printInOrder();
        tree.printInBinary();
        System.out.println(tree.exists(1));
        tree.remove(50);
//        tree.printInOrder();
        tree.printInBinary();
        tree.balance();
        tree.printInBinary();
    }
}
