import bowling._
import org.scalatest.{FunSpec, Matchers}


class BowlingSpec extends FunSpec with Matchers {

  val rolls = List(0)
  val game = Game(rolls)

  describe("isSpare") {

    it("should return true") {
      val rolls = List(8, 2)
      assert(game.isSpare(rolls))
    }

    it("should return false") {
      val rolls = List(4, 2)
      assert(!game.isSpare(rolls))
    }

  }

  describe("isStrike") {

    it("should return true") {
      val rolls = List(10, 0)
      assert(game.isStrike(rolls))
    }

    it("should return false") {
      val rolls = List(4, 2)
      assert(!game.isStrike(rolls))
    }

  }

  describe("strike") {

    it("should return the right score") {
      val rolls = List(5, 2)
      assert(game.strike(rolls) == 7)
    }

    it("should not return the right score") {
      val rolls = List(6, 2)
      assert(!(game.strike(rolls) == 10))
    }

  }

  describe("spare") {

    it("should return the right score") {
      val rolls = List(10, 8)
      assert(game.spare(rolls) == 8)
    }

    it("should not return the right score") {
      val rolls = List(6, 2)
      assert(!(game.spare(rolls) == 6))
    }

  }


  describe("sumRolls") {

    it("should return the right score") {
      val rolls = List(5,4)
      assert(game.sumRolls(rolls) == 9)
    }

    it("should not return the right score") {
      val rolls = List(6, 2)
      assert(!(game.sumRolls(rolls) == 6))
    }

  }


  describe("play") {

    it("all rolls with 0 should return a score of 0") {
      val game = Game(Nil)
      val pins = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 0)
    }

    it("all rolls with 1 should return a score of 20") {
      val game = Game(Nil)
      val pins = List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 20)
    }

    it("all rolls with 1 except one with 1 spare should return a score of 29") {
      val game = Game(Nil)
      val pins = List(8,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 29)
    }

    it("all rolls with 1 except one with 1 strike should return a score of 30") {
      val game = Game(Nil)
      val pins = List(10,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 30)
    }

    it("all rolls with 1 except a spare at the end with 2 should return a score of 31") {
      val game = Game(Nil)
      val pins = List(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8,2,3,0)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 31)
    }

    it("a strike at the start and a spare at the end with 2 should return a score of 40") {
      val game = Game(Nil)
      val pins = List(10,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8,2,2,0)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 40)
    }


    it("should return a score of 300") {
      val game = Game(Nil)
      val pins = List(10,0,10,0,10,0)
      val newGame = game.roll(pins, game)
      val score = newGame.getScore(newGame.rolls, 0, 1)
      assert(score == 300)
    }

  }


}

