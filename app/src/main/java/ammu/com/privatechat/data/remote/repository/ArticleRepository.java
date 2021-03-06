package ammu.com.privatechat.data.remote.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import java.util.List;

import javax.inject.Inject;

import ammu.com.privatechat.data.local.dao.UserDao;
import ammu.com.privatechat.data.local.entity.UserEntity;
import ammu.com.privatechat.data.remote.ApiService;
import ammu.com.privatechat.data.remote.NetworkBoundResource;
import ammu.com.privatechat.data.remote.Resource;
import ammu.com.privatechat.data.remote.ResponseListener;
import ammu.com.privatechat.data.remote.model.PopularArticleResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * The article repository which has access to local and remote data fetching services
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
public class ArticleRepository {

    private final UserDao articleDao;
    private final ApiService apiService;

    @Inject
    ArticleRepository(UserDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the popular articles from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @param howfarback index indicating how far back
     * @return List of articles
     */
    public LiveData<Resource<List<UserEntity>>> loadPopularArticles(int howfarback) {
        return new NetworkBoundResource<List<UserEntity>, PopularArticleResponse>() {

            @Override
            protected void saveCallResult(PopularArticleResponse item) {
//                if(null != item)
//                    articleDao.saveArticles(item.getPopularArticles());
            }

            @NonNull
            @Override
            protected LiveData<List<UserEntity>> loadFromDb() {
                return articleDao.loadUser();
            }

            @NonNull
            @Override
            protected Call<PopularArticleResponse> createCall() {
                return apiService.loadPopularArticles(howfarback);
            }
        }.getAsLiveData();
    }


    /**
     * This method fetches the HTML comntent from the url and parses it and fills the model
     * @param url url to be fetched
     * @param responseListener callback
     */
    @SuppressLint("CheckResult")
    public void loadArticleDetails(String url, ResponseListener responseListener) {
        UserEntity articleDetails = new UserEntity();
        Observable.fromCallable(() -> {
            //Document document = Jsoup.connect(url).get();
            articleDetails.setTitle("");
            articleDetails.setContent("");
            return false;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> responseListener.onSuccess(articleDetails),
                 (error -> responseListener.onFailure(error.getMessage())));

    }

}
