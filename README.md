# AStar
To find optimal shortest path between two nodes(cities / countries).
I’ve developed couple of class one with misplaced hueristic and another with Manhattan 
heuristic.
1) Manhattan heuristic execution starts from main() method, which calls execute().
2) execute() method has the logic of generating initial node and goal state.
3) Once the initial state is generated it will be added to the priority queue.
4) All the nodes are explored in left, right, up and down order.
5) All these nodes are added to priority queue.

Classes
1)AStarAlgorithm.java Uses Manhattan approach.
2) AStarHuestricMisplacedTiles.java Uses misplaced tiles approach.

Variables
1)priorityQueue Queue maintains nodes in increasing order of total cost.
2)exploredNodes Nodes explored from the generated nodes.
3)totalNodes Total number of nodes generated.

Methods
1) calculateManhattanDistance(): Calculates the distance based on heuristic approach( either misplaced or manhattan)based on the class invocation.
2) execute() Generate nodes and calculate the distance and based on the approaches.
3) generateNodes() Generates nodes in left, right, up and down  order
4) createNode() Creates a node and calculates the heuristic distance( either misplaced or manthattan).
5) sortExploredNodes() This method helps to sort the nodes in increasing order of heuristic distance and ifthe total cost is same then we would consider heuristic cost.
6) generateParentNodePath() Once the goal state is reached, generatespath to the parent node from the goal state
