package LexicalAnalyzer;

public class Token {
    TokenType type;
    String content;

    public Token(String content, TokenType type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public TokenType getType() {
        return type;
    }
}
