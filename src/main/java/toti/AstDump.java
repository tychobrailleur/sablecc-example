package toti;

import java.util.*;
import toti.analysis.*;
import toti.node.*;

public class AstDump extends DepthFirstAdapter {

    private final static int SIZE = 2;
    private int level;

    public void defaultIn(Node node) {
        for (int i = 0; i < level*SIZE; i++) {
            System.out.print("-");
        }
        level++;
        System.out.println(node.getClass());
    }

    public void defaultOut(Node node) {
        level--;
    }
}
