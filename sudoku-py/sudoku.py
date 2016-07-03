game = [
            6,8,7,5,0,0,4,0,0,
            1,2,0,7,0,4,8,0,3,
            0,0,0,8,0,0,0,5,0,
            4,0,0,2,8,0,1,7,0,
            2,0,9,4,0,0,6,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,1,6,0,0,5,2,8,
            7,0,8,0,0,2,0,0,0,
            0,0,0,3,0,0,0,0,0
]
zeros = [ index for index in range(81) if game[index] == 0 ]
#print(zeros)

def printGame():
    for row in range(9):
        rowValues = [ game[row * 9 + col] for col in range(9) ]
        print("   ".join([str(value) for value in rowValues]))

def allowedValues(level):
    index = zeros[level]
    (row, col) = (index / 9, index % 9)
    rowValues = set([ game[row * 9 + i] for i in range(9) ])
    colValues = set([ game[i * 9 + col] for i in range(9) ])
    blockCoord = [ (row / 3 * 3 + i / 3, col / 3 * 3 + i % 3) for i in range(9) ]
    blockValues = set([ game[r * 9 + c] for (r, c) in blockCoord ])
    return set(range(1, 10)).difference(rowValues, colValues, blockValues)
#for level in range(len(zeros)): print(level, allowedValues(level))

def fit(level):
    for value in allowedValues(level):
        game[zeros[level]] = value
        if level == len(zeros) - 1:
            print("------------------------------")
            printGame()
        else:
            fit(level + 1)
    game[zeros[level]] = 0

printGame()
fit(0)
print("done")
