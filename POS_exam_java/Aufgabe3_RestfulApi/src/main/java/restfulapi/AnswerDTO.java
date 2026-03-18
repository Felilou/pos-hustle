package restfulapi;

public record AnswerDTO(
        String text,
        int points
) {

    public static AnswerDTO fromAnswer(PossibleAnswer a) {
        return new AnswerDTO(a.text, a.points);
    }
}
