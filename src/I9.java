import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * I9 parses and sorts one text file into useful information according to the I9
 * spec.
 * 
 * @author Sean P. Hanly
 * @date 5-18-15
 */
public class I9 {

	/**
	 * @param args
	 *            - the name of one text file.
	 */
	public static void main(String[] args) {
		String fname = null;
		Parser par = null;
		Sorter sort = null;

		if (args.length > 1 || args[0].equals("help")) {
			System.out.println("I9 processes one text file at"
					+ " a time and processes it to I9 spec specifications.");
			
			return;
		} else {
			fname = args[0];
		}

		try {
			par = new Parser(fname);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}

		try {
			par.read();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		sort = new Sorter(par);

		System.out.println(sort.unique());
		System.out.println(sort.palindromes());
		System.out.println(sort.average());
		System.out.println(sort.reverse());
	}
}