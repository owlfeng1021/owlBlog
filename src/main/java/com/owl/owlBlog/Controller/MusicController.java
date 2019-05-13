package com.owl.owlBlog.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.owl.owlBlog.bo.Music;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MusicController {

    private final String prefixUrl = "http://music.163.com/api/playlist/detail?id=";
    private final String playUrl = "http://music.163.com/song/media/outer/url?id=";

    @GetMapping("/getPlayList")
    public List<Music> getPlayList(String listId) throws IOException {
        //拼接完整的url
        String lastUrl = prefixUrl + listId;
        //发起http请求获取歌单信息
        URL url = new URL(lastUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String result = getResponse(conn);
        JSONArray arr = JSON.parseObject(result).getJSONObject("result").getJSONArray("tracks");
        List<Music> list = getAllMusic(arr);
        return list;
    }

    public String getResponse(HttpURLConnection conn) throws IOException {
        //设置属性
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon;)");
        conn.setRequestProperty("cookie", "appver=1.5.0.75771");
        conn.setRequestProperty("referer", "http://music.163.com/");
        //开启连接
        conn.connect();
        //获取响应
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //关闭流
        br.close();
        //关闭连接
        conn.disconnect();
        return sb.toString();
    }

    public List<Music> getAllMusic(JSONArray arr) {
        List<Music> list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Music music = new Music();
            music.setName(obj.getString("name"));
            music.setUrl(playUrl + obj.getString("id") + ".mp3");
            music.setArtist(obj.getJSONArray("artists").getJSONObject(0).getString("name"));
            music.setCover(obj.getJSONObject("album").getString("blurPicUrl"));
            music.setLrc("");
            list.add(music);
        }
        return list;
    }

}