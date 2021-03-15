package com.wl.blog.service.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.util.HtmlUtil;
import com.wl.blog.util.StringUtil;
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
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TypeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @title: ContentService
 * @projectName study
 * @description: TODO
 * @date 2021/3/820:08
 */
@Service
public class ContentService {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    //1解析数据放入es中
    public Boolean paraContent(String keywords,int pageNo)  {
        HtmlUtil htmlUtil = new HtmlUtil();
        BulkResponse bulkResponse = null;
        List<BlogArticle> contents = null;
        try {
            contents = htmlUtil.parseCSDN(keywords,pageNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BulkRequest bulkRequest = new BulkRequest();
                bulkRequest.timeout("2s");
                for (int i = 0; i< contents.size(); i++) {
                    bulkRequest.add(new IndexRequest("csdn").source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
            }
        try {
            bulkResponse=  restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            System.out.println("已导入完成");
            return true;
        }


        return !bulkResponse.hasFailures();
    }

    //实现多重插入
    public JSONObject addPage(String keywords) throws Exception {
        HtmlUtil htmlUtil = new HtmlUtil();
        int allPageNum = htmlUtil.findAllPageNum(keywords, 1);
        int i = 1;
        while (i < allPageNum) {
            Boolean aBoolean = this.paraContent(keywords, i);
            if (aBoolean == true) i++;
            else break;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag",true);
        return jsonObject;
    }

    //实现搜索功能
    public List<Map<String,Object>> searchPage (String keywords ,int pageNo , int pageSize) throws IOException {

        if(pageNo<=1){
            pageNo =1;
        }

            Map<String, String> valueByName = StringUtil.getValueByName(keywords);
            String articleName = valueByName.get("articleName");
            String articleContent = valueByName.get("articleContent");


        //条件搜索
        SearchRequest searchRequest = new SearchRequest("csdn");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);

        //精确匹配
   /*     TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("articleName",articleName);
        TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("articleContent", articleContent);
        searchSourceBuilder.query(termQueryBuilder1);
        searchSourceBuilder.query(termQueryBuilder2);*/
        //先构造bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //must链接条件, should可以查询多个值
        BoolQueryBuilder typeQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(typeQueryBuilder.should(QueryBuilders.termQuery("articleName",articleName)).should(
                QueryBuilders.termQuery("articleContent",articleContent)
        ));
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));


        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("articleName");
        highlightBuilder.requireFieldMatch(false);//关闭多个高亮
        highlightBuilder.preTags("<span style='color:red'>");
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
            HighlightField title = highlightFields.get("articleName");
            //原来的结果
            Map<String,Object> stringObjectMap = searchHit.getSourceAsMap();
            //解析高亮的字段
            if(title!=null){
                Text[] fragments = title.fragments();
                String n_title = "";
                for(Text text : fragments){
                    n_title +=text;
                }
                stringObjectMap.put("articleName",n_title);
            }
            list.add(stringObjectMap);

        }
        return list;
    }

    //转换为表格数据的接口
    //实现搜索功能
    public List<Map<String,Object>> searchPageAll (String keywords ) throws IOException {
         return   this.searchPage(keywords,1,9000);

    }




}
