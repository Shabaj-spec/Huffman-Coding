# Huffman-Coding
This project is an implementation of the Huffman Coding Algorithm using the Binary Tree data structure. It demonstrates the process of data compression by assigning variable-length binary codes to symbols based on their frequencies, ensuring efficient encoding and decoding.

**Features:**
*Build Huffman Tree:*
Reads input from a file containing symbols (uppercase letters) and their probabilities.
Constructs a Huffman tree by iteratively combining the smallest weighted trees.

*Derive Huffman Codes:*
Generates unique binary codes for each symbol based on the Huffman algorithm.

*Encode Text:*
Encodes user-input text (uppercase letters only, with spaces preserved) into a binary string.

*Decode Binary String:*
Decodes the binary string back into its original text.

**How It Works:**
*Input:*
A text file (LettersProbability.txt) containing symbols and their probabilities.
A line of uppercase text entered by the user.

*Process:*
Builds the Huffman tree using two queues and combines nodes based on probabilities.
Derives encoding for each symbol using recursive traversal of the Huffman tree.
Encodes the input text into a binary string.
Decodes the binary string back to the original text.

*Output:*
Displays the encoded binary string.
Outputs the decoded text, ensuring it matches the original input.

**Sample Usage:**
Enter the name of the file with letters and probability: LettersProbability.txt
Building the Huffman tree...
Huffman coding completed.
Enter a line (uppercase letters only): THIS IS COOL
Here's the encoded line: 0100010010010100000 0001011001 101010100001000011010100
The decoded line is: THIS IS COOL

**Files Included:**
Huffman.java: Implements the Huffman coding algorithm.
Pair.java: Helper class for symbol-probability pairs.
BinaryTree.java: Generic binary tree class.
LettersProbability.txt: Input file with symbols and probabilities.

**Requirements:**
Java 8 or later.
Basic understanding of data structures, particularly binary trees.
