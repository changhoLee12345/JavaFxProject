package random;

public class RandomExample {

	public static void main(String[] args) {
		randomQuiz();
	}

	public static void randomQuiz() {
		int[] intAry = new int[10];
		int aryLength = intAry.length;

		for (int i = 0; i < aryLength;) {
			int temp = (int) (Math.random() * aryLength);
			System.out.println("temp: " + temp);
			intAry[i] = temp;
			int j = i;
			for (; j > 0;) {
				if (i != 0) {
					if (intAry[i] == intAry[j - 1]) {
						break;
					}
					j--;
				}
			}
			if (j != 0)
				continue;

			i++;

		} // end of for

		for (int i = 0; i < aryLength; i++) {
			System.out.println(intAry[i]);
		}
	}

}
