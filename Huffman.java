import java.io.*;
import java.util.*;

public class Huffman {
    public static void main(String[] args) {
        System.out.println("Huffman Coding");

        try {
            // Prompt for the file name
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Enter the name of the file with letters and probability: ");
            String fileName = inputScanner.nextLine();

            // Step 1: Read the file and create BinaryTree<Pair> objects
            Scanner fileScanner = new Scanner(new File(fileName));
            Queue<BinaryTree<Pair>> queueS = new LinkedList<>();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split("\t");
                char letter = parts[0].charAt(0);
                double probability = Double.parseDouble(parts[1].trim());

                BinaryTree<Pair> tree = new BinaryTree<>();
                tree.makeRoot(new Pair(letter, probability));
                queueS.add(tree);
            }
            fileScanner.close();

            System.out.println("\nBuilding the Huffman tree ….");

            // Step 2: Build the Huffman tree
            Queue<BinaryTree<Pair>> queueT = new LinkedList<>();
            while (queueS.size() + queueT.size() > 1) {
                BinaryTree<Pair> A = dequeueSmallest(queueS, queueT);
                BinaryTree<Pair> B = dequeueSmallest(queueS, queueT);

                Pair newPair = new Pair('0', A.getData().getProb() + B.getData().getProb());
                BinaryTree<Pair> P = new BinaryTree<>();
                P.makeRoot(newPair);
                P.attachLeft(A);
                P.attachRight(B);

                queueT.add(P);
            }

            BinaryTree<Pair> huffmanTree = queueT.poll();
            System.out.println("Huffman coding completed.\n");

            // Step 3: Generate Huffman codes
            String[] huffmanCodes = findEncoding(huffmanTree);

            // Step 4: Encode and decode a line of text
            System.out.print("Enter a line (uppercase letters only): ");
            String inputText = inputScanner.nextLine();

            String encodedText = encodeText(inputText, huffmanCodes);
            System.out.println("Here’s the encoded line: " + encodedText);

            String decodedText = decodeText(encodedText, huffmanTree);
            System.out.println("The decoded line is: " + decodedText);

            inputScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static BinaryTree<Pair> dequeueSmallest(Queue<BinaryTree<Pair>> queueS, Queue<BinaryTree<Pair>> queueT) {
        if (queueT.isEmpty() || (!queueS.isEmpty() && queueS.peek().getData().getProb() < queueT.peek().getData().getProb())) {
            return queueS.poll();
        } else {
            return queueT.poll();
        }
    }

    private static String[] findEncoding(BinaryTree<Pair> bt) {
        String[] result = new String[26];
        findEncoding(bt, result, "");
        return result;
    }

    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix) {
        if (bt.getLeft() == null && bt.getRight() == null) {
            a[bt.getData().getValue() - 'A'] = prefix;
        } else {
            findEncoding(bt.getLeft(), a, prefix + "0");
            findEncoding(bt.getRight(), a, prefix + "1");
        }
    }

    private static String encodeText(String text, String[] huffmanCodes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                encoded.append(" ");
            } else {
                encoded.append(huffmanCodes[c - 'A']);
            }
        }
        return encoded.toString();
    }

    private static String decodeText(String encodedText, BinaryTree<Pair> huffmanTree) {
        StringBuilder decoded = new StringBuilder();
        BinaryTree<Pair> current = huffmanTree;

        for (char bit : encodedText.toCharArray()) {
            if (bit == ' ') {
                decoded.append(" ");
                current = huffmanTree; // Reset to root for the next sequence
            } else {
                current = (bit == '0') ? current.getLeft() : current.getRight();
                if (current.getLeft() == null && current.getRight() == null) {
                    decoded.append(current.getData().getValue());
                    current = huffmanTree; // Reset to root after decoding a letter
                }
            }
        }
        return decoded.toString();
    }
}
