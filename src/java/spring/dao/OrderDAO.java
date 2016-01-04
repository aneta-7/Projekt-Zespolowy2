package java.spring.dao;

import java.util.List;
import java.spring.model.*;


public interface OrderDAO {
	
	List<OrderModel> getAllOrders();
	void addOrder(OrderModel order);
	OrderModel findOrderByName(String name);
	void deleteOrder(OrderModel order);
	void updateOrder(OrderModel order);
}