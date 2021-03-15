package com.wl.blog.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wl.blog.pojo.DLPResource;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ParseDLPHtml implements ParseHtml{

    @Autowired
    RestHighLevelClient restHighLevelClient;
    public static void main(String[] args) throws Exception {
        ParseHtml parseHtml = new ParseDLPHtml();
        JSONObject jsonObject = parseHtml.searchPageAll("filename=switch");
        System.out.println(jsonObject.toString());

    }



    public  static String url = "https://api.dalipan.com/api/v1/pan/search?kw=";
    private static  String keywords = "switch";
    @Override
    public JSONObject findDataByURL(String url , String keywords, int pageNum)throws Exception {
        //获取页面的JSONObject对象

        //获取js异步加载后的页面  暂时未用
            Connection.Response accept = Jsoup.connect(url + keywords + "&page=" + pageNum).header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate")
                    .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36")
                    .header("Host","api.dalipan.com")
                    .header("Origin","https://www.dalipan.com")
                    .header("Sec-Fetch-Dest","empty")
                    .header("Sec-Fetch-Mode","cors")
                    .header("Sec-Fetch-Site","same-site")
                    .header("Referer", "https://www.dalipan.com/")
                    .header("X-Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlvbmlkIjoib2JoZWFzNWpCaE9CZmMtTVMxUlNrc2lpUlVBbyIsImlhdCI6MTYxNTc4NzExNCwiZXhwIjoxNjE1ODczNTE0fQ.CE697PhM85LWoe77h5fANamDoh-E5QjKaRId1v2YMpM")
                    .timeout(10000000).ignoreContentType(true).execute();//.get();;
            String jsonData = accept.body();
            JSONObject jsonObject = JSON.parseObject(jsonData);
            return jsonObject;


        }


    @Override
    public List parseData(JSONObject daLPData) {
        JSONArray resources = daLPData.getJSONArray("resources");
        int total = Integer.parseInt(daLPData.getString("total"));
        int allPageNum = total/resources.size()+1;
        List<DLPResource> list = new ArrayList<>();

        for (int i = 0 ; i < resources.size() ; i++) {
            JSONObject resource = resources.getJSONObject(i);
            JSONObject res = resource.getJSONObject("res");
            String filename = res.getString("filename");
            String utime = res.getString("utime");
            String ctime = res.getString("ctime");
            String id = res.getString("id");
            String ext = res.getString("ext");
            DLPResource dlpResource = new DLPResource();
            dlpResource.setCtime(ctime);
            dlpResource.setExt(ext);
            dlpResource.setId(id);
            dlpResource.setFilename(filename);
            dlpResource.setUtime(utime);
            //执行插入操作
            list.add(dlpResource);

        }
        return list;
    }

    @Override
    public JSONObject addJSONData(List list) {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2s");
        BulkResponse bulkResponse =null;

        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(new IndexRequest("dlp").source(JSON.toJSONString(list.get(i)), XContentType.JSON));
        }
        try {
             bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        boolean dealFlag = !bulkResponse.hasFailures();
        jsonObject.put("flag",dealFlag);
        return jsonObject;
    }

    @Override
    public JSONObject searchPageAll(String keywords) {
        JSONObject jsonObject=null;
        try {
            List<Map<String, Object>> maps = this.searchPage(keywords, 1, 9000);
             jsonObject = LayUITableData.TransDataToLayUITable(maps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    //实现搜索功能
    public List<Map<String,Object>> searchPage (String keywords , int pageNo , int pageSize) throws IOException {

        if(pageNo<=1){
            pageNo =1;
        }

        Map<String, String> valueByName = StringUtil.getValueByName(keywords);
        String filename = valueByName.get("filename");
        String ext = valueByName.get("ext");


        //条件搜索
        SearchRequest searchRequest = new SearchRequest("dlp");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //先构造bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //must链接条件, should可以查询多个值
        BoolQueryBuilder typeQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(typeQueryBuilder.should(QueryBuilders.termQuery("filename",filename)).should(QueryBuilders.termQuery("ext",ext)));
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));


        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("filename");
        highlightBuilder.requireFieldMatch(false);//关闭多个高亮
        highlightBuilder.preTags("<span style='color:blue'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);


        //执行结果返回
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit searchHit: searchResponse.getHits().getHits()
        ) {

            //获取高亮的字段
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            //获取高亮的标题
            HighlightField title = highlightFields.get("filename");
            //原来的结果
            Map<String,Object> stringObjectMap = searchHit.getSourceAsMap();
            //解析高亮的字段
            if(title!=null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for(Text text : fragments){
                    n_title +=text;
                }
                stringObjectMap.put("filename",n_title);
            }
            list.add(stringObjectMap);

        }
        return list;
    }

    //执行所有的操作

    public void delBuss(String keywords) throws Exception {

        int page=1;
        while (true){
            JSONObject dataByURL = this.findDataByURL(url, keywords, page);
            if (dataByURL!=null&&page<=100){
                List list = this.parseData(dataByURL);
                JSONObject jsonObject = this.addJSONData(list);
                if(jsonObject.getBoolean("flag")){
                    page++;
                }
                else break;
            }
            else break;

        }

    }



    public int findAllNum(JSONObject daLPData){
        JSONArray resources = daLPData.getJSONArray("resources");
        int total = Integer.parseInt(daLPData.getString("total"));
        return total;
    }

}
