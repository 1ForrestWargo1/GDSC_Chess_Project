"""Python version of Board.java
"""

from __future__ import annotations
import math
import chess

class Board(chess.Board): #extends chess.Board class
	def get_all_boards(self) -> list[Board]:
		temp_board = None
		next_boards = []
		curr_move = chess.Move.from_uci('0000')

		for square1 in chess.SQUARES: #iterate through all possible from_square and to_square
			for square2 in chess.SQUARES:
				curr_move.from_square = square1
				curr_move.to_square = square2

				if curr_move in self.legal_moves: #add to list only if the move is legal
					#print(f'legal move: {curr_move.uci()}')
					temp_board = self.copy()
					temp_board.push(curr_move)
					next_boards.append(temp_board)
		return next_boards
	
	def sigmoid(x) -> float:
		return 1 / (1 + math.exp(-x))

	def evaluate_board(self) -> float:
		if self.is_checkmate(): #TODO: unclear which is in checkmate, need to figure out
			return -1
		curr_piece = None
		sum_score = 0
		score_map = {chess.KING: 10000, chess.QUEEN: 9, chess.ROOK: 5, chess.BISHOP: 3, chess.KNIGHT: 3, chess.PAWN: 1}
		for square in chess.SQUARES:
			curr_piece = self.piece_at(square)
			if curr_piece == None:
				continue
			if curr_piece.color == chess.WHITE: #TODO: assume white is ours, other colors are opponents
				sum_score += score_map[curr_piece.piece_type]
			else:
				sum_score -= score_map[curr_piece.piece_type]
		return Board.sigmoid(sum_score)
		


def main():
	"""Test function, for debug use
	"""
	b = Board()
	print(b.evaluate_board())
	#for next_b in b.get_all_boards():
	#	print(next_b, end = '\n\n')

if __name__ == '__main__':
	main()
