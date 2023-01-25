public class BSTree <T extends Comparable<T>> {
    private BSTNode<T> root;

    public BSTree(){
        root = null;
    }

    public void add(T data){
        if(root==null) root = new BSTNode<>(data);
        else addRecur(root,data);

    }

    private void addRecur(BSTNode<T> root, T data){
        //if the data is less than the root
        if(data.compareTo(root.getData())< 0){
            //if left does not exist
            if(root.getLeft() == null){
                root.setLeft(new BSTNode<>(data));
            }else{ //left exists, recursively call myself on left subtree
                addRecur(root.getLeft(),data);
            }
        }
        else{ //the data is greater than the root
            if(root.getRight() == null){
                root.setRight(new BSTNode<>(data));
            }else{ //right exists, recursively call myself on right subtree
                addRecur(root.getRight(),data);
            }
        }
    }
    public void printInOrder(){
        printInOrderRecur(root);
    }

    public boolean exists(T data){
        BSTNode<T> currentRoot = root;
        while(currentRoot !=null){
            if(data.compareTo(currentRoot.getData()) == 0) return true;
            else if(data.compareTo(currentRoot.getData())<0) currentRoot = currentRoot.getLeft();
            else currentRoot = currentRoot.getRight();
        }
        return false;
    }
    public void remove(T data){
        BSTNode<T> currentRoot = root;
        BSTNode<T> parentRoot = root;
        int parentLorR = 0; //0 = no parent, 1 = left, 2 = right
        if(!exists(data)) return;
        while(currentRoot !=null){
//            System.out.println(parentRoot.getData());
            if(data.compareTo(currentRoot.getData()) == 0) {
                BSTNode<T> replaceRoot = replace(currentRoot);
                if(isLeafNode(currentRoot)){
//                    System.out.println(parentRoot.getData());
                    if(parentLorR == 1){
                        parentRoot.setLeft(null);
                    }else{
                        parentRoot.setRight(null);
                    }
                    return;
                }//when is leaf node
                else if(replaceRoot == null){
                    if(parentLorR == 1){
                        parentRoot.setLeft(currentRoot.getRight());
                    }else{
                        parentRoot.setRight(currentRoot.getRight());
                    }
                } // when the node doesn't have left child node
                else{
                    currentRoot.setData(replaceRoot.getData());
                }//delete node and replace it

            }
            else if(data.compareTo(currentRoot.getData())<0) {
//                System.out.println("Left");
                parentRoot = currentRoot;
                currentRoot = currentRoot.getLeft();
                parentLorR = 1;
            }
            else {
//                System.out.println("Right");
                parentRoot = currentRoot;
                currentRoot = currentRoot.getRight();
                parentLorR = 2;
            }

        }
    }

    public BSTNode<T> replace(BSTNode<T> root){
        BSTNode<T> currentRoot = root.getLeft();
        BSTNode<T> parentRoot = root;
        if(currentRoot == null){
            return null;
        }
//        if(currentRoot.getRight() == null){
//            return currentRoot;
//        }
        while(currentRoot.getRight() != null){
            parentRoot = currentRoot;
            currentRoot = currentRoot.getRight();
        }
        if(parentRoot.getLeft() == currentRoot){
            parentRoot.setLeft(currentRoot.getLeft());
        }else if(parentRoot.getRight() == currentRoot){
            parentRoot.setRight(currentRoot.getLeft());
        }
        currentRoot.setLeft(null);
        return currentRoot;

//        BSTNode<T> currentRoot = root.getLeft();
//        if(currentRoot.getData() == null){
//             return null;
//        }
//        BSTNode<T> parentRoot = null;
//        while(currentRoot.getRight() != null){
//            parentRoot = currentRoot;
//            currentRoot = currentRoot.getRight();
//        }
//        if(parentRoot == null){
//            root.setLeft(null);
//        }else{
//            parentRoot.setRight(null);
//        }
//        return currentRoot;
    }
    public boolean isLeafNode(BSTNode<T> node){
        return node.getLeft() == null && node.getRight() == null;
    }
    public void printInOrderRecur(BSTNode<T> node) {
        if(node == null) return;
        System.out.println(traversePreOrder(node));
//        printInOrderRecur(node.getLeft());
//        System.out.print(node + ",");
//        printInOrderRecur(node.getRight());
    }
    public String traversePreOrder(BSTNode<T> root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getData());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }
    public void traverseNodes(StringBuilder sb, String padding, String pointer, BSTNode<T> node,
                              boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getData().toString());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
}
