/*
Código escrito por trincot
https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/
A resposta está bem abaixo com apenas 1 upvote.
*/
import java.util.ArrayList;
import java.util.List;

public class Printer {
    int padding = 2; // minimum number of horizontal spaces between two node data

    private int indent(List<String> lines, int margin) {
        // If negative, prefix all lines with spaces and return 0
        if (margin >= 0) return margin;
        String spaces = " ".repeat(-margin);
        int i = 0;
        for (var line : lines) {
            lines.set(i++, spaces + line);
        }
        return 0;
    }

    private List<String> merge(List<String> left, List<String> right) {
        // Merge two arrays, where the right strings are indented so there is no overlap
        int minSize = Math.min(left.size(), right.size());
        int offset = 0;
        for (int i = 0; i < minSize; i++) {
            offset = Math.max(offset, left.get(i).length() + padding - right.get(i).replaceAll("\\S.*", "").length());
        }
        indent(right, -indent(left, offset));
        for (int i = 0; i < minSize; i++) {
            left.set(i, left.get(i) + right.get(i).substring(left.get(i).length()));
        }
        if (right.size() > minSize) {
            left.addAll(right.subList(minSize, right.size()));
        }
        return left;
    }

    private List<String> buildLines(No node) {
        if (node == null) return new ArrayList<>();
        List<String> lines = merge(buildLines(node.getEsquerda()), buildLines(node.getDireita()));
        int half = String.valueOf(node.getRaAluno()).length() / 2;
        int i = half;
        if (lines.size() > 0) {
            String line;
            i = lines.get(0).indexOf("*"); // Find index of first subtree
            if (node.getDireita() == null) {
                line = " ".repeat(i) + "┌─┘";
                i += 2;
            } else if (node.getEsquerda() == null) {
                line = " ".repeat(i = indent(lines, i - 2)) + "└─┐";
            } else {
                int dist = lines.get(0).length() - 1 - i; // Find distance between subtree roots
                line = String.format("%s┌%s┴%s┐", " ".repeat(i), "─".repeat(dist / 2 - 1), "─".repeat((dist - 1) / 2));
                i += dist / 2;
            }
            lines.set(0, line);
        }
        lines.add(0, " ".repeat(indent(lines, i - half)) + node.getRaAluno());
        lines.add(0, " ".repeat(i + Math.max(0, half - i)) + "*"); // Add a marker for caller
        return lines;
    }

    public String topDown(No raiz) {
        List<String> lines = buildLines(raiz);
        return String.join("\n", lines.subList(1, lines.size()));
    }
}
