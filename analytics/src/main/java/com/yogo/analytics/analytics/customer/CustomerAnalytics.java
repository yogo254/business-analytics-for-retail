package com.yogo.analytics.analytics.customer;

import com.yogo.analytics.domain.OrderItem;
import com.yogo.analytics.entity.OrderTransaction;
import com.yogo.analytics.util.DoubleStats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerAnalytics {
    private final List<CustomerValues> resentCustomers=new ArrayList<>();
    private final List<CustomerValues> previousCustomers=new ArrayList<>();


private List<CustomerValue> getTopCustomers(){
   return previousCustomers.stream()
           .sorted(Comparator.comparing(CustomerValues::getValue))
           .limit(5)
           .collect(Collectors.groupingBy(CustomerValues::getCustomerId,Collectors.summarizingDouble(CustomerValues::getValue)))
           .entrySet()
           .stream()
           .map(e-> new CustomerValue(e.getKey(),new DoubleStats(e.getValue())))
           .collect(Collectors.toList());


}
private DoubleStats getStatistcs(){
    DoubleSummaryStatistics statistics= previousCustomers
            .stream()
            .collect(Collectors.summarizingDouble(CustomerValues::getValue));
    return new DoubleStats(statistics);

}
private CustomerCounts resentCustomers(){
    List<String>resent=resentCustomers.stream()
            .map(CustomerValues::getCustomerId)
            .collect(Collectors.toList());
    DoubleSummaryStatistics statistics=resentCustomers
            .stream()
            .collect(Collectors.summarizingDouble(CustomerValues::getValue));
    return new CustomerCounts(resent,new DoubleStats(statistics));

}

public CustomerStatistics analyse(List<OrderTransaction> transaction){
    List<CustomerValues> resentCustomers=transaction
            .stream()
            .map(e->{
                DoubleSummaryStatistics stats=e.getOrderItems().stream().collect(Collectors.summarizingDouble(OrderItem::getPrice));
                return new CustomerValues(e.getCustomer().getCustomerUniqueId(),new DoubleStats(stats).getSum());
            }).collect(Collectors.toList());

    this.resentCustomers.clear();
    this.resentCustomers.addAll(resentCustomers);

    this.previousCustomers.addAll(resentCustomers);
    return new CustomerStatistics(this.getTopCustomers(),this.getStatistcs(),this.resentCustomers());

}



}
