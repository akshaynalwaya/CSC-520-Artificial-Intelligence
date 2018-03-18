edge(bird, ako, animal).
edge(fish, ako, animal).
edge(penguin, ako, bird).
edge(canary, ako, bird).
edge(nemo, isa, fish).
edge(opus, isa, penguin).
edge(tweety, isa, canary).
edge(robin, isa, canary).


% rel(SourceNode, RelationshipType, DestinationNode)
% recursive rule to find the type of relationship

rel(SourceNode,RelationshipType,DestinationNode) :- 
	edge(SourceNode,RelationshipType,DestinationNode). 

rel(SourceNode,isa,DestinationNode) :- 
	edge(SourceNode,isa,Node1), 
	rel(Node1,ako,DestinationNode).
rel(SourceNode, isa, DestinationNode):- 
	edge(SourceNode, isa, Node1), 
	rel(Node1, isa, DestinationNode).

rel(SourceNode,ako,DestinationNode) :- 
	edge(SourceNode,ako,Node1), 
	rel(Node1,ako,DestinationNode).
rel(SourceNode, ako, DestinationNode) :- 
	edge(SourceNode, ako, Node1), 
	rel(Node1, isa, DestinationNode).

travel(nemo,swimming).
travel(opus,walking).
travel(tweety,flying).
color(robin,yellow).

characteristic(X,travel,Y):-
	travel(X,Y).

characteristic(X,color,Y):-
	color(X,Y).