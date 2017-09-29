package com.shixizhi.controller;

import com.shixizhi.entity.Article;
import com.shixizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/article/get/{id}")
//    @ResponseBody
    public String get(Model model, @PathVariable(name = "id") String id){
        Article article = this.articleService.getById(id);
        System.out.println(article.getTitle());
        return "admin/index";
    }

    /*
    TODO：分页
     */
    @GetMapping("/article")
    public String list(Model model){
        List<Article> articles = articleService.list();
        model.addAttribute("articles", articles);

        return "front/index";
    }

    /*
    按类型显示博客
     */
    @GetMapping("/article/column/{displayname}/{category}")
    public String column(@PathVariable("displayname") String dispalyname, @PathVariable("category") String category, Model model){
        model.addAttribute("articles", articleService.getArticleByCategoryName(category));
        model.addAttribute("displayName", dispalyname);

        return "front/columnPage";
    }

    /**
     * 显示详细信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/article/detail/{id}")
    public String detail(@PathVariable("id") String id, Model model){
        System.out.println(id);
        Article article = articleService.getById(id);
        System.out.println(article.getId());
        Markdown markdown = new Markdown();
        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
        }catch (ParseException e){
            e.printStackTrace();
        }
        model.addAttribute("article", article);

        return "front/detail";
    }


}
