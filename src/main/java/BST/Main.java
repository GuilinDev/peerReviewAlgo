package BST;

public class Main {

    public static void main(String[] args) {

        BST bst = new BST();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
    //    bst.preOrder();
   //     System.out.println();

   //     bst.postOrder();
   //     System.out.println();

    //    bst.preOrderNR();
    //    System.out.println();
        bst.inOrderMorris(bst.getRoot());
        System.out.println();
        bst.preOrderMorris(bst.getRoot());
        System.out.println();
        bst.postOrderMorris(bst.getRoot());
        System.out.println();
        getMorrisPre(bst.getRoot());
    }

    public static void getMorrisPre(Node root){
        if (root == null) return ;
        Node cur = root;
        Node mostRight = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    System.out.println(cur.e);
                    mostRight.right = null;
                }
            }else {
                System.out.println(cur.e);
            }
            cur = cur.right;
        }
    }
}
