# Sorting and Searching Visualizer

A JavaFX desktop application for exploring sorting algorithms and binary search tree traversal through step-by-step animation. Developed as a team coursework project.

## Features

- Bubble, insertion, selection, merge, quick, and heap sort.
- Binary search tree search with a user-specified target.
- Array input, algorithm selection, and animated comparisons.
- Support for negative numbers, duplicate values, and 32-bit integers.
- Input validation and a 50-element limit.
- Starting a new animation stops the previous playback.

## My contribution

Yawei Xiao (GitHub: [YARA-111111](https://github.com/YARA-111111)) worked on the JavaFX interface, sorting-module integration, and code merging. The algorithms and other components are team contributions.

## Requirements and setup

- JDK 17
- Maven 3.9
- A graphical desktop environment

From the repository root:

```shell
mvn clean compile
mvn javafx:run
```

Maven downloads JavaFX dependencies on the first build. No machine-specific JavaFX SDK path is needed.

Choose an algorithm, enter integers separated by commas or spaces, and click **Play**. For **BSTSearch**, also enter an integer search target.

## Project structure

| Directory | Purpose |
| --- | --- |
| `src/main/java/ui` | Interface, rendering, and animation control |
| `src/main/java/algorithms` | Sorting implementations and recorded steps |
| `src/main/java/datastructures` | Binary search tree structures and traversal |
| `tests` | Input-validation and sorting smoke tests |

## Tests

After compiling, run:

```shell
javac -cp target/classes -d target/test-classes tests/SmokeTest.java
java -cp "target/classes;target/test-classes" SmokeTest
```

On macOS/Linux, replace the classpath semicolon with a colon.

Compilation and smoke tests for all six sorting algorithms have passed. Full desktop interaction testing has not been completed.

## Limitations and attribution

Sorting bars scale to the input range. Tree rendering uses a fixed canvas, so deep trees may extend beyond the visible area.

See [CONTRIBUTORS.md](CONTRIBUTORS.md) for attribution. An open-source license has not yet been selected by the team.
