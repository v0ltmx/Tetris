package com.example.qtetris

class Pecas {
    var x = 1
    var y = 11
    var linha = 4
    var coluna = 4
    var type: Int = 0

    constructor(type: Int){
        this.type = type
        criarPecas(type)
    }
    var peca = Array(linha){
        Array(coluna){0}
    }

    fun criarPecas(type: Int){
        when(type){
     //Peça I
            1 ->{
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[1][3] = 1
            }
    //Peca O
            2 ->{
                this.peca[1][1]= 1
                this.peca[1][2]= 1
                this.peca[2][1]= 1
                this.peca[2][2]= 1
            }
    //PEca T
            3 ->{
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[2][1] = 1
            }
    //Peca J
            4 ->{
                this.peca[1][0] = 1
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
            }
   //Peca L
            5 ->{
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
                this.peca[1][2] = 1
            }
 //Peca S
            6 ->{
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
            }
 // PEca Z
            7 ->{
                this.peca[1][0] = 1
                this.peca[1][1] = 2
                this.peca[2][1] = 3
                this.peca[2][2] = 4
            }
        }
    }

    fun verificaColisaoMoveRight(board: Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i][this.y+j+1] != 0)
                        return false
                }
            }
        }
        return true
    }

    fun moveRight(){
        y++
    }

    fun verificaColisaoMoveLeft(board: Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i][this.y+j-1] != 0)
                        return false
                }
            }
        }
        return true
    }
    fun moveLeft(){
        y--
    }

    fun verificaColisaoMoveDown(board:Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i+1][this.y+j] != 0){
                        return false
                    }
                }
            }
        }
        return true
    }
    fun moveDown(){
        this.x++
    }

    fun verificaPecaI(board: Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(verificaColisaoMoveRight(board) && verificaColisaoMoveLeft(board) && verificaColisaoMoveDown(board)){
                        if(board[this.x+i+2][this.y+j]  != 0 || board[this.x+i][this.y+j-2] != 0 || board[this.x+i][this.y+j+2] != 0){
                            return false
                        }
                    }else{
                            return false
                    }
                }
            }
        }
        return true
    }
    fun verificaRotate(board: Array<Array<Int>>){
        if(this.type != 1){
            if(verificaColisaoMoveRight(board) && verificaColisaoMoveLeft(board) && verificaColisaoMoveDown(board)){
                rotarPeca()
            }
        }
    }

    fun rotarPeca(){
        var temp = Array(linha){
            Array(coluna){0}
        }
        for(i in 0 until linha){
            var aux = 0
            for(j in coluna - 1 downTo 0){
                temp[i][aux] = peca[j][i]
                aux++
            }
        }
        peca = temp
    }

    fun printPeca(board: Array<Array<Int>>):Array<Array<Int>>{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    board[this.x+i][this.y+j] = 1
                }
            }
        }
        return board
    }

}