"""Python version of Board.java
"""

from __future__ import annotations
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

	def evaluate_board(self) -> int:
		pass


def main():
	"""Test function, for debug use
	"""
	b = Board()
	for next_b in b.get_all_boards():
		print(next_b, end = '\n\n')

if __name__ == '__main__':
	main()
