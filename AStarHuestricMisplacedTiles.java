package com.vveginati.intelligentsystem.project;

public class AStarHuestricMisplacedTiles extends AStarAlgorithm {

	@Override
	public void calculateManhattanDistance(Node currentState, Node goalState) {

		Integer costValue = 0;

		for (int i = 0; i < currentState.getArray().length; i++) {
			for (int j = 0; j < currentState.getArray().length; j++) {

				if (currentState.getArray()[i][j] != goalState.getArray()[i][j]) {
					// iterate to find the index of the current array index mismatch value.
					costValue += 1;

				}
			}
		}

		currentState.setHeuristicCost(costValue);
	}
	
	public static void main(String[] args) {
	
		AStarHuestricMisplacedTiles misplacedTiles = new AStarHuestricMisplacedTiles();
		misplacedTiles.execute();
		
	}

}
