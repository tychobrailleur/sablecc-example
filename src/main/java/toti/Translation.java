package toti;

import java.util.*;
import toti.analysis.*;
import toti.node.*;


class Translation extends DepthFirstAdapter {

    private Map identifiers = new HashMap();
    private Map values = new HashMap();

    public void caseADeclStatement(ADeclStatement node) {
        TIdentifier id = node.getId();
        System.out.println(" Found identifier: " + id.getText());

        if (identifiers.get(id.getText()) != null) {
            throw new RuntimeException(id.getText() + " already defined!");
        }

        identifiers.put(id.getText(), id);
    }
    /*
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
        }*/

    public void caseAAssignmentStatement(AAssignmentStatement node) {
        TIdentifier id = node.getId();
        if (identifiers.get(id.getText()) == null) {
            throw new RuntimeException("Undefined variable: " + id.getText());
        }

        PExpression number = node.getValue();
        //   values.put(id.getText(), Integer.parseInt(number.getText()));
    }

    public void caseAPrintStatement(APrintStatement node) {
        TIdentifier id = node.getId();
        if (identifiers.get(id.getText()) == null) {
            throw new RuntimeException("Undefined variable: " + id.getText());
        }

        if (values.get(id.getText()) == null) {
            throw new RuntimeException("Uninitialized variable: " + id.getText());
        }

        Integer value = (Integer)values.get(id.getText());
        System.out.println(value);
    }
}
