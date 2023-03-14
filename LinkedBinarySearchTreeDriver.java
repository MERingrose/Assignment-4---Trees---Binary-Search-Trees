public class LinkedBinarySearchTreeDriver {
    public static void main(String[] args) {

        //instantiate two LinkedBinarySearchTrees, one for Integers, one for Strings
        LinkedBinarySearchTree<Integer> intTree = new LinkedBinarySearchTree<Integer>();
        LinkedBinarySearchTree<String> strTree = new LinkedBinarySearchTree<String>();

        //create an array of integers and an array of strings to be added to the trees
        int[] intArr = new int[] { 1,22, 39, 5, 48, 98, 3, 78, 12, 77, 4, 25, 33, 55, 56, 11, 351, 896, 569 };
        String[] strArr = new String[] {"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliette", "Kilo"};

        //add all integers from the array to the tree
        for (int x : intArr)
            intTree.addElement(x);

        //add all Strings from the array to the tree
        for (String y : strArr)
            strTree.addElement(y);

        //print out tree to confirm additions and height
        System.out.println(intTree.toString());
        System.out.println("Tree Height: " + intTree.getHeight());
        System.out.println("Left Tree Height: " + intTree.getLeft().getHeight());
        System.out.println("Right Tree Height: " + intTree.getRight().getHeight());
        
        // balance Integer tree
        intTree.balanceTree();

        System.out.println(intTree.toString());
        System.out.println("Tree Height: " + intTree.getHeight());
        System.out.println("Left Tree Height: " + intTree.getLeft().getHeight());
        System.out.println("Right Tree Height: " + intTree.getRight().getHeight());

        System.out.println(strTree.toString());
        System.out.println("Tree Height: " + strTree.getHeight());
        System.out.println("Left Tree Height: " + strTree.getLeft().getHeight());
        System.out.println("Right Tree Height: " + strTree.getRight().getHeight());
        
        //balance string tree
        strTree.balanceTree();

        System.out.println(strTree.toString());
        System.out.println("Tree Height: " + strTree.getHeight());
        System.out.println("Left Tree Height: " + strTree.getLeft().getHeight());
        System.out.println("Right Tree Height: " + strTree.getRight().getHeight());

        System.out.println(strTree.find("Kilo")); //returns node
        System.out.println(strTree.contains("India")); //true
        System.out.println(strTree.contains("Echo")); //true
        System.out.println(strTree.contains("Alpha")); //true
        System.out.println(strTree.contains("Kilo")); //true
        System.out.println(strTree.contains("Turkey")); //false

        System.out.println(intTree.contains(99)); //false
        System.out.println(intTree.size());
        System.out.println(strTree.size());

        intTree.removeElement(11);
        System.out.println(intTree.size());
        
    }
}
