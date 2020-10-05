import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException{
		Lexical.AnalyzeProgram(args[0]);
		return;
	}
}

class Lexical {
	public static void AnalyzeProgram (String sourceFile) throws IOException{
		char[] buf = new char[1024];
		buf = Reader.ReadFileToCharArray(sourceFile).toCharArray();
		int i=0;
		StringBuffer signal = new StringBuffer("");
		while(i<buf.length) {
			StringBuffer temp = new StringBuffer("");
			if(buf[i]=='\n' || buf[i]=='\t' || buf[i]=='\r' || buf[i]==' ') {
				i++;
				continue;
			}
			else if(Lexical.isLetter(buf[i])) {
				while(Lexical.isLetter(buf[i])||Lexical.isDigit(buf[i])) {
					temp.append(buf[i]);
					i++;
				}
				if(Lexical.isReserved(temp.toString())) {
					String str=temp.toString();
					String str1=str.substring(0,1).toUpperCase();
					String str2=str.substring(1).toLowerCase();
					signal = new StringBuffer(str1+str2);
				}
				else {
					signal = new StringBuffer("Ident(");
					signal.append(temp);
					signal.append(")");
				}
			}
			else if(Lexical.isDigit(buf[i])) {
				while(Lexical.isDigit(buf[i])) {
					temp.append(buf[i]);
					i++;
				}
				signal = new StringBuffer("Int(");
				signal.append(Integer.parseInt(temp.toString()));
				signal.append(")");
			}
			else if(buf[i]==':') {
				if(buf[++i]=='=') {
					signal = new StringBuffer("Assign");
					i++;
				}
				else
					signal = new StringBuffer("Colon");
			}
			else if(buf[i]=='+') {
				i++;
				signal = new StringBuffer("Plus");
			}
			else if(buf[i]=='*') {
				i++;
				signal = new StringBuffer("Star");
			}
				
			else if(buf[i]==','){
				i++;
				signal = new StringBuffer("Comma");
			}
			else if(buf[i]=='(') {
				i++;
				signal = new StringBuffer("LParenthesis");
			}
				
			else if(buf[i]==')') {
				i++;
				signal = new StringBuffer("RParenthesis");
			}
				
			else {
				System.out.println("Unknown");
				return;
			}
			System.out.println(signal);
		}
		return;
	}
	private static boolean isReserved(String str) {
		if(str.equals("BEGIN") || str.equals("END") || str.equals("FOR") || str.equals("IF") || str.equals("THEN") || str.equals("ELSE"))
			return true;
		return false;
	}
	private static boolean isLetter(char chr) {
		if(Character.isUpperCase(chr)||Character.isLowerCase(chr))
			return true;
		return false;
	}
	private static boolean isDigit(char chr) {
		if(Character.isDigit(chr))
			return true;
		return false;
	}
}

class Reader {
	static String ReadFileToCharArray (String sourceFile) throws IOException{
		File file = new File(sourceFile);
		FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s =bReader.readLine()) != null) {
            sb.append(s + "\n");
        }
        bReader.close();
        String str = sb.toString();
		return str;
	}
}
