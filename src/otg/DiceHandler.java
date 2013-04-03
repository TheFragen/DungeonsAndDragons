package otg;

public class DiceHandler {

	public int DiceHandler(int diceVal, int existingVal) {
		int randomInt = (int) (Math.random() * diceVal + 1);
		int o = existingVal + randomInt;
		existingVal = diceVal;
		return o;
	}
	
}
