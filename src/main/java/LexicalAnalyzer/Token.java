package LexicalAnalyzer;

public class Token {
    final TokenType type;
    final String content;
    final int intContent;

    public Token(String content, TokenType type) {
        this.content = content;
        this.type = type;
        this.intContent = 0;
    }

    public Token(int intContent, TokenType type) {
        this.content = "";
        this.type = type;
        this.intContent = intContent;
    }

    public String getContent() {
        return content;
    }

    public int getIntContent() {
        return intContent;
    }

    public TokenType getType() {
        return type;
    }
}
