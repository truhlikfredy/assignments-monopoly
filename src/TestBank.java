
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBank {
//  Bank bank;

  @Before
  public void setUp() throws Exception {
//    bank = new Bank();
  }

  @Test
  public void testRoundDown() {
    assertEquals(0, Bank.roundMoney(4999L).intValue());
    assertEquals(0, Bank.roundMoney(2000L).intValue());
    assertEquals(10000, Bank.roundMoney(14000L).intValue());
    assertEquals(100000, Bank.roundMoney(102000L).intValue());
  }

  @Test
  public void testRoundUp() {
    assertEquals(10000, Bank.roundMoney(5000L).intValue());
    assertEquals(20000, Bank.roundMoney(16000L).intValue());
    assertEquals(110000, Bank.roundMoney(109999L).intValue());
  }
  
  @Test
  public void testMoneyFormating() {
    assertEquals("€1 010k", Bank.moneyEuro(1010000L));
    assertEquals("€30k", Bank.moneyEuro(30000L));
    assertEquals("€100k", Bank.moneyEuro(100000L));
    assertEquals("€1.1M", Bank.moneyEuro(1100000L));
    assertEquals("€900k", Bank.moneyEuro(900000L));
    assertEquals("€1 110k", Bank.moneyEuro(1110000L));
    assertEquals("€96.7M", Bank.moneyEuro(96700000L));
    assertEquals("€96 780k", Bank.moneyEuro(96780000L));
  }

}