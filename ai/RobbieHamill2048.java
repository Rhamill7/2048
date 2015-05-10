package ai;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
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
		int limit = 4;
		double bestScore = Double.NEGATIVE_INFINITY;
		MOVE bestMove = null;
		for (MOVE move: game.getMoves()){
			game.move(move);
		double score = depthLimitedSearch(game, limit);
		game.undo();
		
		if(score > bestScore) {
			bestScore = score;
			bestMove = move;
//		} else if (score == bestScore) {
//			bestMoves.add(move);
//		}
		}
		}
		
		return bestMove;

		//pause();
	}

	
	public double depthLimitedSearch(State game, int limit){
		List<MOVE> bestMoves = new ArrayList<MOVE>();
		double score = 0;
		double bestScore = Double.NEGATIVE_INFINITY;
		for(MOVE move : game.getMoves()) {
			
			game.move(move);
			score = eval.evaluate(game);
			if (limit != 0){
		score =	depthLimitedSearch(game, limit-1);
		}
			
			if(score > bestScore) {
				bestMoves.clear();
				bestMoves.add(move);
				bestScore = score;
			} else if (score == bestScore) {
				bestMoves.add(move);
			}
			
			
			game.undo();

		}
		return bestScore;
		
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