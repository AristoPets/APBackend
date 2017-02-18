package com.AristoPets.web;


import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public class Pagination {


    public static void paginate(Page page, Model model, String requestQuery){
        // страницы считаются с 0, как массив, но для  номера страницы в интерфейсе используется номер+1
        // страница, на которой в данный момент находится юзер
        int nowPage = page.getNumber()+1;
        int totalPages = page.getTotalPages();
        // максимальное кол-во номеров, которые отображаются в центре списка номеров
        final int pagesRange = 5;
        //диапазон страниц показываемых страниц от center1 и до center2. К примеру, если center1 = 5 , то center2 = 10 и страницы в центре будут показаны
        //как  ... 5 6 7 8 9 10 ...  Если center = 13, то center = 18 и диапазон будет ... 13 14 15 16 17 18 ...
        int center1, center2;
        // рассчет диапазона
        if(nowPage >= pagesRange && nowPage <= totalPages - pagesRange){
            center1 = nowPage - (nowPage % pagesRange);
            center2 = center1 + pagesRange;
        }else if(nowPage <= pagesRange){
            center1 = 1;
            center2 = totalPages <= pagesRange ? totalPages : pagesRange;
        }else{
            center1 = totalPages - pagesRange;
            center2 = totalPages;
        }

        if(requestQuery != null) {
            if (requestQuery.contains("page")) {
                String queryWithoutPage = "?" + requestQuery.substring(0, requestQuery.indexOf("page")) + "page=";
                model.addAttribute("query", queryWithoutPage);
            } else {
                model.addAttribute("query", "?" + requestQuery + "&page=");
            }
        }else{
            model.addAttribute("query","?page=");
        }
        model.addAttribute("center1", center1);
        model.addAttribute("center2", center2);
    }
}
