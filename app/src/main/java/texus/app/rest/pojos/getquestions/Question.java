
package texus.app.rest.pojos.getquestions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("opB")
    @Expose
    private String opB;
    @SerializedName("opA")
    @Expose
    private String opA;
    @SerializedName("answerKey")
    @Expose
    private String answerKey;
    @SerializedName("opD")
    @Expose
    private String opD;
    @SerializedName("opC")
    @Expose
    private String opC;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("quizId")
    @Expose
    private Integer quizId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("version")
    @Expose
    private Integer version;

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
