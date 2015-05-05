package ai;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import model.AbstractState.MOVE;
import model.State;
import eval.Evaluator;
import eval.ScoreEvaluator;

public class RobbieHamill2048 extends AbstractPlayer {

	private final Evaluator eval = new ScoreEvaluator();
	private final Random random = new Random();
	private Map<MOVE, Double> possibleMoves = new HashMap<MOVE, Double>();

	@Override
	public MOVE getMove(State game) {
		int depth = 2;
		pause();
		List<MOVE> bestMoves = new ArrayList<MOVE>();
		double bestScore = Double.NEGATIVE_INFINITY;
		// double score = Double.POSITIVE_INFINITY;
		for (MOVE move : game.getMoves()) {
			game.move(move);
			double score = eval.evaluate(game);
			System.out.println(score);
			game.undo();
			score = alphabeta(game, depth, Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY, true);
			System.out.println(score);
			if (score > bestScore) {
				bestMoves.clear();
				bestMoves.add(move);
				bestScore = score;
				 } else if (score == bestScore) {
				 bestMoves.add(move);
				 }
			}
			// return bestMoves.get(random.nextInt(bestMoves.size()));
			System.out.println(bestMoves);
		}
		return bestMoves.get(0);
	}

	public double alphabeta(State game, int depth, double a, double b,
			boolean studentName) {

		// System.out.println("a" + a);
		// System.out.println("b" + b);
		if (depth == 0 || game.getMoves().isEmpty()) {
			return a;
		}
		if (studentName) {
			for (MOVE move : game.getMoves()) {
				game.move(move);
				a = eval.evaluate(game);
				possibleMoves.put(move, a);
				game.undo();
				a = Math.max(a, alphabeta(game, depth - 1, a, b, false));
				// game.move(move);
				// a = eval.evaluate(game);
				// game.undo();
				if (b <= a) {
					break;
				}
				return a;
			}

		} else {
			for (MOVE move : game.getMoves()) {
				game.move(move);
				b = eval.evaluate(game);
				possibleMoves.put(move, b);
				game.undo();
				b = Math.min(b, alphabeta(game, depth - 1, a, b, true));
				// game.move(move);
				b = eval.evaluate(game);
				game.undo();
				if (b <= a) {
					break;
				}
				return b;
			}
		}
		return b;
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