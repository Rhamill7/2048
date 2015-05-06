package ai;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

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
		List<MOVE> possibleMoves = new ArrayList<MOVE>();
		
		double bestScore = Double.NEGATIVE_INFINITY;
		
		
		for(MOVE move : game.getMoves()) {
			game.move(move);
			possibleMoves.add(move);
			double score = eval.evaluate(game);
			
			for(int i = 0; i<20; i++){
			for(MOVE move2 : game.getMoves()) {
				game.move(move2); 
				possibleMoves.add(move2);
				score = eval.evaluate(game);
				List<MOVE> direction = new ArrayList<MOVE>();
				direction.addAll(game.getMoves());
				if(direction.size()<4){
					game.undo();
					possibleMoves.remove(possibleMoves.size()-1);
					}			
				//break;
				}
			}
			game.
			
			
			for(int j = 0; j<possibleMoves.size(); j++){
				game.undo();
			}
			
			
			if(score > bestScore) {
				bestMoves.clear();
				bestMoves.add(move);
				bestScore = score;
			} else if (score == bestScore) {
				bestMoves.add(move);
			}
			
		}
		bestMoves.addAll(possibleMoves);
		
		MOVE bestMove = bestMoves.remove(0);
		
//		let Agenda = [S0
//		              ]
//		             while Agenda ≠ [] do
//		             let Current = First(Agenda)
//		             let Agenda = Rest(Agenda)
//		             if Goal(Current) then return(“Found it!”)
//		             let Next = NextStates(Current)
//		             let Agenda = Next+Agenda
//		
//		return bestMoves.get(random.nextInt(bestMoves.size()));
	return bestMove;
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