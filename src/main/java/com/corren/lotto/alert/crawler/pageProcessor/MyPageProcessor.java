package com.corren.lotto.alert.crawler.pageProcessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author ZhangGR
 * @create on 2019-08-25
 * @description
 */
public class MyPageProcessor implements PageProcessor {


    private Site site = Site.me().setDomain("www.nfd.com.tw");

    @Override
    public void process(Page page) {
//        List<String> links = page.getHtml().links().regex("http://www.nfd.com.tw/house/year/(F\\w+).htm").all();
//        page.addTargetRequests(links);
//        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
//        page.putField("content", page.getHtml().$("div.content").toString());
//        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());

        List<String> all = page.getHtml().links().regex("http://www.nfd.com.tw/house/year/(F20\\d+.htm)").all();
        page.addTargetRequests(all);

        page.putField("content", page.getHtml().$("tr").all().get(5));

//        page.putField("content", page.getHtml().$("tr").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyPageProcessor()).addUrl("http://www.nfd.com.tw/house/year/F2002.htm")
                .thread(5)
                .addPipeline(new ConsolePipeline()).run();
    }
}
