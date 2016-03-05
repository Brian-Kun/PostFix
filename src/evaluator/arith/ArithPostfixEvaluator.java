package evaluator.arith;

import language.Operand;

import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	
	int operandCount = 0, operatorCount = 0;
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
		this.stack = new LinkedStack<>(); 
							//Operand<Integer>
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		// TODO use all of the things you've built so far to 
		// implement the algorithm for postfix expression evaluation
		
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				operandCount++;
				stack.push(token.getOperand());
				break;
				
			case OPERATOR:
				operatorCount++;
				if(token.getOperator().getNumberOfArguments() == 1){
					token.getOperator().setOperand(0, stack.pop());
					stack.push(token.getOperator().performOperation());
				}else{
					token.getOperator().setOperand(1, stack.pop());
					token.getOperator().setOperand(0, stack.pop());
					stack.push(token.getOperator().performOperation());
				}
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}			
		}
		//it is an invalid postfix if there are more operators than operands
		if(operatorCount > operandCount){
			throw new IllegalPostfixExpressionException();
		}
		//it is an invalid postfix there are no operands
		if(operandCount != 1 && operatorCount == 0){
			throw new IllegalPostfixExpressionException();
		}
		
		return stack.top().getValue();
	}

}
