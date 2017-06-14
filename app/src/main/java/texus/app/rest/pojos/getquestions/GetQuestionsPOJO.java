
package texus.app.rest.pojos.getquestions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetQuestionsPOJO {

    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
