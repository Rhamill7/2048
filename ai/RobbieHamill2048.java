package ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.Random;

import model.AbstractState.MOVE;
import model.State;
import eval.Evaluator;
import eval.ScoreEvaluator;

public class RobbieHamill2048 extends AbstractPlayer {

	private final Evaluator eval = new ScoreEvaluator();
	private final Random random = new Random();

	@Override
	public MOVE getMove(State game) {

		pause();

		List<MOVE> bestMoves = new ArrayList<MOVE>();

		double bestScore = Double.NEGATIVE_INFINITY;

		for (MOVE move : game.getMoves()) {

			game.move(move);
			double score = eval.evaluate(game);
			game.undo();

			if (score > bestScore) {
				bestMoves.clear();
				bestMoves.add(move);
				bestScore = score;
			} else if (score == bestScore) {
				bestMoves.add(move);
			}

		}

		return bestMoves.get(random.nextInt(bestMoves.size()));
	}

	public double maxValue(State game,double a, double b) {//returns a utility value α
	if (game.  .equals(terminated)){
		return a; //if Terminal-Test(state) then return Utility(state) α
	}
	double v = a;  //v <= MinimalGameValue a
	for (MOVE move: game.getMoves()){ //for s in Successors(state) do α
		double vDash = minValue(game, a, b); //v′ ← Min-Value(s, α, β))
		if( vDash>v){
			v = vDash;
		} 
		if (vDash>= b){
			return v;
		}
		if (vDash < a) {
			a = vDash;qqe 0
		}
	} 
	return v;	
			 }

	public double minValue(State game,double a, double b){ //returns a utility value α
	 if Terminal-Test(state) then return Utility(state) α
	 v ← MaximalGameValue α
	 for s in Successors(state) do α
	 v′ ← Max-Value(s, α, β))
	 if v′ < v, v ← v′α
	 if v′ ≤ α then return v
	 if v′ < β then β ← v′
	 return v 
	}

	@Override
	public int studentID() {
		return 201213786;
	}

	@Override
	public String studentName() {
		return "Robbie Hamill";
	}

}