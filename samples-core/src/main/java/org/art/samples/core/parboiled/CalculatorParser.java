package org.art.samples.core.parboiled;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.parboiled.BaseParser;
import org.parboiled.Parboiled;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.annotations.SuppressSubnodes;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ToStringFormatter;
import org.parboiled.trees.GraphNode;

import static org.parboiled.errors.ErrorUtils.printParseErrors;
import static org.parboiled.support.ParseTreeUtils.printNodeTree;
import static org.parboiled.trees.GraphUtils.printTree;

@BuildParseTree
public class CalculatorParser extends BaseParser<Integer> {

    public Rule InputLine() {
        return Sequence(Expression(), EOI);
    }

    public Rule Expression() {
        return Sequence(
            Term(), // a successful match of a Term pushes one Integer value onto the value stack
            ZeroOrMore(
                FirstOf(
                    // the action that is run after the '+' and the Term have been matched consumes the
                    // two top value stack elements and replaces them with the calculation result
                    Sequence('+', Term(), push(pop() + pop())),

                    // same for the '-' operator, however, here the order of the "pop"s matters, we need to
                    // retrieve the second to last value first, which is what the pop(1) call does
                    Sequence('-', Term(), push(pop(1) - pop()))
                )
            )
        );
    }

    public Rule Term() {
        return Sequence(
            Factor(), // a successful match of a Factor pushes one Integer value onto the value stack
            ZeroOrMore(
                FirstOf(
                    // the action that is run after the '*' and the Factor have been matched consumes the
                    // two top value stack elements and replaces them with the calculation result
                    Sequence('*', Factor(), push(pop() * pop())),

                    // same for the '/' operator, however, here the order of the "pop"s matters, we need to
                    // retrieve the second to last value first, which is what the pop(1) call does
                    Sequence('/', Factor(), push(pop(1) / pop()))
                )
            )
        );
    }

    public Rule Factor() {
        return FirstOf(Number(), Parens()); // a factor "produces" exactly one Integer value on the value stack
    }

    public Rule Parens() {
        return Sequence('(', Expression(), ')');
    }

    public Rule Number() {
        return Sequence(
            Digits(),

            // parse the input text matched by the preceding "Digits" rule,
            // convert it into an Integer and push it onto the value stack
            // the action uses a default string in case it is run during error recovery (resynchronization)
            push(Integer.parseInt(matchOrDefault("0")))
        );
    }

    @SuppressSubnodes
    public Rule Digits() {
        return OneOrMore(Digit());
    }

    public Rule Digit() {
        return CharRange('0', '9');
    }

    @SuppressWarnings({"unchecked"})
    public static void main(String[] args) {
        CalculatorParser parser = Parboiled.createParser(CalculatorParser.class);

        while (true) {
            System.out.print("Enter a calculators expression (single RETURN to exit)!\n");
            String input = new Scanner(System.in).nextLine();
            if (StringUtils.isEmpty(input)) {
                break;
            }

            ParsingResult<?> result = new RecoveringParseRunner(parser.InputLine()).run(input);

            if (result.hasErrors()) {
                System.out.println("\nParse Errors:\n" + printParseErrors(result));
            }

            Object value = result.parseTreeRoot.getValue();
            if (value != null) {
                String str = value.toString();
                int ix = str.indexOf('|');
                if (ix >= 0) {
                    str = str.substring(ix + 2); // extract value part of AST node toString()
                }
                System.out.println(input + " = " + str + '\n');
            }
            if (value instanceof GraphNode) {
                System.out.println("\nAbstract Syntax Tree:\n" +
                    printTree((GraphNode) value, new ToStringFormatter(null)) + '\n');
            } else {
                System.out.println("\nParse Tree:\n" + printNodeTree(result) + '\n');
            }
        }
    }
}
