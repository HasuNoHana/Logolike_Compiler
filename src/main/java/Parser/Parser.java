package Parser;

import LexicalAnalyzer.Token;
import LexicalAnalyzer.TokenType;
import exceptions.ExpresionExeption;
import exceptions.ParameterException;
import exceptions.functionDefinedUncorrectly;
import exceptions.MemberAcessExeption;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    private ArrayList<Function> functions;
    private int currentToken;
    private Stack<TokenType> bracketsStack;

    public Parser() {
        this.functions = new ArrayList<Function>();
        this.currentToken = 0;
        this.bracketsStack = new Stack<>();
    }

    public ArrayList<Function> parse(ArrayList<Token> tokens) {
        functions.clear();
        functions.add(getFunction(tokens));
        return functions;
    }

    private Function getFunction(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() != TokenType.DEF)
            throw new functionDefinedUncorrectly("statment def is missing");
        currentToken++;

        if (tokens.get(currentToken).getType() != TokenType.ID)
            throw new functionDefinedUncorrectly("function name is missing");
        String name = tokens.get(currentToken).getContent();
        currentToken++;

        ArrayList<Argument> arguments = readArguments(tokens);
        ArrayList<ProgramFragments> insides = getInsides(tokens);

        return new Function(name, arguments, insides);
    }


    //////////////////////////////////////ARGUMENTS/////////////////////////////////////////////////////


    private ArrayList<Argument> readArguments(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() != TokenType.ROUND_OPEN_BRACKET)
            throw new functionDefinedUncorrectly("bracket ( is missing");
        bracketsStack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<Argument> arguments = new ArrayList<Argument>();
        boolean hasToBeNextArgument = false;

        while (tokens.get(currentToken).getType() != TokenType.EOF) {
            if (tokens.get(currentToken).getType() == TokenType.ROUND_CLOSED_BRACKET) {
                bracketsStack.pop();
                currentToken++;
                if (hasToBeNextArgument)
                    throw new functionDefinedUncorrectly("comma without next argument");
                return arguments;
            }
            arguments.add(readArgument(tokens));
            hasToBeNextArgument = false;
            if (tokens.get(currentToken).getType() == TokenType.COMMA) {
                hasToBeNextArgument = true;
                currentToken++;
            }
        }
        throw new functionDefinedUncorrectly("bracket ) is missing");
    }

    private Argument readArgument(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() != TokenType.ID)
            throw new functionDefinedUncorrectly("wrong function argument");
        String type = tokens.get(currentToken).getContent();
        currentToken++;
        if (tokens.get(currentToken).getType() != TokenType.ID)
            throw new functionDefinedUncorrectly("wrong function argument");
        String name = tokens.get(currentToken).getContent();
        currentToken++;
        return new Argument(type, name);
    }


    //////////////////////////////////////INSIDES/////////////////////////////////////////////////////


    private ArrayList<ProgramFragments> getInsides(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() != TokenType.CURLY_OPEN_BRACKET)
            throw new functionDefinedUncorrectly("bracket { is missing");
        bracketsStack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<ProgramFragments> insides = new ArrayList<ProgramFragments>();

        while (tokens.get(currentToken).getType() != TokenType.EOF) {
            insides.add(readProgramFragment(tokens));

            if (tokens.get(currentToken).getType() == TokenType.CURLY_CLOSED_BRACKET) {
                bracketsStack.pop();
                currentToken++;
                return insides;
            }
        }
        throw new functionDefinedUncorrectly("bracket } is missing");
    }

    private ProgramFragments readProgramFragment(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() == TokenType.FOR) {
            //TODO
        }
        if (tokens.get(currentToken).getType() == TokenType.IF) {
            //TODO
        }
        if (tokens.get(currentToken).getType() == TokenType.ID) {
            MemberAcess memberAcess = readMemberAcess(tokens);
            //TODO assign
            return new Instruction(memberAcess);
        }
        throw new functionDefinedUncorrectly("No content inside block");
    }

    private MemberAcess readMemberAcess(ArrayList<Token> tokens) {
        ArrayList<Member> members = readMembers(tokens);
        return new MemberAcess(members);
    }

    private ArrayList<Member> readMembers(ArrayList<Token> tokens) {
        ArrayList<Member> members = new ArrayList<Member>();
        while (tokens.get(currentToken).getType() == TokenType.ID) {

            members = readMember(tokens, members);

            if (tokens.get(currentToken).getType() != TokenType.DOT) {
                break;
            }
            currentToken++;
        }
        return members;
    }

    private ArrayList<Member> readMember(ArrayList<Token> tokens, ArrayList<Member> members) {
        String name = tokens.get(currentToken).getContent();
        currentToken++;
        if (tokens.get(currentToken).getType() == TokenType.ROUND_OPEN_BRACKET) {
            ArrayList<Expresion> parameters = readParameters(tokens);
            members.add(new Member(name, parameters));
        } else {
            members.add(new Member(name));
        }
        return members;
    }

    private ArrayList<Expresion> readParameters(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() != TokenType.ROUND_OPEN_BRACKET) {
            throw new ParameterException("There in no opening ( bracket");
        }
        bracketsStack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<Expresion> parameters = new ArrayList<Expresion>();
        boolean hasToBeAnother = false;
        while (tokens.get(currentToken).getType() != TokenType.EOF) {
            if (tokens.get(currentToken).getType() == TokenType.ROUND_CLOSED_BRACKET) {
                bracketsStack.pop();
                currentToken++;
                if (hasToBeAnother)
                    throw new MemberAcessExeption("No parameter after comma");
                return parameters;
            }
            parameters.add(parseExpresion(tokens));
            hasToBeAnother = false;
            if (tokens.get(currentToken).getType() == TokenType.COMMA) {
                hasToBeAnother = true;
                currentToken++;
            }
        }
        throw new MemberAcessExeption("Unexpected EOF");
    }

    private Expresion parseExpresion(ArrayList<Token> tokens) {
        MulExpresion left = parseMulExpresion(tokens);
        Expresion exp;
        if (tokens.get(currentToken).getType() == TokenType.PLUS || tokens.get(currentToken).getType() == TokenType.MINUS){
            TokenType operand = tokens.get(currentToken).getType();
            currentToken++;
            Expresion right = parseExpresion(tokens);
            exp = new Expresion(left,operand,right);
        } else {
            exp = new Expresion(left);
        }
        return exp;
    }

    private MulExpresion parseMulExpresion(ArrayList<Token> tokens) {
        PrimaryExpresion left = parsePrimaryExpresion(tokens);
        MulExpresion exp;
        if (tokens.get(currentToken).getType() == TokenType.MULTIPLY || tokens.get(currentToken).getType() == TokenType.DIVIDE){
            TokenType operand = tokens.get(currentToken).getType();
            currentToken++;
            MulExpresion right = parseMulExpresion(tokens);
            exp = new MulExpresion(left, operand, right);
        } else {
            exp = new MulExpresion(left);
        }
        return exp;
    }

    private PrimaryExpresion parsePrimaryExpresion(ArrayList<Token> tokens) {
        if (tokens.get(currentToken).getType() == TokenType.NUMBER){
            int number = tokens.get(currentToken).getIntContent();
            currentToken++;
            return new Number(number);
        } else if (tokens.get(currentToken).getType() == TokenType.ROUND_OPEN_BRACKET){
            currentToken++;
            Expresion e = parseExpresion(tokens);
            if (tokens.get(currentToken).getType() != TokenType.ROUND_CLOSED_BRACKET)
                throw new ExpresionExeption("No closing ] bracket");
            currentToken++;
            return new Brackets(e);
        } else if(tokens.get(currentToken).getType() == TokenType.ID){
            return readMemberAcess(tokens);
        } else if(tokens.get(currentToken).getType() == TokenType.SQUARE_OPEN_BRACKET){
            currentToken++;
            Expresion X = parseExpresion(tokens);
            if(tokens.get(currentToken).getType() != TokenType.COMMA)
                throw new ExpresionExeption("No comma between X and Y in point");
            currentToken++;
            Expresion Y = parseExpresion(tokens);
            if(tokens.get(currentToken).getType() != TokenType.SQUARE_CLOSED_BRACKET)
                throw new ExpresionExeption("No closing bracket ] ");
            currentToken++;
            return new SimplePoint(X,Y);
        }
        throw new ExpresionExeption("No Primary Expreson include token: "+tokens.get(currentToken).getContent());
    }

}