package com.yogo.analytics.analytics.product;

import com.yogo.analytics.domain.OrderItem;
import com.yogo.analytics.entity.OrderTransaction;
import com.yogo.analytics.util.CategoryFinder;
import com.yogo.analytics.util.DoubleStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductAnalytics {
    @Autowired
    private CategoryFinder categoryFinder;
    private List<OrderItem>previousItems=new ArrayList<>();
    private List<OrderItem>resentItems=new ArrayList<>();
    private List<CatergoryValue> topProductCartegories(){

         return getTopProducts().stream()
                 .map(e->new ProductValue(categoryFinder.getCategory(e.getProductId()),e.getValue()))
                 .collect(Collectors.groupingBy(ProductValue::getProductId,Collectors.summarizingDouble(ProductValue::getValue)))
                  .entrySet()
                   .stream()
         .map(entry->new CatergoryValue(entry.getKey(), new DoubleStats(entry.getValue())))
         .collect(Collectors.toList());
    }
   private ProductCounts byCount(){

      List<ProductCount> counts=previousItems.stream()
               .collect(Collectors.groupingBy(OrderItem::getProductId,Collectors.counting()))
               .entrySet()
               .stream()
               .map(entry->new ProductCount(entry.getKey(),entry.getValue()))
               .sorted(Comparator.comparing(ProductCount::getCount))
               .collect(Collectors.toList());
      LongSummaryStatistics statistics=counts.stream()
              .collect(Collectors.summarizingLong(ProductCount::getCount));

      return new ProductCounts(resentItems,statistics);


   }
   private DoubleSummaryStatistics getValueStasts(){


      List<ProductValue> valueList=previousItems.stream()
               .collect(Collectors.groupingBy(OrderItem::getProductId,Collectors.summingDouble(OrderItem::getPrice)))
               .entrySet()
               .stream()
               .map(entry -> new ProductValue(entry.getKey(),entry.getValue()))
                .sorted(Comparator.comparing(ProductValue::getValue))
                .collect(Collectors.toList());
      DoubleSummaryStatistics statistics=valueList.stream()
              .collect(Collectors.summarizingDouble(ProductValue::getValue));

      return  statistics;


   }
   private List<ProductValue>getTopProducts(){
      return previousItems.stream()
               .collect(Collectors.groupingBy(OrderItem::getProductId,Collectors.summingDouble(OrderItem::getPrice)))
               .entrySet()
               .stream()
               .map(entry -> new ProductValue(entry.getKey(),entry.getValue()))
               .sorted(Comparator.comparing(ProductValue::getValue))
               .limit(5)
               .collect(Collectors.toList());


   }

   public ProductStatistics analyse(List<OrderTransaction>transactions){

       resentItems.clear();


       transactions.forEach(t->resentItems.addAll(t.getOrderItems()));

       List<ProductValue>productValues=resentItems.stream()
               .collect(Collectors.groupingBy(OrderItem::getProductId,Collectors.summingDouble(OrderItem::getPrice)))
               .entrySet()
               .stream()
               .map(entry -> new ProductValue(entry.getKey(),entry.getValue()))
               .sorted(Comparator.comparing(ProductValue::getValue))
               .collect(Collectors.toList());

       this.previousItems.addAll(resentItems);

       ProductCounts productCounts=this.byCount();

       DoubleSummaryStatistics statistics=this.getValueStasts();

       return new ProductStatistics(productCounts,new DoubleStats(statistics),productValues,getTopProducts(),topProductCartegories());
   }

}
