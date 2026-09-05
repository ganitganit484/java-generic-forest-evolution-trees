# Generic Forest ADT & Pokemon Evolution Trees (Java)

A generic Forest abstract data structure (`MyForest<T>`) implemented in Java that models collections of disjoint N-ary trees, applied within the Pokedex ecosystem to manage Pokemon evolutionary lineages and ancestor-descendant relationships.

## Key Features & Architecture
- Generic Forest Container (`MyForest<T>`): Coordinates multiple disjoint multi-way tree roots without circular dependencies or duplicate elements.
- Evolutionary Lineage Integration: Embeds a specialized `MyForest<String>` inside the `Pokedex` registry to map evolution hierarchies while maintaining compliance with previous interfaces (`Iterable`, `Serializable`).
- Dynamic Tree Operations: Supports inserting child elements under specific parents or generating new root nodes dynamically when the parent reference is null.
- Subtree & Full-Tree Retrieval: Retrieves whole tree structures containing target elements (`getTree`, `getEvolutionTreeByName`) rather than just isolated subtrees.
- Direct Relationship Assertion (`areRelated`): Evaluates immediate parent-child kinship between arbitrary nodes across the forest.
- Deep Copying & Serialization: Fully supports deep cloning of disjoint forest structures and preserves state across object serialization pipelines.

## Method Signatures

### MyForest<T>
- MyForest()
- boolean add(T parent, T element)
- boolean add(T element)
- boolean remove(T element)
- boolean areRelated(T a, T b)
- boolean exists(T element)
- MyTree<T> getTree(T element)

### Pokedex Evolution Extensions
- void addPokemon(Pokemon pokemon, String parentName)
- MyTree<String> getEvolutionTreeByName(String pokemonName)

## Requirements
- Java Development Kit (JDK 8 or higher).

## Build & Run
Compile Java source files:
javac *.java

Run main execution driver or unit tests:
java Main
