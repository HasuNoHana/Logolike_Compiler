package Parser;

import LexicalAnalyzer.Token;
import LexicalAnalyzer.TokenType;
import exceptions.functionDefinedUncorrectly;
import exceptions.instructionExeption;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    private ArrayList<Function> functions;
    private int currentToken;
    private Stack<TokenType> stack;

    public Parser() {
        this.functions = new ArrayList<Function>();
        this.currentToken = 0;
        this.stack = new Stack<>();
    }

    public ArrayList<Function> parse(ArrayList<Token> tokens) throws functionDefinedUncorrectly, instructionExeption {
        functions.clear();
        functions.add(getFunction(tokens));
        return functions;
    }

    private Function getFunction(ArrayList<Token> tokens) throws functionDefinedUncorrectly, instructionExeption {
        if(tokens.get(currentToken).getType()!= TokenType.DEF)
            throw new functionDefinedUncorrectly("statment def is missing");
        currentToken++;

        if(tokens.get(currentToken).getType()!= TokenType.ID)
            throw new functionDefinedUncorrectly("function name is missing");
        String name = tokens.get(currentToken).getContent();
        currentToken++;

        ArrayList<Argument> arguments = readArguments(tokens);
        ArrayList<ProgramFragments>  insides = getInsides(tokens);

        return new Function(name, arguments, insides);
    }

    private ArrayList<Argument> readArguments(ArrayList<Token> tokens) throws functionDefinedUncorrectly {
        if(tokens.get(currentToken).getType()!= TokenType.ROUND_OPEN_BRACKET)
            throw new functionDefinedUncorrectly("bracket ( is missing");
        stack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<Argument>  arguments = new ArrayList<Argument>();
        boolean hasToBeNextArgument = false;

        while(tokens.get(currentToken).getType() != TokenType.EOF){
            if(tokens.get(currentToken).getType() == TokenType.ROUND_CLOSED_BRACKET){
                stack.pop();
                currentToken++;
                if(hasToBeNextArgument)
                    throw new functionDefinedUncorrectly("comma without next argument");
                return arguments;
            }
            arguments.add(readArgument(tokens));
            hasToBeNextArgument = false;
            if(tokens.get(currentToken).getType() == TokenType.COMMA){
                hasToBeNextArgument = true;
                currentToken++;
            }
        }
        throw new functionDefinedUncorrectly("bracket ) is missing");
    }

    private Argument readArgument(ArrayList<Token> tokens) throws functionDefinedUncorrectly {
        if(tokens.get(currentToken).getType()!= TokenType.ID)
            throw new functionDefinedUncorrectly("wrong function argument");
        String type = tokens.get(currentToken).getContent();
        currentToken++;
        if(tokens.get(currentToken).getType()!= TokenType.ID)
            throw new functionDefinedUncorrectly("wrong function argument");
        String name = tokens.get(currentToken).getContent();
        currentToken++;
        return new Argument(type, name);
    }

    private ArrayList<ProgramFragments> getInsides(ArrayList<Token> tokens) throws functionDefinedUncorrectly, instructionExeption {
        if(tokens.get(currentToken).getType()!= TokenType.CURLY_OPEN_BRACKET)
            throw new functionDefinedUncorrectly("bracket { is missing");
        stack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<ProgramFragments>  insides = new ArrayList<ProgramFragments>();

        while(tokens.get(currentToken).getType() != TokenType.EOF){
            insides.add(readProgramFragment(tokens));

            if(tokens.get(currentToken).getType() == TokenType.CURLY_CLOSED_BRACKET){
                stack.pop();
                currentToken++;
                return insides;
            }
        }
        throw new functionDefinedUncorrectly("bracket } is missing");
    }

    private ProgramFragments readProgramFragment(ArrayList<Token> tokens) throws functionDefinedUncorrectly, instructionExeption {
        if(tokens.get(currentToken).getType() == TokenType.FOR) {
            return null;
        }
        if(tokens.get(currentToken).getType() == TokenType.IF) {
            return null;
        }
        if(tokens.get(currentToken).getType() == TokenType.ID) {
            ArrayList<Member> members = readMembers(tokens);
            //TODO assign
            return new Instruction(members);
        }
        throw new functionDefinedUncorrectly("No content inside block");
    }

    private ArrayList<Member> readMembers(ArrayList<Token> tokens) throws instructionExeption {
        ArrayList<Member> members = new ArrayList<Member>();
        while (tokens.get(currentToken).getType() != TokenType.ASSIGN && tokens.get(currentToken).getType() != TokenType.SEMICOLON){
            String name = tokens.get(currentToken).getContent();
            currentToken++;
            if(tokens.get(currentToken).getType() == TokenType.ROUND_OPEN_BRACKET){
                ArrayList<Parameter> parameters = readParameters(tokens);
                members.add(new Member(name, parameters));
            } else{
                members.add(new Member(name));
            }

            if(tokens.get(currentToken).getType() != TokenType.DOT){
                break;
            }
            currentToken++;
        }
        return members;
    }

    private ArrayList<Parameter> readParameters(ArrayList<Token> tokens) throws instructionExeption {
        stack.add(tokens.get(currentToken).getType());
        currentToken++;
        ArrayList<Parameter> parameters = new ArrayList<Parameter>();
        boolean hasToBeAnother = false;
        while (tokens.get(currentToken).getType() != TokenType.EOF){
            if(tokens.get(currentToken).getType() == TokenType.ROUND_CLOSED_BRACKET){
                stack.pop();
                currentToken++;
                if(hasToBeAnother)
                    throw new instructionExeption("No parameter after comma");
                return parameters;
            }

            parameters.add(readExpresion(tokens));
            if(tokens.get(currentToken).getType() == TokenType.COMMA){
                hasToBeAnother = true;
                currentToken++;
            }
        }
        throw new instructionExeption("Unexpected EOF");
    }

    private Parameter readExpresion(ArrayList<Token> tokens) {
        if(tokens.get(currentToken).getType() == TokenType.ROUND_OPEN_BRACKET){
            stack.add(tokens.get(currentToken).getType());
            currentToken++;
        } else if (tokens.get(currentToken).getType() == TokenType.SQUARE_OPEN_BRACKET){

        } else if (tokens.get(currentToken).getType() == TokenType.ID){

        } else if (tokens.get(currentToken).getType() == TokenType.NUMBER){

        } else if (tokens.get(currentToken).getType() == TokenType.ROUND_CLOSED_BRACKET){

        }
    }


}
