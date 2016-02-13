import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class DashGameTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("No Consequences Hangman");
  }

  @Test
  public void dashGame() {

    goTo("http://localhost:4567/");
    fill("#userinput").with("Hello World.");
    submit(".btn");
    assertThat(pageSource()).contains("H-ll- W-rld.");
  }

  @Test
  public void replaceVowel_inputReturnsStringWithNoVowels_noVowels(){
    DashGame testDashGame = new DashGame();
    assertEquals("th-s w-rks n-w", testDashGame.replaceVowel("this works now"));
  }

  @Test
  public void replaceVowel_inputIsCaseInsensitive_capitalizationCorrect() {
    DashGame testDashGame = new DashGame();
    assertEquals("W-W, th-s -v-n w-rks n-w", testDashGame.replaceVowel("WOW, thIs EvEn wOrks nOw"));
  }

  @Test
  public void compareStrings_displaysMissedPossition_returnsCorrectPosition() {
    DashGame testDashGame = new DashGame();
    assertEquals("Your guess is correct up to letter \" t \" ", testDashGame.compareStrings("Right answer", "Rights answer"));
  }

  @Test
  public void compareStrings_displaysMissedLetter_returnsCorrectLetter() {
    DashGame testDashGame = new DashGame();
    assertEquals("Your guess is correct up to letter \" i \" ", testDashGame.compareStrings("RiGht answer", "Right answer"));
  }

  @Test
  public void compareStrings_dislaysWin_returnsWinMessage() {
    DashGame testDashGame = new DashGame();
    assertEquals("Congrats, you guessed it!", testDashGame.compareStrings("Right answer", "Right answer"));
  }
}
