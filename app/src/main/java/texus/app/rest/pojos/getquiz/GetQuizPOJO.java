
package texus.app.rest.pojos.getquiz;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetQuizPOJO {

    @SerializedName("quiz")
    @Expose
    private List<Quiz> quiz = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
