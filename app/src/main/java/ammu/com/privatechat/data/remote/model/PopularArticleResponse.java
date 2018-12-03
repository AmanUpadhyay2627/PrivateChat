package ammu.com.privatechat.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ammu.com.privatechat.data.local.entity.UserEntity;

/**
 * The model class which holds the top popular articles data
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
public class PopularArticleResponse {

    @SerializedName("results")
    private List<UserEntity> popularArticles;


    /**
     * This method return the list of article entities
     * @return List of entities
     */
    public List<UserEntity> getPopularArticles() {
        return popularArticles;
    }

    /**
     * This method sets the article entities
     * @param popularArticles - articleslist
     */
    @SuppressWarnings("unused")
    public void setPopularArticles(List<UserEntity> popularArticles) {
        this.popularArticles = popularArticles;
    }
}
