import java.util.Scanner;
public class scanner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dayNumber;
		String dayText;
		System.out.println("Enter the number of a day: ");
		dayText = scanner.next();
		
		switch(dayText) {
			case "Monday": {
				dayNumber = 1;
				break;
			}
			default: {
				System.out.println("Invalid input");
				break;
			}
		}
		if(dayText != null && !dayText.isEmpty()) {		// Only show output if a day was found
			System.out.println("The day is ");
		}
		scanner.close();
	}
	
}