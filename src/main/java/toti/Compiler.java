package toti;
import toti.parser.*;
import toti.lexer.*;
import toti.node.*;
import java.io.*;

/**
 * Toti compiler class.
 */
public class Compiler {

    public void compile(String[] args) {
        try {
            String filename = args[1];
            Parser p = parseFile(filename);

            // Parse the input.
            Start tree = p.parse();

            if ("compile".equals(args[0])) {
                interprete(tree);
            } else if ("ast".equals(args[0])) {
                dumpAst(tree);
            }

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void dumpAst(Start tree) {
        tree.apply(new AstDump());
    }

    private void interprete(Start tree) {
        tree.apply(new Translation());
    }

    private Parser parseFile(String filename) throws FileNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(new File(filename));
            return new Parser(new Lexer(new PushbackReader(new InputStreamReader(fis), 1024)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting!");
        Compiler compiler = new Compiler();
        compiler.compile(args);
    }
}
