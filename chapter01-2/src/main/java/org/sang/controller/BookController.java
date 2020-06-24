package org.sang.controller;

import org.sang.bean.Author;
import org.sang.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    Book book;

    /**
     * 类型安全配置属性 —— 配置数据注入 Bean 的示例
     * @return
     */
    //@GetMapping("/book")
    public String book(){
        return book.toString();
    }

    /**
     * ModelAndView 返回示例
     * @return
     */
    @GetMapping("/books")
    public ModelAndView books(){
        List<Book> books = new ArrayList<>();

        books.add(new Book("三国演义", "罗贯中", 65.3F));
        books.add(new Book("西游记", "吴承恩", 55.1F));
        books.add(new Book("水浒传", "施耐庵", 36.7F));
        books.add(new Book("红楼梦", "曹雪芹", 52.3F));

        ModelAndView mv = new ModelAndView();
        mv.addObject("books", books);
        mv.setViewName("books");
        return mv;
    }

    /**
     * 请求参数预处理 示例
     * @param book
     * @param author
     * @return
     */
    @GetMapping("/book")
    @ResponseBody
    public String book(@ModelAttribute("b") Book book, @ModelAttribute("a")Author author){
        return book.toString() + " >>>>>>>>>> " + author.toString();
    }
}


