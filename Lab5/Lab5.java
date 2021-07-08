/**
 * Test out Maybe.of, Maybe.flatMap, Maybe.orElse
 * in this class. Uses 2 local transformers.
 *
 * @author Austin Loh (group B06)
 * @version CS2030 AY20/21 ST1
 */
import cs2030.fp.Maybe;
import cs2030.fp.Transformer;
import java.util.Map;

class Lab5 {
  /*
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> db) {
    Map<String, Map<String, String>> std = db.get(student);
    if (std == null) {
      return "No such entry";
    } else {
      Map<String, String> mod = std.get(module);
      if (mod == null) {
        return "No such entry";
      } else {
        String grade = mod.get(assessment);
        if (grade == null) {
          return "No such entry";
        }
        return grade;
      }
    }
  }
  */

  /**
   * If student, module or assessment does not exist in data, return string in
   * orElse. Otherwise, from initial map, get map of student, then map of module,
   * then map of assessment.
   *
   * @param student Access this student modules
   * @param module Access this module assessment
   * @param assessment Access this accessment grades
   * @param map Stores all data using Map
   * @return the grade, if present, else default message
   */
  public static String getGrade(String student, String module, String assessment,
      Map<String, Map<String, Map<String, String>>> map) {

    Transformer<Map<String, Map<String, String>>, Maybe<Map<String, String>>> getModule = new 
        Transformer<>() {
        public Maybe<Map<String, String>> transform(Map<String, Map<String, String>> mapOfPerson) {
          return Maybe.of(mapOfPerson.get(module));
        }
      };

    Transformer<Map<String, String>, Maybe<String>> getAssessment = new Transformer<>() {
      public Maybe<String> transform(Map<String, String> mapOfModule) {
        return Maybe.of(mapOfModule.get(assessment));
      }
    };

    return Maybe.of(map.get(student))
      .flatMap(getModule)
      .flatMap(getAssessment)
      .orElse("No such entry");
  }
  

  public static void main(String[] args) {
    Map<String, Map<String, Map<String, String>>> students =
        Map.of(
            "Steve", Map.of(
                "CS2030", Map.of(
                        "lab1", "A",
                        "lab2", "A-",
                        "lab3", "A+",
                        "lab4", "B",
                        "pe1", "C"),
                "CS2040", Map.of(
                        "lab1", "A",
                        "lab2", "A+",
                        "lab3", "A+",
                        "lab4", "A",
                        "midterm", "A+")),
            "Tony", Map.of(
                "CS2030", Map.of(
                    "lab1", "C",
                    "lab2", "C",
                    "lab3", "B-",
                    "lab4", "B+",
                    "pe1", "A")));

    System.out.println(getGrade("Steve", "CS2030", "lab1", students));
    System.out.println(getGrade("Steve", "CS2030", "lab2", students));
    System.out.println(getGrade("Steve", "CS2040", "lab3", students));
    System.out.println(getGrade("Steve", "CS2040", "lab4", students));
    System.out.println(getGrade("Tony", "CS2030", "lab1", students));
    System.out.println(getGrade("Tony", "CS2030", "midterm", students));
    System.out.println(getGrade("Tony", "CS2040", "lab4", students));
    System.out.println(getGrade("Bruce", "CS2040", "lab4", students));
  }
}
