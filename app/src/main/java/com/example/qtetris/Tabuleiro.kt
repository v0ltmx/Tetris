package com.example.qtetris

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.qtetris.Constantes.Constantes
import com.example.qtetris.Constantes.Dificuldades
import com.example.qtetris.databinding.ActivityTabuleiroBinding
import kotlin.random.Random

class Tabuleiro : AppCompatActivity() {
   lateinit var binding: ActivityTabuleiroBinding
    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300

    var peca = Pecas(1)

    inner class Ponto(var x:Int, var y: Int){
        fun moveDown(){
            x++
        }
    }
    var board = Array(LINHA){
        Array(COLUNA){0}
    }
    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_tabuleiro)

        binding.moveEsquerdaButton.setOnClickListener{
            if(peca.verificaColisaoMoveLeft(board)){
                peca.moveLeft()
            }
        }

        binding.moveDireitaButton.setOnClickListener{
            if(peca.verificaColisaoMoveRight(board)){
                peca.moveRight()
            }
        }

        binding.moveBaixoButton.setOnClickListener{
            if(peca.verificaColisaoMoveDown(board)){
                peca.moveDown()
            }
        }


        binding.girarButton.setOnClickListener{
            peca.verificaRotate(board)
        }

        var gridboard =  findViewById<GridLayout>(R.id.gridboard)
        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)
        for(i in 0 until LINHA){
            for(j in 0 until COLUNA){
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false)as ImageView
                gridboard.addView(boardView[i][j])
            }
        }
        gameRun()
    }
    fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed)
                runOnUiThread{
                    for(i in 0 until LINHA){
                        for(j in 0 until COLUNA){
                            if((i == 0 || i == LINHA-1) || (j == 0 || j == COLUNA-1)){
                                boardView[i][j]!!.setImageResource(R.drawable.rosa)
                                board[i][j] = 2
                            }else if(board[i][j] == 1){
                                boardView[i][j]!!.setImageResource(R.drawable.white)
                            }else{
                                boardView[i][j]!!.setImageResource(R.drawable.black)
                            }
                        }
                    }
                    if(peca.verificaColisaoMoveDown(board)){
                        peca.moveDown()

                            }
                    else{
                        board = peca.printPeca(board)
                        peca = Pecas(Random.nextInt(1, 8))
                        }
                    try{
                        for(i in 0 until peca.linha){
                            for(j in 0 until peca.coluna){
                                if(peca.peca[i][j] == 1){
                                    boardView[peca.x+i][peca.y+j]!!.setImageResource(R.drawable.white)
                                }
                            }
                        }
                    }catch (e:ArrayIndexOutOfBoundsException){
                        running = false
                    }
                }
            }
        }.start()
    }
}