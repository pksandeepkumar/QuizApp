package texus.app.rest;


import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import texus.app.rest.pojos.AddResponsePOJO;
import texus.app.rest.pojos.getquestions.GetQuestionsPOJO;
import texus.app.rest.pojos.getquiz.GetQuizPOJO;

/**
 * Created by sandeep on 17/2/2017.
 */
public interface AppAPIs {

    @POST("getQuizList")
    public Call<GetQuizPOJO> getQuizList();

    @POST("getQuestions")
    public Call<GetQuestionsPOJO> getQuestions(@Body RequestBody params);

    @POST("addQuestion")
    public Call<AddResponsePOJO> addQuestion(@Body RequestBody params);

    @POST("addQuestion")
    public Call<AddResponsePOJO> addQuiz(@Body RequestBody params);


//
//    @POST("getListOfAreas")
//    public Call<List<AreaPOJO>> getListOfAreas(@Body RequestBody params);
//
//    @POST("getListOfRoutes")
//    public Call<List<RoutePOJO>> getListOfRoutes(@Body RequestBody params);
//
//    @POST("getListOfDistributors")
//    public Call<List<DistributorPOJO>> getListOfDistributors(@Body RequestBody params);
//
//    @POST("getProductsWithCategory")
//    public Call<ProductItemsPOJO> getProducts(@Body RequestBody params);
//
//    @POST("getListOfShops")
//    public Call<List<ShopPOJO>> getListOfShops(@Body RequestBody params);
//
//    @POST("getAllSo")
//    public Call<List<SoPOJO>> getAllSo(@Body RequestBody params);
//
//    @POST("addProductOrders")
//    public Call<AddOrderPOJO> addProductOrders(@Body RequestBody params);
//
//    @POST("addTravelExpense")
//    public Call<AddTravelExpensePOJO> addTravelExpense(@Body RequestBody params);
//
//    @POST("addNewShops")
//    public Call<AddShopPOJO> addNewShops(@Body RequestBody params);
//
//    @POST("addTourProgram")
//    public Call<AddTourProgramePOJO> addTourProgram(@Body RequestBody params);
//
//    @POST("viewTravelExpense")
//    public Call<TravelExpensePOJO> viewTravelExpense(@Body RequestBody params);
//
//    @POST("viewTourProgram")
//    public Call<TourProgramePOJO> viewTourProgram(@Body RequestBody params);
//
//    @POST("getDailySales")
//    public Call<GetDailySalesPOJO> getDailySales(@Body RequestBody params);
//
//    @POST("viewSalesReturnData")
//    public Call<SalesReturnPOJO> viewSalesReturnData(@Body RequestBody params);
//
//    @POST("collectSalesReturnData")
//    public Call<AddSalesReturnPOJO> collectSalesReturnData(@Body RequestBody params);
//
//    @POST("login")
//    public Call<RegisterPOJO> login(@Body RequestBody params);
//
//    @POST("getTargetAndAchievement")
//    public Call<TargetAndAchievementPOJO> getTargetAndAchievement(@Body RequestBody params);
//
//    @POST("getPrimaryTargetAndAchievement")
//    public Call<TargetAndAchievementPOJO> getPrimaryTargetAndAchievement(@Body RequestBody params);
//
//    @POST("getCheckInCheckOut")
//    public Call<AttendanceLogoPOJO> getCheckinCheckout(@Body RequestBody params);
//
//    @POST("addOpeningStockDetails")
//    public Call<MessagePOJO> addOpeningStockDetails(@Body RequestBody params);
//
//    @POST("addOpeningStockDetails")
//    public Call<AddOpeningStockPOJO> addOpeningStock(@Body RequestBody params);
//
//    @POST("viewOpeningStockDetails")
//    public Call<ViewOpeningStockPOJO> viewOpeningStockDetails(@Body RequestBody params);
//
//    @POST("addCompetitorDetails")
//    public Call<AddCompetitorProduct> addCompetitorDetails(@Body RequestBody params);
//
//    @POST("removeCompetitorDetails")
//    public Call<RemoveCompetitorProductPOJO> removeCompetitorDetails(@Body RequestBody params);
//
//    @GET("viewOfferDetails")
//    public Call<ViewOfferPOJO> viewOfferDetails();
//
//    @GET("viewSalesInscentiveDetails")
//    public Call<ViewIncentivesPOJO> viewSalesInscentiveDetails();
//
//    @GET
//    Call<ResponseBody> downloadFileWithUrl(@Url String fileUrl);

}
