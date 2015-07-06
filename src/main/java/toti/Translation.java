package toti;
import java.util.*;
import toti.analysis.*;
import toti.node.*;


class Translation extends DepthFirstAdapter {

    private Map identifiers = new HashMap();

    public void caseAValDeclaration(AValDeclaration node) {
        TIdentifier id = node.getIdentifier();
        System.out.println(" Found identifier: " + id.getText());

        if (identifiers.get(id.getText()) != null) {
            throw new RuntimeException(id.getText() + " already defined!");
        }

        identifiers.put(id.getText(), id);
    }

    public void caseAMultiValDeclaration(AMultiValDeclaration node) {
        if (node.getDeclaration() != null) {
            node.getDeclaration().apply(this);
        }
        TIdentifier id = node.getIdentifier();
        System.out.println(" Found identifier: " + id.getText());

        if (identifiers.get(id.getText()) != null) {
            throw new RuntimeException(id.getText() + " already defined!");
        }

        identifiers.put(id.getText(), id);
    }
}
