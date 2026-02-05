import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * A Screwtape interpreter that executes programs written in the Screwtape esoteric programming language.
 * 
 * Screwtape is a minimalistic language with the following commands:
 * 
 * - `>`: Move the tape pointer to the next memory node.
 * - `<`: Move the tape pointer to the previous memory node.
 * - `+`: Increment the value in the current memory node.
 * - `-`: Decrement the value in the current memory node.
 * - `.`: Output the character represented by the value in the current memory node.
 * - `[`: Do nothing
 * - `]`: If the value in the current memory node is not 0, jump back to the matching `[`.
 * 
 * This interpreter provides methods to manipulate the memory tape, execute programs, and handle loops efficiently.
 */
public class ScrewtapeInterpreter {

  /** The head of the doubly linked list representing the tape. */
  private Node tapeHead;

  /** The pointer to the current node in the tape. */
  private Node tapePointer;

  /**
   * Constructs a new Screwtape interpreter with an initialized memory tape of a single node set to 0.
   */
  public ScrewtapeInterpreter() {
    tapeHead = new Node(0);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the current state of the memory tape as a list of integers.
   * 
   * @return A list of integers representing the values in the memory tape, starting from the head.
   */
  public List<Integer> getTapeData() {
    return tapeHead.toList();
  }

  /**
   * Replaces the current memory tape with a new set of values.
   * 
   * @param data A list of integers to initialize the memory tape. Each integer will correspond to a memory node.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public void setTape(List<Integer> data) {
    tapeHead = new Node(data);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the value in the memory node currently pointed to by the tape pointer.
   * 
   * @return The integer value of the current memory node.
   */
  public int getTapePointerValue() {
    return tapePointer.value;
  }

  /**
   * Moves the tape pointer to the head of the memory tape.
   */
  public void moveTapePointerToHead() {
    tapePointer = tapeHead;
  }

  /**
   * Moves the tape pointer to the tail of the memory tape.
   */
  public void moveTapePointerToTail() {
    while (tapePointer.next != null) {
      tapePointer = tapePointer.next;
    }
  }

  /**
   * Creates a map of matching bracket pairs for the given Screwtape program.
   * 
   * The Screwtape language uses brackets `[` and `]` to define loops. This method identifies 
   * matching bracket pairs and returns a map that associates the index of each closing bracket 
   * (`]`) with its corresponding opening bracket (`[`).
   * 
   * For example, in the program `[>+<-]`, the opening bracket at index 0 matches the closing 
   * bracket at index 5. The returned map would contain a single entry: 
   * `{5: 0}`.
   * 
   * A few more examples:
   * 
   * input: `[+++][---]<<[+]`
   * output:`{4: 0, 9: 5, 14: 12}`
   * 
   * input: `[]`
   * output: `{1: 0}`
   * 
   * input: `>[+>[+-]<]`
   * output: `{9: 1, 7: 4}`
   * 
   * 
   * @param program The Screwtape program as a string.
   * @return A map where each key-value pair represents a matching bracket pair.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public Map<Integer, Integer> bracketMap(String program) {
    // TODO: Implement this
    // Hint: use a stack

    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> openingBrackets = new Stack<>();
    
    int i = 0;
    //loop through string using chartoarray() for each char.
    for (char c : program.toCharArray()) {
      //if (current iteration is opening bracket)
      if (c == '[') {
        // then add the index value into the stack
        openingBrackets.push(i);
      }
      //if else the current iteration is closing bracket 
      else if (c == ']') {
        //if stack is empty
        if (openingBrackets.isEmpty()) {
          // throw IllegalArgumentException
          throw new IllegalArgumentException("unmatched brackets");
        }
        // pop the top of the stack and store it as 'value'
        int value = openingBrackets.pop();
        // then add the value variable and the current i into the map
        map.put(i,value);
      }
      i++;
    }
      
      // if (after the loop is finished), there is a value inside the stack still,
      if (!openingBrackets.isEmpty()) {
        // throw IllegalArgumentException
        throw new IllegalArgumentException("unmatched brackets");
      }


    // else return the map
    return map;
  }

  /**
   * Executes a Screwtape program and returns the output as a string.
   * 
   * The Screwtape program is executed by interpreting its commands sequentially. The memory tape is dynamically 
   * extended as needed, and the tape pointer starts at the head of the tape. Loops defined by brackets 
   * `[` and `]` are executed as long as the current memory node's value is non-zero.
   * 
   * Output is generated using the `.` command, which converts the value in the current memory node 
   * to its corresponding ASCII character. The resulting output is returned as a string.
   * 
   * Example:
   * Program: >++++++++[<+++++++++>-]<.>>++++++++[<+++++++++>-]<+.
   * Output: "HI"
   * 
   * @param program The Screwtape program as a string.
   * @return The output generated by the program as a string.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public String execute(String program) {
    /**
 * A Screwtape interpreter that executes programs written in the Screwtape esoteric programming language.
 * 
 * Screwtape is a minimalistic language with the following commands:
 * 
 * - `>`: Move the tape pointer to the next memory node.
 * - `<`: Move the tape pointer to the previous memory node.
 * - `+`: Increment the value in the current memory node.
 * - `-`: Decrement the value in the current memory node.
 * - `.`: Output the character represented by the value in the current memory node.
 * - `[`: Do nothing
 * - `]`: If the value in the current memory node is not 0, jump back to the matching `[`.
 * 
 * This interpreter provides methods to manipulate the memory tape, execute programs, and handle loops efficiently.
 */
    // TODO: Implement this
    // If you get stuck, you can look at hint.md for a hint
    // tapeHead.value = 72;
    // // String test = String.valueOf(tapeHead.value);
    // int value = tapeHead.value;
    // Character testChar = (char) value;
    // String test = testChar.toString();
    // return test;


    //import the map of all the brackets from bracketMaps()
    //create String result (for returning the final decoded message)

    //loop through the string using for i loop
      //if current iteration equal '>'
        //if (check if there is an existing next node) if tapepointer.next == null
          //*CREATE A Next node*
          // create a newNode = new Node(0);

          // tapepointer.next = newNode;
          // newNode.prev = tapepointer;
      
        //moves to the next pointer using tapepointer

      //if current iteration equal '<'
        //if (check if there is an existing prev node) if tapepointer.prev == null
          //*CREATE A prev node*
          // create a newNode = new Node(0);

          // tapepointer.prev = newNode;
          // newNode.next = tapepointer;
      
        //moves to the next pointer using tapepointer
      
      //if current iteration equal '+'
        // tapepointer.value += 1;

      //if current iteration equal '-'
        // tapepointer.value += 1;

      //if current iteration equal '.'
        //*grab the value, convert the int into char, char into string, then add it to variable 'output' */
        // int value = tapepointer.value;
        // Character testChar = (char) value;
        // String test = testChar.toString();
        // output += test;

      //if current iteration equal ']'
        //grab the current iteration index (value i in the loop)
        //search for the same key inside the map and grab the value (using .get(key))
        //set i = value;

    return null;
  }

  public static void main(String[] args) {
    ScrewtapeInterpreter interpreter = new ScrewtapeInterpreter();
    // example test
    String program = "+";
    String output = interpreter.execute(program);
    System.out.println("Output: " + output);
  }
}
