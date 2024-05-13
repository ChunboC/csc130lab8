package lab8;

import java.io.File;

/**
 * <p>
 * Title: Lab8App.java
 * </p>
 *
 * <p>
 * Description: This class contains various recursive methods for solving
 * different problems, including converting a string of digits to a number,
 * finding the minimum value in an array recursively, checking if a string is a
 * palindrome, reversing a string, traversing through directories recursively,
 * solving the Tower of Hanoi problem, reversing the contents of a linked list,
 * and converting a number to a different base.
 * </p>
 *
 * @author Chunbo Cheng
 */

public class Lab8App {

	public static void main(String[] args) {
		Lab8App recursion = new Lab8App();
		System.out.println(recursion.strToNum("12341"));
		System.out.println(recursion.findMin(new int[] { 3, 2, 1, 4, 5 }, 5, 0));
		System.out.println(recursion.isPalindrome("racecar", 0, 6));
		System.out.println(recursion.reverseString("pots&pans"));
		recursion.traverse(new File(""));
		recursion.hanoi(3);
		LinkedList<String> ulist = new LinkedList<String>();
		String[] str = { "hello", "this", "is", "a", "test" };
		for (String s : str)
			ulist.insert(s);
		System.out.println(ulist);
		Node<String> node = ulist.getfront();
		System.out.println(recursion.countNodes(node));
		System.out.println();
		System.out.println("=====================");
		System.out.println("Start of lab 8");
		System.out.println("=====================");
		System.out.println("reversed list: ");
		System.out.println(recursion.reverseList(node) + "\n");
		System.out.println("Sum(1 to 8) = " + recursion.sum(1, 8));
		System.out.println("Sum(1 to 7) = " + recursion.sum(1, 7));
		System.out.println("Sum(1 to 5) = " + recursion.sum(1, 5));
		System.out.println("3^2 = " + recursion.pow(3, 2));
		System.out.println("2^16 = " + recursion.pow(2, 16));
		System.out.println("12 in base 16 = " + recursion.convert(12, 16));
		System.out.println("12 in base 2 = " + recursion.convert(12, 2));
		System.out.println("511 in base 16 = " + recursion.convert(511, 16));
		System.out.println("65535 in base 16 = " + recursion.convert(65535, 16));
		System.out.println("65535 in base 18 = " + recursion.convert(65535, 18));

	}

	public int strToNum(String str) {
		if (str.length() < 1)
			return 0;
		else
			return ((str.charAt(str.length() - 1) - '0') + (10 * strToNum(str.substring(0, str.length() - 1))));
	}

	private <T> int countNodes(Node<T> trav) {
		if (trav == null)
			return 0;
		return 1 + countNodes(trav.getNext());
	}

	public int findMin(int array[], int size, int index) {
		if (index == size - 1)
			return array[index];
		int result = findMin(array, size, index + 1);
		if (array[index] < result)
			return array[index];
		else
			return result;
	}

	public boolean isPalindrome(String str, int low, int high) {
		if (high <= low)
			return true;
		else if (str.charAt(low) != str.charAt(high))
			return false;
		else
			return isPalindrome(str, low + 1, high - 1);
	}

	public String reverseString(String s) {
		if (s.length() == 0)
			return s;
		return reverseString(s.substring(1)) + s.charAt(0);
	}

	public static void traverse(File file) {
		if (file.isDirectory()) {
			System.out.println(file);
			String dirContents[] = file.list();
			if (dirContents != null)
				for (String directory : dirContents)
					traverse(new File(file, directory));
		}
	}

	public static void hanoi(int discs) {
		for (int x = 1; x < (1 << discs); x++) {
			int from = (x & x - 1) % 3;
			int to = ((x | x - 1) + 1) % 3;
			System.out.println("Move " + from + " to " + to);
		}
	}

	public static int sum(int num) {
		int result;
		if (num == 1)
			result = 1;
		else
			result = num + sum(num - 1);
		return result;
	}

	/*
	 * 1. Modify the method that calculates the sum of the integers between 1 and N
	 * shown above. Have the new version match the following recursive definition:
	 * The sum of 1 to N is the sum of 1 to (N/2) plus the sum of (N/2 + 1) to N.
	 * Trace your solution using an N of 7.
	 */
	/**
	 * Calculates the sum of integers between two given numbers.
	 *
	 * @param low  the starting number
	 * @param high the ending number
	 * @return the sum of integers between low and high
	 */
	public static int sum(int low, int high) {
		if (low + 1 == high)
			return low + high;
		else if (low == high)
			return high;
		else
			return sum(low, (low + high) / 2) + sum((low + high) / 2 + 1, high);
	}

	/*
	 * 2. tower of Hanoi recursion
	 */
	/**
	 * This method calculates the number of moves required to solve the Tower of
	 * Hanoi problem recursively.
	 * 
	 * @param numDisc The number of disks to be moved.
	 * @param fromRod The rod from which disks are initially stacked.
	 * @param toRod   The rod to which disks are to be moved.
	 * @param auxRod  The auxiliary rod used for moving disks.
	 * @return The total number of moves required to solve the Tower of Hanoi
	 *         problem.
	 */
	public static int recursiveHanoi(int numDisc, String fromRod, String toRod, String auxRod) {
		// base case
		if (numDisc == 1)
			return 1;
		// Recursive case
		int moves = recursiveHanoi(numDisc - 1, fromRod, auxRod, toRod)
				+ recursiveHanoi(numDisc - 1, auxRod, toRod, fromRod) + 1;
		return moves;
	}

	/*
	 * 3. Write a recursive definition of x^y, where x and y are integers and y >=
	 * 0. In addition, write the recursive method.
	 */
	/**
	 * This method calculates the result of exponentiation recursively.
	 * 
	 * @param base     The base number.
	 * @param exponent The exponent to which the base is raised.
	 * @return The result of the base raised to the power of the exponent.
	 */
	public static int pow(int base, int exponent) {
		if (exponent == 0)
			return 1;
		else if (exponent % 2 == 1)
			return base * pow(base, (exponent - 1) / 2) * (pow(base, (exponent - 1) / 2));
		else
			return pow(base, exponent / 2) * (pow(base, exponent / 2));
	}

	/*
	 * 4. Write a recursive method to display the contents of a linked-list in
	 * reverse order.
	 */
	/**
	 * Recursively displays the contents of a linked list in reverse order.
	 *
	 * @param trav the current node being traversed
	 * @param <T>  the type of data stored in the linked list
	 * @return the contents of the linked list in reverse order as a string
	 */
	public static <T> String reverseList(Node<T> trav) {
		// base case
		if (trav == null)
			return "";
		// recursive step
		return reverseList(trav.getNext()) + trav.getData() + " ";
	}

	/*
	 * 5. Write a recursive method to convert a number, n, to a base, b, and return
	 * result as a String.
	 */
	/**
	 * Recursively converts a number to a specified base and returns the result as a
	 * string.
	 *
	 * @param n the number to be converted
	 * @param b the base to convert to
	 * @return the number converted to the specified base as a string
	 */
	public static String convert(int n, int b) {
		int remainder = n % b;
		char element = (char) (remainder < 10 ? remainder + '0' : remainder - 10 + 'A');
		if (n < b)
			return element + "";
		else
			return convert(n / b, b) + element;
	}

}

class Node<E> {
	private E data;
	private Node<E> next;

	public Node() {
		data = null;
		next = null;
	}

	public Node(E d) {
		data = d;
		next = null;
	}

	public Node(E d, Node<E> n) {
		data = d;
		next = n;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> n) {
		next = n;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
}

class LinkedList<T extends Comparable<T>> {

	protected Node<T> head = new Node<T>(); // dummy Node
	protected int numItems;

	public Node<T> getfront() {
		return head.getNext();
	}

	public int getSize() {
		return numItems;
	}

	public boolean isEmpty() {
		return numItems == 0;
	}

	public void insert(T insertItem) {
		if (insertItem == null)
			throw new NullPointerException();
		Node<T> trav = head;
		while (trav.getNext() != null)
			trav = trav.getNext();
		trav.setNext(new Node<T>(insertItem));
		++numItems;
	}

	public String toString() {
		String str = "\n==================================\n" + "The list contains " + numItems + " items.\n"
				+ "==================================\n[";
		Node<T> trav = head.getNext();
		while (trav != null) {
			// str += trav.data + "\n";
			str += trav.getData() + ((trav.getNext() == null) ? "" : "->");
			trav = trav.getNext();
		}
		return str + "]";
	}
}