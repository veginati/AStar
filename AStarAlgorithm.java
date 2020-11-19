package com.vveginati.intelligentsystem.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.vveginati.intelligentsystem.project.Node.Index;

public class AStarAlgorithm {

	List<Node> priorityQueue = null;
	List<Node> exploredNodees = null;
	List<Node> totalNodes = null;

	public AStarAlgorithm() {

		priorityQueue = new ArrayList<Node>();
		exploredNodees = new ArrayList<Node>();
		totalNodes = new ArrayList<Node>();
	}

	public static void main(String[] args) {
		AStarAlgorithm aStarAlgObj = new AStarAlgorithm();
		aStarAlgObj.execute();
	}
	

	public void execute() {


		// created two nodes
		Node initialNode = new Node(3, 3);
		Node finalNode = new Node(3, 3);

		// generate initial node
		initialNode.generateInitialState();

		// generate final node
		finalNode.generateFinalState();
		
		
		calculateManhattanDistance(initialNode, finalNode);

		//aStarAlgObj.totalNodes.add(initialNode);
		priorityQueue.add(initialNode);

		while (priorityQueue.size() > 0) {

			Node nodeTobeExplored = priorityQueue.get(0);
			priorityQueue.remove(0);

			if (null != nodeTobeExplored.getHueristicCost() && nodeTobeExplored.getHueristicCost() == 0) {
				System.out.println("node explored matched the finalNode");

				System.out.println(" \n # Generate parent nodes for the parent node start ");
				generateParentNodePath(nodeTobeExplored);
				System.out.println("\n # Generate parent nodes for the parent node end ");

				break;
			} else {
				Node goalNode = generateNodes(nodeTobeExplored, finalNode);

				if (null != goalNode) {
					System.out.println(" \n # Generate parent nodes for the parent node start ");
					generateParentNodePath(goalNode);
					System.out.println("\n # Generate parent nodes for the parent node end ");
					break;
				}
			}

			System.out.println(" Before Sorting ###################### " + priorityQueue);
			sortExploredNodes();
			System.out.println(" After Sorting  ######################" + priorityQueue);

			exploredNodees.add(nodeTobeExplored);
		}
		
		System.out.println(" Total number of nodes generated "+totalNodes.size());
		System.out.println(" Total number of nodes explored "+exploredNodees.size());
		System.out.println(" Total number of nodes in the queue "+priorityQueue.size());

	
	}

	public Node generateNodes(Node currentNode, Node finalNode) {
		
		for (int i=0;i<4;i++) {
			Node node = createNode(currentNode, i, finalNode);
			
			if (null != node && (null==currentNode.getParentNode() || !Arrays.deepEquals(currentNode.getParentNode().getArray(), node.getArray()))) {
				
				System.out.println(" Node ##############  " + node);
				priorityQueue.add(node);
				totalNodes.add(node);
				currentNode.setChildNode(node);

				if (node.getHueristicCost() == 0) {
					return node;
				}
			}
			
		}

		return null;
	}

	public Node createNode(Node currentNode, Integer shift, Node finalNode) {

		Node.Index indexObj = currentNode.getZeroIndex();

		int tempindexI = indexObj.i;
		int tempindexJ = indexObj.j;

		int indexI = tempindexI;
		int indexJ = tempindexJ;
		int actualValue = currentNode.getArray()[indexI][indexJ];

		if (shift == 0) {
			// left shift
			indexJ -= 1;
		} else if (shift == 1) {

			// right shift
			indexJ += 1;
		} else if (shift == 2) {

			// top shift
			indexI -= 1;
		} else if (shift == 3) {
			// down shift
			indexI += 1;
		}

		if (indexJ >= 0 && indexJ <= 2 && indexI <= 2 && indexI >= 0) {

			Node node = new Node(currentNode.getArray().length, currentNode.getArray()[0].length);

			for (int f = 0; f < currentNode.getArray().length; f++) {
				node.getArray()[f] = currentNode.getArray()[f].clone();
			}

			node.setCost(currentNode);
			node.setParentNode(currentNode);

			int tempValue = node.getArray()[indexI][indexJ];

			node.getArray()[indexI][indexJ] = actualValue;
			node.getArray()[tempindexI][tempindexJ] = tempValue;
			node.setZeroIndex(indexI, indexJ);

			// Hueristic cost of the current node is set inside the calculate Manhattan
			// distance method.
			calculateManhattanDistance(node, finalNode);
			return node;
		}

		return null;
	}

	public void sortExploredNodes() {

		priorityQueue.sort(new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {

				if (node1.getTotalCost() < node2.getTotalCost()) {
					return -1;
				} else if (node1.getTotalCost() > node2.getTotalCost()) {
					return 1;
				} else {
					return node1.getHueristicCost().compareTo(node2.getHueristicCost());
				}
			}
		});
	}

	public void calculateManhattanDistance(Node currentState, Node goalState) {

		List<Index> indexList = new ArrayList<Index>();

		for (int i = 0; i < currentState.getArray().length; i++) {
			for (int j = 0; j < currentState.getArray().length; j++) {

				if (currentState.getArray()[i][j] != goalState.getArray()[i][j]) {
					// iterate to find the index of the current array index mismatch value.
					if (currentState.getArray()[i][j] != 0) {
						Node.Index index = currentState.new Index(i, j);
						indexList.add(index);
					}

				}
			}
		}

		Iterator<Index> indexIterator = indexList.iterator();
		Integer costValue = 0;

		while (indexIterator.hasNext()) {

			Index indexObj = indexIterator.next();

			Integer value = currentState.getArray()[indexObj.i][indexObj.j];

			for (int i = 0; i < goalState.getArray().length; i++) {
				for (int j = 0; j < goalState.getArray().length; j++) {

					if (value.equals(goalState.getArray()[i][j])) {
						costValue += (Math.abs(i - indexObj.i) + Math.abs(j - indexObj.j));
						break;
					}
				}
			}
		}
		currentState.setHeuristicCost(costValue);

	}

	public void generateParentNodePath(Node node) {

		if (null == node.getParentNode()) {
			System.out.print(node + "\t ");
		} else {
			generateParentNodePath(node.getParentNode());
			System.out.print(node + "\t ");
		}

	}

}
