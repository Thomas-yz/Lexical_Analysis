import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException{
		Lexical.AnalyzeProgram("src/hello.txt", "src/output.txt");
		return;
	}
}
