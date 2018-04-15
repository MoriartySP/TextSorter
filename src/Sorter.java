/**
 * Sorts parsed data to I9 spec.
 * 
 * @author Sean P. Hanly
 * @date 5-18-15
 */
public class Sorter {
	private StringBuilder[][] sent;
	private AlphaTree[] diction = new AlphaTree[26];
	private String[] palindromes = new String[100];
	private String reverse = new String();
	private String unique = new String();
	private double words = 0;
	private double letters = 0;
	private int pal = 0;
	private int uni = 0;

	Sorter(Parser par) {
		this.sent = par.getSentences();
		sort();
	}

	/**
	 * Primary sorting function, sorts data to I9 spec.
	 */
	private void sort() {
		int i, j, ofs;
		StringBuilder v;
		String w;
		char fc;

		for (i = 0; i < sent.length && sent[i][0] != null; i++) {

			for (j = 0; j < sent[i].length && sent[i][j] != null; j++) {
				v = sent[i][j];
				fc = v.charAt(0);
				if (fc > 96 && fc < 123) { // is this a word
					w = v.toString();
					v.reverse();
					ofs = fc - 97;
					if (diction[ofs] == null) { // offset in to diction
						diction[ofs] = new AlphaTree(w);
						uni++;
					} else if (diction[ofs].addNode(w)) {
						uni++;
						if (w.equals(v.toString()))
							palindromes[pal++] = w;
					}
					words++;
					letters += w.length();
				}
			}
			addReverse(sent[i], j);
		}
	}

	/**
	 * Adds a sentence to the reverse String.
	 * 
	 * @param sb
	 *            - StringBuilder array to be added.
	 * @param j
	 *            - first null index of the array.
	 */
	private void addReverse(StringBuilder[] sb, int j) {
		int l;
		String w;
		String p = sb[--j].toString();

		for (j--; j >= 0; j--) {
			w = sb[j].toString();
			l = w.length() - 1;

			if (j == 0) {
				sb[j].setCharAt(l, (char) (w.charAt(l) - 32));
				w = sb[j].toString();
				reverse = reverse + ' ' + w + p;
			} else {
				if (sb[j - 1].charAt(0) == ',') {
					reverse = reverse + ' ' + w + ',';
					j--;
				} else
					reverse = reverse + ' ' + w;
			}
		}
	}

	/**
	 * Sorts the unique values of the dictionary.
	 * 
	 * @param n
	 *            - Node to be sorted.
	 */
	private void sortUnique(Node n) {
		if (n != null) {
			sortUnique(n.getLeft());
			unique = unique + new String(n.getWord()) + ", ";
			sortUnique(n.getRight());

		}
	}

	/**
	 * @return the number of unique words and a list of them in a String
	 */
	String unique() {
		int i;

		for (i = 0; i < 26; i++)
			if (diction[i] != null)
				sortUnique(diction[i].getRoot());

		unique = "There are " + uni + " unique words: " + unique;

		return unique.substring(0, unique.length() - 2) + '.';
	}

	/**
	 * @return the number of palindromes and lists them in a String.
	 */
	String palindromes() {
		int i;
		String s = new String(" of them are palindromes: ");

		for (i = pal - 1; i >= 0; i--) {
			if (i == 0)
				s = s + palindromes[i] + '.';
			else
				s = s + palindromes[i] + ", ";
		}

		return pal + s;
	}

	/**
	 * @return average number of letters per word.
	 */
	String average() {
		double val = letters / words;
		return "The average number of letters per word is: " + val;
	}

	/**
	 * @return the reversed sentences.
	 */
	String reverse() {
		return "The sentences in reverse are:" + reverse;
	}

}
