package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@code MultOperator} is an operator that performs multiplication on two
 * integers.
 * @author jcollard
 *
 */
public class MultOperator extends BinaryOperator<Integer> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operand<Integer> performOperation() {
		if(op0 == null || op1 == null){
			throw new IllegalStateException("Could not perform operation prior to operands being set.");
		}
		Integer result = op0.getValue() * op1.getValue();
		return new Operand<Integer>(result);
	}
	
	public String toString() {
		return "*";
	}
}
