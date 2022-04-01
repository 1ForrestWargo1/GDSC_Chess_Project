"""Python version of Minimax.java
"""

import board


class Minimax:
	def __init__(self):
		self.initial = board.Board()
		self.depth = 0
		self.turns = 0
		self.depth_limit = 0
	
	def evaluate_board_max(self, initial: board.Board, depth: int) -> int:
		nextb = initial.get_all_boards()
		max = -999
		if nextb == None or depth >= self.depth_limit:
			return initial.evaluate_board()
		for b in nextb:
			holder = self.evaluate_board_min(b, depth + 1)
			if max <= holder:
				max = holder
		return max
	
	def evaluate_board_min(self, initial: board.Board, depth: int) -> int:
		nextb = initial.get_all_boards()
		min = 999
		if nextb == None or depth >= self.depth_limit:
			return initial.evaluate_board()
		for b in nextb:
			holder = self.evaluate_board_max(b, depth + 1)
			if min >= holder:
				min = holder
		return min


def main():
	"""Test function, for debug use
	"""
	pass

if __name__ == '__main__':
	main()
