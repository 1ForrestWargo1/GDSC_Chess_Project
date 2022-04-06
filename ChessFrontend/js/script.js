var board = ChessBoard('board', {
    draggable: true,
    dropOffBoard: 'snapback',
    sparePieces: false,
    orientation: 'white',
})

board.start()

$('#startBtn').on('click', board.start)
$('#flipBtn').on('click', board.flip)