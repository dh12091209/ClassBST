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
        int parentLorR = 0; //0 = no parent, 1 = left, 2 = right
        if(!exists(data)) return;
        while(currentRoot !=null){
            parentRoot = currentRoot;
            if(data.compareTo(currentRoot.getData()) == 0) {
                BSTNode<T> replaceRoot = replace(currentRoot);
                if(isLeafNode(currentRoot)){
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

                }//delete node and replace it 

            }
            else if(data.compareTo(currentRoot.getData())<0) {
                currentRoot = currentRoot.getLeft();
                parentLorR = 1;
            }
            else {
                currentRoot = currentRoot.getRight();
                parentLorR = 2;
            }

        }
    }

//    public BSTNode<T> findParentRoot(T data){
//        BSTNode<T> parentRoot = null;
//        BSTNode<T> currentRoot = root;
//        while(data.compareTo(currentRoot.getData()) == 0){
//            parentRoot = currentRoot;
//            if(data.compareTo(currentRoot.getData())<0) currentRoot = currentRoot.getLeft();
//            else currentRoot = currentRoot.getRight();
//        }
//        return parentRoot;
//    }
    public BSTNode<T> replace(BSTNode<T> root){
        BSTNode<T> currentRoot = root.getLeft();
        if(currentRoot.getData() == null){
             return null;
        }
        if(currentRoot.getRight() == null){
            return currentRoot;
        }
        while(currentRoot.getRight() != null){
            currentRoot = currentRoot.getRight();
        }
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
        printInOrderRecur(node.getLeft());
        System.out.print(node + ",");
        printInOrderRecur(node.getRight());
    }
}
