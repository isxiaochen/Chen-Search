package com.czq.search.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czq.search.model.dto.picture.PictureQueryRequest;
import com.czq.search.model.entity.Picture;
import com.czq.search.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> getPicturePage(PictureQueryRequest pictureQueryRequest) {

        String searchText = pictureQueryRequest.getSearchText();
        long pages = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        long current = (pages-1)*size;

        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s",searchText,current);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictureList = new ArrayList<>();
        for (Element element : elements) {
            //取出图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            System.out.println(murl);
            //取出图片标题
            String title = element.select(".inflnk").get(0).attr("aria-label");

            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictureList.add(picture);

            if (pictureList.size()>=size){
                break;
            }
        }

        Page<Picture> page = new Page<Picture>(current,size);

        page.setRecords(pictureList);
        return page;
    }

}
