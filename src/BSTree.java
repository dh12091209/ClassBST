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
        BSTNode<T> parentRoot;
        if(!exists(data)) return;
        while(currentRoot !=null){
            if(data.compareTo(currentRoot.getData()) == 0) {
                BSTNode<T> replaceRoot = findLargest(currentRoot);
                if(replaceRoot != null){
                    
                }else{

                }
            }
            else if(data.compareTo(currentRoot.getData())<0) currentRoot = currentRoot.getLeft();
            else currentRoot = currentRoot.getRight();

        }
    }
    public BSTNode<T> findParentRoot(T data){
        BSTNode<T> parentRoot = null;
        BSTNode<T> currentRoot = root;
        while(data.compareTo(currentRoot.getData()) == 0){
            parentRoot = currentRoot;
            if(data.compareTo(currentRoot.getData())<0) currentRoot = currentRoot.getLeft();
            else currentRoot = currentRoot.getRight();
        }
        return parentRoot;
    }
    public BSTNode<T> findLargest(BSTNode<T> root){
        BSTNode<T> currentRoot = root.getLeft();
        BSTNode<T> parentRoot = null;
        while(currentRoot.getLeft() != null && currentRoot.getRight() != null){
            parentRoot = currentRoot;
            currentRoot = currentRoot.getRight();
        }
        parentRoot.setRight(null);
        return currentRoot;
    }
    public void printInOrderRecur(BSTNode<T> node) {
        if(node == null) return;
        printInOrderRecur(node.getLeft());
        System.out.print(node + ",");
        printInOrderRecur(node.getRight());
    }
}
