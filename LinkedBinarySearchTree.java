import java.util.ArrayList;
import java.util.List;
// import java.util.List;
import java.util.NoSuchElementException;

/**
 * LinkedBinarySearchTree implements the BinarySearchTreeADT interface
 * 
 * @author Java Foundations - modified by Mark Ringrose
 * @version 4.1
 */

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T>
		implements BinarySearchTreeADT<T> {

	public LinkedBinarySearchTree() {
		super();
	}

	/**
	 * Creates a binary search with the specified element as its root.
	 *
	 * @param element the element that will be the root
	 */
	public LinkedBinarySearchTree(T element) {
		super(element);

		if (!(element instanceof Comparable))
			throw new NonComparableElementException("LinkedBinarySearchTree");
	}

	/**
	 * Balances a binary tree using an inOrder traversal and a temporary ArrayList.
	 * Uses brute-force method to methodically insert the middle of the sorted list,
	 * then the middle of the left sublist and the middle of the right sublist.
	 * 
	 */

	public void balanceTree() {

		// instantiate a temporary ArrayList and a new Linked Binary Search Tree
		ArrayList<T> tempList = new ArrayList<T>();

		// in order traversal of existing list
		inOrder(root, tempList);

		// recursively build tree and set root to new tree root
		this.root = buildBalancedTree(tempList, 0, tempList.size() - 1);
	}

	// private worker method that recursively builds tree. Returns BinaryTreeNode.

	private BinaryTreeNode<T> buildBalancedTree(List<T> list, int start, int end) {
		if (start > end)
			return null;

		// get midpoint element and create new node with that element
		int mid = (start + end) / 2;
		T element = list.get(mid);
		BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(element);

		// recursively build the left sub-tree
		newNode.setLeft(buildBalancedTree(list, start, mid - 1));

		// recursively build right sub tree
		newNode.setRight(buildBalancedTree(list, mid + 1, end));

		// return newly created balanced tree
		return newNode;
	}

	/**
	 * Adds the specified object to the binary search tree in the
	 * appropriate position according to its natural order. Note that
	 * equal elements are added to the right.
	 *
	 * @param element the element to be added to the binary search tree
	 */
	@SuppressWarnings("unchecked")
	public void addElement(T element) {
		if (!(element instanceof Comparable))
			throw new NonComparableElementException("LinkedBinarySearchTree");

		Comparable<T> comparableElement = (Comparable<T>) element;

		if (isEmpty())
			root = new BinaryTreeNode<T>(element);
		else {
			if (comparableElement.compareTo(root.getElement()) < 0) {
				if (root.getLeft() == null)
					this.getRootNode().setLeft(new BinaryTreeNode<T>(element));
				else
					addElement(element, root.getLeft());
			} else {
				if (root.getRight() == null)
					this.getRootNode().setRight(new BinaryTreeNode<T>(element));
				else
					addElement(element, root.getRight());
			}
		}
		modCount++;
	}

	/**
	 * Adds the specified object to the binary search tree in the
	 * appropriate position according to its natural order. Note that
	 * equal elements are added to the right.
	 *
	 * @param element the element to be added to the binary search tree
	 */
	@SuppressWarnings("unchecked")
	private void addElement(T element, BinaryTreeNode<T> node) {
		Comparable<T> comparableElement = (Comparable<T>) element;

		if (comparableElement.compareTo(node.getElement()) < 0) {
			if (node.getLeft() == null)
				node.setLeft(new BinaryTreeNode<T>(element));
			else
				addElement(element, node.getLeft());
		} else {
			if (node.getRight() == null)
				node.setRight(new BinaryTreeNode<T>(element));
			else
				addElement(element, node.getRight());
		}
	}

	/**
	 * Returns true if the specified target element is
	 * found in the binary search tree. Returns false otherwise.
	 *
	 * @param targetElement the element being sought in the binary tree
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(T targetElement) {
		BinaryTreeNode<T> current = root;
		boolean result = false;

		if (current.element.equals(targetElement))
			result = true;
		else if (((Comparable<T>) current.element).compareTo(targetElement) < 0 &&
				current.right != null)
			result = (null != findNode(targetElement, current.right));
		else if (current.left != null)
			result = (null != findNode(targetElement, current.left));
		return result;
	}

	/**
	 * Removes the first element that matches the specified target
	 * element from the binary search tree and returns a reference to
	 * it. Throws a NoSuchElementException if the specified target
	 * element is not found in the binary search tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @throws NoSuchElementException if the target element is not found
	 */
	@SuppressWarnings("unchecked")
	public T removeElement(T targetElement)
			throws NoSuchElementException {
		T result = null;

		if (isEmpty())
			throw new NoSuchElementException("LinkedBinarySearchTree");
		else {
			BinaryTreeNode<T> parent = null;
			if (((Comparable<T>) targetElement).equals(root.element)) {
				result = root.element;
				BinaryTreeNode<T> temp = replacement(root);
				if (temp == null)
					root = null;
				else {
					root.element = temp.element;
					root.setRight(temp.right);
					root.setLeft(temp.left);
				}

				modCount--;
			} else {
				parent = root;
				if (((Comparable<T>) targetElement).compareTo(root.element) < 0)
					result = removeElement(targetElement, root.getLeft(), parent);
				else
					result = removeElement(targetElement, root.getRight(), parent);
			}
		}

		return result;
	}

	/**
	 * Removes the first element that matches the specified target
	 * element from the binary search tree and returns a reference to
	 * it. Throws a NoSuchElementException if the specified target
	 * element is not found in the binary search tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @param node          the node from which to search
	 * @param parent        the parent of the node from which to search
	 * @throws NoSuchElementException if the target element is not found
	 */
	@SuppressWarnings("unchecked")
	private T removeElement(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
			throws NoSuchElementException {
		T result = null;

		if (node == null)
			throw new NoSuchElementException("LinkedBinarySearchTree");
		else {
			if (((Comparable<T>) targetElement).equals(node.element)) {
				result = node.element;
				BinaryTreeNode<T> temp = replacement(node);
				if (parent.right == node)
					parent.right = temp;
				else
					parent.left = temp;

				modCount--;

			} else {
				parent = node;
				if (((Comparable<T>) targetElement).compareTo(node.element) < 0)
					result = removeElement(targetElement, node.getLeft(), parent);
				else
					result = removeElement(targetElement, node.getRight(), parent);
			}
		}

		return result;
	}

	/**
	 * Returns a reference to a node that will replace the one
	 * specified for removal. In the case where the removed node has
	 * two children, the inorder successor is used as its replacement.
	 *
	 * @param node the node to be removed
	 * @return a reference to the replacing node
	 */
	private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
		BinaryTreeNode<T> result = null;

		if ((node.left == null) && (node.right == null))
			result = null;

		else if ((node.left != null) && (node.right == null))
			result = node.left;

		else if ((node.left == null) && (node.right != null))
			result = node.right;

		else {
			BinaryTreeNode<T> current = node.right;
			BinaryTreeNode<T> parent = node;

			while (current.left != null) {
				parent = current;
				current = current.left;
			}

			current.left = node.left;
			if (node.right != current) {
				parent.left = current.right;
				current.right = node.right;
			}

			result = current;
		}

		return result;
	}

	/**
	 * Removes elements that match the specified target element from
	 * the binary search tree. Throws a NoSuchElementException if
	 * the specified target element is not found in this tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @throws NoSuchElementException if the target element is not found
	 */
	public void removeAllOccurrences(T targetElement)
			throws NoSuchElementException {
		removeElement(targetElement);

		try {
			while (contains((T) targetElement))
				removeElement(targetElement);
		}

		catch (Exception NoSuchElementException) {
		}
	}

	/**
	 * Removes the node with the least value from the binary search
	 * tree and returns a reference to its element. Throws an
	 * EmptyCollectionException if this tree is empty.
	 *
	 * @return a reference to the node with the least value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T removeMin() throws EmptyCollectionException {
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else {
			if (root.left == null) {
				result = root.element;
				root = root.right;
			} else {
				BinaryTreeNode<T> parent = root;
				BinaryTreeNode<T> current = root.left;
				while (current.left != null) {
					parent = current;
					current = current.left;
				}
				result = current.element;
				parent.left = current.right;
			}

			modCount--;
		}

		return result;
	}

	/**
	 * Removes the node with the highest value from the binary
	 * search tree and returns a reference to its element. Throws an
	 * EmptyCollectionException if this tree is empty.
	 *
	 * @return a reference to the node with the highest value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T removeMax() throws EmptyCollectionException {
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else {
			if (root.right == null) {
				result = root.element;
				root = root.left;
			} else {
				BinaryTreeNode<T> parent = root;
				BinaryTreeNode<T> current = root.right;
				while (current.right != null) {
					parent = current;
					current = current.right;
				}

				result = current.element;
				parent.right = current.left;
			}

			modCount--;
		}

		return result;
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in the binary search tree. Throws a NoSuchElementException if
	 * the specified target element is not found.
	 *
	 * @param targetElement the element being sought in the binary tree
	 * @throws NoSuchElementException if the target element is not found
	 */
	@SuppressWarnings("unchecked")
	public T find(T targetElement) throws NoSuchElementException {
		BinaryTreeNode<T> current = root;

		if (!(current.element.equals(targetElement)) && (current.left != null) &&
				(((Comparable<T>) current.element).compareTo(targetElement) > 0))
			current = findNode(targetElement, current.left);

		else if (!(current.element.equals(targetElement)) && (current.right != null))
			current = findNode(targetElement, current.right);

		if (!(current.element.equals(targetElement)))
			throw new NoSuchElementException("LinkedBinarySearchTree");

		return current.element;
	}

	/**
	 * Returns a reference to the specified target element if it is
	 * found in this tree.
	 *
	 * @param targetElement the element being sought in the tree
	 * @param next          the tree node to begin searching on
	 */
	@SuppressWarnings("unchecked")
	private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next) {
		if (next.element.equals(targetElement)) {
			return next;
		} else if (!(next.element.equals(targetElement)) && (next.left != null) &&
				(((Comparable<T>) next.element).compareTo(targetElement) > 0))
			next = findNode(targetElement, next.left);

		else if (!(next.element.equals(targetElement)) && (next.right != null))
			next = findNode(targetElement, next.right);
		else
			next = null;

		return next;
	}

	/**
	 * Returns the element with the least value in the binary search
	 * tree. It does not remove the node from the binary search tree.
	 * Throws an EmptyCollectionException if this tree is empty.
	 *
	 * @return the element with the least value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T findMin() throws EmptyCollectionException {
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else {
			BinaryTreeNode<T> current = root;

			while (current.left != null)
				current = current.left;

			result = current.element;
		}

		return result;
	}

	/**
	 * Returns the element with the highest value in the binary
	 * search tree. It does not remove the node from the binary
	 * search tree. Throws an EmptyCollectionException if this
	 * tree is empty.
	 *
	 * @return the element with the highest value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T findMax() throws EmptyCollectionException {
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else {
			BinaryTreeNode<T> current = root;

			while (current.right != null)
				current = current.right;

			result = current.element;
		}

		return result;
	}

	/**
	 * Returns the left subtree of the root of this tree.
	 *
	 * @return a link to the left subtree of the tree
	 */
	public LinkedBinarySearchTree<T> getLeft() {
		if (root == null)
			throw new EmptyCollectionException("Get left operation "
					+ "failed. The tree is empty.");

		LinkedBinarySearchTree<T> result = new LinkedBinarySearchTree<T>();
		result.root = root.getLeft();

		return result;
	}

	/**
	 * Returns the right subtree of the root of this tree.
	 *
	 * @return a link to the right subtree of the tree
	 */
	public LinkedBinarySearchTree<T> getRight() {
		if (root == null)
			throw new EmptyCollectionException("Get right operation "
					+ "failed. The tree is empty.");

		LinkedBinarySearchTree<T> result = new LinkedBinarySearchTree<T>();
		result.root = root.getRight();

		return result;
	}

}
