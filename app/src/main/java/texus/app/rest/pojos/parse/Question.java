
package texus.app.rest.pojos.parse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Ans")
    @Expose
    private String ans;
    @SerializedName("A")
    @Expose
    private String a;
    @SerializedName("B")
    @Expose
    private String b;
    @SerializedName("C")
    @Expose
    private String c;
    @SerializedName("D")
    @Expose
    private String d;
    @SerializedName("comnts")
    @Expose
    private String comnts;
    @SerializedName("Ques")
    @Expose
    private String ques;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getComnts() {
        return comnts;
    }

    public void setComnts(String comnts) {
        this.comnts = comnts;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

}
