from typing import Optional
from fastapi import FastAPI
from pydantic import BaseModel

class State(BaseModel):
    gameType: str 
    nextBoard: Optional[list[list[int]]] # need to add board equivalency function for checkers
    curBoard: Optional[list[list[int]]] # curBoard will be optional when game is over

app = FastAPI()


@app.post("/")
async def process_move(state: State):
    newStateType = ""
    # parse state parameter
    # check game type:
    # if the game is chess
    if state.gameType == "Chess": 
        newStateType = "Chess"
        # convert to fen notation 
        # pass into engine/minimax 
        # choose next move
        state.nextBoard

    # else if the game is checkers
    elif state.gameType == "Checkers": 
        newStateType = "Checkers"
        # take next board, check if valid, then currentBoard = nextBoard
        # take current board, pass into AI and generate next board
    # create new state based off engine result and send back
    nextBoard = 0
    curBoard = 0

    new_state = State(gameType = newStateType, nextBoard = nextBoard, curBoard = curBoard)

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

    # Helper functions 

