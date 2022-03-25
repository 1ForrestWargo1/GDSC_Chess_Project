from typing import Optional

from fastapi import FastAPI
from pydantic import BaseModel

class Board(BaseModel):
    arr: list[list[int]]

class State(BaseModel):
    nextMoves: list[Board]
    nextBoard: int
    curBoard: Optional[Board]

app = FastAPI()


@app.post("/")
async def play_move(state: State):
    # parse state parameter
    # run java program and pass in parsed state object

    nextMoves = 0
    nextBoard = 0
    curBoard = 0
    # create new state and send back
    

    new_state = State(nextMoves = nextMoves, nextBoard = nextBoard, curBoard = curBoard)
    return {"message": "Hello World"}