package spring.security.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationApi {

private List<Quotation> quotations;


    public QuotationApi() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("Cytat nr jeden", "Andrej Szewczenko"));
        quotations.add(new Quotation("Cytat nr dwa", "Nikita Chruszczow"));
    }

    @GetMapping("/api")
    public List<Quotation> getQuotations (){
    return quotations;
    }

    @PostMapping("/api")
    public boolean addQuotation (@RequestBody Quotation quotation ){
        return quotations.add(quotation);
    }
    @DeleteMapping("/api")
    public void deleteQuoteation(@RequestParam int index){
        quotations.remove(index);

    }


}
