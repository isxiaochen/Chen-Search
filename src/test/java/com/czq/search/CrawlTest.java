package com.czq.search;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.czq.search.model.entity.Picture;
import com.czq.search.model.entity.Post;
import com.czq.search.service.PostService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CrawlTest {

    @Autowired
    private PostService postService;

    @Test
    public void fetchPicture() throws IOException {
        int current = 1;
        String url = "https://cn.bing.com/images/search?q=小黑子图片&first="+current;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictureList = new ArrayList<>();
        for (Element element : elements) {
            //取出图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map<String,Object> map = JSONUtil.toBean(m,Map.class);
            String murl = (String) map.get("murl");
            System.out.println(murl);
            //取出图片标题
            String title = element.select(".inflnk").get(0).attr("aria-label");

            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictureList.add(picture);
        }
    }


    @Test
    public void fetchArticles(){

        //1.获取数据源
        String json = "{\n" +
                "  \"current\": 7,\n" +
                "  \"pageSize\": 8,\n" +
                "  \"sortField\": \"createTime\",\n" +
                "  \"sortOrder\": \"descend\",\n" +
                "  \"category\": \"文章\",\n" +
                "  \"reviewStatus\": 1\n" +
                "}";
        String url = "https://www.code-nav.cn/api/post/search/page/vo";
        String result = HttpRequest.post(url)
                .header("Cookie","SESSION=MmRlNzQ1YTktNjZhNy00NmRkLTk5NmMtYWM1OTRlZDE4Nzg2; __51vcke__JtwgXxXhywMwFmhF=40819050-7fa4-5066-be08-6c4b412d8023; __51vuft__JtwgXxXhywMwFmhF=1683946544508; __51huid__JyijP8zIhGUD9hWn=7fc14870-6437-51ec-8e90-a9354ae3e6b4; __51uvsct__JtwgXxXhywMwFmhF=74; __vtins__JtwgXxXhywMwFmhF=%7B%22sid%22%3A%20%229e9e5126-3380-5af1-bbb4-ecf8e5eeb168%22%2C%20%22vd%22%3A%204%2C%20%22stt%22%3A%201232174%2C%20%22dr%22%3A%201200632%2C%20%22expires%22%3A%201692111979924%2C%20%22ct%22%3A%201692110179924%7D")
                .body(json)
                .execute().body();

        //2.解析数据源，将抓取到的数据转换成我们所需的对象
        Map<String,Object> map = JSONUtil.toBean(result,Map.class);
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records = (JSONArray) data.get("records");
        List<Post> postList = new ArrayList<>(records.size());
        for (Object record : records) {
            JSONObject obj = (JSONObject)record;
            Post post = new Post();
            post.setTitle(obj.getStr("title"));
            post.setContent(obj.getStr("content"));
            //直接getStr能获取标签列表马？
            String tags = obj.getStr("tags");
            post.setTags(tags);
            post.setUserId(1L);
            postList.add(post);
        }

        postService.saveBatch(postList);
//        System.out.println(postList);

//        System.out.println(map);


    }
}
