import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.regex.*;

public class DashGame {
  public static void main(String[] args) {}

    public static String replaceVowel(String userInput) {
      String currentInput = userInput.replaceAll("[?ia,e,i,o,u]", "_");
      return currentInput;
    }
}
