package br.com.bmsti.produto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("titulo","CRUD Produto");
		return "index";
	}
 
    @RequestMapping("/parcial/{pagina}")
    String partialHandler(@PathVariable("pagina") final String page) {
        return page;
    }
	
}
