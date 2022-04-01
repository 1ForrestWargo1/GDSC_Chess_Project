from typing import Optional
from fastapi import FastAPI
from pydantic import BaseModel


class State(BaseModel):
    gameType: str 
    nextBoard: list[list[int]] # need to add board equivalency function for checkers
    curBoard: list[list[int]]

app = FastAPI()


@app.post("/")
async def process_move(state: State):
    # parse state parameter
    # check game type
    # if chess 
        # take current board, pass into AI and generate next board

    # else if checkers 
        # take next board, check if valid, then currentBoard = nextBoard
        # take current board, pass into AI and generate next board
    # (run java program and pass in parsed state object?)

    # create new state (based off java process?) and send back
    nextBoard = 0
    curBoard = 0

    new_state = State(nextBoard = nextBoard, curBoard = curBoard)

    return {"State": new_state}
    # full JSON format:
    # { 
    #   "State": { 
    #       gameType: str,
    #       nextBoard: int[][], (should this be optional)
    #       curBoard: int[][],
    #    }
    # }
    