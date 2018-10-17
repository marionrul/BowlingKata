package bowling

case class Game(rolls: List[Int]) {


  def roll(pins: List[Int], game: Game): Game = {
    if(pins.isEmpty) {
      val newGame = Game(game.rolls)
      newGame
    }
    else {
      val newGame = Game(game.rolls :+ pins.head)
      roll(pins.tail, newGame)
    }
  }

  def isSpare(rolls: List[Int]): Boolean = {
    rolls.head + rolls.tail.head == 10
  }

  def isStrike(rolls: List[Int]): Boolean = {
    rolls.head == 10
  }

  def strike(rolls: List[Int]): Int = {
   rolls.head + rolls.tail.head
  }

  def spare(rolls: List[Int]): Int= {
    rolls.tail.head
  }

  def sumRolls(rolls: List[Int]): Int= {
    rolls.head + rolls.tail.head
  }

  def getScore(rolls: List[Int], score: Int, nbFrame: Int): Int = {
    if(rolls.isEmpty) {
      score
    }
    else {
      if(isStrike(rolls)) {
        val newRolls = rolls.tail
        if(newRolls.tail.isEmpty) {
          val newScore = score + 10
          newScore
        }
        else {
          val newRolls2 = newRolls.tail
          if(newRolls2.tail.head == 0) {
            val newRolls3 = newRolls2.tail
            val newScore = 20 + newRolls3.tail.head
            val newNb = nbFrame + 1
            getScore(newRolls2, newScore, newNb)
          }
          else {
            val newScore = score + 10 + strike(newRolls.tail)
            if(nbFrame == 10) {
              newScore
            }
            else {
              val newNb = nbFrame + 1
              getScore(newRolls.tail, newScore, newNb)
            }

          }
        }
      }
      else if(isSpare(rolls)) {
        val newRolls = rolls.tail
        val newScore = score + 10 + spare(newRolls)
        if(newRolls.tail.isEmpty) {
          newScore
        }
        else {
          if(nbFrame == 10) {
            newScore
          }
          else {
            val newNb = nbFrame + 1
            getScore(newRolls.tail, newScore, newNb)
          }
        }
      }
      else {
        val newScore = score + sumRolls(rolls)
        val newRolls = rolls.tail
        if(newRolls.tail.isEmpty) {
          newScore
        }
        else {
          val newNb = nbFrame + 1
          getScore(newRolls.tail, newScore, newNb)
        }
      }
    }
  }

}

object Bowling {



}
