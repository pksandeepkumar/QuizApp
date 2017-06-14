package texus.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import texus.app.database.DatabasesHelper;
import texus.app.rest.AppAPIs;
import texus.app.rest.ServiceGenerator;
import texus.app.rest.pojos.AddResponsePOJO;
import texus.app.rest.pojos.getquestions.GetQuestionsPOJO;
import texus.app.utils.AppConstance;

/**
 * Created by sandeep on 4/5/2017.
 */

public class Question extends BaseDataModel  {

    public static final String TABLE_NAME = "TableQuestion";

    public static final String OP_B = "opB";
    public static final String OP_A = "opA";
    public static final String ANSWER_KEY = "answerKey";
    public static final String OP_D = "opD";
    public static final String OP_C = "opC";
    public static final String COMMENTS = "comments";
    public static final String QUESTION = "question";
    public static final String QUIZ_ID = "quizId";
    public static final String QUESTION_ID = "question_id";
    public static final String VERSION = "version";
    public static final String VIEWED="viewed";
    public static final String USER_ANSWER = "userAnswer";

    public static final String UNANSWER_STRING = "U";


    public static final String CREATE_TABLE_QUERY = "CREATE TABLE  " + TABLE_NAME
            + " ( " + ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + OP_B + " TEXT , "
            + OP_A + " TEXT , "
            + ANSWER_KEY + " VARCHAR(3) , "
            + USER_ANSWER + " VARCHAR(3) , "
            + OP_D + " TEXT , "
            + OP_C + " TEXT , "
            + COMMENTS + " TEXT , "
            + QUESTION + " TEXT , "
            + QUIZ_ID + " INTEGER , "
            + QUESTION_ID + " INTEGER , "
            + VIEWED + " INTEGER DEFAULT 0 , "
            + VERSION + " INTEGER)";


    public String opB = "";
    public String opA = "";
    public String answerKey = "";
    public String userAnswer = UNANSWER_STRING;
    public String opD = "";
    public String opC = "";
    public String comments = "";
    public String question = "";
    public int quizId = 0;
    public int question_id = 0;
    public int version = 0;
    public boolean viewed = false;


    public Question() {

    }

    public Question(texus.app.rest.pojos.parse.Question object,int quizId,  int version) {
        if( object != null) {
            opB = (object.getB() == null)? "" :object.getB();
            opA = (object.getA() == null)? "" :object.getA();
            answerKey = (object.getAns() == null)? "" :object.getAns();
            opD = (object.getD() == null)? "" :object.getD();
            opC = (object.getC() == null)? "" :object.getC();
            comments = (object.getComnts() == null)? "" :object.getComnts();
            this.question = (object.getQues() == null)? "" :object.getQues();
            this.quizId = quizId;
            question_id = AppConstance.INVALID_VALUE;
            this.version = version;


        }
    }

    public Question( texus.app.rest.pojos.getquestions.Question question) {
        if( question != null) {
            opB = (question.getOpB() == null)? "" :question.getOpB();
            opA = (question.getOpA() == null)? "" :question.getOpA();
            answerKey = (question.getAnswerKey() == null)? "" :question.getAnswerKey();
            opD = (question.getOpD() == null)? "" :question.getOpD();
            opC = (question.getOpC() == null)? "" :question.getOpC();
            comments = (question.getComments() == null)? "" :question.getComments();
            this.question = (question.getQuestion() == null)? "" :question.getQuestion();
            quizId = (question.getQuizId() == null)? 0 :question.getQuizId();
            question_id = (question.getId() == null)? 0 :question.getId();
            version = (question.getVersion() == null)? 0 :question.getVersion();
        }

    }

//    public Quiz(sbl.kilban.rest.pojos.offers.Offer offer) {
//        if( offer != null) {
//            offerName = ( offer.getOfferName() == null ) ? "" : offer.getOfferName();
//            offerDescription = ( offer.getOfferDescription() == null )
//                    ? "" : offer.getOfferDescription();
//            adAttachmentId = ( offer.getAdAttachmentId() == null ) ? 0 : offer.getAdAttachmentId();
//            offerID = ( offer.getOfferId() == null ) ? 0 : offer.getOfferId();
//            String uploadedDateString = ( offer.getUploadedDate() == null )
//                    ? "" : offer.getUploadedDate();
//            DateManager dateManager = DateManager.parseYYYYMMDD(uploadedDateString);
//            if( dateManager != null) {
//                uploadedDate = dateManager.getTimeInMilliseconds();
//            }
//            isAttachment = ( offer.getIsAttachment() == null ) ? false : offer.getIsAttachment();
//        }
//
//
//    }

    //DB Methods>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static void insertObject(DatabasesHelper db, Question object) {
        if( object == null) return;
        SQLiteDatabase sqld = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OP_B, object.opB);
        cv.put(OP_A, object.opA);
        cv.put(ANSWER_KEY, object.answerKey);
        cv.put(OP_D, object.opD);
        cv.put(OP_C, object.opC);
        cv.put(COMMENTS, object.comments);
        cv.put(QUESTION, object.question);
        cv.put(QUIZ_ID, object.quizId);
        cv.put(QUESTION_ID, object.question_id);
        cv.put(VERSION, object.version);
        cv.put(USER_ANSWER, object.userAnswer);
        cv.put(VIEWED, (object.viewed) ? 1 : 0 );
        long result = sqld.insert(TABLE_NAME, null,cv);
        object.id = result;
        sqld.close();
    }


    public static void insertOrUpdate(DatabasesHelper db, Question object) {
        if( object == null) return;
        Question temp = getAnObjectQuestionID(db, object.question_id);
        if( temp == null ) {
            insertObject(db, object);
        } else {
            updateObjectWrtQuestionID(db, object);
        }
    }

    public static void updateObject(DatabasesHelper db, Question object) {
        if( object == null) return;
        SQLiteDatabase sqld = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OP_B, object.opB);
        cv.put(OP_A, object.opA);
        cv.put(ANSWER_KEY, object.answerKey);
        cv.put(OP_D, object.opD);
        cv.put(OP_C, object.opC);
        cv.put(COMMENTS, object.comments);
        cv.put(QUESTION, object.question);
        cv.put(QUIZ_ID, object.quizId);
        cv.put(QUESTION_ID, object.question_id);
        cv.put(VERSION, object.version);
//        cv.put(USER_ANSWER, object.userAnswer);
//        cv.put(VIEWED, (object.viewed) ? 1 : 0 );
        sqld.update(TABLE_NAME, cv, ID + "=" + object.id, null);
        sqld.close();
    }

    public static void updateObjectWrtQuestionID(DatabasesHelper db, Question object) {
        if( object == null) return;
        SQLiteDatabase sqld = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OP_B, object.opB);
        cv.put(OP_A, object.opA);
        cv.put(ANSWER_KEY, object.answerKey);
        cv.put(OP_D, object.opD);
        cv.put(OP_C, object.opC);
        cv.put(COMMENTS, object.comments);
        cv.put(QUESTION, object.question);
        cv.put(QUIZ_ID, object.quizId);
        cv.put(QUESTION_ID, object.question_id);
        cv.put(VERSION, object.version);
        sqld.update(TABLE_NAME, cv, QUESTION_ID + " = " + object.question_id, null);
        sqld.close();
    }

    public static void saveAnswer(DatabasesHelper db, Question object) {
        if( object == null) return;
        SQLiteDatabase sqld = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_ANSWER, object.userAnswer);
        sqld.update(TABLE_NAME, cv, QUESTION_ID + " = " + object.question_id, null);
        sqld.close();
    }

    public static void setViewed(DatabasesHelper db, Question object) {
        if( object == null) return;
        SQLiteDatabase sqld = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(VIEWED, (object.viewed) ? 1 : 0 );
        sqld.update(TABLE_NAME, cv, QUESTION_ID + " = " + object.question_id, null);
        sqld.close();
    }

    public static void insertObjects(DatabasesHelper db, ArrayList<Question> objects) {
        if( objects == null) return;
        for( Question object : objects) {
            if( null == object ) continue;
            insertObject(db, object);
        }
    }

    public static Question getIdOnlyFromCursor(Cursor c ) {
        Question instance = null;
        if( c != null) {
            instance = new Question();
            instance.id = c.getLong(c.getColumnIndex(ID));
            instance.question_id = c.getInt(c.getColumnIndex(QUESTION_ID));
        } else {
        }
        return instance;
    }

    public static Question getAnObjectFromCursor(Cursor c ) {
        Question instance = null;
        if( c != null) {
            instance = new Question();
            instance.id = c.getLong(c.getColumnIndex(ID));
            instance.opB = c.getString(c.getColumnIndex(OP_B));
            instance.opA = c.getString(c.getColumnIndex(OP_A));
            instance.answerKey = c.getString(c.getColumnIndex(ANSWER_KEY));
            instance.opD = c.getString(c.getColumnIndex(OP_D));
            instance.opC = c.getString(c.getColumnIndex(OP_C));
            instance.comments = c.getString(c.getColumnIndex(COMMENTS));
            instance.question = c.getString(c.getColumnIndex(QUESTION));
            instance.quizId = c.getInt(c.getColumnIndex(QUIZ_ID));
            instance.question_id = c.getInt(c.getColumnIndex(QUESTION_ID));
            instance.version = c.getInt(c.getColumnIndex(VERSION));
            instance.userAnswer = c.getString(c.getColumnIndex(USER_ANSWER));
            instance.viewed = (c.getInt(c.getColumnIndex(VIEWED)) == 1)? true: false;

        } else {
        }
        return instance;
    }


    public static ArrayList<Question> getAllObjects(DatabasesHelper db) {
        ArrayList<Question> objects = new ArrayList<Question>();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME ;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                objects.add(getAnObjectFromCursor(c));
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return objects;
    }

//    public static ArrayList<Question> getAllObjects(DatabasesHelper db, Version version) {
//        ArrayList<Question> objects = new ArrayList<Question>();
//        SQLiteDatabase dbRead = db.getReadableDatabase();
//        String query = "select * from " + TABLE_NAME + " WHERE " + QUIZ_ID + " = "
//                + version.quizID + " AND " + VERSION + " = " + version.versions;
//        Cursor c = dbRead.rawQuery(query, null);
//        if (c.moveToFirst()) {
//            do {
//                objects.add(getAnObjectFromCursor(c));
//            } while ( c.moveToNext()) ;
//        }
//        c.close();
//        dbRead.close();
//        return objects;
//    }

    public static int getTotalQuestionCount(DatabasesHelper db, int quizID) {
        int count = 0;
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " WHERE " + QUIZ_ID + " = "
                + quizID ;
        Cursor c = dbRead.rawQuery(query, null);
        count = c.getCount();
        c.close();
        dbRead.close();
        return count;
    }




    public static Question getAnObjectFromQuizID(DatabasesHelper db, int quizID) {
        Question object = null;
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " WHERE "
                + QUIZ_ID + " = " + quizID;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                object = getAnObjectFromCursor(c);
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return object;
    }

    public static Question getAnObjectQuestionID(DatabasesHelper db, int questionID) {
        Question object = null;
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " WHERE "
                + QUESTION_ID + " = " + questionID;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                object = getAnObjectFromCursor(c);
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return object;
    }

    //Retruns IDs of viewed or unviewed questions
    public static ArrayList<Question> getAllObjectsIDViewed(DatabasesHelper db, boolean viewed) {
        ArrayList<Question> objects = new ArrayList<Question>();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select " + ID + "," + QUESTION_ID
                + "  from " + TABLE_NAME + " WHERE " + VIEWED
                + " = " + (viewed ? 1 : 0) ;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                objects.add(getIdOnlyFromCursor(c));
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return objects;
    }

    public static ArrayList<Question> getAllObjectsID(DatabasesHelper db) {
        ArrayList<Question> objects = new ArrayList<Question>();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME ;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                objects.add(getIdOnlyFromCursor(c));
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return objects;
    }

    public static ArrayList<Question> getAllObjectsIDAnswered(DatabasesHelper db, boolean viewed) {
        ArrayList<Question> objects = new ArrayList<Question>();
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " WHERE " + VIEWED
                + " = " + (viewed ? 1 : 0) ;;
        Cursor c = dbRead.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                objects.add(getIdOnlyFromCursor(c));
            } while ( c.moveToNext()) ;
        }
        c.close();
        dbRead.close();
        return objects;
    }

    public static int getAllQuestionsCount(DatabasesHelper db, Context context) {
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = " select " + QUESTION_ID  + " from " + TABLE_NAME  ;
        Cursor c = dbRead.rawQuery(query, null);
        int count = c.getCount();
        c.close();
        dbRead.close();
        db.close();
        return count;
    }


    public static int getUnAnsweredQuestionsCount(DatabasesHelper db, Context context) {

        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = " select " + QUESTION_ID  +" from "
                + TABLE_NAME + " where " + USER_ANSWER + " = '" + UNANSWER_STRING + "'" ;
        Cursor c = dbRead.rawQuery(query, null);
        int count = c.getCount();
        c.close();
        dbRead.close();
        db.close();
        return count;
    }

    public static int getCorrectAnswerQuestionsCount(DatabasesHelper db, Context context) {
        SQLiteDatabase dbRead = db.getReadableDatabase();
        String query = " select " + QUESTION_ID  +" from "
                + TABLE_NAME + " where " + USER_ANSWER
                + " = " + ANSWER_KEY ;
        Cursor c = dbRead.rawQuery(query, null);
        int count = c.getCount();
        c.close();
        dbRead.close();
        db.close();
        return count;
    }






    public static boolean deleteTable(DatabasesHelper db) {
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            String query = "DELETE from " + TABLE_NAME;
            sql.execSQL(query);
            sql.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteTableARow(DatabasesHelper db, int id) {
        try {
            SQLiteDatabase sql = db.getWritableDatabase();
            String query = "DELETE from " + TABLE_NAME + "WHERE " + ID + " = " + id;
            sql.execSQL(query);
            sql.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //API Calls>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public Call<GetQuestionsPOJO> getQuestions(int quizID, int version) {
        AppAPIs serviceApi =  ServiceGenerator.createService(AppAPIs.class);
        Call<GetQuestionsPOJO> call = serviceApi.getQuestions(
                getRequestParam(quizID, version ));
        return call;
    }

//    {
//        "quizID":2,
//            "version":1
//    }

    public static RequestBody getRequestParam(int quizID, int version) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("quizID",quizID);
        hashMap.put("version",version);
        String param = (new JSONObject(hashMap)).toString();
        RequestBody body = RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                param);
        return  body;
    }

    public Call<AddResponsePOJO> addQuestion() {
        AppAPIs serviceApi =  ServiceGenerator.createService(AppAPIs.class);
        Call<AddResponsePOJO> call = serviceApi.addQuestion(getRequestParam());
        return call;
    }

    public  RequestBody getRequestParam() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("version",version);
        hashMap.put("quizId",quizId);
        hashMap.put("question",question);
        hashMap.put("opA",opA);
        hashMap.put("opB",opB);
        hashMap.put("opC",opC);
        hashMap.put("opD",opD);
        hashMap.put("answerKey",answerKey);
        hashMap.put("comments",comments);
        String param = (new JSONObject(hashMap)).toString();
        RequestBody body = RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                param);
        return  body;
    }

    public static String getJSON( ArrayList<Question> questions) {
        String json = "";
        if( questions == null) return json;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        JSONArray array = new JSONArray();
        for ( Question question : questions) {
            JSONObject object = new JSONObject();
            try {
                object.put("Ans", question.answerKey);
                object.put("A", question.opA);
                object.put("B", question.opB);
                object.put("C", question.opC);
                object.put("D", question.opD);
                object.put("comnts", question.comments);
                object.put("Ques", question.question);
                object.put("id", 1);
                array.put(object);
            } catch ( JSONException e) {}

        }
        hashMap.put("Questions",array);

        return  json;
    }






}
