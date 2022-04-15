from typing import Optional
from fastapi import FastAPI
from pydantic import BaseModel

import board

class State(BaseModel):
    gameType: str 
    currentSquare: str
    nextSquare: str # need to add board equivalency function for checkers
    curBoard: Optional[list[list[int]]] # curBoard will be optional when game is over

app = FastAPI()

# Helper functions
def fen_to_board(fen: str):
    #init new board array 
    for char in str:
        # if it is a space, end
        # if it is a / go to next row 
s
        piece = 1
        # and add offset (since our notations are different)

        # if char is lowercase, make it negative
    d = 0

    return 

gameBoard = 0 # global game board for running instance

@app.post("/")
async def process_move(state: State):
    newStateType = ""
    # parse state parameter
    # check game type:
    # if the game is chess
    if state.gameType == "Chess": 
        newStateType = "Chess"
        newMove = chess.Move(from_square = chess.parse_square(state.currentSquare), to_square = chess.parse_square(state.nextSquare))
        # convert to fen notation
        if newMove in gameBoard.legal_moves:
            passIn = 0 
            # pass into engine/minimax 
        else: return # move was illegal so return nothing
        # choose next move and set the board 
        state.nextBoard = 0 # should call fen to board function 

    # else if the game is checkers
    elif state.gameType == "Checkers": 
        newStateType = "Checkers"
        # take next board, check if valid, then currentBoard = nextBoard
        # take current board, pass into AI and generate next board
    # create new state based off engine result and send back
    curBoard = 0

    new_state = State(gameType = newStateType, currentSquare = "", nextSquare = "", curBoard = curBoard)

    return {"State": new_state}
    # full JSON format:
    # { 
    #   "State": { 
    #       gameType: str,
    #       nextBoard: int[][], (should this be optional)
    #       curBoard: int[][],
    #    }
    # }
    
    # representations of pieces
    # chess 
    # pawn: 1
    # king: 9
    # queen: 8
    # rook: 6
    # bishop: 4
    # knight: 3

        

