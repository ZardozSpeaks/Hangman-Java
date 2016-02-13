import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.regex.*;

public class DashGame {
  public static void main(String[] args) {
    staticFileLocation("/public");

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/results.vtl");

      String input = request.queryParams("userinput");
      String results = replaceVowel(input);

      model.put("results", results);
      model.put("input", input);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/detector", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/detector.vtl");

      String guess = request.queryParams("userguess");
      String input = request.queryParams("userinput");
      String results = compareStrings(input, guess);

      model.put("results", results);
      model.put("input", input);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }

  public static String replaceVowel(String userInput) {
    String noVowels = userInput.replaceAll("(?i)[aeiou]", "-");
    return noVowels;
  }

  public static String compareStrings(String userInput, String guess) {
    char[] newUserInput = userInput.replaceAll(" ", "0").toCharArray();
    char[] newGuess = guess.replaceAll(" ", "0").toCharArray();
    String result = "Congrats, you guessed it!";
    int count = -1;

    for(int i=0; i < newUserInput.length; i++) {
      if (newUserInput[i] == newGuess[i]) {
        count += 1;
      } else if (newUserInput[i] != newGuess[i]) {
        result = String.format("Your guess is correct up to letter \" %s \" ", newGuess[count]);
      }
    }
    return result;
  }

}
