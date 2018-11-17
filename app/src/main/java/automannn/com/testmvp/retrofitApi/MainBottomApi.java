package automannn.com.testmvp.retrofitApi;


import automannn.com.testmvp.entity.MainBottomItem;
import automannn.com.testmvp.entity.UnicodeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainBottomApi {
    @POST("/mainmenu/findAll")
    Call<UnicodeResponse> chaxun(@Body MainBottomItem mainBottomItem, @Query(value = "pageSize") Integer pageSize, @Query(value = "pageNumber") Integer pageNum);
}
