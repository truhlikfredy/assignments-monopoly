
import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class TestBoard {
  Board board;

  @Before
  public void setUp() throws Exception {
    board = new Board();
  }

  @Test
  public void testFilling() {
    //TODO maybe eclipse glitch but once it didn't wanted to run test at all and NotEquals wasn't present
    //     rebuilding project helped, but don't know what happened or it will happen again
//    assertThat(0, not(board.boardSize()));
    assertNotEquals(0, board.boardSize());
  }

  @Test
  public void testFull() {
    assertEquals(40,board.boardSize());
  }
   
  @Test
  public void getJailPosition() {
    assertNotEquals(0,board.getJailPosition());
  }

  @Test 
  public void getWestMeath() {
    assertNotEquals(0,board.getWestMeath());
  }

  @Test  
  public void getRoscommon() {
    assertNotEquals(0,board.getRoscommon());
  }

  @Test  
  public void getTyrone() {
    assertNotEquals(0,board.getTyrone());
  }

  @Test  
  public void getOffaly() {
    assertNotEquals(0,board.getOffaly());
  }  

}