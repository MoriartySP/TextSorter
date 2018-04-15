import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.lang.StringBuilder;

/**
 * Parser parses the input file.
 *
 * @author Sean P. Hanly
 * @date 5-18-15
 */
class Parser {
	private FileReader reader;
	private int[] buff = new int[250];
	private StringBuilder[][] sent = new StringBuilder[100][50];

	Parser(String fname) throws FileNotFoundException {
		this.reader = new FileReader(fname);
	}

	/**
	 * Gets the parsed sentences.
	 * 
	 * @return a two dimensional array or sentences constructed with
	 *         StringBuilder objects.
	 */
	StringBuilder[][] getSentences() {
		return sent;
	}

	/**
	 * Processes the buffer and stores the sentences in a StringBuilder[][]
	 * 
	 * @throws IOException
	 *             FileReader Object.
	 */
	void read() throws IOException {
		int c, i, x = 0, y = 0;

		buff = buffer();
		for (i = 0; i < buff.length; i++) {
//			if (sent[y][x] == null)
//				sent[y][x] = new StringBuilder();
			c = buff[i];
			if ((c > 96 && c < 123) || (c > 47 && c < 58))
				sent[y][x].append((char) c);
			else if (c == ' ')
				++x;
			else if (c == -1) {
				sent[y][x] = null;
				return;
			} else {
				(sent[y][++x] = new StringBuilder()).append((char) c);
				if (c == '.' || c == '!') {
					++y;
					x = 0;
					i = 0;
					buff = buffer();
				}
			}

			if (x == (sent[y].length - 1))
				sentenceRealocateX(sent[y]);
			if (y == (sent.length - 1))
				sentenceRealocateY();
		}
	}

	/**
	 * Reads the the file stream in sentences and buffers them.
	 * 
	 * @return The buffered sentences in a char[].
	 * @throws IOException
	 *             FileReader object.
	 */
	private int[] buffer() throws IOException {
		int c, i, s = (buff.length - 1);

		for (i = 0; i < s; i++) {
			c = reader.read();
			if (c > 64 && c < 91)
				c = c + 32; // To lower case
			buff[i] = c;
			if (c == '.' || c == '!' || c == -1)
				return buff;
//			if (i == s) { // Check for buffer overflow
//				buffRealocate();
//				s = (buff.length - 1);
//			}
		}

		return buff;
	}

	/**
	 * @param buff
	 *            - A char array to be doubled in size.
	 * @return The array doubled in size and copied.
//	 */
//	private void buffRealocate() {
//		int i, l = buff.length;
//		int[] nbuff = new int[l * 2];
//
//		for (i = 0; i < l; i++)
//			nbuff[i] = buff[i];
//
//		buff = nbuff;
//	}

	/**
	 * Dynamically reallocate memory for a sentence.
	 * 
	 * @param s
	 *            - The sentence to be doubled in size.
	 */
	private void sentenceRealocateX(StringBuilder[] s) {
		int i, l = s.length;
		StringBuilder[] n = new StringBuilder[l * 2];

		for (i = 0; i < l; i++)
			n[i] = s[i];
	}

	/**
	 * Dynamically reallocates memory for a group of sentences.
	 */
	private void sentenceRealocateY() {
		int i, l = sent.length;
		StringBuilder[][] n = new StringBuilder[l * 2][];

		for (i = 0; i < l; i++)
			n[i] = sent[i];
	}

}
