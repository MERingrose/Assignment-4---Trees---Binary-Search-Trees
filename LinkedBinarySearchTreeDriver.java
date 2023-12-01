
/**
 * LinkedBinarySearchTreeDriver exercises the methods and functionality of the
 * LinkedBinarySearchTree class
 * 
 * 
 */

public class LinkedBinarySearchTreeDriver {
    public static void main(String[] args) {

        // instantiate two LinkedBinarySearchTrees, one for Integers, one for Strings
        LinkedBinarySearchTree<Integer> intTree = new LinkedBinarySearchTree<Integer>();
        LinkedBinarySearchTree<String> strTree = new LinkedBinarySearchTree<String>();

        // create an array of integers and an array of strings to be added to the trees
        int[] intArr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                24, 25, 26, 27, 28, 29, 30, 31 };
        String[] strArr = new String[] { "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel",
                "India", "Juliette", "Kilo" };

        // add all integers from the array to the tree
        for (int x : intArr)
            intTree.addElement(x);

        // add all Strings from the array to the tree
        for (String y : strArr)
            strTree.addElement(y);

        // print out tree to confirm additions and height
        System.out.println(intTree.toString());
        System.out.println("Integer Tree Height: " + intTree.getHeight());
        System.out.println("Integer Left Tree Height: " + intTree.getLeft().getHeight());
        System.out.println("Integer Right Tree Height: " + intTree.getRight().getHeight());

        // balance Integer tree
        intTree.balanceTree();

        System.out.println("Integer Tree Height after balancing: " + intTree.getHeight());
        System.out.println("Integer Left Tree Height after balancing: " + intTree.getLeft().getHeight());
        System.out.println("Integer Left Tree after balancing: " + intTree.getLeft().toString());
        System.out.println("Integer Right Tree Height after balancing: " + intTree.getRight().getHeight());
        System.out.println("Integer Right Tree after balancing: " + intTree.getRight().toString());

        System.out.println(strTree.toString());
        System.out.println("String Tree Height: " + strTree.getHeight());
        System.out.println("String Left Tree Height: " + strTree.getLeft().getHeight());
        System.out.println("String Right Tree Height: " + strTree.getRight().getHeight());

        // balance string tree
        strTree.balanceTree();

        // output string elements and the height of the tree and left & right subtrees
        System.out.println("String tree after balancing Height: " + strTree.getHeight());
        System.out.println("String Left Subtree after balancing Height: " + strTree.getLeft().getHeight());
        System.out.println("String Right Subtree after balancing Height: " + strTree.getRight().getHeight());

        // find and contains methods
        System.out.println(strTree.find("Kilo")); // returns node
        System.out.println(intTree.find(11)); // returns node
        System.out.println(strTree.contains("Echo")); // true
        System.out.println(strTree.contains("Alpha")); // true
        System.out.println(strTree.contains("Kilo")); // true
        System.out.println(strTree.contains("Turkey")); // false
        System.out.println(intTree.contains(99)); // false

        System.out.println("Integer tree size: " + intTree.size());
        System.out.println("String tree size: " + strTree.size());

        // testing removeElement and removeMin & Max
        intTree.removeElement(11);
        strTree.removeElement("Echo");
        intTree.removeMax();
        strTree.removeMax();
        intTree.removeMin();
        strTree.removeMin();
        System.out.println("Integer tree size after removal: " + intTree.size());
        System.out.println("String tree size after removal: " + strTree.size());

    }
}
