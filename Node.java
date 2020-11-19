package com.vveginati.intelligentsystem.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Node implements Cloneable {

	private int array[][] = null;
	private List<Node> child = null;
	private Node parent = null;
	private Integer cost = 0;
	private Integer hueristicCost = null;
	private Index indexZero;
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	public Node() {
	}

	public Node(int a, int b) {

		array = new int[a][b];
		child = new ArrayList<Node>();
	}

	public int[][] generateInitialState() {

		//Scanner scanner = null;

		try {
			//scanner = new Scanner(System.in);

			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					System.out.println("Enter value for initial array i and J "+ i +" "+j );
					Integer integer = new Integer(scanner.nextLine());
					array[i][j] = integer;

					if (array[i][j] == 0) {
						this.indexZero = new Index(i, j);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//scanner.close();
		}

		return array;
	}

	public int[][] generateFinalState() {
		//Scanner scanner = null;

		try {
			//scanner = new Scanner(System.in);

			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					System.out.println("Enter value for Final array i and J "+ i +" "+j );
					Integer integer = new Integer(scanner.nextLine());
					array[i][j] = integer;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//scanner.close();
		}

		return array;
	}

	public void setParentNode(Node parent) {
		this.parent = parent;
		this.parent.child.add(this);
	}

	public Integer getCost() {
		
		return cost;
	}
	
	public void setCost(Node parent) {
		this.cost = parent.getCost() + 1;
	}

	public Integer getHueristicCost() {
		return hueristicCost;
	}

	public Integer getTotalCost() {
		return getCost() + getHueristicCost();
	}

/*	public Integer getManhattanDistance(Node currentState, Node goalState) {

		List<Index> indexList = new ArrayList<Index>();

		for (int i = 0; i < currentState.array.length; i++) {
			for (int j = 0; j < currentState.array.length; j++) {

				if (currentState.array[i][j] != goalState.array[i][j]) {

					// iterate to find the index of the current array index mismatch value.
					Node.Index index = new Node.Index(i, j);
					indexList.add(index);

				}
			}
		}

		Iterator<Index> indexIterator = indexList.iterator();
		Integer costValue = 0;

		while (indexIterator.hasNext()) {

			Index indexObj = indexIterator.next();

			Integer value = currentState.array[indexObj.i][indexObj.j];

			for (int i = 0; i < goalState.array.length; i++) {
				for (int j = 0; j < goalState.array.length; j++) {

					if (value.equals( goalState.array[i][j])) {
						costValue += (Math.abs(i - indexObj.i) + Math.abs(j - indexObj.j));
						break;
					}

				}
			}
		}
		return costValue;
	}*/
	
	public Index getZeroIndex() {
		
		return indexZero;
	}
	
	public void setZeroIndex(int i, int j) {
		
		this.indexZero = new Index(i, j);
	}

	public class Index {
		
		Integer i;
		Integer j;

		public Index(Integer i, Integer j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public String toString() {
			return "Index [i=" + i + ", j=" + j + "]";
		}		
		
	}
	
	public int[][] getArray(){
	
		return array;
	}
	
	public void setArray(int[][] array) {
		this.array = array;
	}
	
	public void setHeuristicCost(Integer heuristicCost) {
		this.hueristicCost = heuristicCost;
	}
	
	public void setChildNode(Node node) {
		this.child.add(node);
	}
	
	public Node getParentNode() {
		return parent;
	}

	@Override
	public String toString() {
		return "\n [" + Arrays.toString(array [0]) + "\n "+Arrays.toString(array [1]) +"\n "+Arrays.toString(array [2]) +"]"+" \t cost=" + cost
				+ ", hueristicCost=" + hueristicCost + ", indexZero=" + indexZero + "]";
	}
	
	
}
